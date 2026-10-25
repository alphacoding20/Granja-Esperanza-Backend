package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.infraestructure.persistence.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.autenticacion.infraestructure.persistence.AdminUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUsuarioJpaRepository extends JpaRepository<AdminUsuarioEntity, Integer> {
}
