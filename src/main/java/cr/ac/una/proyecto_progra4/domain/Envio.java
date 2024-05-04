/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import java.time.LocalDateTime;

/**
 *
 * @author Aaron
 */
public class Envio {

    private int idEnvio;
    private String codigoEnvio;
    private int idPedido;
    private int idCliente;
    private LocalDateTime fechaEnvio;
    private String observacion;
    private String direccionEnvio;
    private String estadoEnvio;

    public Envio() {
    }

    public Envio(int idEnvio, String codigoEnvio, int idPedido, int idCliente, LocalDateTime fechaEnvio, String observacion, String direccionEnvio, String estadoEnvio) {
        this.idEnvio = idEnvio;
        this.codigoEnvio = codigoEnvio;
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaEnvio = fechaEnvio;
        this.observacion = observacion;
        this.direccionEnvio = direccionEnvio;
        this.estadoEnvio = estadoEnvio;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public void setCodigoEnvio(String codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    
}
