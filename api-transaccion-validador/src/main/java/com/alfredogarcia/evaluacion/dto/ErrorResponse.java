package com.alfredogarcia.evaluacion.dto;

public record ErrorResponse(
        int codigo,
        String mensaje
) {
}