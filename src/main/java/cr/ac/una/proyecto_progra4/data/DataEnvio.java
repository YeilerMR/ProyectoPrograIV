/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Envio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 *
 * @author Aaron
 */
public class DataEnvio {

//    private static final String TBENVIOS = "envio";
//
//    // CRUD
//    // CREATE
//    // Método para inserta un nuevo envio en la base de datos
//    public static boolean insertar(Envio envio) throws SQLException {
//        String sql = "INSERT INTO " + TBENVIOS + " (codigo_envio, idPedido_Envio, idCliente_Envio, fechaEnvio_Envio, observacion_Envio, direccionEnvio_Envio, estadoEnvio_Envio) VALUES(?,?,?,?,?,?,?)";
//        Connection conexion = conectar();
//        PreparedStatement statement = conexion.prepareStatement(sql);
//        statement.setString(1, envio.getCodigoEnvio());
//        statement.setInt(2, envio.getIdPedido());
//        statement.setInt(3, envio.getIdCliente());
//        statement.setObject(4, envio.getFechaEnvio());
//        statement.setString(5, envio.getObservacion());
//        statement.setString(6, envio.getDireccionEnvio());
//        statement.setString(7, envio.getEstadoEnvio());
//        boolean inserted = statement.executeUpdate() > 0;
//        statement.close();
//        conexion.close();
//        return inserted;
//    }
//
//    // READ ALL
//    // Método para obtener la información del envio
//    public static LinkedList<Envio> getEnvios() throws SQLException {
//        LinkedList<Envio> envios = new LinkedList<>();
//
//        String sql = "SELECT * FROM " + TBENVIOS;
//        Connection conexion = conectar();
//        PreparedStatement statement = conexion.prepareStatement(sql);
//        ResultSet result = statement.executeQuery();
//        Envio envio;
//        while (result.next()) {
//            envio = new Envio();
//            envio.setIdEnvio(result.getInt("id_Envio"));
//            envio.setCodigoEnvio(result.getString("codigo_envio"));
//            envio.setIdPedido(result.getInt("idPedido_Envio"));
//            envio.setIdCliente(result.getInt("idCliente_Envio"));
//            String fechaTexto = result.getString("fechaEnvio_Envio");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime fechaDateTime = LocalDateTime.parse(fechaTexto, formatter);
//            LocalDateTime fechaEnvio = fechaDateTime;
//            envio.setFechaEnvio(fechaEnvio);
//            envio.setObservacion(result.getString("observacion_Envio"));
//            envio.setDireccionEnvio(result.getString("direccionEnvio_Envio"));
//            envio.setEstadoEnvio(result.getString("estadoEnvio_Envio"));
//            envios.add(envio);
//        }
//        statement.close();
//        conexion.close();
//        return envios;
//    }
//
//    public static Envio getEnvioPorCodigo(String codigo) throws SQLException {
//        String sql = "SELECT * FROM " + TBENVIOS + " WHERE codigo_envio = ?";
//        Connection conexion = conectar();
//        PreparedStatement statement = conexion.prepareStatement(sql);
//        statement.setString(1, codigo);
//        ResultSet result = statement.executeQuery();
//
//        Envio envio = null;
//        if (result.next()) {
//            envio = new Envio();
//            envio.setIdEnvio(result.getInt("id_Envio"));
//            envio.setCodigoEnvio(result.getString("codigo_envio"));
//            envio.setIdPedido(result.getInt("idPedido_Envio"));
//            envio.setIdCliente(result.getInt("idCliente_Envio"));
//
//            String fechaTexto = result.getString("fechaEnvio_Envio");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime fechaDateTime = LocalDateTime.parse(fechaTexto, formatter);
//            LocalDateTime fechaEnvio = fechaDateTime;
//            envio.setFechaEnvio(fechaEnvio);
//            envio.setObservacion(result.getString("observacion_Envio"));
//            envio.setDireccionEnvio(result.getString("direccionEnvio_Envio"));
//            envio.setEstadoEnvio(result.getString("estadoEnvio_Envio"));
//        }
//
//        statement.close();
//        conexion.close();
//        return envio;
//    }
//
//    public static boolean actualizar(Envio envio) throws SQLException {
//        Connection conexion = conectar();
//        boolean actualizadoUsuario = false;
//        System.out.println("ID-> " + envio.getIdEnvio());
//        try {
//            String sqlUsuario = "UPDATE " + TBENVIOS + " SET codigo_envio = ?, idPedido_Envio = ?, idCliente_Envio = ?, "
//                    + "fechaEnvio_Envio = ?, observacion_Envio = ?, direccionEnvio_Envio = ?, estadoEnvio_Envio = ? WHERE id_Envio = ?";
//            PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario);
//            statementUsuario.setString(1, envio.getCodigoEnvio());
//            statementUsuario.setInt(2, envio.getIdPedido());
//            statementUsuario.setInt(3, envio.getIdCliente());
//            statementUsuario.setObject(4, envio.getFechaEnvio());
//            statementUsuario.setString(5, envio.getObservacion());
//            statementUsuario.setString(6, envio.getDireccionEnvio());
//            statementUsuario.setString(7, envio.getEstadoEnvio());
//            statementUsuario.setInt(8, envio.getIdEnvio());
//            actualizadoUsuario = statementUsuario.executeUpdate() > 0;
//            statementUsuario.close();
//        } finally {
//            conexion.close();
//        }
//        return actualizadoUsuario;
//    }
//
//    // DELETE
//    public static boolean eliminar(int idEnvio) throws SQLException {
//        String sql = "DELETE FROM " + TBENVIOS + " WHERE id_Envio = ?";
//        Connection conexion = conectar();
//        PreparedStatement statement = conexion.prepareStatement(sql);
//        statement.setInt(1, idEnvio);
//        boolean deleted = statement.executeUpdate() > 0;
//        statement.close();
//        conexion.close();
//        return deleted;
//    }
//
}
