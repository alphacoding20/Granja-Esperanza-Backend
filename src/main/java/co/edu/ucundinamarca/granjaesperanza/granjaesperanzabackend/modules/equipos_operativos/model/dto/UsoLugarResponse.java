package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.LugarTrabajoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;

public record UsoLugarResponse (
        Integer id,
        LugarTrabajoEntity idLugarTrabajo,
        RegistroUsoEntity idRegistroUso
){
}
