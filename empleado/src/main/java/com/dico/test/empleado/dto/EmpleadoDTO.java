package com.dico.test.empleado.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class EmpleadoDTO {
    private Integer idEmpleado;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @Min(value = 18, message = "El empleado debe ser mayor de edad")
    @Max(value = 70, message = "El empleado debe tener una edad menor a 70")
    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @NotNull(message = "El salario es requerido")
    @DecimalMin(value = "1025", message = "El salario debe ser superior o igual al salario minimo de Perú")
    private BigDecimal salario;

    @NotBlank(message = "El area de trabajo es requerido")
    private String area;
}
