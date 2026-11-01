package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.TipoHerramientaResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.service.TipoHerramientaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-herramienta")
@RequiredArgsConstructor
public class TipoHerramientaController {

    private final TipoHerramientaService tipoHerramientaService;

    @PostMapping
    public ResponseEntity<TipoHerramientaResponse> crearTipoHerramienta(@RequestBody @Valid TipoHerramientaRequest request) {
        TipoHerramientaResponse response = tipoHerramientaService.crearTipoHerramienta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHerramientaResponse> consultarTipoHerramienta(@PathVariable Integer id) {
        TipoHerramientaResponse response = tipoHerramientaService.consultarTipoHerramienta(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TipoHerramientaResponse>> obtenerTodosLosTiposHerramienta() {
        List<TipoHerramientaResponse> response = tipoHerramientaService.obtenerTodosLosTiposHerramienta();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoHerramientaResponse> actualizarTipoHerramienta(
            @PathVariable Integer id,
            @RequestBody @Valid TipoHerramientaRequest request) {
        TipoHerramientaResponse response = tipoHerramientaService.actualizarTipoHerramienta(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoHerramienta(@PathVariable Integer id) {
        tipoHerramientaService.eliminarTipoHerramienta(id);
        return ResponseEntity.noContent().build();
    }
}