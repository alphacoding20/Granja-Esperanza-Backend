package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NovedadMapper {

    private final ImagenNovedadMapper imagenNovedadMapper;

    public NovedadEntity toEntity(NovedadRequest request) {
        if (request == null) {
            return null;
        }

        NovedadEntity entity = new NovedadEntity();
        entity.setDescripcion(request.descripcion());

        if (request.idRegistroUso() != null) {
            RegistroUsoEntity registroUso = new RegistroUsoEntity();
            registroUso.setId(request.idRegistroUso());
            entity.setRegistroUso(registroUso);
        }

        return entity;
    }

    public NovedadResponse toResponse(NovedadEntity entity) {
        if (entity == null) {
            return null;
        }

        List<ImagenNovedadResponse> imagenesResponse = entity.getImagenes() != null
                ? entity.getImagenes().stream()
                .map(imagenNovedadMapper::toResponse)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new NovedadResponse(
                entity.getId(),
                entity.getRegistroUso() != null ? entity.getRegistroUso().getId() : null,
                entity.getDescripcion(),
                imagenesResponse
        );
    }

    public void updateEntityFromRequest(NovedadRequest request, NovedadEntity entity) {
        if (request == null || entity == null) {
            return;
        }

        entity.setDescripcion(request.descripcion());
    }
}