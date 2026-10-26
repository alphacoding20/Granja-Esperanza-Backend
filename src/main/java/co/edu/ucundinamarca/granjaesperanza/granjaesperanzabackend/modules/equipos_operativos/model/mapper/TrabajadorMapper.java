package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import org.springframework.stereotype.Component;

@Component
public class TrabajadorMapper {

    public TrabajadorEntity toEntity(TrabajadorRequest request) {
        if (request == null) {
            return null;
        }

        TrabajadorEntity entity = new TrabajadorEntity();
        entity.setNombre(request.nombre());
        entity.setCedula(request.cedula());

        return entity;
    }

    public TrabajadorResponse toResponse(TrabajadorEntity entity) {
        if (entity == null) {
            return null;
        }

        return new TrabajadorResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getCedula()
        );
    }

    public void updateEntityFromRequest(TrabajadorRequest request, TrabajadorEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setNombre(request.nombre());
        entity.setCedula(request.cedula());
    }
}
