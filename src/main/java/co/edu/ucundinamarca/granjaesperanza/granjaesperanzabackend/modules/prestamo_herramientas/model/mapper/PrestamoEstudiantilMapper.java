package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.PrestamoEstudiantilEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class PrestamoEstudiantilMapper {

    public PrestamoEstudiantilEntity toEntity(PrestamoEstudiantilRequest request) {
        if (request == null) {
            return null;
        }

        PrestamoEstudiantilEntity entity = new PrestamoEstudiantilEntity();
        entity.setCedula(request.cedula());
        entity.setFechaPrestamo(request.fechaPrestamo() != null ? request.fechaPrestamo() : OffsetDateTime.now());
        entity.setFechaDevolucion(request.fechaDevolucion());

        return entity;
    }

    public PrestamoEstudiantilResponse toResponse(PrestamoEstudiantilEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PrestamoEstudiantilResponse(
                entity.getId(),
                entity.getCedula(),
                entity.getFechaPrestamo(),
                entity.getFechaDevolucion()
        );
    }

    public void updateEntityFromRequest(PrestamoEstudiantilRequest request, PrestamoEstudiantilEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setCedula(request.cedula());
        entity.setFechaPrestamo(request.fechaPrestamo() != null ? request.fechaPrestamo() : entity.getFechaPrestamo());
        entity.setFechaDevolucion(request.fechaDevolucion());
    }
}
