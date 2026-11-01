package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RegistroUsoMapper {

    public RegistroUsoEntity toEntity(RegistroUsoRequest request) {
        if (request == null) {
            return null;
        }

        TrabajadorEntity trabajador = new TrabajadorEntity();
        trabajador.setId(request.idTrabajador());

        EquipoEntity equipo = new EquipoEntity();
        equipo.setId(request.idEquipo());

        RegistroUsoEntity entity = new RegistroUsoEntity();
        entity.setTrabajador(trabajador);
        entity.setEquipo(equipo);
        entity.setAccesorio(request.accesorio());
        entity.setFecha(request.fecha() != null ? request.fecha() : LocalDate.now());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());
        entity.setTotalHoras(request.totalHoras());

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
                entity.getAccesorio(),
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

        TrabajadorEntity trabajador = new TrabajadorEntity();
        trabajador.setId(request.idTrabajador());

        EquipoEntity equipo = new EquipoEntity();
        equipo.setId(request.idEquipo());

        entity.setTrabajador(trabajador);
        entity.setEquipo(equipo);
        entity.setAccesorio(request.accesorio());
        entity.setFecha(request.fecha() != null ? request.fecha() : entity.getFecha());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());
        entity.setTotalHoras(request.totalHoras());
        entity.setNovedades(request.novedades());
    }
}
