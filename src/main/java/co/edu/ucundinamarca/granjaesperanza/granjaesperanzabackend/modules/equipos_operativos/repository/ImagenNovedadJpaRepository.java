package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.ImagenNovedadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenNovedadJpaRepository extends JpaRepository<ImagenNovedadEntity, Integer> {
    List<ImagenNovedadEntity> findByNovedadId(Integer idNovedad);
}
