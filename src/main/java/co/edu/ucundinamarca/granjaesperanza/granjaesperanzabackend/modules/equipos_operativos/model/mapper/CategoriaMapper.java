package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.CategoriaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.CategoriaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaEntity toEntity(CategoriaRequest request) {
        if(request == null) {
            return null;
        }

        CategoriaEntity entity = new CategoriaEntity();
        entity.setNombre(request.nombre());
        entity.setActivo(true);

        return entity;
    }

    public CategoriaResponse toResponse(CategoriaEntity entity) {
        if(entity == null) {
            return null;
        }

        return new CategoriaResponse(entity.getId(), entity.getNombre(), entity.getActivo());
    }

    public void updateEntityFromRequest(CategoriaRequest request, CategoriaEntity entity) {
        if(entity == null || request == null){
            return;
        }

        entity.setNombre(request.nombre());
    }
}
