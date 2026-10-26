package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TipoMantenimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MantenimientoRequest (
        @NotNull(message = "El id del equipo es obligatorio")
        EquipoEntity equipo,
        OffsetDateTime fecha,
        @NotBlank(message = "El tipo es obligatorio")
        @Size(max = 20)
        TipoMantenimiento tipo,
        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,
        BigDecimal horasAlMantenimiento
){}
