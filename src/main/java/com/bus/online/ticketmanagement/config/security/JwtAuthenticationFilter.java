package com.bus.online.ticketmanagement.config.security;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.model.entity.RsaKeyPair;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.RsaKeyPairRepository;
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
import java.util.UUID;

import static com.bus.online.ticketmanagement.constant.ApplicationConstants.AUTH_KEY;
import static com.bus.online.ticketmanagement.constant.ApplicationConstants.TOKEN_TYPE;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.KEY_PAIR_NOT_FOUND;
import static com.bus.online.ticketmanagement.constant.ExceptionConstant.USER_NOT_FOUND;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RsaKeyPairRepository rsaKeyPairRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String authKeyId = request.getHeader(AUTH_KEY);

        if (authHeader == null || !authHeader.startsWith(TOKEN_TYPE) || authKeyId == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String accessToken = authHeader.substring(TOKEN_TYPE.length());
        try {
            RsaKeyPair rsaKeyPair = rsaKeyPairRepository.findById(UUID.fromString(authKeyId))
                    .orElseThrow(() -> new ResourceNotFoundException(KEY_PAIR_NOT_FOUND));

            String username = jwtService.extractUsername(accessToken, rsaKeyPair.getPublicKey());
            User user = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND.getMessage()));

            if (jwtService.isTokenValid(accessToken, rsaKeyPair.getPublicKey())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ignored) {

        }

        filterChain.doFilter(request, response);

    }
}
