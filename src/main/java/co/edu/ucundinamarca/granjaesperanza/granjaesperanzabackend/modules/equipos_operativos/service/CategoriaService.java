package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.CategoriaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.CategoriaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.entity.CategoriaEntity;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.mapper.CategoriaMapper;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.repository.CategoriaJpaRepository;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.shared.exception.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaJpaRepository categoriaJpaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaResponse crearCategoria(CategoriaRequest request) {
        CategoriaEntity categoria = categoriaMapper.toEntity(request);
        CategoriaEntity guardada = categoriaJpaRepository.save(categoria);
        return categoriaMapper.toResponse(guardada);
    }

    public CategoriaResponse consultarCategoria(Integer id) {
        CategoriaEntity categoria = categoriaJpaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + id));
        return categoriaMapper.toResponse(categoria);
    }

    public List<CategoriaResponse> obtenerTodasLasCategorias() {
        return categoriaJpaRepository.findAll().stream()
                .filter(CategoriaEntity::getActivo)
                .map(categoriaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaResponse actualizarCategoria(Integer id, CategoriaRequest request) {
        CategoriaEntity existente = categoriaJpaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + id));

        categoriaMapper.updateEntityFromRequest(request, existente);

        CategoriaEntity actualizada = categoriaJpaRepository.save(existente);
        return categoriaMapper.toResponse(actualizada);
    }

    public void eliminarCategoria(Integer id) {
        CategoriaEntity existente = categoriaJpaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + id));
        existente.setActivo(false);
        categoriaJpaRepository.save(existente);
    }
}
