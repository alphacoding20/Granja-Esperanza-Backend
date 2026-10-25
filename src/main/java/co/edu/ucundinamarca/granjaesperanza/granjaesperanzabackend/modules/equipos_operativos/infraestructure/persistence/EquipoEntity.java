package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.infraestructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "equipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    private CategoriaEntity categoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 50)
    @Builder.Default
    private EstadoEquipo estado = EstadoEquipo.ACTIVO;

    @Column(name = "horas_acumuladas", precision = 7, scale = 1)
    @Builder.Default
    private BigDecimal horasAcumuladas = BigDecimal.valueOf(0.0);

    @Column(name = "umbral_mantenimiento", precision = 7, scale = 1)
    private BigDecimal umbralMantenimiento;
}
