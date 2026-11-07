package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovedadRequest (

        @NotNull(message = "El id del registro es obligatorio")
        Integer idRegistroUso,
        @NotBlank(message = "La descripción es obligatoria")
        String descripcion
){}
