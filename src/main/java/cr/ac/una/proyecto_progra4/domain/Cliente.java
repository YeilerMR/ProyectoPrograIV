/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

/**
 *
 * @author Aaron
 */
public class Cliente extends Usuario {

    private int idCliente;

    public Cliente() {
    }

    public Cliente(int idCliente, int id, String nombre, String apellidos, String email, String password, String cedula, String telefono, int credencial) {
        super(id, nombre, apellidos, email, password, cedula, telefono, credencial);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
