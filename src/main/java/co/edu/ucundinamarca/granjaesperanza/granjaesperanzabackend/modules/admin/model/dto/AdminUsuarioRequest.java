package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminUsuarioRequest (
       @NotBlank(message = "El usuario es obligatorio")
       @Size(max = 50, message = "El usuario no debe superar los 50 carácteres")
       String usuario,
       @NotBlank(message = "La contraseña es obligatoria")
       @Size(max = 50, message = "La contraseña no debe superar los 50 carácteres")
       String password
)
{}
