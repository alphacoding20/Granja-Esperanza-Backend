package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.TipoMantenimiento;

import java.time.OffsetDateTime;

public record MantenimientoResponse (
        Integer id,
        EquipoEntity idEquipo,
        OffsetDateTime fecha,
        TipoMantenimiento tipo,
        String descripcion
){}
