package com.protostellar.zugplaner.trackandpredict.infra.spi.schedulers;

import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadChecklist;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.CalculateAlarmDueTypeCalenderDays;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.CalculateAlarmDueTypeDaysInOperation;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.CalculateAlarmDueTypeKilometer;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculateMaintenancePlanAlarmDue {

  private final ReadChecklist readChecklist;
  private final ReadMaintenancePlan readMaintenancePlan;
  private final WriteMaintenancePlan maintenancePlanWriter;

  public CalculateMaintenancePlanAlarmDue(ReadChecklist readChecklist,
                                          ReadMaintenancePlan readMaintenancePlan,
                                          WriteMaintenancePlan maintenancePlanWriter) {
    this.readChecklist = readChecklist;
    this.readMaintenancePlan = readMaintenancePlan;
    this.maintenancePlanWriter = maintenancePlanWriter;
  }

  @Scheduled(cron = "${jobs.alarmCronSchedule:-}")
  public void calculate() {
    log.info("Alarm calculation scheduler >>>>>");
    CalculateAlarmDueTypeCalenderDays
      .with(readChecklist, readMaintenancePlan, maintenancePlanWriter)
      .perform(FrequencyType.CALENDAR_DAYS);
    CalculateAlarmDueTypeDaysInOperation
      .with(readChecklist, readMaintenancePlan, maintenancePlanWriter)
      .perform(FrequencyType.DAYS_IN_OPERATION);
    CalculateAlarmDueTypeKilometer
      .with(readChecklist, readMaintenancePlan, maintenancePlanWriter)
      .perform(FrequencyType.KILOMETERS);
    log.info("<<<<<< Alarm calculation scheduler");
  }
}
