package com.protostellar.zugplaner.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthenticatedUser> {

  @Override
  public SecurityContext createSecurityContext(WithAuthenticatedUser customUser) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    final Authentication authentication = buildAuthenticationToken(customUser);

    context.setAuthentication(authentication);

    return context;
  }

  private Authentication buildAuthenticationToken(WithAuthenticatedUser customUser) {

    Map<String, Object> claims = Map.of(
            "sub", customUser.accountId(),
            "email",customUser.email(),
            "name", customUser.displayedName(),
            "realm_access", Map.of("roles", List.of(customUser.groups()))
    );
    Jwt jwt = new Jwt("token", Instant.now(), Instant.MAX, Map.of("alg", "none"), claims);
    return new JwtAuthenticationToken(jwt);
//    UserPrincipal userPrincipal = new UserPrincipal(Set.of(customUser.groups()),
//            customUser.accountId(),
//            customUser.email(),
//            customUser.displayedName());
//
//    Set<SimpleGrantedAuthority> authorities = Arrays.stream(customUser.groups())
//            .map(s -> new SimpleGrantedAuthority(s))
//            .collect(Collectors.toSet()
//            );
//    return new PreAuthenticatedAuthenticationToken(
//            userPrincipal, null, authorities);
  }


}

