package com.protostellar.zugplaner.marketplace.infra.spi.postgres.dao;

import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.marketplace.domain.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data(staticConstructor = "from")
@NoArgsConstructor
@Entity
public class ProductDAO {

  @Id
  private String id;
  private String name;
  private String model;

  public Product toProduct() {
    return Product.builder()
      .id(Identifier.from(id))
      .name(name)
      .model(model)
      .build();
  }

}
