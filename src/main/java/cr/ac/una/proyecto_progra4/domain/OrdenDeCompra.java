/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import java.sql.Date;

/**
 *
 * @author Adam Acuña
 */
public class OrdenDeCompra {
    
    private int idPedido;
    private Proveedor idProveedor;
    private Date fechaOrden;
    private Date fechaEntrega;
    private String estadoOrden;
    private String numeroReferencia;

    public OrdenDeCompra() {
        idPedido = 0;
        idProveedor = new Proveedor();
        fechaOrden = new Date(0, 0, 0);
        fechaEntrega = new Date(0, 0, 0);
        numeroReferencia = "";
    }

    public OrdenDeCompra(int idPedido, Proveedor idProveedor, Date fechaOrden, Date fechaEntrega, String estadoOrden, String numeroReferencia) {
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
        this.fechaOrden = fechaOrden;
        this.fechaEntrega = fechaEntrega;
        this.estadoOrden = estadoOrden;
        this.numeroReferencia = numeroReferencia;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
}
