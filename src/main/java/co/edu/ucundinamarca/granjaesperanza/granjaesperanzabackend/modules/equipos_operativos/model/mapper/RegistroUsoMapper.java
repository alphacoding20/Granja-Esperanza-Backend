package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RegistroUsoMapper {

    public RegistroUsoEntity toEntity(RegistroUsoRequest request) {
        if (request == null) {
            return null;
        }

        RegistroUsoEntity entity = new RegistroUsoEntity();
        entity.setTrabajador(request.idTrabajador());
        entity.setEquipo(request.idEquipo());
        entity.setFecha(request.fecha() != null ? request.fecha() : LocalDate.now());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());

        return entity;
    }

    public RegistroUsoResponse toResponse(RegistroUsoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new RegistroUsoResponse(
                entity.getId(),
                entity.getTrabajador(),
                entity.getEquipo(),
                entity.getFecha(),
                entity.getHoraInicio(),
                entity.getHoraFin(),
                entity.getTotalHoras(),
                entity.getNovedades()
        );
    }

    public void updateEntityFromRequest(RegistroUsoRequest request, RegistroUsoEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        entity.setTrabajador(request.idTrabajador());
        entity.setEquipo(request.idEquipo());
        entity.setFecha(request.fecha() != null ? request.fecha() : entity.getFecha());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());
        entity.setNovedades(request.novedades());
    }
}
