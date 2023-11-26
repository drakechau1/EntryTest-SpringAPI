package com.example.entrytestspringbootapida.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
//@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTDecoder jwtDecoder;
    private final JWT2PrincipalConverter jwt2PrincipalConverter;

    public JWTAuthenticationFilter(JWTDecoder jwtDecoder, JWT2PrincipalConverter jwt2PrincipalConverter) {
        this.jwtDecoder = jwtDecoder;
        this.jwt2PrincipalConverter = jwt2PrincipalConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        extractTokenFromRequest(request)
                .map(jwtDecoder::decode)
                .map(jwt2PrincipalConverter::convert)
                .map(UserPrincipalAuthenticationToken::new)
                .ifPresent(authenticationToken -> SecurityContextHolder.getContext().setAuthentication(authenticationToken));

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }
}
