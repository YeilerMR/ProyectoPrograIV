/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Aaron
 */
public class DataCliente {

    private static final String TBUSUARIOS = "usuario";
    private static final String TBCLIENTES = "cliente";
    private static final String TBENVIOS = "envio";

    // -- CRUD --
    // Create
    // Método que inserta (Registra un nuevo cliente en la base de datos)
    // El método inserta en la tabla Usuario ya que cada cliente es un usuario 
    // Después inserta en la tabla Cliente con el idUsuario que le correspondría
    public static boolean insertar(Cliente cliente) throws SQLException {
        // Insertar usuario
        String sqlUsuario = "INSERT INTO " + TBUSUARIOS + " (nombre_Usuario, apellidos_Usuario, email_Usuario, password_Usuario, cedula_Usuario,"
                + " telefono_Usuario, credencial_Usuario) VALUES(?,?,?,?,?,?,?)";
        Connection conexion = conectar();
        PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
        statementUsuario.setString(1, cliente.getNombre());
        statementUsuario.setString(2, cliente.getApellidos());
        statementUsuario.setString(3, cliente.getEmail());
        statementUsuario.setString(4, cliente.getPassword());
        statementUsuario.setString(5, cliente.getCedula());
        statementUsuario.setString(6, cliente.getTelefono());
        statementUsuario.setInt(7, cliente.getCredencial());
        statementUsuario.executeUpdate();

        ResultSet generatedKeys = statementUsuario.getGeneratedKeys();
        int idUsuario = -1;
        if (generatedKeys.next()) {
            idUsuario = generatedKeys.getInt(1);
        } else {
            throw new SQLException("No se pudo obtener el ID generado para el usuario.");
        }
        statementUsuario.close();

        // Insertar cliente
        String sqlCliente = "INSERT INTO " + TBCLIENTES + " (idUsuario_Cliente) VALUES(?)";
        PreparedStatement statementCliente = conexion.prepareStatement(sqlCliente);
        statementCliente.setInt(1, idUsuario);
        boolean inserted = statementCliente.executeUpdate() > 0;
        statementCliente.close();
        conexion.close();
        return inserted;
    }

    // Read ALL
    // Método que obtiene todos los Usuarios que sean Clientes
    // Obtendra todos los Usuarios los cuales su 'id' este presente en la 
    // Tabla Cliente. Para esto se utiliza un INNER JOIN 
    public static LinkedList<Cliente> getClientes() throws SQLException {
        LinkedList<Cliente> clientes = new LinkedList<>();
        String sql = "SELECT u.*, c.id_Cliente, c.idUsuario_Cliente FROM " + TBUSUARIOS + " u "
                + "INNER JOIN " + TBCLIENTES + " c ON u.id_usuario = c.idUsuario_Cliente";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Cliente cliente;
        while (result.next()) {
            cliente = new Cliente();
            cliente.setId(result.getInt("idUsuario_Cliente"));
            cliente.setIdCliente(result.getInt("id_Cliente"));
            cliente.setNombre(result.getString("nombre_Usuario"));
            cliente.setApellidos(result.getString("apellidos_Usuario"));
            cliente.setEmail(result.getString("email_Usuario"));
            cliente.setPassword(result.getString("password_Usuario"));
            cliente.setCedula(result.getString("cedula_Usuario"));
            cliente.setTelefono(result.getString("telefono_Usuario"));
            cliente.setCredencial(result.getInt("credencial_Usuario"));
            clientes.add(cliente);
        }
        statement.close();
        conexion.close();
        return clientes;
    }

    public static LinkedList<Cliente> getClientesConEnvios() throws SQLException {
        LinkedList<Cliente> clientes = new LinkedList<>();
        String sql = "SELECT DISTINCT c.*, u.* "
                + "FROM " + TBCLIENTES + " c "
                + "INNER JOIN " + TBUSUARIOS + " u ON c.idUsuario_Cliente = u.id_Usuario "
                + "INNER JOIN " + TBENVIOS + " e ON c.id_Cliente = e.idCliente_Envio";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Cliente cliente = new Cliente();
            cliente.setNombre(result.getString("nombre_Usuario"));
            cliente.setApellidos(result.getString("apellidos_Usuario"));
            cliente.setEmail(result.getString("email_Usuario"));
            cliente.setPassword(result.getString("password_Usuario"));
            cliente.setCedula(result.getString("cedula_Usuario"));
            cliente.setTelefono(result.getString("telefono_Usuario"));
            cliente.setCredencial(result.getInt("credencial_Usuario"));
            cliente.setIdCliente(result.getInt("id_Cliente"));
            cliente.setId(result.getInt("idUsuario_Cliente")); // ID USUARIO

            // Verificar si el cliente ya está presente en la lista
            boolean clienteExistente = false;
            for (Cliente existingCliente : clientes) {
                if (existingCliente.getIdCliente() == cliente.getIdCliente()) {
                    clienteExistente = true;
                    break;
                }
            }

            // Si el cliente no está presente, agregar a la lista
            if (!clienteExistente) {
                clientes.add(cliente);
            }
        }

        statement.close();
        conexion.close();
        return clientes;
    }

    // UPDATE
    // Método para poder modificar la información de un Cliente
    public static boolean actualizar(Cliente cliente) throws SQLException {
        Connection conexion = conectar();
        boolean actualizadoUsuario = false;
        try {
            String sqlUsuario = "UPDATE " + TBUSUARIOS + " SET nombre_Usuario = ?, apellidos_Usuario = ?, email_Usuario = ?, "
                    + "password_Usuario = ?, telefono_Usuario = ?, credencial_Usuario = ? WHERE id_Usuario = ?";
            PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario);
            statementUsuario.setString(1, cliente.getNombre());
            statementUsuario.setString(2, cliente.getApellidos());
            statementUsuario.setString(3, cliente.getEmail());
            statementUsuario.setString(4, cliente.getPassword());
            statementUsuario.setString(5, cliente.getTelefono());
            statementUsuario.setInt(6, cliente.getCredencial());
            statementUsuario.setInt(7, cliente.getId());
            actualizadoUsuario = statementUsuario.executeUpdate() > 0;
            statementUsuario.close();
        } finally {
            conexion.close();
        }
        return actualizadoUsuario;
    }

    public static boolean eliminar(int idUsuario_Cliente) throws SQLException {
        Connection conexion = conectar();
        boolean eliminado = false;
        try {
            // Eliminar cliente
            String sqlCliente = "DELETE FROM " + TBCLIENTES + " WHERE idUsuario_Cliente = ?";
            PreparedStatement statementCliente = conexion.prepareStatement(sqlCliente);
            statementCliente.setInt(1, idUsuario_Cliente);
            eliminado = statementCliente.executeUpdate() > 0;
            statementCliente.close();

            // Eliminar usuario
            String sqlUsuario = "DELETE FROM " + TBUSUARIOS + " WHERE id_Usuario = ?";
            PreparedStatement statementUsuario = conexion.prepareStatement(sqlUsuario);
            statementUsuario.setInt(1, idUsuario_Cliente);
            eliminado = eliminado && (statementUsuario.executeUpdate() > 0);
            statementUsuario.close();
        } finally {
            conexion.close();
        }
        return eliminado;
    }

}
