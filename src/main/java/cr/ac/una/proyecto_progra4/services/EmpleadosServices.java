/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataEmpleado;
import cr.ac.una.proyecto_progra4.domain.Empleado;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author kinco
 */
@Service
public class EmpleadosServices {
     public static LinkedList<Empleado> getEmpleados(){
        LinkedList<Empleado> empleados;
        try {
            empleados = new DataEmpleado().getEmpleados();
        } catch (SQLException ex) {
            empleados = null;
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleados;
    }
    
  public static boolean eliminar(int identificador) {
         return new DataEmpleado().eliminar(identificador); 
    }
    
    public static boolean insertar(Empleado empleado) {
        boolean result = false;
        try {
        new DataEmpleado().insertar(empleado);
        result = true;
        } catch (SQLException ex) {
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        }
        return result;
    }
    
    public static boolean editar(Empleado empleado) {
        try {
            return new DataEmpleado().editar(empleado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
