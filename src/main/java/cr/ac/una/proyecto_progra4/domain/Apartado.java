/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author kinco
 */
public class Apartado {

        private int idApartado;
        private int idCliente;
        private int idProducto;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date fechaInicioApartado;
    
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date fechaFinalApartado;
    
        private double abono;
        private String estadoApartado;
     
    public Apartado(int idApartado, int idCliente, int idProducto, Date fechaInicioApartado, Date fechaFinalApartado, double abono, String estadoApartado) {
        this.idApartado = idApartado;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
