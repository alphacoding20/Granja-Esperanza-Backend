package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.EstadoEquipo;

import java.math.BigDecimal;

public record EquipoResponse (
        Integer id,
        Integer idCategoria,
        String nombre,
        EstadoEquipo estado,
        BigDecimal horasAcumuladas,
        BigDecimal umbralMantenimiento
) {}
