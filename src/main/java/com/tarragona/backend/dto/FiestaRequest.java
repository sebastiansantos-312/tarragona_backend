package com.tarragona.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate fechaFiesta;
}