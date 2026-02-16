package com.alfredogarcia.evaluacion.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TransaccionRequest(
    
    @NotBlank(message = "La operación es obligatoria")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "La operación solo debe contener letras")
    String operacion,

    @NotNull(message = "El importe es obligatorio")
    @Digits(integer = 10, fraction = 2, message = "Formato de moneda inválido (ej: 100.00)")
    @Positive(message = "El importe debe ser positivo")
    BigDecimal importe,

    @NotBlank(message = "El cliente es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del cliente debe tener entre 2 y 50 caracteres")
    String cliente,

    @NotBlank(message = "La firma es obligatoria")
    String firma
) {}