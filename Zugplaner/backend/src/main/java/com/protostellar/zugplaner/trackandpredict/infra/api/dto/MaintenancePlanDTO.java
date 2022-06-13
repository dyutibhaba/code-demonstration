package com.protostellar.zugplaner.trackandpredict.infra.api.dto;

import com.protostellar.zugplaner.common.infra.api.dto.UserDTO;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import lombok.Data;
import org.apache.commons.collections.map.HashedMap;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class MaintenancePlanDTO {
  public static final int CHECKLIST_TYPE = 5;
  UUID id;
  Map<String, Object> body;
  Integer type;
  String maintenanceType;
  Boolean alarmIsSet;
  Boolean alarmIsDue;
  Integer frequencyType;
  Integer frequencyValue;
  UUID assetId;
  UUID creatorId;
  String comment;
  UserDTO creator;
  LocalDateTime creationDate;
  LocalDateTime lastUpdateDateTime;

  public MaintenancePlanDTO() {
  }

  public static MaintenancePlanDTO from(MaintenancePlan maintenancePlan, UserDTO user) {
    return new MaintenancePlanDTO(
      UUID.fromString(maintenancePlan.getId().getRepresentation()),
      createMaintenancePlanBody(maintenancePlan),
      CHECKLIST_TYPE,
      maintenancePlan.getMaintenanceType().getType(),
      maintenancePlan.getAlarm().getIsSet(),
      maintenancePlan.getAlarm().getIsDue(),
      maintenancePlan.getFrequency().getType().getValue(),
      maintenancePlan.getFrequency().getValue(),
      UUID.fromString(maintenancePlan.getAssetId().getRepresentation()),
      UUID.fromString(maintenancePlan.getCreatorId().getRepresentation()),
      maintenancePlan.getComment(),
      user,
      maintenancePlan.getCreationDate(),
      maintenancePlan.getLastUpdateDateTime()
    );
  }

  private static Map<String, Object> createMaintenancePlanBody(MaintenancePlan maintenancePlan) {
    Map<String, Object> body = new HashedMap();
    body.put("maintenanceType", maintenancePlan.getMaintenanceType().getType());
    body.put("frequencyType", maintenancePlan.getFrequency().getType().getValue());
    body.put("frequencyValue", maintenancePlan.getFrequency().getValue());
    body.put("alarmIsSet", maintenancePlan.getAlarm().getIsSet());
    body.put("alarmIsDue", maintenancePlan.getAlarm().getIsDue());
    body.put("comment", maintenancePlan.getComment());
    return body;
  }

  private MaintenancePlanDTO( // NOSONAR: number of parameters is needed for the frontend
                              UUID id,
                              Map<String, Object> body,
                              Integer type,
                              String maintenanceType,
                              Boolean alarmIsSet,
                              Boolean alarmIsDue,
                              Integer frequencyType,
                              Integer frequencyValue,
                              UUID assetId,
                              UUID creatorId,
                              String comment,
                              UserDTO creator,
                              LocalDateTime creationDate,
                              LocalDateTime lastUpdateDateTime
  ) {
    this.id = id;
    this.body = body;
    this.type = type;
    this.maintenanceType = maintenanceType;
    this.alarmIsSet = alarmIsSet;
    this.alarmIsDue = alarmIsDue;
    this.frequencyType = frequencyType;
    this.frequencyValue = frequencyValue;
    this.assetId = assetId;
    this.creatorId = creatorId;
    this.comment = comment;
    this.creator = creator;
    this.creationDate = creationDate;
    this.lastUpdateDateTime = lastUpdateDateTime;
  }
}
