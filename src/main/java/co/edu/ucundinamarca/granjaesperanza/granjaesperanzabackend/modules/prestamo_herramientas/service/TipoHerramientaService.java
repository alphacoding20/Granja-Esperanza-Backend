package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.mapper.TipoHerramientaMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.repository.TipoHerramientaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoHerramientaService {

    private final TipoHerramientaJpaRepository tipoHerramientaJpaRepository;
    private final TipoHerramientaMapper tipoHerramientaMapper;

    public TipoHerramientaResponse crearTipoHerramienta(TipoHerramientaRequest request) {
        TipoHerramientaEntity entity = tipoHerramientaMapper.toEntity(request);
        TipoHerramientaEntity guardado = tipoHerramientaJpaRepository.save(entity);
        return tipoHerramientaMapper.toResponse(guardado);
    }

    public TipoHerramientaResponse consultarTipoHerramienta(Integer id) {
        TipoHerramientaEntity entity = tipoHerramientaJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de herramienta no encontrado con ID: " + id));
        return tipoHerramientaMapper.toResponse(entity);
    }

    public List<TipoHerramientaResponse> obtenerTodosLosTiposHerramienta() {
        return tipoHerramientaJpaRepository.findAll().stream()
                .map(tipoHerramientaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TipoHerramientaResponse actualizarTipoHerramienta(Integer id, TipoHerramientaRequest request) {
        TipoHerramientaEntity existente = tipoHerramientaJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de herramienta no encontrado con ID: " + id));

        tipoHerramientaMapper.updateEntityFromRequest(request, existente);
        TipoHerramientaEntity actualizado = tipoHerramientaJpaRepository.save(existente);

        return tipoHerramientaMapper.toResponse(actualizado);
    }

    public void eliminarTipoHerramienta(Integer id) {
        TipoHerramientaEntity existente = tipoHerramientaJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de herramienta no encontrado con ID: " + id));

        existente.setActivo(false);
        tipoHerramientaJpaRepository.save(existente);
    }
}
