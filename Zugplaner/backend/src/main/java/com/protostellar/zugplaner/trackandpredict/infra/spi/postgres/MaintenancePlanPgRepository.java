package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.AssociationError;
import com.protostellar.zugplaner.common.errors.MaintenancePlanRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.infra.spi.dao.MaintenancePlanDAO;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.FrequencyType;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class MaintenancePlanPgRepository implements WriteMaintenancePlan, ReadMaintenancePlan {
  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenancePlanPgRepository.class);

  private final JdbcTemplate template;

  public static final String ID_COLUMN_NAME = "Id";

  private static final String WHERE_ID = "WHERE \"" + ID_COLUMN_NAME + "\" = ?::uuid ";

  public static final String SELECT_FROM_MAINTENANCEPLAN_AND_MAINTENANCETYPE_ON_MAINTENANCETYPEID = "" +
    "SELECT MP.*, MT.\"Type\" FROM track_predict.\"MaintenancePlan\" AS MP " +
    "LEFT JOIN track_predict.\"MaintenanceType\" AS MT " +
    "ON MP.\"MaintenanceTypeId\" = MT.\"Id\" ";

  public static final String CREATE_MAINTENANCE_PLAN = "" +
    "INSERT INTO track_predict.\"MaintenancePlan\" (\"MaintenanceTypeId\", \"AlarmSet\", \"AlarmDue\", \"FrequencyType\", \"FrequencyValue\", \"AssetId\", \"CreatorId\", \"Comment\", \"SysInsertTS\", \"SysUpdateTS\") " +
    "VALUES(?::uuid, ?::boolean, ?::boolean, ?::integer, ?::integer, ?::uuid, ?::uuid, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); ";

  public static final String UPDATE_MAINTENANCE_PLAN = "" +
    "UPDATE track_predict.\"MaintenancePlan\" " +
    "SET \"MaintenanceTypeId\"=?::uuid, " +
    "\"AlarmSet\"=?::boolean, " +
    "\"FrequencyType\"=?::integer, " +
    "\"FrequencyValue\"=?::integer, " +
    "\"AssetId\"=?::uuid, " +
    "\"Comment\"=?, " +
    "\"SysUpdateTS\"=CURRENT_TIMESTAMP " +
    WHERE_ID;


  public static final String FIND_MAINTENANCE_PLAN_BY_ID = SELECT_FROM_MAINTENANCEPLAN_AND_MAINTENANCETYPE_ON_MAINTENANCETYPEID +
    "WHERE MP.\"" + ID_COLUMN_NAME + "\" = ?::uuid " +
    "ORDER BY MP.\"Id\" " +
    "DESC LIMIT 1";

  public static final String GET_ALL_MAINTENANCE_PLANS_FOR_ASSET_ID =
    SELECT_FROM_MAINTENANCEPLAN_AND_MAINTENANCETYPE_ON_MAINTENANCETYPEID +
      "WHERE \"AssetId\" = ?::uuid; ";

  public static final String DELETE_MAINTENANCE_PLAN =
    "DELETE FROM track_predict.\"MaintenancePlan\" " +
      WHERE_ID;

  public static final String FIND_ALL_MAINTENANCE_PLANS_WITH_ALARM_SET =
    "SELECT * FROM track_predict.\"MaintenancePlan\" " +
      "WHERE \"FrequencyType\"=?::integer AND \"AlarmSet\" = ?::boolean; ";

  public static final String UPDATE_MAINTENANCE_PLAN_ALARM_DUE = "" +
    "UPDATE track_predict.\"MaintenancePlan\" " +
    "SET \"AlarmDue\"=?::boolean " +
    WHERE_ID;

  public MaintenancePlanPgRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> save(MaintenancePlan maintenancePlan) {
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      template.update(
        connection -> {
          PreparedStatement ps =
            connection.prepareStatement(CREATE_MAINTENANCE_PLAN, new String[]{"Id"});
          ps.setString(1, maintenancePlan.getMaintenanceType().getId().getRepresentation());
          ps.setBoolean(2, maintenancePlan.getAlarm().getIsSet());
          ps.setBoolean(3, maintenancePlan.getAlarm().getIsDue());
          ps.setInt(4, maintenancePlan.getFrequency().getType().getValue());
          ps.setInt(5, maintenancePlan.getFrequency().getValue());
          ps.setString(6, maintenancePlan.getAssetId().getRepresentation());
          ps.setString(7, maintenancePlan.getCreatorId().getRepresentation());
          ps.setString(8, maintenancePlan.getComment()); // todo check for sql injection ?
          return ps;
        },
        keyHolder
      );
      Map<String, Object> keys = keyHolder.getKeys();
      if (keys == null) {
        return Either.left(new ProtostellarError());
      }
      UUID insertedId = UUID.fromString(keys.get(ID_COLUMN_NAME).toString());
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error(String.format("insert maintenance plan has been completed successfully {%s}", insertedId));
      }
      return findById(Identifier.from(insertedId));
    } catch (DataAccessException e) {
      LOGGER.error(String.format("insert maintenance plan has thrown an exception {%s}", e.getMessage()));
      return Either.left(new ProtostellarError());
    }
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> update(MaintenancePlan updatedMaintenancePlan) {
    try {
      template.update(UPDATE_MAINTENANCE_PLAN,
        updatedMaintenancePlan.getMaintenanceType().getId().getRepresentation(),
        updatedMaintenancePlan.getAlarm().getIsSet(),
        updatedMaintenancePlan.getFrequency().getType().getValue(),
        updatedMaintenancePlan.getFrequency().getValue(),
        updatedMaintenancePlan.getAssetId().getRepresentation(),
        updatedMaintenancePlan.getComment(),
        updatedMaintenancePlan.getId().getRepresentation()
      );
      return findById(updatedMaintenancePlan.getId());
    } catch (DataAccessException e) {
      LOGGER.error(String.format("update maintenance plan has thrown an exception {%s}", e.getMessage()));
      return Either.left(new MaintenancePlanRepositoryError());
    }
  }

  @Override
  public Either<ProtostellarError, List<MaintenancePlan>> getAllFor(Identifier assetId) {
    try {
      return Either.right(
        template.query(
            GET_ALL_MAINTENANCE_PLANS_FOR_ASSET_ID,
            new BeanPropertyRowMapper<>(MaintenancePlanDAO.class),
            assetId.getRepresentation()
          ).stream().map(MaintenancePlanDAO::toMaintenancePlan)
          .collect(Collectors.toList())
      );
    } catch (DataAccessException e) {
      return Either.left(new MaintenancePlanRepositoryError());
    }
  }

  @Override
  public Function<Void, Either<ProtostellarError, Void>> isInScope(Identifier maintenancePlanId, Identifier assetId) {
    return v -> this.getAllFor(assetId)
      .map(m -> m.stream().anyMatch(it -> it.getId().equals(maintenancePlanId)))
      .flatMap(b -> Boolean.TRUE.equals(b) ? Either.right(null) : Either.left(new AssociationError()));
  }

  @Override
  public Either<ProtostellarError, Boolean> delete(Identifier maintenancePlanId) {
    try {
      int deletedCount = template.update(DELETE_MAINTENANCE_PLAN, maintenancePlanId.getRepresentation());
      if (deletedCount >= 1) {
        if (LOGGER.isErrorEnabled()) {
          LOGGER.error(String.format("delete maintenance plan with id {%s} has been completed successfully",
            maintenancePlanId.getRepresentation()));
        }
        return Either.right(true);
      } else {
        if (LOGGER.isErrorEnabled()) {
          LOGGER.error(String.format("delete maintenance plan with id {%s} has been failed",
            maintenancePlanId.getRepresentation()));
        }
        return Either.left(new MaintenancePlanRepositoryError.NotFound());
      }
    } catch (DataAccessException ex) {
      LOGGER.error(String.format("delete maintenance plan with id {%s} thrown an exception {%s}",
        maintenancePlanId.getRepresentation(), ex.getMessage()));
      return Either.left(new MaintenancePlanRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, MaintenancePlan> findById(Identifier id) {
    try {
      MaintenancePlanDAO maintenancePlanDAO = template.queryForObject(
        FIND_MAINTENANCE_PLAN_BY_ID,
        new BeanPropertyRowMapper<>(MaintenancePlanDAO.class),
        id.getRepresentation()
      );
      if (maintenancePlanDAO != null) {
        return Either.right(maintenancePlanDAO.toMaintenancePlan());
      }
      return Either.left(new MaintenancePlanRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new MaintenancePlanRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, List<MaintenancePlan>> findAllByFrequencyTypeAndAlarmIsSet(
    FrequencyType frequencyType, Boolean alarmIsSet) {
    try {
      return Either.right(
        template.query(
            FIND_ALL_MAINTENANCE_PLANS_WITH_ALARM_SET,
            new BeanPropertyRowMapper<>(MaintenancePlanDAO.class), frequencyType.getValue(), alarmIsSet)
          .stream().map(MaintenancePlanDAO::toMaintenancePlan)
          .collect(Collectors.toList())
      );
    } catch (DataAccessException e) {
      return Either.left(new MaintenancePlanRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, Boolean> updateAlarmDue(Identifier maintenancePlanId, Boolean alarmDue) {
    try {
      template.update(UPDATE_MAINTENANCE_PLAN_ALARM_DUE,
        alarmDue,
        maintenancePlanId.getRepresentation()
      );
      return Either.right(Boolean.TRUE);
    } catch (DataAccessException e) {
      LOGGER.error(String.format("update alarm due has thrown an exception {%s}", e.getMessage()));
      return Either.left(new ProtostellarError());
    }
  }
}
