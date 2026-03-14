package com.tarragona.backend.controller;

import com.tarragona.backend.dto.ClienteRequest;
import com.tarragona.backend.dto.ClienteResponse;
import com.tarragona.backend.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.crearCliente(req));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable String cedula) {
        return ResponseEntity.ok(clienteService.buscarPorCedula(cedula));
    }
}