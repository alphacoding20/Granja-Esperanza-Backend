package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto.DetallePrestamoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto.DetallePrestamoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.persistence.DetallePrestamoEntity;
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
