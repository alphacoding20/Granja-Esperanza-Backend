package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.*;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.RegistroUsoMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroUsoService {

    private final RegistroUsoJpaRepository registroUsoJpaRepository;
    private final TrabajadorJpaRepository trabajadorJpaRepository;
    private final EquipoJpaRepository equipoJpaRepository;
    private final RegistroUsoMapper registroUsoMapper;

    @Transactional
    public RegistroUsoResponse crearRegistroUso(RegistroUsoRequest request) {
        TrabajadorEntity trabajador = trabajadorJpaRepository.findById(request.idTrabajador())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + request.idTrabajador()));

        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        RegistroUsoEntity entity = registroUsoMapper.toEntity(request);
        entity.setTrabajador(trabajador);
        entity.setEquipo(equipo);

        if (entity.getFecha() == null) {
            entity.setFecha(LocalDate.now());
        }

        RegistroUsoEntity guardado = registroUsoJpaRepository.save(entity);

        return registroUsoMapper.toResponse(guardado);
    }

    public RegistroUsoResponse consultarRegistroUso(Integer id) {
        RegistroUsoEntity entity = registroUsoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + id));
        return registroUsoMapper.toResponse(entity);
    }

    public List<RegistroUsoResponse> obtenerTodosLosRegistrosUso() {
        return registroUsoJpaRepository.findAll().stream()
                .map(registroUsoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RegistroUsoResponse actualizarRegistroUso(Integer id, RegistroUsoRequest request) {
        RegistroUsoEntity existente = registroUsoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + id));

        TrabajadorEntity trabajador = trabajadorJpaRepository.findById(request.idTrabajador())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + request.idTrabajador()));

        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        registroUsoMapper.updateEntityFromRequest(request, existente);
        existente.setTrabajador(trabajador);
        existente.setEquipo(equipo);

        RegistroUsoEntity actualizado = registroUsoJpaRepository.save(existente);

        return registroUsoMapper.toResponse(actualizado);
    }

    @Transactional
    public void eliminarRegistroUso(Integer id) {
        RegistroUsoEntity existente = registroUsoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + id));

        registroUsoJpaRepository.delete(existente);
    }
}
