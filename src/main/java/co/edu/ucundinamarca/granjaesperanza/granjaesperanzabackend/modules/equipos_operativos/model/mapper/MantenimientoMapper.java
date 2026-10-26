package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.MantenimientoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Component
public class MantenimientoMapper {

    public MantenimientoEntity toEntity(MantenimientoRequest request) {
        if (request == null) {
            return null;
        }

        MantenimientoEntity entity = new MantenimientoEntity();
        entity.setEquipo(request.equipo());
        entity.setFecha(request.fecha() != null ? request.fecha() : OffsetDateTime.now());
        entity.setTipo(request.tipo());
        entity.setDescripcion(request.descripcion());
        entity.setHorasAlMantenimiento(request.horasAlMantenimiento() != null ? request.horasAlMantenimiento() : BigDecimal.valueOf(0.0));

        return entity;
    }

    public MantenimientoResponse toResponse(MantenimientoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new MantenimientoResponse(
                entity.getId(),
                entity.getEquipo(),
                entity.getFecha(),
                entity.getTipo(),
                entity.getDescripcion()
        );
    }

    public void updateEntityFromRequest(MantenimientoRequest request, MantenimientoEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setEquipo(request.equipo());
        entity.setFecha(request.fecha() != null ? request.fecha() : entity.getFecha());
        entity.setTipo(request.tipo());
        entity.setDescripcion(request.descripcion());
        entity.setHorasAlMantenimiento(request.horasAlMantenimiento() != null ? request.horasAlMantenimiento() : entity.getHorasAlMantenimiento());
    }
}
