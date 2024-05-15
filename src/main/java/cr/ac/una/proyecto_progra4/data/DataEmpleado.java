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
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author kinco
 */
public class DataEmpleado {
    private static final String TBUSUARIOS = "usuario";
    private static final String TBEMPLEADOS = "empleado";

    public static boolean insertar(Empleado empleado) throws SQLException {
        // Insertar usuario
        String sqlUsuario = "INSERT INTO " + TBUSUARIOS + " (nombre_Usuario, apellidos_Usuario, email_Usuario, password_Usuario, cedula_Usuario,"
                + " telefono_Usuario, credencial_Usuario) VALUES(?,?,?,?,?,?,?)";
        Connection conexion = conectar();
        PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
        statementUsuario.setString(1, empleado.getNombre());
        statementUsuario.setString(2, empleado.getApellidos());
        statementUsuario.setString(3, empleado.getEmail());
        statementUsuario.setString(4, empleado.getPassword());
        statementUsuario.setString(5, empleado.getCedula());
        statementUsuario.setString(6, empleado.getTelefono());
        statementUsuario.setInt(7, empleado.getCredencial());
        statementUsuario.executeUpdate();

        ResultSet generatedKeys = statementUsuario.getGeneratedKeys();
        int idUsuario = -1;
        if (generatedKeys.next()) {
            idUsuario = generatedKeys.getInt(1);
        } else {
            throw new SQLException("No se pudo obtener el ID generado para el usuario.");
        }
        statementUsuario.close();

        // Insertar empleado
        String sqlCliente = "INSERT INTO " + TBEMPLEADOS + " (idUsuario_Empleado) VALUES(?)";
        PreparedStatement statementCliente = conexion.prepareStatement(sqlCliente);
        statementCliente.setInt(1, idUsuario);
        boolean inserted = statementCliente.executeUpdate() > 0;
        statementCliente.close();
        conexion.close();
        return inserted;
    }

    
    public static LinkedList<Empleado> getEmpleados() throws SQLException {
        LinkedList<Empleado> empleados = new LinkedList<>();
        String sql = "SELECT u.*, c.idUsuario_Empleado FROM " + TBUSUARIOS + " u "
                + "INNER JOIN " + TBEMPLEADOS + " c ON u.id_usuario = c.idUsuario_Empleado";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Empleado empleado;
        while (result.next()) {
            empleado = new Empleado();
            empleado.setId(result.getInt("idUsuario_Empleado"));
            empleado.setNombre(result.getString("nombre_Usuario"));
            empleado.setApellidos(result.getString("apellidos_Usuario"));
            empleado.setEmail(result.getString("email_Usuario"));
            empleado.setPassword(result.getString("password_Usuario"));
            empleado.setCedula(result.getString("cedula_Usuario"));
            empleado.setTelefono(result.getString("telefono_Usuario"));
            empleado.setCredencial(result.getInt("credencial_Usuario"));
            empleados.add(empleado);
        }
        statement.close();
        conexion.close();
        return empleados;
    }


    public static boolean actualizar(Empleado empleado) throws SQLException {
        Connection conexion = conectar();
        boolean actualizadoUsuario = false;
        try {
            String sqlUsuario = "UPDATE " + TBUSUARIOS + " SET nombre_Usuario = ?, apellidos_Usuario = ?, email_Usuario = ?, "
                    + "password_Usuario = ?, telefono_Usuario = ?, credencial_Usuario = ? WHERE id_Usuario = ?";
            PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario);
            statementUsuario.setString(1, empleado.getNombre());
            statementUsuario.setString(2, empleado.getApellidos());
            statementUsuario.setString(3, empleado.getEmail());
            statementUsuario.setString(4, empleado.getPassword());
            statementUsuario.setString(5, empleado.getTelefono());
            statementUsuario.setInt(6, empleado.getCredencial());
            statementUsuario.setInt(7, empleado.getId());
            actualizadoUsuario = statementUsuario.executeUpdate() > 0;
            statementUsuario.close();
        } finally {
            conexion.close();
        }
        return actualizadoUsuario;
    }

    public static boolean eliminar(int idUsuario_Empleado) throws SQLException {
        Connection conexion = conectar();
        boolean eliminado = false;
        try {

            String sqlEmpleado = "DELETE FROM " + TBEMPLEADOS + " WHERE idUsuario_Empleado = ?";
            PreparedStatement statementEmpleado = conexion.prepareStatement(sqlEmpleado);
            statementEmpleado.setInt(1, idUsuario_Empleado);
            eliminado = statementEmpleado.executeUpdate() > 0;
            statementEmpleado.close();


            String sqlUsuario = "DELETE FROM " + TBUSUARIOS + " WHERE id_Usuario = ?";
            PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario);
            statementUsuario.setInt(1, idUsuario_Empleado);
            eliminado = eliminado && (statementUsuario.executeUpdate() > 0);
            statementUsuario.close();
        } finally {
            conexion.close();
        }
        return eliminado;
    }

}    
