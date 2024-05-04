/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Adam Acuña
 */
public class ProveedoresData extends ConectarBD {

    private static final String TBPROVEEDORES = "proveedor";
    
    public boolean crearProveedorBD(Proveedor proveedor){
        try {
            String sql = "INSERT INTO " + TBPROVEEDORES + " (id_Proveedor ,nombreProveedor_Proveedor, telefonoProveedor_Proveedor, descripcionProveedor_Proveedor, correo_Proveedor, direccionProveedor_Proveedor, categoriaServicio_Proveedor, informacionAdicional_Proveedor) VALUES(?,?,?,?,?,?,?,?)";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);
            
            statement.setInt(1, proveedor.getIdProveedor());
            statement.setString(2, proveedor.getNombreProveedor());
            statement.setString(3, proveedor.getTelefonoProveedor());
            statement.setString(4, proveedor.getDescripcionProveedor());
            statement.setString(5, proveedor.getCorreo());
            statement.setString(6, proveedor.getDireccionProveedor());
            statement.setString(7, proveedor.getCategoriaServicio());
            statement.setString(8, proveedor.getInformacionAdicional());

            statement.execute(); //Ejecuta el SQL 
            statement.close();
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error sin controlar: " + e.toString());
            return false;
        }
    }

    public LinkedList<Proveedor> listaProveedores() {
        LinkedList<Proveedor> listaProveedores = new LinkedList();
        try {
            String sql = "SELECT * FROM " + TBPROVEEDORES + ";";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Proveedor pro;
            while (rs.next()) {
                pro = new Proveedor();

                pro.setIdProveedor(rs.getInt("id_Proveedor"));
                pro.setNombreProveedor(rs.getString("nombreProveedor_Proveedor"));
                pro.setTelefonoProveedor(rs.getString("telefonoProveedor_Proveedor"));
                pro.setDescripcionProveedor(rs.getString("descripcionProveedor_Proveedor"));
                pro.setCorreo(rs.getString("correo_Proveedor"));
                pro.setDireccionProveedor(rs.getString("direccionProveedor_Proveedor"));
                pro.setCategoriaServicio(rs.getString("categoriaServicio_Proveedor"));
                pro.setInformacionAdicional(rs.getString("informacionAdicional_Proveedor"));

                listaProveedores.add(pro);
            }

            cn.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error sin controlar: " + e.toString());
            listaProveedores = null;
        }
        return listaProveedores;
    }

    public boolean editarProveedorDB(Proveedor proveedor) {
        try {
            String sql = "UPDATE " + TBPROVEEDORES + " SET nombreProveedor_Proveedor = ?, telefonoProveedor_Proveedor = ?, descripcionProveedor_Proveedor = ?, correo_Proveedor = ?, direccionProveedor_Proveedor = ?, categoriaServicio_Proveedor = ?, informacionAdicional_Proveedor = ? WHERE id_Proveedor = ?";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);

            statement.setString(1, proveedor.getNombreProveedor());
            statement.setString(2, proveedor.getTelefonoProveedor());
            statement.setString(3, proveedor.getDescripcionProveedor());
            statement.setString(4, proveedor.getCorreo());
            statement.setString(5, proveedor.getDireccionProveedor());
            statement.setString(6, proveedor.getCategoriaServicio());
            statement.setString(7, proveedor.getInformacionAdicional());
            statement.setInt(8, proveedor.getIdProveedor());

            statement.execute(); //Ejecuta el SQL 
            statement.close();
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean eliminarProveedorBD (int proveedorID) {
        try {
            String sql = "DELETE FROM " + TBPROVEEDORES + " WHERE id_Proveedor = ?";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);

            statement.setInt(1, proveedorID);

            statement.execute(); //Ejecuta el SQL 
            statement.close();
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
