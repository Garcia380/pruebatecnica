package com.alfredogarcia.evaluacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operacion;
    private BigDecimal importe;
    private String cliente;
    private String referencia;
    private String estatus;
    private String secreto;

    // Constructor con parámetros (Para el error: Constructor is undefined)
    public Transaccion(String operacion, BigDecimal importe, String cliente, String secreto) {
        this.operacion = operacion;
        this.importe = importe;
        this.cliente = cliente;
        this.secreto = secreto;
    }

}