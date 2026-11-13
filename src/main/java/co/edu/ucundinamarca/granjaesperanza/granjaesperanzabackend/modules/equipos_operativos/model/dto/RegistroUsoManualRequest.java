package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record RegistroUsoManualRequest(
        @NotNull(message = "El id del trabajador es obligatorio")
        Integer idTrabajador,
        @NotNull(message = "El id del equipo es obligatorio")
        Integer idEquipo,
        String accesorio,
        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,
        @NotBlank(message = "El lugar de uso es obligatorio")
        String lugarUso,
        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime horaInicio,
        @NotNull(message = "La hora de finalización es obligatoria")
        LocalTime horaFin,
        BigDecimal horasInactivo,
        BigDecimal usoCombustible
){}
