package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.List;

public record PrestamoEstudiantilRequest (
        @NotBlank(message = "La cédula es obligatoria")
        @Size(max = 20, message = "La cédula no debe superar los 20 carácteres")
        String cedula,
        OffsetDateTime fechaPrestamo,
        OffsetDateTime fechaDevolucion,
        List<DetallePrestamoItemRequest> detalles
){
        public record DetallePrestamoItemRequest(
                @NotNull(message = "El id del tipo de herramienta es obligatorio")
                Integer idTipoHerramienta,

                @NotNull(message = "La cantidad es obligatoria")
                Integer cantidad
        ) {}
}
