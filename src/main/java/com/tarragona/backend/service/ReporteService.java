package com.tarragona.backend.service;

import com.tarragona.backend.dto.ReporteMesResponse;
import com.tarragona.backend.exception.ResourceNotFoundException;
import com.tarragona.backend.model.ReporteMes;
import com.tarragona.backend.model.ReporteMes.ReporteMesId;
import com.tarragona.backend.repository.ReporteMesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteMesRepository reporteMesRepository;

    public ReporteMesResponse getReporteMes(int anio, int mes) {
        ReporteMes reporte = reporteMesRepository
                .findById(new ReporteMesId(anio, mes))
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Sin datos para " + mes + "/" + anio));

        return ReporteMesResponse.builder()
                .anio(reporte.getId().getAnio())
                .mes(reporte.getId().getMes())
                .totalFiestas(reporte.getTotalFiestas())
                .totalInvitados(reporte.getTotalInvitados())
                .totalHoras(reporte.getTotalHoras())
                .totalIngresos(reporte.getTotalIngresos())
                .fiestas1_3h(reporte.getFiestas1_3h())
                .fiestas4_6h(reporte.getFiestas4_6h())
                .fiestasMas6h(reporte.getFiestasMas6h())
                .build();
    }
}