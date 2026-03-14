package com.tarragona.backend.service;

import com.tarragona.backend.dto.ClienteRequest;
import com.tarragona.backend.dto.ClienteResponse;
import com.tarragona.backend.exception.ResourceNotFoundException;
import com.tarragona.backend.model.Cliente;
import com.tarragona.backend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse crearCliente(ClienteRequest req) {
        if (clienteRepository.existsByCedula(req.getCedula())) {
            throw new IllegalArgumentException(
                "Ya existe un cliente con cédula: " + req.getCedula());
        }
        Cliente cliente = Cliente.builder()
                .cedula(req.getCedula())
                .nombre(req.getNombre())
                .build();
        return toResponse(clienteRepository.save(cliente));
    }

    public ClienteResponse buscarPorCedula(String cedula) {
        return toResponse(obtenerEntidadPorCedula(cedula));
    }

    // Uso interno desde FiestaService
    public Cliente obtenerEntidadPorCedula(String cedula) {
        return clienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Cliente no encontrado con cédula: " + cedula));
    }

    private ClienteResponse toResponse(Cliente c) {
        return ClienteResponse.builder()
                .id(c.getId())
                .cedula(c.getCedula())
                .nombre(c.getNombre())
                .build();
    }
}