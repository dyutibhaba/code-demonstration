package com.protostellar.zugplaner.trackandpredict.infra.api.rest;

import com.protostellar.zugplaner.common.infra.authorization.UserPrincipal;
import com.protostellar.zugplaner.common.domain.usecases.user.services.AuthorizationHandler;
import com.protostellar.zugplaner.common.domain.usecases.user.services.PayingHandler;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.infra.api.dto.UserDTO;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadAssetIdentity;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadUser;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.CreateMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.DeleteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.GetAllMaintenancePlans;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.GetMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.plan.UpdateMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.usecases.maintenance.type.FindOrCreateMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.infra.api.dto.MaintenancePlanDTO;
import com.protostellar.zugplaner.trackandpredict.infra.api.dto.PatchMaintenancePlanDTO;
import com.protostellar.zugplaner.trackandpredict.infra.api.rest.util.UserUtility;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Alarm;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.Frequency;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/maintenance-plan")
public class MaintenancePlanController {
  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenancePlanController.class);

  private final WriteMaintenancePlan writeMaintenancePlan;
  private final ReadMaintenancePlan readMaintenancePlan;
  private final ReadAssetIdentity assetIdentityReader;
  private final PayingHandler payingHandler;
  private final AuthorizationHandler authorizationHandler;
  private final ReadUser userReader;
  private final WriteMaintenanceType writeMaintenanceType;
  private final ReadMaintenanceType readMaintenanceType;

  public MaintenancePlanController(
    WriteMaintenancePlan writeMaintenancePlan,
    ReadAssetIdentity assetIdentityReader,
    PayingHandler payingHandler,
    ReadMaintenancePlan readMaintenancePlan,
    AuthorizationHandler authorizationHandler,
    ReadUser userReader,
    WriteMaintenanceType writeMaintenanceType,
    ReadMaintenanceType readMaintenanceType) {
    this.writeMaintenancePlan = writeMaintenancePlan;
    this.assetIdentityReader = assetIdentityReader;
    this.payingHandler = payingHandler;
    this.readMaintenancePlan = readMaintenancePlan;
    this.authorizationHandler = authorizationHandler;
    this.userReader = userReader;
    this.writeMaintenanceType = writeMaintenanceType;
    this.readMaintenanceType = readMaintenanceType;
  }

  @GetMapping
  public ResponseEntity<List<MaintenancePlanDTO>> getMaintenancePlanFor(
    @AuthenticationPrincipal Jwt jwt,
    @RequestParam String assetId
  ) throws ProtostellarError {
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    return ResponseEntity.ok(getMaintenancePlans(Identifier.from(assetId), UserUtility.getIdentifier(userPrincipal)));
  }

  private List<MaintenancePlanDTO> getMaintenancePlans(
    Identifier assetId,
    Identifier userId
  ) throws ProtostellarError {
    return getAllMaintenancePlans(assetId, userId)
      .map(maintenancePlans -> maintenancePlans.stream()
        .map(maintenancePlan -> MaintenancePlanDTO.from(maintenancePlan,
          UserDTO.from(userReader.findById(maintenancePlan.getCreatorId()))))
        .collect(Collectors.toList()))
      .getOrElseThrow(Function.identity());
  }

  private Either<ProtostellarError, List<MaintenancePlan>> getAllMaintenancePlans(Identifier assetId,
                                                                                  Identifier userId) {
    GetAllMaintenancePlans getAllMaintenancePlans = new GetAllMaintenancePlans(readMaintenancePlan,
      assetIdentityReader,
      payingHandler);
    return getAllMaintenancePlans.perform(assetId, userId);
  }

  @GetMapping("/{maintenancePlanId}")
  public ResponseEntity<MaintenancePlanDTO> getMaintenancePlan(
    @AuthenticationPrincipal Jwt jwt,
    @PathVariable String maintenancePlanId,
    @RequestParam String assetId
  ) throws ProtostellarError {
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    GetMaintenancePlan getMaintenancePlan = new GetMaintenancePlan(readMaintenancePlan,
      assetIdentityReader,
      payingHandler);
    Identifier userId = UserUtility.getIdentifier(userPrincipal);
    return getMaintenancePlan.perform(userId, Identifier.from(assetId), Identifier.from(maintenancePlanId))
      .map(maintenancePlan -> ResponseEntity.ok(MaintenancePlanDTO.from(maintenancePlan,
        UserDTO.from(userReader.findById(maintenancePlan.getCreatorId())))))
      .getOrElseThrow(error -> error);
  }

  @PostMapping
  public ResponseEntity<MaintenancePlanDTO> createMaintenancePlan(
    @AuthenticationPrincipal Jwt jwt,
    @RequestParam String assetId,
    @RequestParam String maintenanceType,
    @RequestParam Boolean alarmSet,
    @RequestParam Integer frequencyType,
    @RequestParam Integer frequencyValue,
    @RequestParam String comment
  ) throws ProtostellarError {
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    FindOrCreateMaintenanceType findOrCreateMaintenanceType = new FindOrCreateMaintenanceType(writeMaintenanceType, readMaintenanceType, payingHandler);
    CreateMaintenancePlan createMaintenancePlan = new CreateMaintenancePlan(writeMaintenancePlan, assetIdentityReader, payingHandler, findOrCreateMaintenanceType);
    Identifier userId = UserUtility.getIdentifier(userPrincipal);
    return createMaintenancePlan.perform(
      userId,
      Identifier.from(assetId),
      maintenanceType,
      Frequency.from(frequencyValue, FrequencyType.from(frequencyType)),
      Alarm.from(alarmSet, Boolean.FALSE),
      comment
    )
      .map(maintenancePlan -> ResponseEntity.ok(MaintenancePlanDTO.from(maintenancePlan,
        UserDTO.from(userReader.findById(maintenancePlan.getCreatorId())))))
      .getOrElseThrow(error -> error);
  }

  @DeleteMapping("/{maintenancePlanId}")
  public ResponseEntity<Object> deleteMaintenancePlan( // TODO update for type3
                                                       @AuthenticationPrincipal Jwt jwt,
                                                       @PathVariable String maintenancePlanId
  ) throws ProtostellarError {
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    DeleteMaintenancePlan deleteMaintenancePlan = new DeleteMaintenancePlan(authorizationHandler,
      payingHandler,
      writeMaintenancePlan,
      readMaintenancePlan,
      assetIdentityReader);

    return deleteMaintenancePlan.perform(
      UserUtility.getIdentifier(userPrincipal),
      Identifier.from(maintenancePlanId))
      .map(v -> ResponseEntity.noContent().build())
      .getOrElseThrow(error -> {
        LOGGER.error("delete maintenance plan failed with error %s", error);
        return error;
      });
  }

  @PatchMapping("/{maintenancePlanId}")
  public ResponseEntity<MaintenancePlanDTO> updateMaintenancePlan(
    @AuthenticationPrincipal Jwt jwt,
    @PathVariable String maintenancePlanId,
    @RequestBody PatchMaintenancePlanDTO body
  ) throws ProtostellarError {
    UserPrincipal userPrincipal = UserPrincipal.from(jwt);
    FindOrCreateMaintenanceType findOrCreateMaintenanceType = new FindOrCreateMaintenanceType(writeMaintenanceType,
      readMaintenanceType, payingHandler);
    UpdateMaintenancePlan updateMaintenancePlan = new UpdateMaintenancePlan(
      readMaintenancePlan, writeMaintenancePlan, assetIdentityReader, payingHandler, findOrCreateMaintenanceType);
    return updateMaintenancePlan.perform(
      UserUtility.getIdentifier(userPrincipal),
      Identifier.from(maintenancePlanId),
      body.toMaintenancePlanChangeRequest()
    ).map(maintenancePlan -> ResponseEntity.ok(MaintenancePlanDTO.from(maintenancePlan,
      UserDTO.from(userReader.findById(maintenancePlan.getCreatorId())))))
      .getOrElseThrow(error -> error);
  }
}
