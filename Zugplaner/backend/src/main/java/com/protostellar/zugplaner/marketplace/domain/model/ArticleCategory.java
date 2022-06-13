package com.protostellar.zugplaner.marketplace.domain.model;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Value
public class ArticleCategory {
  private final String name;
  private final List<Article> articles;
}
