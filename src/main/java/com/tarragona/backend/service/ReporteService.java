// ReporteService.java  (sin cambios)
// ─────────────────────────────────────────
package com.tarragona.backend.service;
 
import com.tarragona.backend.dto.ReporteMesResponse;
import com.tarragona.backend.exception.ResourceNotFoundException;
import com.tarragona.backend.model.ReporteMes;
import com.tarragona.backend.model.ReporteMes.ReporteMesId;
import com.tarragona.backend.repository.ReporteMesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReporteService {
 
    private final ReporteMesRepository reporteMesRepository;
 
    public ReporteMesResponse getReporteMes(int anio, int mes) {
        ReporteMes r = reporteMesRepository
                .findById(new ReporteMesId(anio, mes))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sin datos para " + mes + "/" + anio));
 
        return ReporteMesResponse.builder()
                .anio(r.getId().getAnio())
                .mes(r.getId().getMes())
                .totalFiestas(r.getTotalFiestas())
                .totalInvitados(r.getTotalInvitados())
                .totalHoras(r.getTotalHoras())
                .totalIngresos(r.getTotalIngresos())
                .fiestas1a3h(r.getFiestas1a3h())
                .fiestas4a6h(r.getFiestas4a6h())
                .fiestasMas6h(r.getFiestasMas6h())
                .build();
    }
}