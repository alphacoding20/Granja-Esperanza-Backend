package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto;

public record TipoHerramientaResponse(
        Integer id,
        String nombre,
        Integer cantidad,
        Integer disponibles
) {}
