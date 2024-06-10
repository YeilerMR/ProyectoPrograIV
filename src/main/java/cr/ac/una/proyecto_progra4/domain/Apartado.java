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
import java.sql.Date;

/**
 *
 * @author kinco
 */

@Entity
@Table(name = "apartado")
public class Apartado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Apartado")
    private int idApartado;

    @ManyToOne
    @JoinColumn(name = "idCliente_Apartado", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idProducto_Apartado", nullable = false)
    private Producto producto;

    @Column(name = "fechaInicio_Apartado", nullable = false)
    private Date fechaInicioApartado;

    @Column(name = "fechaFinal_Apartado", nullable = false)
    private Date fechaFinalApartado;

    @Column(name = "abono_Apartado", nullable = false)
    private double abono;

    @Column(name = "estado_Apartado", nullable = false, length = 50)
    private String estadoApartado;

    public Apartado(int idApartado, Cliente cliente, Producto producto, Date fechaInicioApartado, Date fechaFinalApartado, double abono, String estadoApartado) {
        this.idApartado = idApartado;
        this.cliente = cliente;
        this.producto = producto;
        this.fechaInicioApartado = fechaInicioApartado;
        this.fechaFinalApartado = fechaFinalApartado;
        this.abono = abono;
        this.estadoApartado = estadoApartado;
    }

    public Apartado() {
    }

    public int getIdApartado() {
        return idApartado;
    }

    public void setIdApartado(int idApartado) {
        this.idApartado = idApartado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaInicioApartado() {
        return fechaInicioApartado;
    }

    public void setFechaInicioApartado(Date fechaInicioApartado) {
        this.fechaInicioApartado = fechaInicioApartado;
    }

    public Date getFechaFinalApartado() {
        return fechaFinalApartado;
    }

    public void setFechaFinalApartado(Date fechaFinalApartado) {
        this.fechaFinalApartado = fechaFinalApartado;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getEstadoApartado() {
        return estadoApartado;
    }

    public void setEstadoApartado(String estadoApartado) {
        this.estadoApartado = estadoApartado;
    }
}
