package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;

public record NovedadResponse (
        Integer id,
        RegistroUsoEntity idRegistroUso,
        String descripcion
){}
