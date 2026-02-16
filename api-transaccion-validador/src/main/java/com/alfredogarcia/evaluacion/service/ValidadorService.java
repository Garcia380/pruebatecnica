package com.alfredogarcia.evaluacion.service;

import com.alfredogarcia.evaluacion.client.PersistenciaClient;
import com.alfredogarcia.evaluacion.dto.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

@Service
public class ValidadorService {

    private final PersistenciaClient persistenciaClient;

    public ValidadorService(PersistenciaClient persistenciaClient) {
        this.persistenciaClient = persistenciaClient;
    }

    public TransaccionResponse procesarTransaccion(TransaccionRequest req) {
        // Validar Firma (Integridad de datos)
        if (!esFirmaValida(req)) {
            throw new IllegalArgumentException("Firma inválida: Los datos han sido alterados o la firma es incorrecta.");
        }

        // Si es válido, enviar a persistencia
        return persistenciaClient.guardarEnPersistencia(req);
    }

    private boolean esFirmaValida(TransaccionRequest req) {
        try {
            
            String importeFormateado = req.importe().setScale(2, java.math.RoundingMode.HALF_UP).toPlainString();
            String dataConcatenada = req.operacion() + importeFormateado + req.cliente();
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataConcatenada.getBytes(StandardCharsets.UTF_8));
            String hashCalculado = HexFormat.of().formatHex(hashBytes);

            // --- DEBUG EN CONSOLA ---
            System.out.println("CADENA A VALIDAR: [" + dataConcatenada + "]");
            System.out.println("HASH CALCULADO: " + hashCalculado);
            System.out.println("HASH RECIBIDO:   " + req.firma().trim());

            return hashCalculado.equalsIgnoreCase(req.firma().trim());

        } catch (Exception e) {
            throw new RuntimeException("Error al calcular el hash SHA", e);
        }
    }
}