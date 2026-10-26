package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.PrestamoEstudiantilEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;

public record DetallePrestamoResponse (
        Integer id,
        PrestamoEstudiantilEntity idPrestamoEstudiantil,
        TipoHerramientaEntity idTipoHerramienta,
        Integer cantidad
){}
