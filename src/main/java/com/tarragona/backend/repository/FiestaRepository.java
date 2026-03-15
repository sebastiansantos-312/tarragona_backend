package com.tarragona.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tarragona.backend.model.Fiesta;

public interface FiestaRepository extends JpaRepository<Fiesta, Long> {

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente ORDER BY f.fechaFiesta DESC")
    List<Fiesta> findAllWithCliente();

    @Query("""
            SELECT f FROM Fiesta f JOIN FETCH f.cliente
            WHERE EXTRACT(YEAR  FROM f.fechaFiesta) = :anio
              AND EXTRACT(MONTH FROM f.fechaFiesta) = :mes
            ORDER BY f.fechaFiesta DESC
            """)
    List<Fiesta> findByAnioAndMes(@Param("anio") int anio, @Param("mes") int mes);

    @Query("""
            SELECT f FROM Fiesta f JOIN FETCH f.cliente
            WHERE EXTRACT(YEAR FROM f.fechaFiesta) = :anio
            ORDER BY f.fechaFiesta DESC
            """)
    List<Fiesta> findByAnio(@Param("anio") int anio);

    @Query("SELECT f FROM Fiesta f JOIN FETCH f.cliente WHERE f.id = :id")
    Optional<Fiesta> findByIdWithCliente(@Param("id") Long id);

    // Usado por ClienteService para verificar si tiene fiestas antes de eliminar
    @Query("SELECT COUNT(f) FROM Fiesta f WHERE f.cliente.id = :clienteId")
    long countByClienteId(@Param("clienteId") Long clienteId);
}