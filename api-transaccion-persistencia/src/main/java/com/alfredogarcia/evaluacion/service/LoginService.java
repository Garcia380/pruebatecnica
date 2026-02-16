package com.alfredogarcia.evaluacion.service;

import com.alfredogarcia.evaluacion.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;

    public LoginService(UsuarioRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public boolean validarAcceso(String username, String passwordPlano) {
        return repository.findByUsername(username)
                .map(user -> {
                    // DEBUG: Verificamos qué llega de la DB y del Front
                    System.out.println("DEBUG - Usuario encontrado: [" + user.getUsername() + "]");
                    System.out.println("DEBUG - Password DB: [" + user.getPassword() + "]");
                    System.out.println("DEBUG - Password Front: [" + passwordPlano + "]");
                    
                    boolean matches = encoder.matches(passwordPlano, user.getPassword());
                    System.out.println("DEBUG - ¿Coinciden?: " + matches);
                    
                    return matches;
                })
                .orElseGet(() -> {
                    System.out.println("DEBUG - Usuario no encontrado: [" + username + "]");
                    return false;
                });
    }
    
    @PostConstruct
    public void init() {
        System.out.println("HASH REAL PARA admin123: " + encoder.encode("admin123"));
    }
    
}