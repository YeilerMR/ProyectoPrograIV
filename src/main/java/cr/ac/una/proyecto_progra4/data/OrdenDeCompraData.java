/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import static cr.ac.una.proyecto_progra4.data.ConectarBD.conectar;
import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Adam Acuña
 */
public class OrdenDeCompraData {

    private static final String TBORDENESDECOMPRA = "ordendecompra";

    public boolean crearOrdenCompraBD(OrdenDeCompra ordenCompra) {
        try {
            String sql = "INSERT INTO " + TBORDENESDECOMPRA + " (id_OrdenDeCompra, idPedido_OrdenDeCompra, idProveedor_OrdenDeCompra, fechaOrden_OrdenDeCompra, fechaEntrega_OrdenDeCompra, estadoOrden_OrdenDeCompra, numeroReferencia_OrdenDeCompra) VALUES(?,?,?,?,?,?,?)";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);

            statement.setInt(1, 0);
            statement.setInt(2, 0);
            statement.setInt(3, ordenCompra.getIdProveedor().getIdProveedor());
            statement.setDate(4, ordenCompra.getFechaOrden());
            statement.setDate(5, ordenCompra.getFechaEntrega());
            statement.setString(6, ordenCompra.getEstadoOrden());
            statement.setString(7, ordenCompra.getNumeroReferencia());

            statement.execute(); //Ejecuta el SQL 
            statement.close();
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error sin controlar: " + e.toString());
            return false;
        }
    }

    public LinkedList<OrdenDeCompra> listaOrdenesDB() {
        LinkedList<OrdenDeCompra> listaOrdenes = new LinkedList();
        try {
            String sql = "SELECT " + TBORDENESDECOMPRA + ".*, proveedor.nombreProveedor_Proveedor AS nombreProveedor FROM " + TBORDENESDECOMPRA + " INNER JOIN proveedor ON " + TBORDENESDECOMPRA + ".idProveedor_OrdenDeCompra = proveedor.id_Proveedor";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            OrdenDeCompra orden;
            while (rs.next()) {
                orden = new OrdenDeCompra();

                orden.setIdPedido(rs.getInt("idPedido_OrdenDeCompra"));
                orden.getIdProveedor().setIdProveedor(rs.getInt("idProveedor_OrdenDeCompra"));
                orden.getIdProveedor().setNombreProveedor(rs.getString("nombreProveedor"));
                orden.setFechaOrden(rs.getDate("fechaOrden_OrdenDeCompra"));
                orden.setFechaEntrega(rs.getDate("fechaEntrega_OrdenDeCompra"));
                orden.setEstadoOrden(rs.getString("estadoOrden_OrdenDeCompra"));
                orden.setNumeroReferencia(rs.getString("numeroReferencia_OrdenDeCompra"));

                listaOrdenes.add(orden);
            }

            cn.close();
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error sin controlar: " + e.toString());
            listaOrdenes = null;
        }
        return listaOrdenes;
    }

    public boolean editarOrdenDB(OrdenDeCompra orden) {
        try {
            String sql = "UPDATE " + TBORDENESDECOMPRA + " SET idPedido_OrdenDeCompra = ?, idProveedor_OrdenDeCompra = ?, fechaOrden_OrdenDeCompra = ?, fechaEntrega_OrdenDeCompra = ?, estadoOrden_OrdenDeCompra = ?, numeroReferencia_OrdenDeCompra = ?  WHERE numeroReferencia_OrdenDeCompra	 = ?";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);

            statement.setInt(1, orden.getIdPedido());
            statement.setInt(2, orden.getIdProveedor().getIdProveedor());
            statement.setDate(3, orden.getFechaOrden());
            statement.setDate(4, orden.getFechaEntrega());
            statement.setString(5, orden.getEstadoOrden());
            statement.setString(6, orden.getNumeroReferencia());
            statement.setString(7, orden.getNumeroReferencia());

            statement.execute(); //Ejecuta el SQL 
            statement.close();
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean eliminarOrdenDB(String numeroReferencia) {
        try {
            String sql = "DELETE FROM " + TBORDENESDECOMPRA + " WHERE numeroReferencia_OrdenDeCompra = ?";
            Connection cn = conectar();
            PreparedStatement statement = cn.prepareStatement(sql);

            statement.setString(1, numeroReferencia);

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
