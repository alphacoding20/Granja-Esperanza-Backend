package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.model.dto.PrestamoEstudiantilResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.prestamo_herramientas.service.PrestamoEstudiantilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamos-estudiantiles")
@RequiredArgsConstructor
public class PrestamoEstudiantilController {

    private final PrestamoEstudiantilService prestamoEstudiantilService;

    @PostMapping
    public ResponseEntity<PrestamoEstudiantilResponse> crearPrestamoEstudiantil(@RequestBody @Valid PrestamoEstudiantilRequest request) {
        PrestamoEstudiantilResponse response = prestamoEstudiantilService.crearPrestamoEstudiantil(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoEstudiantilResponse> consultarPrestamoEstudiantil(@PathVariable Integer id) {
        PrestamoEstudiantilResponse response = prestamoEstudiantilService.consultarPrestamoEstudiantil(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoEstudiantilResponse>> obtenerTodosLosPrestamosEstudiantiles() {
        List<PrestamoEstudiantilResponse> response = prestamoEstudiantilService.obtenerTodosLosPrestamosEstudiantiles();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoEstudiantilResponse> actualizarPrestamoEstudiantil(
            @PathVariable Integer id,
            @RequestBody @Valid PrestamoEstudiantilRequest request) {
        PrestamoEstudiantilResponse response = prestamoEstudiantilService.actualizarPrestamoEstudiantil(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamoEstudiantil(@PathVariable Integer id) {
        prestamoEstudiantilService.eliminarPrestamoEstudiantil(id);
        return ResponseEntity.noContent().build();
    }
}
