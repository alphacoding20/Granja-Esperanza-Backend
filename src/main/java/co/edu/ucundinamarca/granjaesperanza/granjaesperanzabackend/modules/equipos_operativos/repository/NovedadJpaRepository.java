package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.MantenimientoEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.NovedadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovedadJpaRepository extends JpaRepository<NovedadEntity, Integer> {
    Optional<NovedadEntity> findByRegistroUsoId(Integer idRegistroUso);
    boolean existsByRegistroUsoId(Integer idRegistroUso);
}