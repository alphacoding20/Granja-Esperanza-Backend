package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.DetallePrestamoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.DetallePrestamoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.DetallePrestamoEntity;
import org.springframework.stereotype.Component;

@Component
public class DetallePrestamoMapper {

    public DetallePrestamoEntity toEntity(DetallePrestamoRequest request) {
        if (request == null) {
            return null;
        }

        DetallePrestamoEntity entity = new DetallePrestamoEntity();
        entity.setPrestamoEstudiantil(request.idPrestamoEstudiantil());
        entity.setTipoHerramienta(request.idTipoHerramienta());
        entity.setCantidad(request.cantidad());

        return entity;
    }

    public DetallePrestamoResponse toResponse(DetallePrestamoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new DetallePrestamoResponse(
                entity.getId(),
                entity.getPrestamoEstudiantil(),
                entity.getTipoHerramienta(),
                entity.getCantidad()
        );
    }

    public void updateEntityFromRequest(DetallePrestamoRequest request, DetallePrestamoEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setPrestamoEstudiantil(request.idPrestamoEstudiantil());
        entity.setTipoHerramienta(request.idTipoHerramienta());
        entity.setCantidad(request.cantidad());
    }
}
