package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoHerramientaMapper {

    public TipoHerramientaEntity toEntity(TipoHerramientaRequest request) {
        if (request == null) {
            return null;
        }

        TipoHerramientaEntity entity = new TipoHerramientaEntity();
        entity.setNombre(request.nombre());
        entity.setCantidad(request.cantidad());
        entity.setDisponibles(request.disponibles());

        return entity;
    }

    public TipoHerramientaResponse toResponse(TipoHerramientaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new TipoHerramientaResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getCantidad(),
                entity.getDisponibles()
        );
    }

    public void updateEntityFromRequest(TipoHerramientaRequest request, TipoHerramientaEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setNombre(request.nombre());
        entity.setCantidad(request.cantidad());
        entity.setDisponibles(request.disponibles());
    }
}
