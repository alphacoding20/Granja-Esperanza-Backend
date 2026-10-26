package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LugarTrabajoRequest (
        @NotBlank(message = "El nombre es obligatorio")
        String nombre
) {}
