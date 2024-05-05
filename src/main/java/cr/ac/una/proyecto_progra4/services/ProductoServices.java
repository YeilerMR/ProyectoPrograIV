/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataProductos;
import cr.ac.una.proyecto_progra4.domain.Producto;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeile
 */
public class ProductoServices {
    
    //Este metodo es para verificar si el producto
    //esta correcto para agregar a BD
    public static boolean agregarProductos(Producto producto){
        boolean resultado= true;
        try {
            resultado= DataProductos.insertar(producto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE,null,ex);
        }
        return resultado;
    }
    
    public static LinkedList<Producto> getProductos(){
        LinkedList<Producto> productos;
        
        try {
            productos= DataProductos.getProductos();
            //System.out.println("Codigo P: "+productos.get(0).getCodigo());
        } catch (SQLException ex) {
            productos= null;
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    //falta el arreglo para obtener datos de la base de datos
    public static boolean eliminar(String codigo){
        boolean resultado= true;
        try {
            resultado=DataProductos.eliminar(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public static boolean modificar(Producto producto) {
        boolean resultado= true;
        try {
            resultado= DataProductos.modificar(producto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
