package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.ImagenNovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.ImagenNovedadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/imagenes-novedad")
@RequiredArgsConstructor
public class ImagenNovedadController {

    private final ImagenNovedadService imagenNovedadService;

    @PostMapping
    public ResponseEntity<ImagenNovedadResponse> crearImagen(@RequestBody @Valid ImagenNovedadRequest request) {
        ImagenNovedadResponse response = imagenNovedadService.crearImagen(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenNovedadResponse> consultarImagen(@PathVariable Integer id) {
        return ResponseEntity.ok(imagenNovedadService.consultarImagen(id));
    }

    @GetMapping("/novedad/{idNovedad}")
    public ResponseEntity<List<ImagenNovedadResponse>> listarPorNovedad(@PathVariable Integer idNovedad) {
        return ResponseEntity.ok(imagenNovedadService.listarPorNovedad(idNovedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenNovedadResponse> actualizarImagen(
            @PathVariable Integer id,
            @RequestBody @Valid ImagenNovedadRequest request) {
        ImagenNovedadResponse response = imagenNovedadService.actualizarImagen(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Integer id) {
        imagenNovedadService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }
}
