package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_prestamo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetallePrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prestamo_estudiantil", referencedColumnName = "id", nullable = false)
    private PrestamoEstudiantilEntity prestamoEstudiantil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_herramienta", referencedColumnName = "id", nullable = false)
    private TipoHerramientaEntity tipoHerramienta;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
