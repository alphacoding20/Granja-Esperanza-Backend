package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugar_trabajo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LugarTrabajoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
}
