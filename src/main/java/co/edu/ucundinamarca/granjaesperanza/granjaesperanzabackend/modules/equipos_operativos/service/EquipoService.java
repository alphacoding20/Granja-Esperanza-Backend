package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.EquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.EquipoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.CategoriaEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EstadoEquipo;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.EquipoMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.CategoriaJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.EquipoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoJpaRepository equipoJpaRepository;
    private final CategoriaJpaRepository categoriaJpaRepository;
    private final EquipoMapper equipoMapper;

    public EquipoResponse crearEquipo(EquipoRequest equipoRequest) {
        CategoriaEntity categoria = categoriaJpaRepository.findById(equipoRequest.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id " + equipoRequest.idCategoria()));

        EquipoEntity equipo = equipoMapper.toEntity(equipoRequest);
        equipo.setCategoria(categoria);

        if (equipo.getEstado() == null) {
            equipo.setEstado(EstadoEquipo.ACTIVO);
        }
        if (equipo.getHorasAcumuladas() == null) {
            equipo.setHorasAcumuladas(BigDecimal.valueOf(0.0));
        }

        EquipoEntity equipoGuardado = equipoJpaRepository.save(equipo);
        return equipoMapper.toResponse(equipoGuardado);
    }

    public EquipoResponse consultarEquipo(Integer idEquipo) {
        EquipoEntity equipo = equipoJpaRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + idEquipo));
        return equipoMapper.toResponse(equipo);
    }

    public List<EquipoResponse> obtenerTodosLosEquipos() {
        return equipoJpaRepository.findAll().stream()
                .map(equipoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EquipoResponse actualizarEquipo(Integer idEquipo, EquipoRequest equipoRequest) {
        EquipoEntity equipoExistente = equipoJpaRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + idEquipo));

        CategoriaEntity categoria = categoriaJpaRepository.findById(equipoRequest.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + equipoRequest.idCategoria()));

        equipoExistente.setCategoria(categoria);
        equipoExistente.setNombre(equipoRequest.nombre());
        equipoExistente.setEstado(equipoRequest.estado());
        equipoExistente.setHorasAcumuladas(equipoRequest.horasAcumuladas());

        EquipoEntity equipoActualizado = equipoJpaRepository.save(equipoExistente);
        return equipoMapper.toResponse(equipoActualizado);
    }

    public void eliminarEquipo(Integer idEquipo) {
        EquipoEntity equipoExistente = equipoJpaRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + idEquipo));

        equipoExistente.setEstado(EstadoEquipo.INACTIVO);
        equipoJpaRepository.save(equipoExistente);
    }
}
