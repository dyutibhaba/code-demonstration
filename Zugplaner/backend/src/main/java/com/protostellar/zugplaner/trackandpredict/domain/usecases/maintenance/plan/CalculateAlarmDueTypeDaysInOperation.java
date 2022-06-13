package com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan;

import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadChecklist;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.checklist.AbstractChecklist;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CalculateAlarmDueTypeDaysInOperation {

  private final ReadChecklist readChecklist;
  private final WriteMaintenancePlan maintenancePlanWriter;
  private final ReadMaintenancePlan maintenancePlanReader;

  public CalculateAlarmDueTypeDaysInOperation(ReadChecklist readChecklist,
                                              ReadMaintenancePlan maintenancePlanReader,
                                              WriteMaintenancePlan maintenancePlanWriter) {
    this.readChecklist = readChecklist;
    this.maintenancePlanWriter = maintenancePlanWriter;
    this.maintenancePlanReader = maintenancePlanReader;
  }

  public static CalculateAlarmDueTypeDaysInOperation with(ReadChecklist readChecklist,
                                                          ReadMaintenancePlan maintenancePlanReader,
                                                          WriteMaintenancePlan maintenancePlanWriter) {
    return new CalculateAlarmDueTypeDaysInOperation(
      readChecklist, maintenancePlanReader, maintenancePlanWriter);
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
        Integer maintenancePlanDaysInOperation = maintenancePlan.getFrequency().getValue();
        LocalDate lastMaintenanceDate = CalculateAlarmDueTypeCalenderDays
          .with(readChecklist, maintenancePlanReader, maintenancePlanWriter)
          .evaluateLastMaintenanceDate(maintenancePlan);
        Integer totalDaysInOperation = evaluateDaysInOperation(maintenancePlan, lastMaintenanceDate);
        if (totalDaysInOperationIsEqualOrMore(totalDaysInOperation, maintenancePlanDaysInOperation)) {
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

  private boolean totalDaysInOperationIsEqualOrMore(Integer totalDaysInOperation, Integer maintenancePlanDaysInOperation) {
    return (totalDaysInOperation >= maintenancePlanDaysInOperation);
  }

  private Integer evaluateDaysInOperation(MaintenancePlan maintenancePlan, LocalDate lastMaintenanceDate) {
    return readChecklist.getAllFor(maintenancePlan.getAssetId()).map(
      checklists -> getTotalDistinctOperatingDays(lastMaintenanceDate, checklists)
    ).getOrElse(0);
  }

  private Integer getTotalDistinctOperatingDays(LocalDate lastMaintenanceDate, List<AbstractChecklist> checklists) {
    return (int) checklists
      .stream()
      .filter(c -> Objects.nonNull(c.getBody().getOperatingDay()))
      .map(e -> LocalDate.parse(e.getBody().getOperatingDay().getContent()))
      .collect(Collectors.toList())
      .stream()
      .filter(operatingDay -> (operatingDay.isEqual(lastMaintenanceDate) || operatingDay.isAfter(lastMaintenanceDate)))
      .distinct().count();
  }

}
