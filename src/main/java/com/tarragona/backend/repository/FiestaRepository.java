package com.tarragona.backend.repository;

import com.tarragona.backend.model.Fiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface FiestaRepository extends JpaRepository<Fiesta, UUID> {

    // Todas ordenadas por fecha descendente
    List<Fiesta> findAllByOrderByFechaFiestaDesc();

    // Fiestas de un mes específico
    @Query("SELECT f FROM Fiesta f WHERE " +
           "FUNCTION('YEAR', f.fechaFiesta) = :anio AND " +
           "FUNCTION('MONTH', f.fechaFiesta) = :mes " +
           "ORDER BY f.fechaFiesta DESC")
    List<Fiesta> findByAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    // Fiestas de un cliente
    List<Fiesta> findByClienteId(UUID clienteId);
}