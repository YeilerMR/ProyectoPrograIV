/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;


/**
 *
 * @author kinco
 */
public class Empleado extends Usuario{
    private int idEmpleado;
    
    public Empleado() {
    }
    
    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(int idEmpleado, int id, String nombre, String apellidos, String email, String password, String cedula, String telefono, int credencial) {
        super(id, nombre, apellidos, email, password, cedula, telefono, credencial);
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    
}