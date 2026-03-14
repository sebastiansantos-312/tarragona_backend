package com.tarragona.backend.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ReporteMesResponse {
    private Integer anio;
    private Integer mes;
    private Long totalFiestas;
    private Long totalInvitados;
    private BigDecimal totalHoras;
    private BigDecimal totalIngresos;
    private Long fiestas1_3h;
    private Long fiestas4_6h;
    private Long fiestasMas6h;
}