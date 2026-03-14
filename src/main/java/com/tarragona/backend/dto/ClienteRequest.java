package com.tarragona.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRequest {

    @NotBlank(message = "La cédula es obligatoria")
    @Size(max = 20, message = "La cédula no puede superar 20 caracteres")
    private String cedula;

    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String nombre;
}