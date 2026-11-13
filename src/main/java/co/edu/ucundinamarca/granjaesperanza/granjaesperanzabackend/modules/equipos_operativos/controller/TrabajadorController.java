package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.TrabajadorResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.TrabajadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @PostMapping
    public ResponseEntity<TrabajadorResponse> crearTrabajador(@RequestBody @Valid TrabajadorRequest trabajadorRequest) {
        TrabajadorResponse response = trabajadorService.crearTrabajador(trabajadorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorResponse> buscarTrabajadorPorId(@PathVariable Integer id) {
        TrabajadorResponse response = trabajadorService.buscarTrabajadorPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<TrabajadorResponse> buscarTrabajadorPorCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(trabajadorService.buscarTrabajadorPorCedula(cedula));
    }

    @GetMapping
    public ResponseEntity<List<TrabajadorResponse>> listarOFiltrarTrabajadores(
            @RequestParam(required = false) String nombre) {

        if (nombre != null && !nombre.isBlank()) {
            return ResponseEntity.ok(trabajadorService.buscarTrabajadorPorNombre(nombre));
        }

        return ResponseEntity.ok(trabajadorService.obtenerTodosLosTrabajadores());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabajadorResponse> actualizarTrabajador(
            @PathVariable Integer id,
            @RequestBody @Valid TrabajadorRequest trabajadorRequest) {
        TrabajadorResponse response = trabajadorService.actualizarTrabajador(id, trabajadorRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Integer id) {
        trabajadorService.eliminarTrabajador(id);
        return ResponseEntity.noContent().build();
    }
}
