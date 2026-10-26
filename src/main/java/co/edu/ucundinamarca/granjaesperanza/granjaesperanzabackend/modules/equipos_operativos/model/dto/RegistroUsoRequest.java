package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroUsoRequest (
        @NotNull(message = "El id del trabajador es obligatorio")
        TrabajadorEntity idTrabajador,
        @NotNull(message = "El id del equipo es obligatorio")
        EquipoEntity idEquipo,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        String novedades
){}
