package com.api.secureauthapi.service;

import com.api.secureauthapi.dto.AuthRequest;
import com.api.secureauthapi.dto.AuthResponse;
import com.api.secureauthapi.dto.RegisterRequest;
import com.api.secureauthapi.model.User;
import com.api.secureauthapi.repository.UserRepository;
import com.api.secureauthapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Verificar si el email ya está registrado
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        // Crear nuevo usuario
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);

        // Generar token JWT
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.Collections.emptyList()
                )
        );

        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        // Autenticar usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        // Buscar usuario
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Generar token JWT
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        java.util.Collections.emptyList()
                )
        );

        return new AuthResponse(token);
    }
}