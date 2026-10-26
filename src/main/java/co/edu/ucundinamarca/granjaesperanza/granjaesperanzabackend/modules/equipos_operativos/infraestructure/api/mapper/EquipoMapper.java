package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.mapper;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.EquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto.EquipoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.CategoriaEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.EquipoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EquipoMapper {

    public EquipoEntity toEntity(EquipoRequest request) {
        if (request == null) {
            return null;
        }

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(request.idCategoria());

        EquipoEntity entity = new EquipoEntity();
        entity.setCategoria(categoria);
        entity.setNombre(request.nombre());
        entity.setEstado(request.estado());
        entity.setHorasAcumuladas(request.horasAcumuladas());

        return entity;
    }

    public EquipoResponse toResponse(EquipoEntity entity) {
        if (entity == null) {
            return null;
        }

        Integer idCategoria = entity.getCategoria() != null ? entity.getCategoria().getId() : null;

        return new EquipoResponse(
                entity.getId(),
                idCategoria,
                entity.getNombre(),
                entity.getEstado(),
                entity.getHorasAcumuladas(),
                entity.getUmbralMantenimiento()
        );
    }

    public void updateEntityFromRequest(EquipoRequest request, EquipoEntity entity) {
        if (entity == null || request == null) {
            return;
        }

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(request.idCategoria());
        entity.setCategoria(categoria);
        entity.setNombre(request.nombre());
        entity.setEstado(request.estado());
        entity.setHorasAcumuladas(request.horasAcumuladas());
    }
}
