package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.TrabajadorEntity;
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
