package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_herramienta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TipoHerramientaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "disponibles", nullable = false)
    private Integer disponibles;
}
