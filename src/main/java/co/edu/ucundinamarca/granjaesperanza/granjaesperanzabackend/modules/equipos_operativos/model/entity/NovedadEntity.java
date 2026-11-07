package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "novedad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovedadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro_uso", referencedColumnName = "id", nullable = false)
    private RegistroUsoEntity registroUso;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
}
