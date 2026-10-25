package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.api.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.persistence.PrestamoEstudiantilEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.persistence.TipoHerramientaEntity;

public record DetallePrestamoResponse (
        Integer id,
        PrestamoEstudiantilEntity idPrestamoEstudiantil,
        TipoHerramientaEntity idTipoHerramienta,
        Integer cantidad
){}
