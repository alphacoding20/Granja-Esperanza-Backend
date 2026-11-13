package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrabajadorJpaRepository extends JpaRepository<TrabajadorEntity, Integer> {
    Optional<TrabajadorEntity> findByCedula(String cedula);
    List<TrabajadorEntity> findByNombreContainingIgnoreCase(String nombre);
}
