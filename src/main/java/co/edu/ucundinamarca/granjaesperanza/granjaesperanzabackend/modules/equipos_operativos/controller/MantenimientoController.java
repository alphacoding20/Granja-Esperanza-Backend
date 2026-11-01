package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.MantenimientoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.MantenimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<MantenimientoResponse> crearMantenimiento(@RequestBody @Valid MantenimientoRequest request) {
        MantenimientoResponse response = mantenimientoService.crearMantenimiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoResponse> consultarMantenimiento(@PathVariable Integer id) {
        MantenimientoResponse response = mantenimientoService.consultarMantenimiento(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoResponse>> listarMantenimientos() {
        List<MantenimientoResponse> response = mantenimientoService.obtenerTodosLosMantenimientos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoResponse> actualizarMantenimiento(
            @PathVariable Integer id,
            @RequestBody @Valid MantenimientoRequest request) {
        MantenimientoResponse response = mantenimientoService.actualizarMantenimiento(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable Integer id) {
        mantenimientoService.eliminarMantenimiento(id);
        return ResponseEntity.noContent().build();
    }
}
