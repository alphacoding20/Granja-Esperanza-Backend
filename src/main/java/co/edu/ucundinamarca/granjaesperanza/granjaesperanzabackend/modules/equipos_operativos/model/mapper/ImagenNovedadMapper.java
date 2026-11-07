package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.ImagenNovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import org.springframework.stereotype.Component;

@Component
public class ImagenNovedadMapper {

    public ImagenNovedadEntity toEntity(ImagenNovedadRequest request) {
        if (request == null) {
            return null;
        }

        ImagenNovedadEntity entity = new ImagenNovedadEntity();
        entity.setUrl(request.url());

        if (request.idNovedad() != null) {
            NovedadEntity novedad = new NovedadEntity();
            novedad.setId(request.idNovedad());
            entity.setNovedad(novedad);
        }

        return entity;
    }

    public ImagenNovedadResponse toResponse(ImagenNovedadEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ImagenNovedadResponse(
                entity.getId(),
                entity.getNovedad(),
                entity.getUrl()
        );
    }
}