package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import org.springframework.stereotype.Component;

@Component
public class NovedadMapper {

    public NovedadEntity toEntity(NovedadRequest request) {
        if (request == null) {
            return null;
        }

        NovedadEntity entity = new NovedadEntity();
        entity.setDescripcion(request.descripcion());

        if (request.idRegistroUso() != null) {
            RegistroUsoEntity registroUso = new RegistroUsoEntity();
            registroUso.setId(request.idRegistroUso());
            entity.setRegistroUso(registroUso);
        }

        return entity;
    }

    public NovedadResponse toResponse(NovedadEntity entity) {
        if (entity == null) {
            return null;
        }

        return new NovedadResponse(
                entity.getId(),
                entity.getRegistroUso(),
                entity.getDescripcion()
        );
    }
}