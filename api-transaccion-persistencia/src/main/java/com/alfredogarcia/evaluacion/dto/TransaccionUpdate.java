package com.alfredogarcia.evaluacion.dto;

public record TransaccionUpdate(
    Long id,
    String referencia,
    String estatus // Aquí llegará "cancelar"
) {}