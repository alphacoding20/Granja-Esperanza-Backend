package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record PrestamoEstudiantilRequest (
        @NotBlank(message = "La cédula es obligatoria")
        @Size(max = 20, message = "La cédula no debe superar los 20 carácteres")
        String cedula,
        OffsetDateTime fechaPrestamo,
        OffsetDateTime fechaDevolucion
){}
