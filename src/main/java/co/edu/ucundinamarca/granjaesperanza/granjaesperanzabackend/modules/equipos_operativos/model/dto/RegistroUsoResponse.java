package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroUsoResponse (
        Integer id,
        TrabajadorResponse trabajador,
        EquipoResponse equipo,
        String accesorio,
        LocalDate fecha,
        String lugarUso,
        LocalTime horaInicio,
        LocalTime horaFin,
        BigDecimal horasInactivo,
        BigDecimal usoCombustible,
        BigDecimal totalHoras
){}
