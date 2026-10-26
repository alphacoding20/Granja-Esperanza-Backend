package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.PrestamoEstudiantilEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;
import jakarta.validation.constraints.NotNull;

public record DetallePrestamoRequest (
        @NotNull(message = "El id del prestamo estudiantil es obligatorio")
        PrestamoEstudiantilEntity idPrestamoEstudiantil,
        @NotNull(message = "El id del tipo de herramienta es obligatorio")
        TipoHerramientaEntity idTipoHerramienta,
        @NotNull(message = "La cantidad es obligatoria")
        Integer cantidad
)
{}
