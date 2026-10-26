package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.LugarTrabajoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import jakarta.validation.constraints.NotNull;

public record UsoLugarRequest (
        @NotNull(message = "El id del lugar de trabajo es obligatorio")
        LugarTrabajoEntity idLugarTrabajo,
        @NotNull(message = "El id del registro de uso es obligatorio")
        RegistroUsoEntity idRegistroUso
){}
