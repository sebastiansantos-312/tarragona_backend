// ReporteMesRepository.java
// ─────────────────────────────────────────
package com.tarragona.backend.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.tarragona.backend.model.ReporteMes;
import com.tarragona.backend.model.ReporteMes.ReporteMesId;
 
public interface ReporteMesRepository extends JpaRepository<ReporteMes, ReporteMesId> {
    // findById(ReporteMesId) viene heredado de JpaRepository
}