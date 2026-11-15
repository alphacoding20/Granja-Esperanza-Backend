package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.NovedadResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.NovedadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/novedades")
@RequiredArgsConstructor
public class NovedadController {

    private final NovedadService novedadService;

    @PostMapping
    public ResponseEntity<NovedadResponse> crearNovedad(@RequestBody @Valid NovedadRequest request) {
        NovedadResponse response = novedadService.crearNovedad(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovedadResponse> consultarNovedad(@PathVariable Integer id) {
        return ResponseEntity.ok(novedadService.consultarNovedad(id));
    }

    @GetMapping
    public ResponseEntity<List<NovedadResponse>> listarNovedades() {
        return ResponseEntity.ok(novedadService.obtenerTodasLasNovedades());
    }

    @GetMapping("/registro/{idRegistroUso}")
    public ResponseEntity<NovedadResponse> buscarPorRegistroUso(@PathVariable Integer idRegistroUso) {
        return ResponseEntity.ok(novedadService.buscarPorRegistroUso(idRegistroUso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NovedadResponse> actualizarNovedad(
            @PathVariable Integer id,
            @RequestBody @Valid NovedadRequest request) {
        NovedadResponse response = novedadService.actualizarNovedad(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNovedad(@PathVariable Integer id) {
        novedadService.eliminarNovedad(id);
        return ResponseEntity.noContent().build();
    }
}
