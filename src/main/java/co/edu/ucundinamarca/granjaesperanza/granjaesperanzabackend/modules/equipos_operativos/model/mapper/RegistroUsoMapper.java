package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.DevolucionEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.PrestamoEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoManualRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RegistroUsoMapper {

    private final TrabajadorMapper trabajadorMapper;
    private final EquipoMapper equipoMapper;

    public RegistroUsoEntity toEntity(RegistroUsoManualRequest request) {
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
        entity.setLugarUso(request.lugarUso());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());
        entity.setHorasInactivo(request.horasInactivo());
        entity.setUsoCombustible(request.usoCombustible());

        return entity;
    }

    public RegistroUsoEntity toEntityFromPrestamo(PrestamoEquipoRequest request) {
        if (request == null) {
            return null;
        }

        RegistroUsoEntity entity = new RegistroUsoEntity();
        entity.setAccesorio(request.accesorio());
        entity.setLugarUso(request.lugarUso());
        entity.setFecha(LocalDate.now());
        entity.setHoraInicio(LocalTime.now());

        return entity;
    }

    public RegistroUsoResponse toResponse(RegistroUsoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new RegistroUsoResponse(
                entity.getId(),
                trabajadorMapper.toResponse(entity.getTrabajador()),
                equipoMapper.toResponse(entity.getEquipo()),
                entity.getAccesorio(),
                entity.getFecha(),
                entity.getLugarUso(),
                entity.getHoraInicio(),
                entity.getHoraFin(),
                entity.getHorasInactivo(),
                entity.getUsoCombustible(),
                entity.getTotalHoras()
        );
    }

    public void updateEntityFromRequest(RegistroUsoManualRequest request, RegistroUsoEntity entity) {
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
        entity.setLugarUso(request.lugarUso());
        entity.setHoraInicio(request.horaInicio());
        entity.setHoraFin(request.horaFin());
        entity.setHorasInactivo(request.horasInactivo());
        entity.setUsoCombustible(request.usoCombustible());
    }

    public void updateEntityFromDevolucion(DevolucionEquipoRequest request, RegistroUsoEntity entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setHoraFin(LocalTime.now());

        BigDecimal horasInactivo = request.horasInactivo() != null
                ? request.horasInactivo()
                : BigDecimal.ZERO;
        entity.setHorasInactivo(horasInactivo);

        entity.setUsoCombustible(request.usoCombustible());
    }
}
