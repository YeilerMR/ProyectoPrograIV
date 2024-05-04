/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

/**
 *
 * @author pucho
 */
public class Proveedor {

    private int idProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String descripcionProveedor;
    private String correo;
    private String direccionProveedor;
    private String categoriaServicio;
    private String informacionAdicional;

    public Proveedor() {
        idProveedor = 0;
        nombreProveedor = "";
        telefonoProveedor = "";
        descripcionProveedor = "";
        correo = "";
        direccionProveedor = "";
        categoriaServicio = "";
        informacionAdicional = "";
    }

    public Proveedor(int idProveedor, String nombreProveedor, String telefonoProveedor, String descripcionProveedor, String correo, String direccionProveedor, String categoriaServicio, String informacionAdicional) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.descripcionProveedor = descripcionProveedor;
        this.correo = correo;
        this.direccionProveedor = direccionProveedor;
        this.categoriaServicio = categoriaServicio;
        this.informacionAdicional = informacionAdicional;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(String categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
}
