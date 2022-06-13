package com.protostellar.zugplaner.trackandpredict.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.AssociationError;
import com.protostellar.zugplaner.common.errors.MaintenanceTypeRepositoryError;
import com.protostellar.zugplaner.common.errors.MaintenanceTypeRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.WriteMaintenanceType;
import com.protostellar.zugplaner.trackandpredict.infra.spi.dao.MaintenancePlanDAO;
import com.protostellar.zugplaner.trackandpredict.infra.spi.dao.MaintenanceTypeDAO;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenancePlan;
import com.protostellar.zugplaner.trackandpredict.model.maintenance.MaintenanceType;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class MaintenanceTypePgRepository implements WriteMaintenanceType, ReadMaintenanceType {
  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceTypePgRepository.class);

  private final JdbcTemplate template;

  public static final String ID_COLUMN_NAME = "Id";

  private static final String WHERE_ID = "WHERE \"" + ID_COLUMN_NAME + "\" = ?::uuid ";


  public static final String CREATE_MAINTENANCE_TYPE = "" +
    "INSERT INTO track_predict.\"MaintenanceType\" (\"Type\") " +
    "VALUES(?); ";

  public static final String FIND_MAINTENANCE_TYPE_BY_ID = "SELECT * FROM track_predict.\"MaintenanceType\" " +
    WHERE_ID +
    "ORDER BY \"Id\" " +
    "DESC LIMIT 1";

  public static final String FIND_MAINTENANCE_TYPE_BY_TYPE = "SELECT * FROM track_predict.\"MaintenanceType\" " +
    "WHERE \"Type\" = ? " +
    "ORDER BY \"Id\" " +
    "DESC LIMIT 1";

  public static final String DELETE_MAINTENANCE_TYPE =
    "DELETE FROM track_predict.\"MaintenanceType\" " +
      WHERE_ID;

  public static final String UPDATE_MAINTENANCE_TYPE = "" +
    "UPDATE track_predict.\"MaintenanceType\" " +
    "SET \"Type\" = ? " +
    WHERE_ID;

  public MaintenanceTypePgRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> save(MaintenanceType maintenanceType) {
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      template.update(
        connection -> {
          PreparedStatement ps =
            connection.prepareStatement(CREATE_MAINTENANCE_TYPE, new String[] {"Id"});
          ps.setString(1, maintenanceType.getType()); // todo check for sql injection ?
          return ps;
        },
        keyHolder
      );
      UUID insertedId = UUID.fromString(keyHolder.getKeys().get(ID_COLUMN_NAME).toString());
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info(String.format("insert maintenance type has been completed successfully {%s}", insertedId));
      }
      return findById(Identifier.from(insertedId));
    } catch (DataAccessException e) {
      LOGGER.error(String.format("insert maintenance type has thrown an exception {%s}", e.getMessage()));
      return Either.left(new ProtostellarError());
    }
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> update(MaintenanceType updatedMaintenanceType) {
    try {
      template.update(UPDATE_MAINTENANCE_TYPE,
        updatedMaintenanceType.getType(),
        updatedMaintenanceType.getId().getRepresentation()
      );
      return findById(updatedMaintenanceType.getId());
    } catch (DataAccessException e) {
      LOGGER.error(String.format("update maintenance type has thrown an exception {%s}", e.getMessage()));
      return Either.left(new MaintenanceTypeRepositoryError());
    }
  }

  @Override
  public Either<ProtostellarError, Boolean> delete(Identifier maintenanceTypeId) {
    try {
      int deletedCount = template.update(DELETE_MAINTENANCE_TYPE, maintenanceTypeId.getRepresentation());
      if (deletedCount >= 1) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info(String.format("delete maintenance type with id {%s} has been completed successfully", maintenanceTypeId.getRepresentation()));
        }
        return Either.right(true);
      } else {
        if (LOGGER.isErrorEnabled()) {
          LOGGER.error(String.format("delete maintenance type with id {%s} has been failed", maintenanceTypeId.getRepresentation()));
        }
        return Either.left(new MaintenanceTypeRepositoryError.NotFound());
      }
    } catch (DataAccessException ex) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error(String.format("delete maintenance type with id {%s} thrown an exception {%s}", maintenanceTypeId.getRepresentation(), ex.getMessage()));
      }
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> findById(Identifier maintenanceTypeId) {
    try {
      MaintenanceTypeDAO maintenanceTypeDAO = template.queryForObject(
        FIND_MAINTENANCE_TYPE_BY_ID,
        new BeanPropertyRowMapper<>(MaintenanceTypeDAO.class),
        maintenanceTypeId.getRepresentation()
      );
      if (maintenanceTypeDAO != null) {
        return Either.right(maintenanceTypeDAO.toMaintenanceType());
      }
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, MaintenanceType> findByType(String maintenanceType) {
    try {
      MaintenanceTypeDAO maintenanceTypeDAO = template.queryForObject(
        FIND_MAINTENANCE_TYPE_BY_TYPE,
        new BeanPropertyRowMapper<>(MaintenanceTypeDAO.class),
        maintenanceType  // todo check for sql injection - in controller sanitize + here ?
      );
      if (maintenanceTypeDAO != null) {
        return Either.right(maintenanceTypeDAO.toMaintenanceType());
      }
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new MaintenanceTypeRepositoryError.NotFound());
    }
  }

}
