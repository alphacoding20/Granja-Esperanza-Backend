package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registro_uso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegistroUsoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador", referencedColumnName = "id", nullable = false)
    private TrabajadorEntity trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", referencedColumnName = "id", nullable = false)
    private EquipoEntity equipo;

    @Column(name = "fecha", nullable = false)
    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "total_horas", nullable = false, precision = 4, scale = 1)
    private BigDecimal totalHoras;

    @Column(name = "novedades", columnDefinition = "TEXT")
    private String novedades;
}