package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.UsoLugarRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.UsoLugarResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.UsoLugarEntity;
import org.springframework.stereotype.Component;

@Component
public class UsoLugarMapper {

    public UsoLugarEntity toEntity(UsoLugarRequest request) {
        if (request == null) {
            return null;
        }

        UsoLugarEntity entity = new UsoLugarEntity();
        entity.setLugarTrabajo(request.idLugarTrabajo());
        entity.setRegistroUso(request.idRegistroUso());

        return entity;
    }

    public UsoLugarResponse toResponse(UsoLugarEntity entity) {
        if (entity == null) {
            return null;
        }

        return new UsoLugarResponse(
                entity.getId(),
                entity.getLugarTrabajo(),
                entity.getRegistroUso()
        );
    }

    public void updateEntityFromRequest(UsoLugarRequest request, UsoLugarEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setLugarTrabajo(request.idLugarTrabajo());
        entity.setRegistroUso(request.idRegistroUso());
    }
}
