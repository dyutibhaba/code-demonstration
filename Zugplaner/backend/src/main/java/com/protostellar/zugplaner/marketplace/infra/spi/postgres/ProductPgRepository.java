package com.protostellar.zugplaner.marketplace.infra.spi.postgres;

import com.protostellar.zugplaner.common.errors.ProductRepositoryError;
import com.protostellar.zugplaner.common.errors.ProtostellarError;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import com.protostellar.zugplaner.marketplace.domain.repositories.ReadProduct;
import com.protostellar.zugplaner.marketplace.infra.spi.postgres.dao.ProductDAO;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ProductPgRepository implements ReadProduct {
  private final JdbcTemplate template;

  public ProductPgRepository(JdbcTemplate template) {
    this.template = template;
  }

  private static final String SELECT_PRODUCT_BY_NAME_AND_MODEL = "SELECT \"ID\", \"Name\", \"Model\" " +
    "FROM protostellar.\"Product\" p WHERE LOWER(p.\"Name\") = ? AND LOWER(p.\"Model\") = ?";


  @Override
  public Either<ProtostellarError, Product> findByNameAndModel(String name, String model) {
    try{
      ProductDAO productDAO = template.queryForObject(
          SELECT_PRODUCT_BY_NAME_AND_MODEL,
          new BeanPropertyRowMapper<>(ProductDAO.class), name, model);
      if(productDAO != null) {
        return Either.right(productDAO.toProduct());
      }
      return Either.left(new ProductRepositoryError());
    } catch (DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error("getting product thrown an exception");
      }
      return Either.left((new ProductRepositoryError()));
    }
  }
}
