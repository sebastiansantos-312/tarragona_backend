package com.tarragona.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FiestaRequest {

    @NotBlank(message = "La cédula del contratante es obligatoria")
    @Size(max = 20)
    private String cedula;

    @NotNull(message = "El número de invitados es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 invitado")
    private Integer numInvitados;

    @NotNull(message = "Las horas de duración son obligatorias")
    @DecimalMin(value = "0.5", message = "La duración mínima es 0.5 horas")
    private BigDecimal horasDuracion;

    @NotNull(message = "La fecha de la fiesta es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFiesta;
}