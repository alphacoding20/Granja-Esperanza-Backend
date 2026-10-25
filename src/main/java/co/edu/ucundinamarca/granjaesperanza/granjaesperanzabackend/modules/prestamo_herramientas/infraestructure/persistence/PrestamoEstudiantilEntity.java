package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.infraestructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "prestamo_estudiantil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PrestamoEstudiantilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cedula", nullable = false, length = 20, unique = true)
    private String cedula;

    @Column(name = "fecha_prestamo", nullable = false)
    @Builder.Default
    private OffsetDateTime fechaPrestamo = OffsetDateTime.now();

    @Column(name = "fecha_devolucion")
    private OffsetDateTime fechaDevolucion;
}
