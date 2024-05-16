/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Apartado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 *
 * @author kinco
 */
public class DataApartado {
    private static final String TBAPARTADO = "apartado";
    private static final String IDENTIFICADOR = "id_Apartado";
   
    public static boolean insertarApartado(Apartado apartado) throws SQLException {
    String sql = "INSERT INTO " + TBAPARTADO + " (idCliente, idProducto, fechaInicioApartado, fechaFinalApartado, abono, estadoApartado) VALUES (?, ?, ?, ?, ?, ?)";
    Connection conexion = conectar();
    PreparedStatement statement = conexion.prepareStatement(sql);
    statement.setInt(1, apartado.getIdCliente());
    statement.setInt(2, apartado.getIdProducto());
    statement.setDate(3, new java.sql.Date(apartado.getFechaInicioApartado().getTime()));
    statement.setDate(4, new java.sql.Date(apartado.getFechaFinalApartado().getTime()));
    statement.setDouble(5, apartado.getAbono());
    statement.setString(6, apartado.getEstadoApartado());
    
    ResultSet generatedKeys = statement.getGeneratedKeys();
        int idApartado = -1;
        if (generatedKeys.next()) {
            idApartado = generatedKeys.getInt(1);
        } else {
            throw new SQLException("No se pudo obtener el ID generado para el usuario.");
        }
        statement.close(); 
        boolean inserted = statement.executeUpdate() > 0;
        statement.close();
        conexion.close();    
    return inserted;
}
    
    public static LinkedList<Apartado> getApartados() throws SQLException {
    LinkedList<Apartado> apartados = new LinkedList<>();
    String sql = "SELECT a.*, c.nombre_cliente, p.nombre_producto " +
                 "FROM " + TBAPARTADO + " a " +
                 "INNER JOIN Clientes c ON a.idCliente_Apartado = c.id_cliente " +
                 "INNER JOIN Productos p ON a.idProducto_Apartado = p.id_producto";
    Connection conexion = conectar();
    PreparedStatement statement = conexion.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    Apartado apartado;
    while (result.next()) {
        apartado = new Apartado();
        apartado.setIdCliente(result.getInt("idCliente_Apartado"));
        apartado.setIdProducto(result.getInt("idProducto_Apartado"));
        apartado.setFechaInicioApartado(result.getDate("fecha_inicio_apartado"));
        apartado.setFechaFinalApartado(result.getDate("fecha_final_apartado"));
        apartado.setAbono(result.getDouble("abono_apartado"));
        apartado.setEstadoApartado(result.getString("estado_apartado"));
        apartados.add(apartado);
    }
    statement.close();
    conexion.close();
    return apartados;
}
    
        public static boolean actualizar(Apartado apartado) throws SQLException {
        Connection conexion = conectar();
        boolean actualizadoApartado = false;
        try {
            String sqlApartado = "UPDATE " + TBAPARTADO + " SET idCliente_Apartado = ?, idProducto_Apartado = ?, fecha_inicio_apartado = ?, "
                    + "fecha_final_apartado = ?, abono_apartado = ?, estado_apartado = ? WHERE id_Apartado = ?";
            PreparedStatement statementApartado = conexion.prepareStatement(sqlApartado);
            statementApartado.setInt(1, apartado.getIdCliente());
            statementApartado.setInt(2, apartado.getIdProducto());
            statementApartado.setDate(3, apartado.getFechaInicioApartado());
            statementApartado.setDate(4, apartado.getFechaFinalApartado());
            statementApartado.setDouble(5, apartado.getAbono());
            statementApartado.setString(6, apartado.getEstadoApartado());
            statementApartado.setInt(7, apartado.getIdApartado());
            actualizadoApartado = statementApartado.executeUpdate() > 0;
            statementApartado.close();
        } finally {
            conexion.close();
        }
        return actualizadoApartado;
    }

public static boolean eliminar(int idApartado) throws SQLException {
        Connection conexion = conectar();
        boolean eliminado = false;
        try {

            String sqlApartado = "DELETE FROM " + TBAPARTADO + " WHERE idApartado = ?";
            PreparedStatement statementApartado = conexion.prepareStatement(sqlApartado);
            statementApartado.setInt(1, idApartado);
            eliminado = statementApartado.executeUpdate() > 0;
            statementApartado.close();

        } finally {
            conexion.close();
        }
        return eliminado;
    }

}
