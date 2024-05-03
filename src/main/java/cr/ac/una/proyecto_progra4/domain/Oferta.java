/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

import java.sql.Date;

/**
 *
 * @author yeile
 */
public class Oferta {
    
    private String codigoOferta, codigoProducto, tipoOferta;
    private int descuentoOferta;
    private Date fechaInicio,fechaFin; 
    
    private boolean estado;

    public Oferta() {
    }

    public Oferta(String codigoOferta, String codigoProducto, String tipoOferta, int descuentoOferta, Date fechaInicio, Date fechaFin, boolean estado) {
        this.codigoOferta = codigoOferta;
        this.codigoProducto = codigoProducto;
        this.tipoOferta = tipoOferta;
        this.descuentoOferta = descuentoOferta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public int getDescuentoOferta() {
        return descuentoOferta;
    }

    public void setDescuentoOferta(int descuentoOferta) {
        this.descuentoOferta = descuentoOferta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
