/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import cr.ac.una.proyecto_progra4.domain.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yeile
 */
public class DataProductos extends ConectarBD {

    private static final String TB_PRODUCTO = "producto";
    private static final String CODIGO = "codProducto";

    public static boolean insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO " + TB_PRODUCTO
                + " (nombre_Producto,descripcion_Producto,precio_Producto,categoria_Producto,"
                + "calificacion_Producto,disponibilidad_Producto,codProducto) VALUES(?,?,?,?,?,?,?)";

        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setDouble(3, producto.getPrecio());
        statement.setString(4, producto.getCategoria());
        statement.setInt(5, producto.getCalificacion());
        statement.setBoolean(6, producto.isDisponible());
        statement.setString(7, producto.getCodigo());
        
        boolean inserted = statement.executeUpdate()>0;
        
        statement.close();
        conexion.close();
        
        return inserted;
    }

   public static LinkedList<Producto> getProductos() throws SQLException{
       LinkedList<Producto> productos= new LinkedList<>();
       
       String sql= "SELECT * FROM "+TB_PRODUCTO;
       Connection conexion= conectar();
       
       PreparedStatement statement= conexion.prepareStatement(sql);
       ResultSet resultado= statement.executeQuery();
       
       Producto producto;
       while (resultado.next()) {           
           producto= new Producto();
           producto.setCodigo(resultado.getString("codProducto"));
           producto.setNombre(resultado.getString("nombre_Producto"));
           producto.setDescripcion(resultado.getString("descripcion_Producto"));
           producto.setPrecio(resultado.getDouble("precio_Producto"));
           producto.setCategoria(resultado.getString("categoria_Producto"));
           producto.setCalificacion(resultado.getInt("calificacion_Producto"));
           producto.setDisponibilidad(resultado.getBoolean("disponibilidad_Producto"));
           
           productos.add(producto);
       }
       statement.close();
       conexion.close();
       
       return productos;
   }

   public static boolean eliminar(String codigo) throws SQLException{
       String sql= "DELETE FROM "+TB_PRODUCTO+" WHERE "+CODIGO+" = ?";
       Connection conexion= conectar();
       PreparedStatement statement= conexion.prepareStatement(sql);
       statement.setString(1, codigo);
       
       boolean deleted= statement.executeUpdate()>0;
       statement.close();
       conexion.close();
       
       return deleted;
   }

    public static boolean modificar(Producto producto) throws SQLException{
        System.out.println("modificar de DataProductos");
        String sql= "UPDATE "+TB_PRODUCTO+" SET nombre_Producto = ?, descripcion_Producto = ?, precio_Producto = ?, categoria_Producto = ?, calificacion_Producto = ?, disponibilidad_Producto = ? WHERE "+CODIGO+" = ?";
        Connection conexion= conectar();
        PreparedStatement statement= conexion.prepareStatement(sql);
        
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setDouble(3, producto.getPrecio());
        statement.setString(4, producto.getCategoria());
        statement.setInt(5, producto.getCalificacion());
        statement.setBoolean(6, producto.isDisponible());
        statement.setString(7, producto.getCodigo());
    
        boolean modified= statement.executeUpdate()>0;
        statement.close();
        conexion.close();
        
        return modified;
    }
   
}
