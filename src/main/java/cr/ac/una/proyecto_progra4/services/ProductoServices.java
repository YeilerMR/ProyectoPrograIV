/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

//import cr.ac.una.proyecto_progra4.data.DataProductos;
import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.jpa.ProductoRepository;


//import java.sql.SQLException;
//import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
/**
 *
 * @author yeile
 */
@Service
@Primary
public class ProductoServices implements IProductoServices{
    
    @Autowired
    ProductoRepository productoRep;

    @Override
    public boolean agregarProductos(Producto producto) {
        boolean resultado= true;
        try {
            productoRep.save(producto);
        } catch (Exception ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE,null,ex);
            resultado=false;
        }
        return resultado;
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> productos;
        try {
            productos= productoRep.findAll();
        } catch (Exception ex) {
            productos= null;
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    /**Metodo de busqueda Dinamica
    public static List<Producto> busquedaProductos(String textoBuscar){
        List<Producto> productos;
        try {
            productos= productoRep.busquedaProductos(textoBuscar);
        } catch (Exception ex) {
            productos=null;
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }**/

    @Override
    public boolean eliminar(String codigo) {
        boolean resultado= true;
        
        try {
            productoRep.eliminarProducto(codigo);

        } catch (Exception ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
            resultado= false;
        }
        return resultado;
    }

    @Override
    public List<Producto> buscarProductos(String textoBusqueda) {
        List<Producto> productos;
        try {
            productos= productoRep.busquedaProductos(textoBusqueda);
        } catch (Exception ex) {
            productos=null;
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    } 

    @Override
    public boolean modificar(Producto producto) {
       boolean resultado;
       
        try {
            productoRep.modificarProducto(producto.getCodigo(), producto.getNombre(), 
                    producto.getDescripcion(), producto.getPrecio(), producto.getCategoria(), 
                    producto.getCalificacion(), producto.isDisponible(), producto.getStock());
            
            //System.out.println("Metodo modificar: Disponible["+producto.isDisponible()+"]");
            resultado= true;
        } catch (Exception ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE,null,ex);
            resultado= false;
        }
       return resultado;
    }
    //Este metodo es para verificar si el producto
    //esta correcto para agregar a BD
    /***
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
    
    public static LinkedList<Producto> busquedaProductos(String textoBuscar){
        LinkedList<Producto> productos = new LinkedList<>();
        
        try {
            productos= DataProductos.buscarProductos(textoBuscar);
        } catch (SQLException ex) {
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
    **/
}
