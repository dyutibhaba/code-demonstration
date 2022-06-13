package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.OrderRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.OrderLine;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadOrderLine;
import com.protostellar.zugplaner.marketplace.domain.repositories.WriteOrderLine;
import com.protostellar.zugplaner.marketplace.infra.spi.dao.OrderLineDAO;
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
public class OrderLinePgRepository implements WriteOrderLine, ReadOrderLine {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderLinePgRepository.class);

  private final JdbcTemplate template;

  public static final String ID_COLUMN_NAME = "ID";

  private static final String WHERE_ID = "WHERE \"" + ID_COLUMN_NAME + "\" = ?::uuid ";

  public OrderLinePgRepository(JdbcTemplate template) {
    this.template = template;
  }
  public static final String CREATE_ORDER_LINE = "" +
    "INSERT INTO marketplace.\"OrderLine\" (\"OrderId\", \"ProductId\", \"ArticleId\", \"PricePerArticle\", \"Quantity\", \"TotalPrice\", \"SysInsertTS\",\"SysUpdateTS\") " +
    "VALUES(?::uuid, ?::uuid, ?::uuid, ?, ?::integer, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); ";

  public static final String FIND_ORDER_LINE_BY_ID = "SELECT * FROM marketplace.\"OrderLine\" " +
    WHERE_ID +
    "ORDER BY \"ID\" " +
    "DESC LIMIT 1";

  @Override
  public Either<ProtostellarError, OrderLine> save(OrderLine orderLine) {
    {
      try {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
          connection -> {
            PreparedStatement ps =
              connection.prepareStatement(CREATE_ORDER_LINE, new String[]{"ID"});
            ps.setString(1, orderLine.getOrderId().getRepresentation());
            ps.setString(2, orderLine.getProductArticleWithPrice().getProductId().getRepresentation());
            ps.setString(3, orderLine.getProductArticleWithPrice().getArticleId().getRepresentation());
            ps.setBigDecimal(4, orderLine.getProductArticleWithPrice().getPricePerArticle());
            ps.setInt(5, orderLine.getArticleQuantity());
            ps.setBigDecimal(6, orderLine.getArticleTotalPrice());
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
          LOGGER.error(String.format("insert orderLine has been completed successfully {%s}", insertedId));
        }
        return findById(Identifier.from(insertedId));
      } catch (DataAccessException e) {
        LOGGER.error(String.format("insert order line has thrown an exception {%s}", e.getMessage()));
        return Either.left(new ProtostellarError());
      }
    }
  }

  @Override
  public Either<ProtostellarError, OrderLine> findById(Identifier id) {
    try {
      OrderLineDAO orderLineDAO = template.queryForObject(
        FIND_ORDER_LINE_BY_ID,
        new BeanPropertyRowMapper<>(OrderLineDAO.class),
        id.getRepresentation()
      );
      if (orderLineDAO != null) {
        return Either.right(orderLineDAO.toOrderLine());
      }
      return Either.left(new OrderRepositoryError.NotFound());
    } catch (DataAccessException e) {
      return Either.left(new OrderRepositoryError.NotFound());
    }
  }
}
