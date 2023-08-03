package com.dico.test.empleado;

import com.dico.test.empleado.dto.EmpleadoDTO;
import com.dico.test.empleado.mapper.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository repository;
    private final EmpleadoMapper mapper;

    private final String EMPLEADO_NOT_FOUND_MESSAGE = "Empleado no existe";

    public EmpleadoDTO create(EmpleadoDTO dto) {
        Empleado entity = mapper.mapToEmpleado(dto);
        repository.save(entity);
        return mapper.mapToEmpleadoDTO(entity);
    }

    public EmpleadoDTO update(EmpleadoDTO dto, Integer id) {
        validateExistence(id);
        return mapper.mapToEmpleadoDTO(updateEmpleadoEntity(dto, id));
    }

    public EmpleadoDTO getById(Integer id) {
        Empleado empleado = getEmpleadoById(id);
        return mapper.mapToEmpleadoDTO(empleado);
    }

    public void deleteById(Integer id) {
        validateExistence(id);
        repository.deleteById(id);
    }

    private Empleado updateEmpleadoEntity(EmpleadoDTO dto, Integer id) {
        dto.setIdEmpleado(id);
        Empleado empleado = mapper.mapToEmpleado(dto);
        return repository.save(empleado);
    }

    private Empleado getEmpleadoById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EMPLEADO_NOT_FOUND_MESSAGE));
    }

    private void validateExistence(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(EMPLEADO_NOT_FOUND_MESSAGE);
        }
    }

}
