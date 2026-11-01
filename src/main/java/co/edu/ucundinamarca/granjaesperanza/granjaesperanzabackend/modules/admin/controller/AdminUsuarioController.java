package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.controller;

import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.dto.AdminUsuarioRequest;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.model.dto.AdminUsuarioResponse;
import co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.modules.admin.service.AdminUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminUsuarioController {

    private final AdminUsuarioService adminUsuarioService;

    @PostMapping("/login")
    public ResponseEntity<AdminUsuarioResponse> login(@RequestBody @Valid AdminUsuarioRequest request) {
        AdminUsuarioResponse response = adminUsuarioService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AdminUsuarioResponse> crearAdminUsuario(@RequestBody @Valid AdminUsuarioRequest request) {
        AdminUsuarioResponse response = adminUsuarioService.crearAdminUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminUsuarioResponse> consultarAdminUsuario(@PathVariable Integer id) {
        AdminUsuarioResponse response = adminUsuarioService.consultarAdminUsuario(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AdminUsuarioResponse>> obtenerTodosLosAdminUsuarios() {
        List<AdminUsuarioResponse> response = adminUsuarioService.obtenerTodosLosAdminUsuarios();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminUsuarioResponse> actualizarAdminUsuario(
            @PathVariable Integer id,
            @RequestBody @Valid AdminUsuarioRequest request) {
        AdminUsuarioResponse response = adminUsuarioService.actualizarAdminUsuario(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdminUsuario(@PathVariable Integer id) {
        adminUsuarioService.eliminarAdminUsuario(id);
        return ResponseEntity.noContent().build();
    }
}