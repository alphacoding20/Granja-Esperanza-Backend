package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.LugarTrabajoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.LugarTrabajoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.LugarTrabajoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.LugarTrabajoMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.LugarTrabajoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LugarTrabajoService {

    private final LugarTrabajoJpaRepository lugarTrabajoJpaRepository;
    private final LugarTrabajoMapper lugarTrabajoMapper;

    public LugarTrabajoResponse crearLugarTrabajo(LugarTrabajoRequest request) {
        LugarTrabajoEntity entity = lugarTrabajoMapper.toEntity(request);
        LugarTrabajoEntity guardado = lugarTrabajoJpaRepository.save(entity);
        return lugarTrabajoMapper.toResponse(guardado);
    }

    public LugarTrabajoResponse consultarLugarTrabajo(Integer id) {
        LugarTrabajoEntity entity = lugarTrabajoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar de trabajo no encontrado con ID: " + id));
        return lugarTrabajoMapper.toResponse(entity);
    }

    public List<LugarTrabajoResponse> obtenerTodosLosLugaresTrabajo() {
        return lugarTrabajoJpaRepository.findAll().stream()
                .map(lugarTrabajoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LugarTrabajoResponse actualizarLugarTrabajo(Integer id, LugarTrabajoRequest request) {
        LugarTrabajoEntity existente = lugarTrabajoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar de trabajo no encontrado con ID: " + id));

        lugarTrabajoMapper.updateEntityFromRequest(request, existente);
        LugarTrabajoEntity actualizado = lugarTrabajoJpaRepository.save(existente);
        return lugarTrabajoMapper.toResponse(actualizado);
    }

    public void eliminarLugarTrabajo(Integer id) {
        LugarTrabajoEntity existente = lugarTrabajoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lugar de trabajo no encontrado con ID: " + id));
        existente.setActivo(false);
        lugarTrabajoJpaRepository.save(existente);
    }
}
