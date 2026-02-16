package com.alfredogarcia.evaluacion.controller;

import com.alfredogarcia.evaluacion.dto.*;
import com.alfredogarcia.evaluacion.service.ValidadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validador")
public class TransaccionValidadorController {

    private final ValidadorService service;

    public TransaccionValidadorController(ValidadorService service) {
        this.service = service;
    }

    @PostMapping("/transaccion")
    public ResponseEntity<TransaccionResponse> recibirTransaccion(@Valid @RequestBody TransaccionRequest request) {
        // Si pasa @Valid, entra aquí. Si la firma falla, el Service lanza excepción
        // que captura el RestControllerAdvice.
        return ResponseEntity.ok(service.procesarTransaccion(request));
    }
}