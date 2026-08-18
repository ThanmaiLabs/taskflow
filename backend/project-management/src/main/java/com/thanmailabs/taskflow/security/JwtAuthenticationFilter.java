package com.thanmailabs.taskflow.security;

import com.thanmailabs.taskflow.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("JWT Filter Executed");
        String token = authorizationHeader.substring(7);
        if (jwtService.isValidAccessToken(token)
            && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = jwtService.getUserNameFromToken(token);
            Long userId = jwtService.getUserIdFromToken(token);
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(userId, username);
            Authentication authentication =
              new UsernamePasswordAuthenticationToken(authenticatedUser, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);
    }
}
