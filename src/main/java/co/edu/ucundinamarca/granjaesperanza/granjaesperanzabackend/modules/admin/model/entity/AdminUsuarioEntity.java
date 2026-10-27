package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false, length = 50)
    private String password;
}
