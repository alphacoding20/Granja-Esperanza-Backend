package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.RegistroUsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registros-uso")
@RequiredArgsConstructor
public class RegistroUsoController {

    private final RegistroUsoService registroUsoService;

    @PostMapping
    public ResponseEntity<RegistroUsoResponse> crearRegistroUso(@RequestBody @Valid RegistroUsoRequest request) {
        RegistroUsoResponse response = registroUsoService.crearRegistroUso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroUsoResponse> consultarRegistroUso(@PathVariable Integer id) {
        RegistroUsoResponse response = registroUsoService.consultarRegistroUso(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistroUsoResponse>> obtenerTodosLosRegistrosUso() {
        List<RegistroUsoResponse> response = registroUsoService.obtenerTodosLosRegistrosUso();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroUsoResponse> actualizarRegistroUso(
            @PathVariable Integer id,
            @RequestBody @Valid RegistroUsoRequest request) {
        RegistroUsoResponse response = registroUsoService.actualizarRegistroUso(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistroUso(@PathVariable Integer id) {
        registroUsoService.eliminarRegistroUso(id);
        return ResponseEntity.noContent().build();
    }
}