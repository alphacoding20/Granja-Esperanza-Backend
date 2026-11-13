package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.RegistroUsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroUsoJpaRepository extends JpaRepository<RegistroUsoEntity, Integer> {
    List<RegistroUsoEntity> findByFecha(LocalDate fecha);
    List<RegistroUsoEntity> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
