package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrestamoEquipoRequest(
        @NotNull(message = "El id del trabajador es obligatorio")
        Integer idTrabajador,
        @NotNull(message = "El id del equipo es obligatorio")
        Integer idEquipo,
        String accesorio,
        @NotBlank(message = "El lugar de uso es obligatorio")
        String lugarUso
) {}
