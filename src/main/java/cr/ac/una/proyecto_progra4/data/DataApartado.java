/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Apartado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinco
 */
public class DataApartado {
    private static final String TBAPARTADO = "apartado";
    private static final String IDENTIFICADOR = "id_Apartado";
   
    public boolean eliminar(int idApartado) {
        String sql = "DELETE FROM " + TBAPARTADO + " WHERE id_Apartado = ?";
        try (Connection conexion = conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idApartado);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return false; 
        }
    }
    
     public Apartado insertar(Apartado apartado) throws SQLException{
    String sql = "INSERT INTO "+TBAPARTADO+" (id_Apartado, idCliente_Apartado, idProducto_Apartado, fechaInicio_Apartado, fechaFinal_Apartado, abono_Apartado, estado_Apartado) VALUES(?,?,?,?,?,?,?);";
    Connection conexion = conectar();
    PreparedStatement statement = conexion.prepareStatement(sql);
    statement.setInt(1, apartado.getIdApartado());
    statement.setInt(2, apartado.getIdCliente());
    statement.setInt(3, apartado.getIdProducto());

    
    java.util.Date fechaInicio = apartado.getFechaInicioApartado();
    if (fechaInicio != null) {
        java.sql.Timestamp timestampInicio = new java.sql.Timestamp(fechaInicio.getTime());
        statement.setTimestamp(4, timestampInicio);
    } else {
        statement.setNull(4, Types.TIMESTAMP);
    }

    
    java.util.Date fechaFinal = apartado.getFechaFinalApartado();
    if (fechaFinal != null) {
        java.sql.Timestamp timestampFinal = new java.sql.Timestamp(fechaFinal.getTime());
        statement.setTimestamp(5, timestampFinal);
    } else {
        statement.setNull(5, Types.TIMESTAMP);
    }
    statement.setDouble(6, apartado.getAbono());
    statement.setString(7, apartado.getEstadoApartado());
    
    int resultado = statement.executeUpdate();
    System.out.println("result= "+resultado);
    statement.close();
    conexion.close();
    
    return apartado;
}

    
    public LinkedList<Apartado> getApartados() throws SQLException{
        LinkedList<Apartado> apartado = new LinkedList();
        String sql = "SELECT * FROM "+TBAPARTADO+";";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Apartado apt;
        while(result.next()){
            apt = new Apartado();
            apt.setIdApartado(result.getInt("id_Apartado"));
            apt.setIdCliente(result.getInt("idCliente_Apartado"));
            apt.setIdProducto(result.getInt("idProducto_Apartado"));
            apt.setFechaInicioApartado(result.getDate("fechaInicio_Apartado"));
            apt.setFechaFinalApartado(result.getDate("fechaFinal_Apartado"));
            apt.setAbono(result.getDouble("abono_Apartado"));
            apt.setEstadoApartado(result.getString("estado_Apartado"));
        apartado.add(apt);
        }
        return apartado;
    }
    
    public boolean editar(Apartado apartado) throws SQLException {
    String sql = "UPDATE " + TBAPARTADO + " SET idCliente_Apartado = ?, idProducto_Apartado = ?, fechaInicio_Apartado = ?, "
            + "fechaFinal_Apartado = ?, abono_Apartado = ?, estado_Apartado = ? WHERE id_Apartado = ?";
    try (Connection conexion = conectar();
         PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, apartado.getIdCliente());
        statement.setInt(2, apartado.getIdProducto());
        statement.setDate(3, new java.sql.Date(apartado.getFechaInicioApartado().getTime())); // Convertir la fecha de Java a SQL
        statement.setDate(4, new java.sql.Date(apartado.getFechaFinalApartado().getTime())); // Convertir la fecha de Java a SQL
        statement.setDouble(5, apartado.getAbono());
        statement.setString(6, apartado.getEstadoApartado());
        statement.setInt(7, apartado.getIdApartado());
        
        int filasAfectadas = statement.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException ex) {
        Logger.getLogger(DataApartado.class.getName()).log(Level.SEVERE, null, ex);
        throw ex; 
    }
}

    
    public static void main(String[] args) {
       
        try {
            new DataApartado().insertar(new Apartado());
            LinkedList<Apartado> apartados = new DataApartado().getApartados();
            System.out.println(""+apartados.getFirst().getIdApartado());
            
        } catch (SQLException ex) {
            Logger.getLogger(DataApartado.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    
}
