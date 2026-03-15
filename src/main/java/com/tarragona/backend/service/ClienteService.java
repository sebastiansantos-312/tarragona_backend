package com.tarragona.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarragona.backend.dto.ClienteRequest;
import com.tarragona.backend.dto.ClienteResponse;
import com.tarragona.backend.exception.ResourceNotFoundException;
import com.tarragona.backend.model.Cliente;
import com.tarragona.backend.repository.ClienteRepository;
import com.tarragona.backend.repository.FiestaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final FiestaRepository  fiestaRepository;

    public ClienteResponse crearCliente(ClienteRequest req) {
        if (clienteRepository.existsByCedula(req.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con cédula: " + req.getCedula());
        }
        Cliente cliente = Cliente.builder()
                .cedula(req.getCedula())
                .nombre(req.getNombre())
                .telefono(req.getTelefono())
                .build();
        return toResponse(clienteRepository.save(cliente));
    }

    public ClienteResponse actualizarCliente(Long id, ClienteRequest req) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));

        // Si cambia la cédula, verificar que no exista en otro cliente
        if (!c.getCedula().equals(req.getCedula()) && clienteRepository.existsByCedula(req.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con cédula: " + req.getCedula());
        }

        c.setCedula(req.getCedula());
        c.setNombre(req.getNombre());
        c.setTelefono(req.getTelefono());
        return toResponse(clienteRepository.save(c));
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado: " + id);
        }
        // Protección: no borrar si tiene fiestas registradas
        long fiestas = fiestaRepository.countByClienteId(id);
        if (fiestas > 0) {
            throw new IllegalArgumentException(
                "No se puede eliminar: el cliente tiene " + fiestas +
                " fiesta" + (fiestas != 1 ? "s" : "") + " registrada" + (fiestas != 1 ? "s" : ""));
        }
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorCedula(String cedula) {
        return toResponse(obtenerEntidadPorCedula(cedula));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    /** Uso interno desde FiestaService */
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
                .telefono(c.getTelefono())
                .build();
    }
}