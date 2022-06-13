package com.protostellar.zugplaner.trackandpredict.infra.api.rest.util;

import com.protostellar.zugplaner.common.infra.authorization.UserPrincipal;
import com.protostellar.zugplaner.common.model.id.Identifier;
import com.protostellar.zugplaner.trackandpredict.domain.repositories.ReadUser;

public final class UserUtility {

  private static ReadUser userRepository;

  private UserUtility() {
  }

  public static void setUserRepository(ReadUser userRepository) {
    UserUtility.userRepository = userRepository;
  }

  public static Identifier userId(UserPrincipal userPrincipal) {
    return userRepository.findByAccountId(userPrincipal.getAccountIdentifier()).getId();
  }

  public static Identifier getIdentifier(UserPrincipal userPrincipal) {
    return userId(userPrincipal);
  }
}
