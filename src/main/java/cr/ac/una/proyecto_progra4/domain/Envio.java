/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Aaron
 */
@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Envio")
    private int idEnvio;

    @Column(name = "codigo_envio")
    private String codigoEnvio;

    @ManyToOne
    @JoinColumn(name = "idPedido_Envio", referencedColumnName = "id_Pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idCliente_Envio", referencedColumnName = "id_Cliente")
    private Cliente cliente;

    @Column(name = "fechaEnvio_Envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "observacion_Envio")
    private String observacion;

    @Column(name = "direccionEnvio_Envio")
    private String direccionEnvio;

    @Column(name = "estadoEnvio_Envio")
    private String estadoEnvio;

    public Envio() {
    }

    public Envio(int idEnvio, String codigoEnvio, Pedido pedido, Cliente cliente, LocalDateTime fechaEnvio, String observacion, String direccionEnvio, String estadoEnvio) {
        this.idEnvio = idEnvio;
        this.codigoEnvio = codigoEnvio;
        this.pedido = pedido;
        this.cliente = cliente;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
