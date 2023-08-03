package com.dico.test.empleado;

import com.dico.test.empleado.dto.EmpleadoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@Valid @RequestBody EmpleadoDTO dto) {
        EmpleadoDTO empleadoDTO = empleadoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empleadoDTO.getIdEmpleado()).toUri();
        return ResponseEntity.created(uri).body(empleadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@Valid @RequestBody EmpleadoDTO dto, @PathVariable Integer id) {
        EmpleadoDTO empleadoDTO = empleadoService.update(dto, id);
        return ResponseEntity.ok(empleadoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Integer id) {
        EmpleadoDTO empleadoDTO = empleadoService.getById(id);
        return ResponseEntity.ok(empleadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        empleadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}