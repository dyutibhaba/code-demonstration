package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadChecklist;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.checklist.AbstractChecklist;
import com.protostellar.zugplaner.trackandpredict.model.checklist.ChecklistType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
public class CalculateAlarmDueTypeKilometer {

  private static final String NUMBER_OF_KMS = "numberOfKilometers";
  private final ReadChecklist readChecklist;
  private final WriteMaintenancePlan maintenancePlanWriter;
  private final ReadMaintenancePlan maintenancePlanReader;

  public CalculateAlarmDueTypeKilometer(ReadChecklist readChecklist,
                                        ReadMaintenancePlan maintenancePlanReader,
                                        WriteMaintenancePlan maintenancePlanWriter) {
    this.readChecklist = readChecklist;
    this.maintenancePlanWriter = maintenancePlanWriter;
    this.maintenancePlanReader = maintenancePlanReader;
  }

  public static CalculateAlarmDueTypeKilometer with(ReadChecklist readChecklist,
                                                    ReadMaintenancePlan maintenancePlanReader,
                                                    WriteMaintenancePlan maintenancePlanWriter) {
    return new CalculateAlarmDueTypeKilometer(readChecklist, maintenancePlanReader, maintenancePlanWriter);
  }

  public Either<ProtostellarError, Void> perform(FrequencyType frequencyType) {
    try {
      List<MaintenancePlan> maintenancePlans = maintenancePlanReader
        .findAllByFrequencyTypeAndAlarmIsSet(frequencyType, Boolean.TRUE).get();
      calculate(maintenancePlans);
    } catch (NoSuchElementException ex) {
      return Either.left(new ProtostellarError());
    }
    return Either.right(null);
  }

  private void calculate(List<MaintenancePlan> maintenancePlans) {
    maintenancePlans.forEach(maintenancePlan -> {
      try {
        Integer maintenancePlanKilometers = maintenancePlan.getFrequency().getValue();
        LocalDate lastMaintenanceDate = CalculateAlarmDueTypeCalenderDays
          .with(readChecklist, maintenancePlanReader, maintenancePlanWriter)
          .evaluateLastMaintenanceDate(maintenancePlan);
        Integer totalKilometersDriven = evaluateTotalKilometersDriven(maintenancePlan, lastMaintenanceDate);
        if (totalKilometersDrivenIsEqualOrMore(totalKilometersDriven, maintenancePlanKilometers)) {
          maintenancePlanWriter.updateAlarmDue(maintenancePlan.getId(), Boolean.TRUE);
        } else {
          maintenancePlanWriter.updateAlarmDue(maintenancePlan.getId(), Boolean.FALSE);
        }
      } catch (Exception ex) {
        log.error(
          String.format("Error while processing maintenance plan %s of type daysInOperation, with exception %s",
            maintenancePlan.getMaintenanceType(), ex.getMessage()));
      }
    });
  }

  private boolean totalKilometersDrivenIsEqualOrMore(Integer totalKilometersDriven,
                                                     Integer maintenancePlanKilometers) {
    return (totalKilometersDriven >= maintenancePlanKilometers);
  }

  private Integer evaluateTotalKilometersDriven(MaintenancePlan maintenancePlan, LocalDate lastMaintenanceDate) {
    return readChecklist.getAllFor(maintenancePlan.getAssetId()).map(
      checklists -> getTotalKilometers(lastMaintenanceDate, checklists)
    ).getOrElse(0);
  }

  private Integer getTotalKilometers(LocalDate lastMaintenanceDate, List<AbstractChecklist> checklists) {
    return checklists
      .stream()
      .filter(c -> c.getType().equals(ChecklistType.END_OF_MISSION))
      .filter(c -> checkIfOperatingDayIsAfter(lastMaintenanceDate, c))
      .map(this::mapToKilometerValue)
      .collect(Collectors.summingInt(Integer::intValue));
  }

  private Integer mapToKilometerValue(AbstractChecklist checklist) {
    String kilometers = String.valueOf(checklist.getBody().getContent().get(NUMBER_OF_KMS));
    return Integer.valueOf(kilometers);
  }

  private boolean checkIfOperatingDayIsAfter(LocalDate lastMaintenanceDate, AbstractChecklist checklist) {
    LocalDate operatingDay = LocalDate.parse(checklist.getBody().getOperatingDay().getContent());
    return operatingDay.isEqual(lastMaintenanceDate) || operatingDay.isAfter(lastMaintenanceDate);
  }

}
