package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import java.math.BigDecimal;

public record DevolucionEquipoRequest(
        BigDecimal horasInactivo,
        BigDecimal usoCombustible
) {}
