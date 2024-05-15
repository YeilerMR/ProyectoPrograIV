/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kinco
 */
public class DataEmpleado {
    private static final String TBEMPLEADO = "empleado";
    private static final String IDENTIFICADOR = "id_Empleado";
   
     public boolean eliminar(int idEmpleado) {
        String sql = "DELETE FROM " + TBEMPLEADO + " WHERE id_Empleado = ?";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idEmpleado);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return false; 
        }
    }
    
    public Empleado insertar(Empleado empleado) throws SQLException{
    String sql = "INSERT INTO "+TBEMPLEADO+" (id_Empleado, nombre_Empleado, apellidos_Empleado, email_Empleado, password_Empleado, telefono_Empleado, direccion_Empleado, puesto_Empleado) VALUES(?,?,?,?,?,?,?,?);";
    Connection conexion = conectar();
    PreparedStatement statement = conexion.prepareStatement(sql);
    statement.setInt(1, empleado.getIdEmpleado());
    statement.setString(2, empleado.getNombre());
    statement.setString(3, empleado.getApellidos());
    statement.setString(4, empleado.getEmail());
    statement.setString(5, empleado.getPassword());
    statement.setString(6, empleado.getTelefono());
    statement.setString(7, empleado.getDireccion());
    statement.setString(8, empleado.getPuesto());
    
    int resultado = statement.executeUpdate();
    System.out.println("result= "+resultado);
    statement.close();
    conexion.close();
    
    return empleado;
}

    
    public LinkedList<Empleado> getEmpleados() throws SQLException{
        LinkedList<Empleado> empleado = new LinkedList();
        String sql = "SELECT * FROM "+TBEMPLEADO+";";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Empleado emp;
        while(result.next()){
            emp = new Empleado();
            emp.setIdEmpleado(result.getInt("id_Empleado"));
            emp.setNombre(result.getString("nombre_Empleado"));
            emp.setApellidos(result.getString("apellidos_Empleado"));
            emp.setEmail(result.getString("email_Empleado"));
            emp.setPassword(result.getString("password_Empleado"));
            emp.setTelefono(result.getString("telefono_Empleado"));
            emp.setDireccion(result.getString("direccion_Empleado"));
            emp.setPuesto(result.getString("puesto_Empleado"));
        empleado.add(emp);
        }
        return empleado;
    }
    
    public boolean editar(Empleado empleado) throws SQLException {
        String sql = "UPDATE " + TBEMPLEADO + " SET nombre_Empleado = ?, apellidos_Empleado = ?, email_Empleado = ?, "
                + "password_Empleado = ?, telefono_Empleado = ?, direccion_Empleado = ?, puesto_Empleado = ? WHERE id_Empleado = ?";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getApellidos());
            statement.setString(3, empleado.getEmail());
            statement.setString(4, empleado.getPassword());
            statement.setString(5, empleado.getTelefono());
            statement.setString(6, empleado.getDireccion());
            statement.setString(7, empleado.getPuesto());
            statement.setInt(8, empleado.getIdEmpleado());
            
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DataEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    public static void main(String[] args) {
       
        try {
            new DataEmpleado().insertar(new Empleado());
            LinkedList<Empleado> apartados = new DataEmpleado().getEmpleados();
            System.out.println(""+apartados.getFirst().getIdEmpleado());
            
        } catch (SQLException ex) {
            Logger.getLogger(DataApartado.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    
}
