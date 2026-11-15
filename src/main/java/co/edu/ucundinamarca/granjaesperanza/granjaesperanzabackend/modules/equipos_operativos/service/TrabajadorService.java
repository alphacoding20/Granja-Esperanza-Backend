package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;


import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.TrabajadorMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.TrabajadorJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.shared.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrabajadorService {

    private final TrabajadorJpaRepository trabajadorJpaRepository;
    private final TrabajadorMapper trabajadorMapper;

    public TrabajadorResponse crearTrabajador(TrabajadorRequest trabajadorRequest) {
        TrabajadorEntity trabajador = trabajadorMapper.toEntity(trabajadorRequest);
        trabajador.setActivo(true);
        TrabajadorEntity trabajadorGuardado = trabajadorJpaRepository.save(trabajador);
        return trabajadorMapper.toResponse(trabajadorGuardado);
    }

    public TrabajadorResponse buscarTrabajadorPorId(Integer idTrabajador) {
        TrabajadorEntity trabajador = trabajadorJpaRepository.findById(idTrabajador)
                .orElseThrow(() -> new RecursoNoEncontradoException("Trabajador no encontrado"));
        return trabajadorMapper.toResponse(trabajador);
    }

    public TrabajadorResponse buscarTrabajadorPorCedula(String cedula) {
        TrabajadorEntity trabajador = trabajadorJpaRepository.findByCedula(cedula)
                .orElseThrow(() -> new RecursoNoEncontradoException("Trabajador no encontrado con la cédula: " + cedula));

        return trabajadorMapper.toResponse(trabajador);
    }

    public List<TrabajadorResponse> buscarTrabajadorPorNombre(String nombre) {
        List<TrabajadorEntity> trabajadores = trabajadorJpaRepository.findByNombreContainingIgnoreCase(nombre);

        return trabajadores.stream()
                .map(trabajadorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TrabajadorResponse> obtenerTodosLosTrabajadores() {
        return trabajadorJpaRepository.findAll().stream()
                .map(trabajadorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TrabajadorResponse actualizarTrabajador(Integer idTrabajador, TrabajadorRequest trabajadorRequest) {
        TrabajadorEntity trabajadorExistente = trabajadorJpaRepository.findById(idTrabajador)
                .orElseThrow(() -> new RecursoNoEncontradoException("Trabajador no encontrado"));

        trabajadorMapper.updateEntityFromRequest(trabajadorRequest, trabajadorExistente);

        TrabajadorEntity trabajadorActualizado = trabajadorJpaRepository.save(trabajadorExistente);
        return trabajadorMapper.toResponse(trabajadorActualizado);
    }

    public void eliminarTrabajador(Integer idTrabajador) {
        TrabajadorEntity trabajadorExistente = trabajadorJpaRepository.findById(idTrabajador)
                .orElseThrow(() -> new RecursoNoEncontradoException("Trabajador no encontrado"));

        trabajadorExistente.setActivo(false);
        trabajadorJpaRepository.save(trabajadorExistente);
    }
}
