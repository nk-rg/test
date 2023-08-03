package com.dico.test.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {
    private final JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> authorities = super.extractAuthorities(jwt);
        authorities.addAll(authoritiesConverter.convert(jwt));
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            Collection<String> roles = (Collection<String>) realmAccess.get("roles");
            authorities.addAll(mapToSimpleGrantedAuthority(roles));
        }
        return authorities;
    }

    private List<SimpleGrantedAuthority> mapToSimpleGrantedAuthority(Collection<String> roles) {
        return roles.stream()
                .map(role -> "ROLE_" + role.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
