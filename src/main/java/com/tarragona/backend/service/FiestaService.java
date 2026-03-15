package com.tarragona.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarragona.backend.dto.FiestaRequest;
import com.tarragona.backend.dto.FiestaResponse;
import com.tarragona.backend.exception.ResourceNotFoundException;
import com.tarragona.backend.model.Cliente;
import com.tarragona.backend.model.Fiesta;
import com.tarragona.backend.repository.FiestaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FiestaService {

    private final FiestaRepository fiestaRepository;
    private final ClienteService clienteService;
    private final TarifaService tarifaService;

    @Transactional
    public FiestaResponse registrarFiesta(FiestaRequest req) {
        Cliente cliente = clienteService.obtenerEntidadPorCedula(req.getCedula());
        Fiesta fiesta = Fiesta.builder()
                .cliente(cliente)
                .numInvitados(req.getNumInvitados())
                .horasDuracion(req.getHorasDuracion())
                .fechaFiesta(req.getFechaFiesta())
                .montoInvitados(tarifaService.calcularMontoInvitados(req.getNumInvitados()))
                .montoHoras(tarifaService.calcularMontoHoras(req.getHorasDuracion()))
                .montoTotal(tarifaService.calcularTotal(req.getNumInvitados(), req.getHorasDuracion()))
                .build();
        return toResponse(fiestaRepository.save(fiesta));
    }

    @Transactional(readOnly = true)
    public List<FiestaResponse> listarFiestas() {
        return fiestaRepository.findAllByOrderByFechaFiestaDesc()
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<FiestaResponse> listarPorMes(int anio, int mes) {
        return fiestaRepository.findByAnioAndMes(anio, mes)
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<FiestaResponse> listarPorAnio(int anio) {
        return fiestaRepository.findByAnio(anio)
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public FiestaResponse obtenerFiesta(UUID id) {
        return toResponse(buscarOFallar(id));
    }

    @Transactional
    public FiestaResponse actualizarFiesta(UUID id, FiestaRequest req) {
        Fiesta f = buscarOFallar(id);
        f.setNumInvitados(req.getNumInvitados());
        f.setHorasDuracion(req.getHorasDuracion());
        f.setFechaFiesta(req.getFechaFiesta());
        f.setMontoInvitados(tarifaService.calcularMontoInvitados(req.getNumInvitados()));
        f.setMontoHoras(tarifaService.calcularMontoHoras(req.getHorasDuracion()));
        f.setMontoTotal(tarifaService.calcularTotal(req.getNumInvitados(), req.getHorasDuracion()));
        return toResponse(fiestaRepository.save(f));
    }

    @Transactional
    public void eliminarFiesta(UUID id) {
        if (!fiestaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fiesta no encontrada: " + id);
        }
        fiestaRepository.deleteById(id);
    }

    private Fiesta buscarOFallar(UUID id) {
        return fiestaRepository.findByIdWithCliente(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fiesta no encontrada: " + id));
    }

    private FiestaResponse toResponse(Fiesta f) {
        return FiestaResponse.builder()
                .id(f.getId())
                .cedulaContratante(f.getCliente().getCedula())
                .nombreContratante(f.getCliente().getNombre())
                .numInvitados(f.getNumInvitados())
                .horasDuracion(f.getHorasDuracion())
                .fechaFiesta(f.getFechaFiesta())
                .montoInvitados(f.getMontoInvitados())
                .montoHoras(f.getMontoHoras())
                .montoTotal(f.getMontoTotal())
                .build();
    }
}