package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RegistroUsoRequest (
        @NotNull(message = "El id del trabajador es obligatorio")
        Integer idTrabajador,
        @NotNull(message = "El id del equipo es obligatorio")
        Integer idEquipo,
        String accesorio,
        LocalDate fecha,
        String lugar_uso,
        LocalTime horaInicio,
        LocalTime horaFin,
        BigDecimal uso_combustible,
        BigDecimal totalHoras
){}
