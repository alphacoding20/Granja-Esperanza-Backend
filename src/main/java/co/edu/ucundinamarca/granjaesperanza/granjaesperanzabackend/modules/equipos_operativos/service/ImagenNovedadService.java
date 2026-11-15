package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.ImagenNovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.ImagenNovedadMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.ImagenNovedadJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.NovedadJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagenNovedadService {

    private final ImagenNovedadJpaRepository imagenNovedadJpaRepository;
    private final NovedadJpaRepository novedadJpaRepository;
    private final ImagenNovedadMapper imagenNovedadMapper;

    @Transactional
    public ImagenNovedadResponse crearImagen(ImagenNovedadRequest request) {
        NovedadEntity novedad = novedadJpaRepository.findById(request.idNovedad())
                .orElseThrow(() -> new RuntimeException("Novedad no encontrada con ID: " + request.idNovedad()));

        ImagenNovedadEntity entity = imagenNovedadMapper.toEntity(request);
        entity.setNovedad(novedad);

        ImagenNovedadEntity guardada = imagenNovedadJpaRepository.save(entity);
        return imagenNovedadMapper.toResponse(guardada);
    }

    public ImagenNovedadResponse consultarImagen(Integer id) {
        ImagenNovedadEntity entity = imagenNovedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen de novedad no encontrada con ID: " + id));
        return imagenNovedadMapper.toResponse(entity);
    }

    public List<ImagenNovedadResponse> listarPorNovedad(Integer idNovedad) {
        return imagenNovedadJpaRepository.findByNovedadId(idNovedad).stream()
                .map(imagenNovedadMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ImagenNovedadResponse actualizarImagen(Integer id, ImagenNovedadRequest request) {
        ImagenNovedadEntity existente = imagenNovedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen de novedad no encontrada con ID: " + id));

        if (!existente.getNovedad().getId().equals(request.idNovedad())) {
            NovedadEntity nuevaNovedad = novedadJpaRepository.findById(request.idNovedad())
                    .orElseThrow(() -> new RuntimeException("Novedad no encontrada con ID: " + request.idNovedad()));
            existente.setNovedad(nuevaNovedad);
        }

        imagenNovedadMapper.updateEntityFromRequest(request, existente);

        ImagenNovedadEntity actualizada = imagenNovedadJpaRepository.save(existente);
        return imagenNovedadMapper.toResponse(actualizada);
    }

    @Transactional
    public void eliminarImagen(Integer id) {
        ImagenNovedadEntity existente = imagenNovedadJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen de novedad no encontrada con ID: " + id));
        imagenNovedadJpaRepository.delete(existente);
    }
}
