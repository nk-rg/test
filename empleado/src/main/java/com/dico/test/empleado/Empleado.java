package com.dico.test.empleado;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;
    private String nombre;
    private String apellido;
    private Integer edad;
    private BigDecimal salario;
    private String area;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(idEmpleado, empleado.idEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpleado);
    }
}
