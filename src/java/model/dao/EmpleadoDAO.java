/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Empleado;

/**
 *
 * @author creanogales
 */
public interface EmpleadoDAO {
    void insertar(Empleado empleado);
    void eliminar(Integer empleado);
    void update(Empleado empleado,Integer idempleado);
    Empleado getEmpleado(Integer idempleado);
    List<Empleado> getAll();
    
}
