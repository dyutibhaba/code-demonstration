package com.protostellar.zugplaner.utils;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithAuthenticatedUser {
  String accountId() default "accountId";

  String displayedName() default "user";

  String email() default "prtsc.user@thalesdigital.io";

  String[] groups() default {"PRTS_ADMIN", "PRTS_USER"};
}
