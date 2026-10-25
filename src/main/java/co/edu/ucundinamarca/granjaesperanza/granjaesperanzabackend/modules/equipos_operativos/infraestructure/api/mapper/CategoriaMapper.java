package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.CategoriaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.CategoriaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaEntity toEntity(CategoriaRequest request) {
        if(request == null) {
            return null;
        }

        CategoriaEntity entity = new CategoriaEntity();
        entity.setNombre(request.nombre());

        return entity;
    }

    public CategoriaResponse toResponse(CategoriaEntity entity) {
        if(entity == null) {
            return null;
        }

        return new CategoriaResponse(entity.getId(), entity.getNombre());
    }

    public void updateEntityFromRequest(CategoriaRequest request, CategoriaEntity entity) {
        if(entity == null || request == null){
            return;
        }

        entity.setNombre(request.nombre());
    }
}
