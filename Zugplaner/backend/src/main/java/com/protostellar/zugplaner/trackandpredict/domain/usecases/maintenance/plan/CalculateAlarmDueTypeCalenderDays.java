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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class CalculateAlarmDueTypeCalenderDays {

  public static final String REGULAR_MAINTENANCE_DATE_FIELD = "regularMaintenanceDate";
  private final ReadChecklist readChecklist;
  private final ReadMaintenancePlan maintenancePlanReader;
  private final WriteMaintenancePlan maintenancePlanWriter;

  public CalculateAlarmDueTypeCalenderDays(ReadChecklist readChecklist,
                                           ReadMaintenancePlan maintenancePlanReader,
                                           WriteMaintenancePlan maintenancePlanWriter) {
    this.readChecklist = readChecklist;
    this.maintenancePlanReader = maintenancePlanReader;
    this.maintenancePlanWriter = maintenancePlanWriter;
  }

  public static CalculateAlarmDueTypeCalenderDays with(ReadChecklist readChecklist,
                                                       ReadMaintenancePlan maintenancePlanReader,
                                                       WriteMaintenancePlan maintenancePlanWriter) {
    return new CalculateAlarmDueTypeCalenderDays(readChecklist, maintenancePlanReader, maintenancePlanWriter);
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
        Integer noOfDays = maintenancePlan.getFrequency().getValue();
        LocalDate lastMaintenanceDate = evaluateLastMaintenanceDate(maintenancePlan);
        LocalDate alarmDueDate = lastMaintenanceDate.plusDays(noOfDays);
        if (currentDateIsEqualOrAfter(alarmDueDate)) {
          maintenancePlanWriter.updateAlarmDue(maintenancePlan.getId(), Boolean.TRUE);
        } else {
          maintenancePlanWriter.updateAlarmDue(maintenancePlan.getId(), Boolean.FALSE);
        }
      } catch (RuntimeException ex) {
        log.error(
          String.format("Error while processing maintenance plan %s of type CalenderDays, with exception %s",
            maintenancePlan.getMaintenanceType(), ex.getMessage()));
      }
    });
  }

  public LocalDate evaluateLastMaintenanceDate(MaintenancePlan maintenancePlan) {
    Optional<LocalDate> maintenanceDate = readChecklist
      .findByMaintenancePlanId(maintenancePlan.getId())
      .map(checklists -> checklists.stream().map(this::getRegularMaintenanceDate))
      .get()
      .max(LocalDate::compareTo);
    if (maintenanceDate.isPresent() && checkIfMaintenancePerformedAfter(maintenancePlan, maintenanceDate.get())) {
      return maintenanceDate.get();
    }
    return maintenancePlan.getCreationDate().toLocalDate();
  }

  private LocalDate getRegularMaintenanceDate(AbstractChecklist checklist) {
    Map<String, Object> content = checklist.getBody().getContent();
    return LocalDate.parse(content.get(REGULAR_MAINTENANCE_DATE_FIELD).toString());
  }


  private boolean checkIfMaintenancePerformedAfter(MaintenancePlan maintenancePlan, LocalDate regularMaintenanceDate) {
    return regularMaintenanceDate.isAfter(maintenancePlan.getCreationDate().toLocalDate());
  }

  private boolean currentDateIsEqualOrAfter(LocalDate alarmDueDate) {
    return (LocalDate.now()).isEqual(alarmDueDate) || (LocalDate.now()).isAfter(alarmDueDate);
  }
}
