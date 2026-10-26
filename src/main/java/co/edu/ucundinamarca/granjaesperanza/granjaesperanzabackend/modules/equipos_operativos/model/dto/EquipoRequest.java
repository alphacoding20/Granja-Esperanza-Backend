package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EstadoEquipo;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record EquipoRequest(
        @NotNull(message = "El id de la categoría no puede estar vacío")
        Integer idCategoria,
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no debe superar los 100 carácteres")
        String nombre,
        @NotBlank(message = "El estado del equipo no puede estar vacío")
        @Size(max = 50, message = "El estado no debe superar los 50 carácteres")
        EstadoEquipo estado,
        @Digits(integer = 7, fraction = 1, message = "El número puede tener máximo 7 digitos enteros y 1 decimal")
        BigDecimal horasAcumuladas
) {}
