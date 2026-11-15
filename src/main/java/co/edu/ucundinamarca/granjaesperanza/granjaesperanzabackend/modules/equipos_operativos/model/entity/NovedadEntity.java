package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro_uso", referencedColumnName = "id", nullable = false, unique = true)
    private RegistroUsoEntity registroUso;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "novedad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenNovedadEntity> imagenes = new ArrayList<>();
}
