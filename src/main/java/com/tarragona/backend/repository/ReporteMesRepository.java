package com.tarragona.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarragona.backend.model.ReporteMes;
import com.tarragona.backend.model.ReporteMes.ReporteMesId;

public interface ReporteMesRepository extends JpaRepository<ReporteMes, ReporteMesId> {

    Optional<ReporteMes> findById(ReporteMesId id);
}