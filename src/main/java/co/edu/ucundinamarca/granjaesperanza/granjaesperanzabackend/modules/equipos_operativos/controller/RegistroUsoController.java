package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.DevolucionEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.PrestamoEquipoRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoManualRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.model.dto.RegistroUsoResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.equipos_operativos.service.RegistroUsoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registros-uso")
@RequiredArgsConstructor
public class RegistroUsoController {

    private final RegistroUsoService registroUsoService;

    @PostMapping
    public ResponseEntity<RegistroUsoResponse> crearRegistroUso(@RequestBody @Valid RegistroUsoManualRequest request) {
        RegistroUsoResponse response = registroUsoService.crearRegistroUso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/automatico/prestamo")
    public ResponseEntity<RegistroUsoResponse> registrarPrestamo(@RequestBody @Valid PrestamoEquipoRequest request) {
        RegistroUsoResponse response = registroUsoService.registrarPrestamo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/automatico/devolucion/{id}")
    public ResponseEntity<RegistroUsoResponse> registrarDevolucion(
            @PathVariable Integer id,
            @RequestBody @Valid DevolucionEquipoRequest request) {
        RegistroUsoResponse response = registroUsoService.registrarDevolucion(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroUsoResponse> consultarRegistroUso(@PathVariable Integer id) {
        RegistroUsoResponse response = registroUsoService.consultarRegistroUso(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistroUsoResponse>> listarOFiltrarRegistros(
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin) {

        if (fecha != null) {
            return ResponseEntity.ok(registroUsoService.buscarPorFecha(fecha));
        }

        if (fechaInicio != null && fechaFin != null) {
            return ResponseEntity.ok(registroUsoService.buscarPorRangoDeFechas(fechaInicio, fechaFin));
        }

        return ResponseEntity.ok(registroUsoService.obtenerTodosLosRegistrosUso());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroUsoResponse> actualizarRegistroUso(
            @PathVariable Integer id,
            @RequestBody @Valid RegistroUsoManualRequest request) {
        RegistroUsoResponse response = registroUsoService.actualizarRegistroUso(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRegistroUso(@PathVariable Integer id) {
        registroUsoService.eliminarRegistroUso(id);
        return ResponseEntity.noContent().build();
    }
}