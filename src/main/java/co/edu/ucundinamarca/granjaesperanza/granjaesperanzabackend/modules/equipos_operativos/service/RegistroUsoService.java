package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.DevolucionEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.PrestamoEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoManualRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.*;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.RegistroUsoMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
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
    public RegistroUsoResponse crearRegistroUso(RegistroUsoManualRequest request) {
        TrabajadorEntity trabajador = trabajadorJpaRepository.findById(request.idTrabajador())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + request.idTrabajador()));

        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        RegistroUsoEntity entity = registroUsoMapper.toEntity(request);
        entity.setTrabajador(trabajador);
        entity.setEquipo(equipo);

        calcularHoras(entity);

        acumularHorasEnEquipo(equipo, entity.getTotalHoras());

        equipoJpaRepository.save(equipo);
        RegistroUsoEntity guardado = registroUsoJpaRepository.save(entity);

        return registroUsoMapper.toResponse(guardado);
    }

    @Transactional
    public RegistroUsoResponse registrarPrestamo(PrestamoEquipoRequest request) {
        TrabajadorEntity trabajador = trabajadorJpaRepository.findById(request.idTrabajador())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + request.idTrabajador()));

        EquipoEntity equipo = equipoJpaRepository.findById(request.idEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + request.idEquipo()));

        if (equipo.getDisponible() != null && !equipo.getDisponible()) {
            throw new IllegalStateException("El equipo ya se encuentra en préstamo actualmente.");
        }

        RegistroUsoEntity entity = registroUsoMapper.toEntityFromPrestamo(request);
        entity.setTrabajador(trabajador);
        entity.setEquipo(equipo);

        equipo.setDisponible(false);
        equipoJpaRepository.save(equipo);

        RegistroUsoEntity guardado = registroUsoJpaRepository.save(entity);

        return registroUsoMapper.toResponse(guardado);
    }

    @Transactional
    public RegistroUsoResponse registrarDevolucion(Integer idRegistro, DevolucionEquipoRequest request) {
        RegistroUsoEntity registro = registroUsoJpaRepository.findById(idRegistro)
                .orElseThrow(() -> new RuntimeException("Registro de uso no encontrado con ID: " + idRegistro));

        if (registro.getHoraFin() != null) {
            throw new IllegalStateException("Este registro de uso ya fue finalizado y devuelto anteriormente.");
        }

        registroUsoMapper.updateEntityFromDevolucion(request, registro);

        calcularHoras(registro);

        EquipoEntity equipo = registro.getEquipo();

        acumularHorasEnEquipo(equipo, registro.getTotalHoras());

        if (equipo != null) {
            equipo.setDisponible(true);
            equipoJpaRepository.save(equipo);
        }

        RegistroUsoEntity actualizado = registroUsoJpaRepository.save(registro);

        return registroUsoMapper.toResponse(actualizado);
    }

    private void calcularHoras(RegistroUsoEntity registro) {
        BigDecimal horasInactivo = registro.getHorasInactivo() != null
                ? registro.getHorasInactivo()
                : BigDecimal.ZERO;
        registro.setHorasInactivo(horasInactivo);

        long minutosTotales = Duration.between(registro.getHoraInicio(), registro.getHoraFin()).toMinutes();
        BigDecimal horasBrutas = BigDecimal.valueOf(minutosTotales)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        BigDecimal totalHoras = horasBrutas.subtract(horasInactivo);

        if (totalHoras.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Las horas inactivas no pueden ser mayores que el tiempo total transcurrido.");
        }
        registro.setTotalHoras(totalHoras);
    }

    private void acumularHorasEnEquipo(EquipoEntity equipo, BigDecimal totalHoras) {
        if (equipo != null && totalHoras != null) {
            BigDecimal horasAcumuladasActuales = equipo.getHorasAcumuladas() != null ? equipo.getHorasAcumuladas() : BigDecimal.ZERO;
            BigDecimal horasTotalesActuales = equipo.getHorasTotales() != null ? equipo.getHorasTotales() : BigDecimal.ZERO;

            equipo.setHorasAcumuladas(horasAcumuladasActuales.add(totalHoras));
            equipo.setHorasTotales(horasTotalesActuales.add(totalHoras));
        }
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

    public List<RegistroUsoResponse> buscarPorFecha(LocalDate fecha) {
        List<RegistroUsoEntity> registros = registroUsoJpaRepository.findByFecha(fecha);

        return registros.stream()
                .map(registroUsoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<RegistroUsoResponse> buscarPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<RegistroUsoEntity> registros = registroUsoJpaRepository.findByFechaBetween(fechaInicio, fechaFin);

        return registros.stream()
                .map(registroUsoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RegistroUsoResponse actualizarRegistroUso(Integer id, RegistroUsoManualRequest request) {
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

        EquipoEntity equipo = existente.getEquipo();

        if (equipo != null) {
            BigDecimal horasAcumuladasActuales = equipo.getHorasAcumuladas() != null ? equipo.getHorasAcumuladas() : BigDecimal.ZERO;
            BigDecimal horasTotalesActuales = equipo.getHorasTotales() != null ? equipo.getHorasTotales() : BigDecimal.ZERO;

            BigDecimal horasADescontar = existente.getTotalHoras() != null ? existente.getTotalHoras() : BigDecimal.ZERO;

            BigDecimal nuevasHorasAcumuladas = horasAcumuladasActuales.subtract(horasADescontar);
            BigDecimal nuevasHorasTotales = horasTotalesActuales.subtract(horasADescontar);

            equipo.setHorasAcumuladas(nuevasHorasAcumuladas.compareTo(BigDecimal.ZERO) >= 0 ? nuevasHorasAcumuladas : BigDecimal.ZERO);
            equipo.setHorasTotales(nuevasHorasTotales.compareTo(BigDecimal.ZERO) >= 0 ? nuevasHorasTotales : BigDecimal.ZERO);

            equipoJpaRepository.save(equipo);
        }

        registroUsoJpaRepository.delete(existente);
    }
}