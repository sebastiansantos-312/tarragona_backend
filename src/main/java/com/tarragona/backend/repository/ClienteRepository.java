package com.tarragona.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarragona.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCedula(String cedula);
    boolean existsByCedula(String cedula);
}