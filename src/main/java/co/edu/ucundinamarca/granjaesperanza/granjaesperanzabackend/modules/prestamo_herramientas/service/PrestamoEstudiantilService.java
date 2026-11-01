package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.DetallePrestamoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.PrestamoEstudiantilEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.mapper.PrestamoEstudiantilMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.repository.DetallePrestamoJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.repository.PrestamoEstudiantilJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.repository.TipoHerramientaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrestamoEstudiantilService {

    private final PrestamoEstudiantilJpaRepository prestamoEstudiantilJpaRepository;
    private final TipoHerramientaJpaRepository tipoHerramientaJpaRepository;
    private final DetallePrestamoJpaRepository detallePrestamoJpaRepository;
    private final PrestamoEstudiantilMapper prestamoEstudiantilMapper;

    @Transactional
    public PrestamoEstudiantilResponse crearPrestamoEstudiantil(PrestamoEstudiantilRequest request) {
        PrestamoEstudiantilEntity entity = prestamoEstudiantilMapper.toEntity(request);

        if (entity.getFechaPrestamo() == null) {
            entity.setFechaPrestamo(OffsetDateTime.now());
        }

        PrestamoEstudiantilEntity guardado = prestamoEstudiantilJpaRepository.save(entity);

        if (request.detalles() != null && !request.detalles().isEmpty()) {
            for (PrestamoEstudiantilRequest.DetallePrestamoItemRequest item : request.detalles()) {
                TipoHerramientaEntity tipoHerramienta = tipoHerramientaJpaRepository.findById(item.idTipoHerramienta())
                        .orElseThrow(() -> new RuntimeException("Tipo de herramienta no encontrado con ID: " + item.idTipoHerramienta()));

                DetallePrestamoEntity detalle = new DetallePrestamoEntity();
                detalle.setPrestamoEstudiantil(guardado);
                detalle.setTipoHerramienta(tipoHerramienta);
                detalle.setCantidad(item.cantidad());

                detallePrestamoJpaRepository.save(detalle);
            }
        }

        return prestamoEstudiantilMapper.toResponse(guardado);
    }

    public PrestamoEstudiantilResponse consultarPrestamoEstudiantil(Integer id) {
        PrestamoEstudiantilEntity entity = prestamoEstudiantilJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo estudiantil no encontrado con ID: " + id));
        return prestamoEstudiantilMapper.toResponse(entity);
    }

    public List<PrestamoEstudiantilResponse> obtenerTodosLosPrestamosEstudiantiles() {
        return prestamoEstudiantilJpaRepository.findAll().stream()
                .map(prestamoEstudiantilMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrestamoEstudiantilResponse actualizarPrestamoEstudiantil(Integer id, PrestamoEstudiantilRequest request) {
        PrestamoEstudiantilEntity existente = prestamoEstudiantilJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo estudiantil no encontrado con ID: " + id));

        prestamoEstudiantilMapper.updateEntityFromRequest(request, existente);
        PrestamoEstudiantilEntity actualizado = prestamoEstudiantilJpaRepository.save(existente);

        detallePrestamoJpaRepository.deleteByPrestamoEstudiantilId(id);

        if (request.detalles() != null && !request.detalles().isEmpty()) {
            for (PrestamoEstudiantilRequest.DetallePrestamoItemRequest item : request.detalles()) {
                TipoHerramientaEntity tipoHerramienta = tipoHerramientaJpaRepository.findById(item.idTipoHerramienta())
                        .orElseThrow(() -> new RuntimeException("Tipo de herramienta no encontrado con ID: " + item.idTipoHerramienta()));

                DetallePrestamoEntity detalle = new DetallePrestamoEntity();
                detalle.setPrestamoEstudiantil(actualizado);
                detalle.setTipoHerramienta(tipoHerramienta);
                detalle.setCantidad(item.cantidad());

                detallePrestamoJpaRepository.save(detalle);
            }
        }

        return prestamoEstudiantilMapper.toResponse(actualizado);
    }

    @Transactional
    public void eliminarPrestamoEstudiantil(Integer id) {
        PrestamoEstudiantilEntity existente = prestamoEstudiantilJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo estudiantil no encontrado con ID: " + id));

        detallePrestamoJpaRepository.deleteByPrestamoEstudiantilId(id);
        prestamoEstudiantilJpaRepository.delete(existente);
    }
}
