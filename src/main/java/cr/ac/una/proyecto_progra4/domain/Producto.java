/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.domain;

/**
 *
 * @author yeile
 */
public class Producto {

    private String codigo, nombre, descripcion, categoria;
    private int calificacion;
    private double precio;
    private boolean disponible; //se vendio el producto??

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, String categoria, int calificacion, double precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.calificacion = calificacion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponibilidad(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigoProducto=" + codigo + ", nombreProducto=" + nombre + ", descripcion=" + descripcion + ", categoria=" + categoria + ", calificacion=" + calificacion + ", precio=" + precio + ", ventaProducto=" + disponible + '}';
    }

}
