package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.LugarTrabajoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.LugarTrabajoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.LugarTrabajoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lugares-trabajo")
@RequiredArgsConstructor
public class LugarTrabajoController {

    private final LugarTrabajoService lugarTrabajoService;

    @PostMapping
    public ResponseEntity<LugarTrabajoResponse> crearLugarTrabajo(@RequestBody @Valid LugarTrabajoRequest request) {
        LugarTrabajoResponse response = lugarTrabajoService.crearLugarTrabajo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LugarTrabajoResponse> consultarLugarTrabajo(@PathVariable Integer id) {
        LugarTrabajoResponse response = lugarTrabajoService.consultarLugarTrabajo(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LugarTrabajoResponse>> obtenerTodosLosLugaresTrabajo() {
        List<LugarTrabajoResponse> response = lugarTrabajoService.obtenerTodosLosLugaresTrabajo();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LugarTrabajoResponse> actualizarLugarTrabajo(
            @PathVariable Integer id,
            @RequestBody @Valid LugarTrabajoRequest request) {
        LugarTrabajoResponse response = lugarTrabajoService.actualizarLugarTrabajo(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLugarTrabajo(@PathVariable Integer id) {
        lugarTrabajoService.eliminarLugarTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}