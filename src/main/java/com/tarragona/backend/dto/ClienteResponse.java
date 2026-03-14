package com.tarragona.backend.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ClienteResponse {
    private UUID id;
    private String cedula;
    private String nombre;
}