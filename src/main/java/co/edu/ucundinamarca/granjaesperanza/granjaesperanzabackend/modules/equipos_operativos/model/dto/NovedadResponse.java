package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.ImagenNovedadEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;

import java.util.List;

public record NovedadResponse (
        Integer id,
        Integer idRegistroUso,
        String descripcion,
        List<ImagenNovedadResponse> imagenes
){}
