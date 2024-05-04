/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

/**
 *
 * @author Aaron
 */
public class Usuario {

    private int id;

    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String cedula;
    private String telefono;
    private int credencial;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos, String email, String password, String cedula, String telefono, int credencial) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.cedula = cedula;
        this.telefono = telefono;
        this.credencial = credencial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCredencial() {
        return credencial;
    }

    public void setCredencial(int credencial) {
        this.credencial = credencial;
    }

}
