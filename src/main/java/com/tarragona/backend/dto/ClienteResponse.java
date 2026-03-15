// ClienteResponse.java
// ─────────────────────────────────────────
package com.tarragona.backend.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data @Builder
public class ClienteResponse {
    private Long   id;
    private String cedula;
    private String nombre;
    private String telefono;
}
 