package com.tarragona.backend.repository;

import com.tarragona.backend.model.Fiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FiestaRepository extends JpaRepository<Fiesta, UUID> {

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente ORDER BY f.fechaFiesta DESC")
    List<Fiesta> findAllByOrderByFechaFiestaDesc();

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente WHERE " +
           "YEAR(f.fechaFiesta) = :anio AND MONTH(f.fechaFiesta) = :mes " +
           "ORDER BY f.fechaFiesta DESC")
    List<Fiesta> findByAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente WHERE " +
           "YEAR(f.fechaFiesta) = :anio " +
           "ORDER BY f.fechaFiesta DESC")
    List<Fiesta> findByAnio(@Param("anio") int anio);

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente WHERE f.cliente.id = :clienteId")
    List<Fiesta> findByClienteId(@Param("clienteId") UUID clienteId);

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente WHERE f.id = :id")
    Optional<Fiesta> findByIdWithCliente(@Param("id") UUID id);
}