package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.entity.TipoHerramientaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHerramientaJpaRepository extends JpaRepository<TipoHerramientaEntity, Integer> {
}
