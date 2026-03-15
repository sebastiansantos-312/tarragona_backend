package com.tarragona.backend.controller;

import com.tarragona.backend.dto.FiestaRequest;
import com.tarragona.backend.dto.FiestaResponse;
import com.tarragona.backend.service.FiestaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fiestas")
@RequiredArgsConstructor
public class FiestaController {

    private final FiestaService fiestaService;

    @PostMapping
    public ResponseEntity<FiestaResponse> registrar(@Valid @RequestBody FiestaRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fiestaService.registrarFiesta(req));
    }

    @GetMapping
    public ResponseEntity<List<FiestaResponse>> listar(
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer mes) {
        if (anio != null && mes != null) {
            return ResponseEntity.ok(fiestaService.listarPorMes(anio, mes));
        }
        if (anio != null) {
            return ResponseEntity.ok(fiestaService.listarPorAnio(anio));
        }
        return ResponseEntity.ok(fiestaService.listarFiestas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiestaResponse> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(fiestaService.obtenerFiesta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FiestaResponse> actualizar(
            @PathVariable UUID id,
            @Valid @RequestBody FiestaRequest req) {
        return ResponseEntity.ok(fiestaService.actualizarFiesta(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        fiestaService.eliminarFiesta(id);
        return ResponseEntity.noContent().build();
    }
}