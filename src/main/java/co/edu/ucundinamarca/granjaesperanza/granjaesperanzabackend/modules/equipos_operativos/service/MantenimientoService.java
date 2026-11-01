package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.MantenimientoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.MantenimientoMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.EquipoJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.MantenimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoJpaRepository mantenimientoJpaRepository;
    private final EquipoJpaRepository equipoJpaRepository;
    private final MantenimientoMapper mantenimientoMapper;

    public MantenimientoResponse crearMantenimiento(MantenimientoRequest request) {
        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        MantenimientoEntity mantenimiento = mantenimientoMapper.toEntity(request);
        mantenimiento.setEquipo(equipo);

        if (mantenimiento.getFecha() == null) {
            mantenimiento.setFecha(OffsetDateTime.now());
        }

        MantenimientoEntity guardado = mantenimientoJpaRepository.save(mantenimiento);
        return mantenimientoMapper.toResponse(guardado);
    }

    public MantenimientoResponse consultarMantenimiento(Integer id) {
        MantenimientoEntity mantenimiento = mantenimientoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con ID: " + id));
        return mantenimientoMapper.toResponse(mantenimiento);
    }

    public List<MantenimientoResponse> obtenerTodosLosMantenimientos() {
        return mantenimientoJpaRepository.findAll().stream()
                .map(mantenimientoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MantenimientoResponse actualizarMantenimiento(Integer id, MantenimientoRequest request) {
        MantenimientoEntity existente = mantenimientoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con ID: " + id));

        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        existente.setEquipo(equipo);
        existente.setFecha(request.fecha());
        existente.setTipo(request.tipo());
        existente.setDescripcion(request.descripcion());
        existente.setHorasAlMantenimiento(request.horasAlMantenimiento());

        MantenimientoEntity actualizado = mantenimientoJpaRepository.save(existente);
        return mantenimientoMapper.toResponse(actualizado);
    }

    public void eliminarMantenimiento(Integer id) {
        MantenimientoEntity existente = mantenimientoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con ID: " + id));
        mantenimientoJpaRepository.delete(existente);
    }
}