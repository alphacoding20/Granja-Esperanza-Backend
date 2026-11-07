package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagen_novedad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenNovedadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_novedad", referencedColumnName = "id", nullable = false)
    private NovedadEntity novedad;

    @Column(name = "url",  nullable = false, columnDefinition = "TEXT")
    private String url;
}
