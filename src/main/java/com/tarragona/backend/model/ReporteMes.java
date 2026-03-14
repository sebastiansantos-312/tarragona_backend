package com.tarragona.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import java.math.BigDecimal;
import java.io.Serializable;

// Mapea la vista reporte_mes de Supabase — solo lectura
@Entity
@Immutable
@Subselect("SELECT * FROM reporte_mes")
@Getter @NoArgsConstructor
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
    private Long fiestas1_3h;

    @Column(name = "fiestas_4_6h")
    private Long fiestas4_6h;

    @Column(name = "fiestas_mas6h")
    private Long fiestasMas6h;

    // Clave compuesta embebida (anio + mes)
    @Embeddable
    @Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    public static class ReporteMesId implements Serializable {
        @Column(name = "anio")
        private Integer anio;

        @Column(name = "mes")
        private Integer mes;
    }
}