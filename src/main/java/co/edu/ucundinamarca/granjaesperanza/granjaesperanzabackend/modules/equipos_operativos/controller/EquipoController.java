package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.EquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.EquipoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @PostMapping
    public ResponseEntity<EquipoResponse> crearEquipo(@RequestBody @Valid EquipoRequest equipoRequest) {
        EquipoResponse response = equipoService.crearEquipo(equipoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> consultarEquipo(@PathVariable Integer id) {
        EquipoResponse response = equipoService.consultarEquipo(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EquipoResponse>> obtenerTodosLosEquipos() {
        List<EquipoResponse> response = equipoService.obtenerTodosLosEquipos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponse> actualizarEquipo(
            @PathVariable Integer id,
            @RequestBody @Valid EquipoRequest equipoRequest) {
        EquipoResponse response = equipoService.actualizarEquipo(id, equipoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Integer id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}