package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoJpaRepository extends JpaRepository<EquipoEntity, Integer> {
    Optional<EquipoEntity> findByPlaca(String placa);
    List<EquipoEntity> findByNombreContainingIgnoreCase(String nombre);
}
