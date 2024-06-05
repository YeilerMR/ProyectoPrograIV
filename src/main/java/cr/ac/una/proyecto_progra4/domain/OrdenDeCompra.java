/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Date;

/**
 *
 * @author Adam Acuña
 */
@Entity
@Table(name = "ordendecompra")
public class OrdenDeCompra {
    
    private Pedido idPedido;
    @OneToMany
    @JoinColumn(name = "idProveedor_OrdenDeCompra", nullable = false)
    private Proveedor idProveedor;
    private Date fechaOrden;
    private Date fechaEntrega;
    private String estadoOrden;
    @Id
    private String numeroReferencia;

    public OrdenDeCompra() {
        idPedido = new Pedido();
        idProveedor = new Proveedor();
        fechaOrden = new Date(0, 0, 0);
        fechaEntrega = new Date(0, 0, 0);
        numeroReferencia = "";
    }

    public OrdenDeCompra(Pedido idPedido, Proveedor idProveedor, Date fechaOrden, Date fechaEntrega, String estadoOrden, String numeroReferencia) {
        this.idPedido = idPedido;
        this.idProveedor = idProveedor;
        this.fechaOrden = fechaOrden;
        this.fechaEntrega = fechaEntrega;
        this.estadoOrden = estadoOrden;
        this.numeroReferencia = numeroReferencia;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
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
