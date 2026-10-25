package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.api.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.LugarTrabajoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence.RegistroUsoEntity;

public record UsoLugarResponse (
        Integer id,
        LugarTrabajoEntity idLugarTrabajo,
        RegistroUsoEntity idRegistroUso
){
}
