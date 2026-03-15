package com.tarragona.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Immutable
@Subselect("SELECT * FROM reporte_mes")
@Getter
@NoArgsConstructor
public class ReporteMes {

    @EmbeddedId
    private ReporteMesId id;

    @Column(name = "total_fiestas")
    private Long totalFiestas;

    @Column(name = "total_invitados")
    private Long totalInvitados;

    @Column(name = "total_horas")
    private BigDecimal totalHoras;

    @Column(name = "total_ingresos")
    private BigDecimal totalIngresos;

    @Column(name = "fiestas_1_3h")
    private Long fiestas1a3h;

    @Column(name = "fiestas_4_6h")
    private Long fiestas4a6h;

    @Column(name = "fiestas_mas6h")
    private Long fiestasMas6h;

    @Embeddable
    @Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    public static class ReporteMesId implements Serializable {
        @Column(name = "anio")
        private Integer anio;

        @Column(name = "mes")
        private Integer mes;
    }
}