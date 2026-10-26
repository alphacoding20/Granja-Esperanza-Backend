package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TipoMantenimiento;

import java.time.OffsetDateTime;

public record MantenimientoResponse (
        Integer id,
        EquipoEntity idEquipo,
        OffsetDateTime fecha,
        TipoMantenimiento tipo,
        String descripcion
){}
