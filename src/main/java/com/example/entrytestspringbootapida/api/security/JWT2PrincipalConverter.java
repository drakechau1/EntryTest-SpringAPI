package com.example.entrytestspringbootapida.api.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWT2PrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt) {
        return new UserPrincipal(
                Long.valueOf(jwt.getSubject()),
                jwt.getClaim("username").asString(),
                jwt.getClaim("password").asString(),
                extractAuthoritiesFromClaim(jwt));
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("username");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }

}
