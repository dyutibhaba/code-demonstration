package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Customer;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadCustomer;
import com.protostellar.zugplaner.marketplace.infra.spi.dao.CustomerDAO;
import io.vavr.control.Either;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerPgRepository implements ReadCustomer {

  private final JdbcTemplate template;

  public static final String ID_COLUMN_NAME = "ID";

  private static final String WHERE_ID = "WHERE \"" + ID_COLUMN_NAME + "\" = ?::uuid ";

  private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM protostellar.\"Customer\" " +
    WHERE_ID +
    "ORDER BY \"ID\" " +
    "DESC LIMIT 1";

  public CustomerPgRepository(JdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Either<ProtostellarError, Customer> findById(Identifier id) {
    try {
      CustomerDAO customerDAO = template.queryForObject(
        FIND_CUSTOMER_BY_ID,
        new BeanPropertyRowMapper<>(CustomerDAO.class), id.getRepresentation());
      if (customerDAO != null) {
        return Either.right(customerDAO.toCustomer());
      }
      return Either.left(new OrderRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new OrderRepositoryError.NotFound());
    }
  }
}
