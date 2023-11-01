package com.bus.online.ticketmanagement.config.security;

import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.bus.online.ticketmanagement.constant.ApplicationConstants.TOKEN_TYPE;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.USER_NOT_FOUND;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String accessToken = authHeader.substring(TOKEN_TYPE.length());
        String username = jwtService.extractUsername(accessToken);
        User user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND.getMessage()));

        if (jwtService.isTokenValid(accessToken)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}
