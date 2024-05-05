/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import cr.ac.una.proyecto_progra4.domain.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author yeile
 */
public class DataOfertas extends ConectarBD {

    private static final String TB_OFERTA = "oferta";
    private static final String CODIGO = "codigoOferta";

    public static boolean insertar(Oferta oferta) throws SQLException {

        boolean inserted;
        try {
            String sql = "INSERT INTO " + TB_OFERTA + " (codigoOferta,codigoProducto,"
                    + "descuentoOferta_Oferta,fechaInicioOferta_Oferta,fechaFinOferta_Oferta,"
                    + "estadoOferta_Oferta,tipoOferta_Oferta) VALUES(?,?,?,?,?,?,?)";
            Connection conexion = conectar();
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, oferta.getCodigoOferta());
            statement.setString(2, oferta.getCodigoProducto());
            statement.setInt(3, oferta.getDescuentoOferta());
            statement.setDate(4, oferta.getFechaInicio());
            statement.setDate(5, oferta.getFechaFin());
            statement.setBoolean(6, oferta.isEstado());
            statement.setString(7, oferta.getTipoOferta());

            inserted = statement.executeUpdate() > 0;

            statement.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error sin controlar: " + e.toString());
            inserted = false;
        }
        return inserted;
    }

    public static LinkedList<Oferta> getOfertas() throws SQLException {
        LinkedList<Oferta> ofertas = new LinkedList<>();
        try {
            String sql = "SELECT * FROM " + TB_OFERTA;
            Connection conexion = conectar();

            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            Oferta oferta;
            while (resultado.next()) {
                oferta = new Oferta();
                oferta.setCodigoOferta(resultado.getString("codigoOferta"));
                oferta.setCodigoProducto(resultado.getString("codigoProducto"));
                oferta.setDescuentoOferta(resultado.getInt("descuentoOferta_Oferta"));
                oferta.setFechaInicio(resultado.getDate("fechaInicioOferta_Oferta"));
                oferta.setFechaFin(resultado.getDate("fechaFinOferta_Oferta"));
                oferta.setEstado(resultado.getBoolean("estadoOferta_Oferta"));
                oferta.setTipoOferta(resultado.getString("tipoOferta_Oferta"));

                ofertas.add(oferta);
            }
            statement.close();
            resultado.close();
            conexion.close();
        } catch (SQLException e) {
             System.out.println("Error sin controlar: " + e.toString());
             ofertas= null;
        }

        return ofertas;
    }

    public static boolean eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM " + TB_OFERTA + " WHERE " + CODIGO + " = ?";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, codigo);

        boolean deleted = statement.executeUpdate() > 0;
        statement.close();
        conexion.close();

        return deleted;
    }

    public static boolean modificar(Oferta oferta) throws SQLException {

        System.out.println("Estoy en dataOferta[codigo oferta: " + oferta.getCodigoOferta() + "]");
        String sql = "UPDATE " + TB_OFERTA + " SET codigoProducto = ?, descuentoOferta_Oferta = ?, fechaInicioOferta_Oferta = ?, fechaFinOferta_Oferta = ?, estadoOferta_Oferta = ?, tipoOferta_Oferta= ? WHERE " + CODIGO + " = ?";
        Connection conexion = conectar();
        PreparedStatement statement = conexion.prepareStatement(sql);

        statement.setString(1, oferta.getCodigoProducto());
        statement.setInt(2, oferta.getDescuentoOferta());
        statement.setDate(3, oferta.getFechaInicio());
        statement.setDate(4, oferta.getFechaFin());
        statement.setBoolean(5, oferta.isEstado());
        statement.setString(6, oferta.getTipoOferta());
        statement.setString(7, oferta.getCodigoOferta());

        boolean modified = statement.executeUpdate() > 0;
        statement.close();
        conexion.close();

        return modified;
    }
}
