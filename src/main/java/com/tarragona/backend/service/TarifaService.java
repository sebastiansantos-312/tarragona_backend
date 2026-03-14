package com.tarragona.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class TarifaService {

    public BigDecimal calcularMontoInvitados(int numInvitados) {
        int precio;
        if (numInvitados <= 100) {
            precio = 8_000;
        } else if (numInvitados <= 500) {
            precio = 6_000;
        } else {
            precio = 4_000;
        }
        return BigDecimal.valueOf((long) numInvitados * precio);
    }

    public BigDecimal calcularMontoHoras(BigDecimal horas) {
        double h = horas.doubleValue();
        if (h <= 3.0)  return BigDecimal.valueOf(100_000);
        if (h <= 6.0)  return BigDecimal.valueOf(200_000);
        return BigDecimal.valueOf(300_000);
    }

    public BigDecimal calcularTotal(int numInvitados, BigDecimal horas) {
        return calcularMontoInvitados(numInvitados).add(calcularMontoHoras(horas));
    }
}