package com.tarragona.backend.controller;

import com.tarragona.backend.dto.ReporteMesResponse;
import com.tarragona.backend.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/mes")
    public ResponseEntity<ReporteMesResponse> getReporteMes(
            @RequestParam int anio,
            @RequestParam int mes) {
        return ResponseEntity.ok(reporteService.getReporteMes(anio, mes));
    }
}