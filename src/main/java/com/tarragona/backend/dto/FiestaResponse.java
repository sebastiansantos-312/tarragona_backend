package com.tarragona.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FiestaResponse {
    private Long       id;          // ← Long, no UUID
    private String     cedulaContratante;
    private String     nombreContratante;
    private Integer    numInvitados;
    private BigDecimal horasDuracion;
    private LocalDate  fechaFiesta;
    private BigDecimal montoInvitados;
    private BigDecimal montoHoras;
    private BigDecimal montoTotal;
}