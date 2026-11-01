package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.dto.AdminUsuarioRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.dto.AdminUsuarioResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.entity.AdminUsuarioEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.mapper.AdminUsuarioMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.repository.AdminUsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUsuarioService {

    private final AdminUsuarioJpaRepository adminUsuarioJpaRepository;
    private final AdminUsuarioMapper adminUsuarioMapper;

    public AdminUsuarioResponse crearAdminUsuario(AdminUsuarioRequest request) {
        AdminUsuarioEntity entity = adminUsuarioMapper.toEntity(request);
        AdminUsuarioEntity guardado = adminUsuarioJpaRepository.save(entity);
        return adminUsuarioMapper.toResponse(guardado);
    }

    public AdminUsuarioResponse consultarAdminUsuario(Integer id) {
        AdminUsuarioEntity entity = adminUsuarioJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
        return adminUsuarioMapper.toResponse(entity);
    }

    public List<AdminUsuarioResponse> obtenerTodosLosAdminUsuarios() {
        return adminUsuarioJpaRepository.findAll().stream()
                .map(adminUsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AdminUsuarioResponse actualizarAdminUsuario(Integer id, AdminUsuarioRequest request) {
        AdminUsuarioEntity existente = adminUsuarioJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));

        adminUsuarioMapper.updateEntityFromRequest(request, existente);
        AdminUsuarioEntity actualizado = adminUsuarioJpaRepository.save(existente);

        return adminUsuarioMapper.toResponse(actualizado);
    }

    public void eliminarAdminUsuario(Integer id) {
        AdminUsuarioEntity existente = adminUsuarioJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
        adminUsuarioJpaRepository.delete(existente);
    }

    public AdminUsuarioResponse login(AdminUsuarioRequest request) {
        AdminUsuarioEntity admin = adminUsuarioJpaRepository.findByUsuario(request.usuario())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas: Usuario no encontrado"));

        if (!admin.getPassword().equals(request.password())) {
            throw new RuntimeException("Credenciales inválidas: Contraseña incorrecta");
        }

        return adminUsuarioMapper.toResponse(admin);
    }
}
