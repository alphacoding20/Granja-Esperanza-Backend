package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TipoHerramientaRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 carácteres")
        String nombre,
        @NotNull(message = "La cantidad es obligatoria")
        Integer cantidad,
        @NotNull(message = "El numero de herramientas disponibles es obligatorio")
        Integer disponibles
) {}
