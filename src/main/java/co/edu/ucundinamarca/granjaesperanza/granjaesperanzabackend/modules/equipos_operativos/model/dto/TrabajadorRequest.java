package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record TrabajadorRequest (
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 carácteres")
        String nombre,
        @NotBlank(message = "La cédula es obligatoria")
        @Size(max = 20, message = "La cédula no debe superar los 20 carácteres")
        String cedula
){}
