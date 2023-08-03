package com.dico.test.empleado.mapper;

import com.dico.test.empleado.Empleado;
import com.dico.test.empleado.dto.EmpleadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    Empleado mapToEmpleado(EmpleadoDTO dto);
    Empleado mergeEmpleados(EmpleadoDTO source, @MappingTarget Empleado destination);
    EmpleadoDTO mapToEmpleadoDTO(Empleado entity);
}
