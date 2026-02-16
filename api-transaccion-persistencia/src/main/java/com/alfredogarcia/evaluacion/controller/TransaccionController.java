package com.alfredogarcia.evaluacion.controller;

import com.alfredogarcia.evaluacion.dto.*;
import com.alfredogarcia.evaluacion.entity.Transaccion;
import com.alfredogarcia.evaluacion.service.TransaccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persistencia")
public class TransaccionController {

    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TransaccionResponse> guardar(@RequestBody TransaccionRequest request) {
        return ResponseEntity.ok(service.guardarTransaccion(request));
    }

    @PatchMapping("/actualizar-estatus")
    public ResponseEntity<String> actualizarEstatus(@RequestBody TransaccionUpdate update) {
        boolean actualizado = service.cancelarTransaccion(update);
        return actualizado ? ResponseEntity.ok("Estatus actualizado") : ResponseEntity.badRequest().body("No se pudo actualizar");
    }

    @GetMapping("/referencia/{ref}")
    public ResponseEntity<Transaccion> buscarPorReferencia(@PathVariable String ref) {
        return service.obtenerPorReferencia(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todas")
    public List<Transaccion> listarTodas() {
        return service.obtenerTodas();
    }
}