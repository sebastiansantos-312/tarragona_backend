package com.tarragona.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/**
 * Centraliza toda la lógica de tarifas.
 * Si cambian los precios, solo se modifica aquí.
 */
@Service
public class TarifaService {

    public BigDecimal calcularMontoInvitados(int numInvitados) {
        int precioPorPersona;
        if      (numInvitados <= 100) precioPorPersona = 8_000;
        else if (numInvitados <= 500) precioPorPersona = 6_000;
        else                          precioPorPersona = 4_000;

        return BigDecimal.valueOf((long) numInvitados * precioPorPersona);
    }

    public BigDecimal calcularMontoHoras(BigDecimal horas) {
        double h = horas.doubleValue();
        if (h <= 3.0) return BigDecimal.valueOf(100_000);
        if (h <= 6.0) return BigDecimal.valueOf(200_000);
        return BigDecimal.valueOf(300_000);
    }

    public BigDecimal calcularTotal(int numInvitados, BigDecimal horas) {
        return calcularMontoInvitados(numInvitados).add(calcularMontoHoras(horas));
    }
}