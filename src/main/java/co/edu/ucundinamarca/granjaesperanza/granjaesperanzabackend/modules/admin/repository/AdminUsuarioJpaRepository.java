package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.repository;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.entity.AdminUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUsuarioJpaRepository extends JpaRepository<AdminUsuarioEntity, Integer> {

    Optional<AdminUsuarioEntity> findByUsuario(String usuario);
}
