package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.model.entity.AdminUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUsuarioJpaRepository extends JpaRepository<AdminUsuarioEntity, Integer> {
}
