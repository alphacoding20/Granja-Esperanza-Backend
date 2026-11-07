package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImagenNovedadRequest (
        @NotNull(message = "El id de la novedad es obligatorio")
        Integer idNovedad,
        @NotBlank(message = "La url es obligatoria")
        String url
){}
