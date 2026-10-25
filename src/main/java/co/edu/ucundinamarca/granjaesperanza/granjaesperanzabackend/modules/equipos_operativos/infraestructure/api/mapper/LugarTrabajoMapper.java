package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.CategoriaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.LugarTrabajoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.LugarTrabajoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.LugarTrabajoEntity;
import org.springframework.stereotype.Component;

@Component
public class LugarTrabajoMapper {

    public LugarTrabajoEntity toEntity(LugarTrabajoRequest request) {
        if(request == null) {
            return null;
        }

        LugarTrabajoEntity entity = new LugarTrabajoEntity();
        entity.setNombre(request.nombre());

        return entity;
    }

    public LugarTrabajoResponse toResponse(LugarTrabajoEntity entity) {
        if(entity == null) {
            return null;
        }

        return new LugarTrabajoResponse(entity.getId(), entity.getNombre());
    }

    public void updateEntityFromRequest(LugarTrabajoRequest request, LugarTrabajoEntity entity) {
        if(request == null || entity == null) {
            return;
        }

        entity.setNombre(request.nombre());
    }
}
