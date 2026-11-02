package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EstadoEquipo;

import java.math.BigDecimal;

public record EquipoResponse (
        Integer id,
        Integer idCategoria,
        String nombre,
        EstadoEquipo estado,
        Boolean disponible,
        BigDecimal horasAcumuladas,
        BigDecimal horasTotales,
        BigDecimal umbralMantenimiento
) {}
