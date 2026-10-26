package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.model.dto.AdminUsuarioRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.model.dto.AdminUsuarioResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.model.entity.AdminUsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminUsuarioMapper {

    public AdminUsuarioEntity toEntity(AdminUsuarioRequest request) {
        if (request == null) {
            return null;
        }

        AdminUsuarioEntity entity = new AdminUsuarioEntity();
        entity.setUsuario(request.usuario());
        entity.setPassword(request.password());

        return entity;
    }

    public AdminUsuarioResponse toResponse(AdminUsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return new AdminUsuarioResponse(
                entity.getId(),
                entity.getUsuario()
        );
    }

    public void updateEntityFromRequest(AdminUsuarioRequest request, AdminUsuarioEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setUsuario(request.usuario());
        entity.setPassword(request.password());
    }
}
