package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.NovedadMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.NovedadJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.RegistroUsoJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovedadService {

    private final RegistroUsoJpaRepository registroUsoJpaRepository;
    private final NovedadJpaRepository novedadJpaRepository;
    private final NovedadMapper novedadMapper;

    @Transactional
    public NovedadResponse crearNovedad(NovedadRequest request) {
        RegistroUsoEntity registroUso = registroUsoJpaRepository.findById(request.idRegistroUso())
                .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + request.idRegistroUso()));

        if (novedadJpaRepository.existsByRegistroUsoId(request.idRegistroUso())) {
            throw new IllegalStateException("Este registro de uso ya tiene una novedad registrada.");
        }

        NovedadEntity entity = novedadMapper.toEntity(request);
        entity.setRegistroUso(registroUso);

        NovedadEntity guardado = novedadJpaRepository.save(entity);
        return novedadMapper.toResponse(guardado);
    }

    public NovedadResponse consultarNovedad(Integer id) {
        NovedadEntity entity = novedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Novedad no encontrada con ID: " + id));
        return novedadMapper.toResponse(entity);
    }

    public List<NovedadResponse> obtenerTodasLasNovedades() {
        return novedadJpaRepository.findAll().stream()
                .map(novedadMapper::toResponse)
                .collect(Collectors.toList());
    }

    public NovedadResponse buscarPorRegistroUso(Integer idRegistroUso) {
        NovedadEntity entity = novedadJpaRepository.findByRegistroUsoId(idRegistroUso)
                .orElseThrow(() -> new RuntimeException("No existe novedad para el registro de uso con ID: " + idRegistroUso));
        return novedadMapper.toResponse(entity);
    }

    @Transactional
    public NovedadResponse actualizarNovedad(Integer id, NovedadRequest request) {
        NovedadEntity existente = novedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Novedad no encontrada con ID: " + id));

        if (!existente.getRegistroUso().getId().equals(request.idRegistroUso())) {
            RegistroUsoEntity nuevoRegistroUso = registroUsoJpaRepository.findById(request.idRegistroUso())
                    .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + request.idRegistroUso()));
            existente.setRegistroUso(nuevoRegistroUso);
        }

        novedadMapper.updateEntityFromRequest(request, existente);

        NovedadEntity actualizado = novedadJpaRepository.save(existente);
        return novedadMapper.toResponse(actualizado);
    }

    @Transactional
    public void eliminarNovedad(Integer id) {
        NovedadEntity existente = novedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Novedad no encontrada con ID: " + id));
        novedadJpaRepository.delete(existente);
    }
}

