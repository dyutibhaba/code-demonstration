package com.protostellar.zugplaner.trackandpredict.infra.api.rest.util;

import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadUser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {

  private final ReadUser userRepository;

  public StaticContextInitializer(ReadUser userRepository) {
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void init() {
    UserUtility.setUserRepository(userRepository);
  }
}
