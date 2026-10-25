package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto;

import java.time.OffsetDateTime;

public record PrestamoEstudiantilResponse (
        Integer id,
        String cedula,
        OffsetDateTime fechaPrestamo,
        OffsetDateTime fechaDevolucion
){}
