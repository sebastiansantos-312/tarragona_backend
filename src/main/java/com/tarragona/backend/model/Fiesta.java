package com.tarragona.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fiestas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Fiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "num_invitados", nullable = false)
    private Integer numInvitados;

    @Column(name = "horas_duracion", nullable = false, precision = 4, scale = 1)
    private BigDecimal horasDuracion;

    @Column(name = "fecha_fiesta", nullable = false)
    private LocalDate fechaFiesta;

    @Column(name = "monto_invitados", precision = 12, scale = 2)
    private BigDecimal montoInvitados;

    @Column(name = "monto_horas", precision = 12, scale = 2)
    private BigDecimal montoHoras;

    @Column(name = "monto_total", precision = 12, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "created_at", updatable = false,
            columnDefinition = "timestamptz DEFAULT now()")
    private OffsetDateTime createdAt;
}