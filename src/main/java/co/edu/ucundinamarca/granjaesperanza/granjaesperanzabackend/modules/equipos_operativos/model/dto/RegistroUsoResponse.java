package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroUsoResponse (
        Integer id,
        TrabajadorEntity idTrabajador,
        EquipoEntity idEquipo,
        String accesorio,
        LocalDate fecha,
        String lugar_uso,
        LocalTime horaInicio,
        LocalTime horaFin,
        BigDecimal uso_combustible,
        BigDecimal totalHoras
){}
