package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Order;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadOrder;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrder;
import com.protostellar.zugplaner.marketplace.infra.spi.dao.CustomerDAO;
import com.protostellar.zugplaner.marketplace.infra.spi.dao.OrderDAO;
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
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderPgRepository implements WriteOrder, ReadOrder {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderPgRepository.class);

  private final JdbcTemplate template;

  public static final String ID_COLUMN_NAME = "ID";

  private static final String WHERE_ID = "WHERE \"" + ID_COLUMN_NAME + "\" = ?::uuid ";

  private static final String CREATE_ORDER = "" +
    "INSERT INTO marketplace.\"Order\" (\"OrderNumber\", \"CustomerId\", \"TotalPrice\", \"CreatorId\", \"SysInsertTS\", \"SysUpdateTS\") " +
    "VALUES(?, ?::uuid, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); ";

  private static final String FIND_ORDER_BY_ID = "SELECT * FROM marketplace.\"Order\" " +
    WHERE_ID +
    "ORDER BY \"ID\" " +
    "DESC LIMIT 1";

  private static final String FIND_CUSTOMER_BY_USER_ID = "SELECT DISTINCT ug.\"CustomerID\" as \"Id\" FROM protostellar.\"UserToUserGroup\" utug \n" +
    "INNER JOIN protostellar.\"UserGroup\" ug on utug.\"UserGroupId\" = ug.\"ID\" WHERE utug.\"UserId\" = ?::uuid  \n" +
    "and ug.\"CustomerID\" is NOT NULL";

  public OrderPgRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Either<ProtostellarError, Order> save(Order orderContent) {

    {
      try {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
          connection -> {
            PreparedStatement ps =
              connection.prepareStatement(CREATE_ORDER, new String[]{"ID"});
            ps.setString(1, orderContent.getOrderNumber());
            ps.setString(2, orderContent.getCustomer().getId().getRepresentation());
            ps.setBigDecimal(3, orderContent.getTotalPrice());
            ps.setString(4, orderContent.getCreatorId().getRepresentation());
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
          LOGGER.error(String.format("insert order has been completed successfully {%s}", insertedId));
        }
        return findById(Identifier.from(insertedId));
      } catch (DataAccessException e) {
        LOGGER.error(String.format("insert order has thrown an exception {%s}", e.getMessage()));
        return Either.left(new OrderRepositoryError());
      }
    }
  }


  @Override
  public Either<ProtostellarError, Order> findById(Identifier id) {
    try {
      OrderDAO orderDAO = template.queryForObject(
        FIND_ORDER_BY_ID,
        new BeanPropertyRowMapper<>(OrderDAO.class), id.getRepresentation());
      if (orderDAO != null) {
        return Either.right(orderDAO.toOrderContent());
      }
      return Either.left(new OrderRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new OrderRepositoryError.NotFound());
    }
  }

  @Override
  public Either<ProtostellarError, Identifier> findCustomerByUserId(Identifier id) {
    try {
      CustomerDAO customerDAO = template.queryForObject(FIND_CUSTOMER_BY_USER_ID,
        new BeanPropertyRowMapper<>(CustomerDAO.class), id.getRepresentation());
      if (customerDAO == null) {
        return Either.left(new OrderRepositoryError.NotFound());
      }
      return Either.right(Identifier.from(customerDAO.getId()));
    } catch (DataAccessException e) {
      return Either.left(new OrderRepositoryError.NotFound());
    }
  }

}
