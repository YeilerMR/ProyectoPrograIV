
package cr.ac.una.proyecto_progra4.data;


import cr.ac.una.proyecto_progra4.domain.Pedido;
import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.time.LocalDate;
import java.util.LinkedList;

/**
 * @author GONZALO DORMOS RODRIGUEZ
 */
public class DataPedido extends ConectarBD {

    private static final String tbPedidos = "pedido";
    //Nombre de las columnas en la tabla
    private static final String col_idPedido = "id_Pedido";
    private static final String col_codigo = "codigo_Pedido";
    private static final String col_idEmpleado = "idEmpleado_Pedido";
    private static final String col_estado = "estadoPedido_Pedido";
    private static final String col_fecha = "fechaPedido__Pedido";
    private static final String col_direccion = "direccionEnvioPedido_Pedido";
    private static final String col_canton = "cantonEnvio_Pedido"; 
    private static final String col_provincia = "provinciaEnvioPedido_pedido";
    private static final String col_idProduct = "idProducto_Pedido";
    private static final String col_cant = "cantidadProductos_pedido";
    private static final String col_idFactura = "idFactura_Pedido";

    public boolean Insertar(Pedido pedido) {
        int insertado = 0;
        try {
            Connection conexion = conectar();
            String datos = col_codigo+","+col_idEmpleado + "," + col_estado + "," + col_fecha + "," + col_direccion + ","+col_canton+","+col_provincia+","+ col_idProduct + ","+col_cant+","+ col_idFactura;
            String query = "INSERT INTO " + tbPedidos + " (" + datos + ") VALUES ( ?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            
            ps.setString(1, pedido.getCodigo());
            // ps.setInt(2, pedido.getId_empleado());
            ps.setString(3, pedido.getEstado_pedido());
            ps.setDate(4, pedido.getFecha());
            ps.setString(5, pedido.getDireccion_pedido());
            ps.setString(6, pedido.getCanton());
            ps.setString(7, pedido.getProvincia());
            // ps.setInt(8, pedido.getId_producto());
            ps.setInt(9, pedido.getCantidad());
            // ps.setInt(10, pedido.getFactura());

            insertado = ps.executeUpdate();

            ps.close();
            conexion.close();
            //return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return (insertado == 1);
    }

    public boolean Eliminar(Pedido pedido) {
        int eliminar = 0;
        try {
            Connection conexion = conectar();
            String query = "DELETE FROM " + tbPedidos + " WHERE " + col_idPedido + " = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, pedido.getId_pedido());
            eliminar = ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return (eliminar == 1);
    }

    public LinkedList<Pedido> Leer() {
        LinkedList<Pedido> lista = new LinkedList<Pedido>();
        try {
            Connection conexion = conectar();
            String query = "SELECT * FROM " + tbPedidos;
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodigo(rs.getString(col_codigo));
                pedido.setId_pedido(rs.getInt(col_idPedido));
                // pedido.setId_empleado(rs.getInt(col_idEmpleado));
                pedido.setEstado_pedido(rs.getString(col_estado));
                pedido.setFecha(rs.getDate(col_fecha));
                pedido.setDireccion_pedido(rs.getString(col_direccion));
                pedido.setCanton(rs.getString(col_canton));
                pedido.setProvincia(rs.getString(col_provincia));
                // pedido.setId_producto(rs.getInt(col_idProduct));
                pedido.setCantidad(rs.getInt(col_cant));
                // pedido.setFactura(rs.getInt(col_idFactura));
                lista.add(pedido);
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return (lista.isEmpty()) ? null : lista;
    }

    public boolean Actualizar(Pedido pedido) {
        int modificar = 0;
        try {

            Connection conexion = conectar();
            String datos = col_codigo+"=?,"+col_idEmpleado + "=?," + col_estado + "=?," + col_fecha + "=?," + col_direccion + "=?,"+col_canton+"=?,"+col_provincia+"=?,"+ col_idProduct + "=?,"+col_cant+"=?,"+ col_idFactura+"=?";
            String query = "UPDATE " + tbPedidos + " SET " + datos + " WHERE " + col_idPedido + " = ?";

            PreparedStatement ps = conexion.prepareStatement(query);
           
            ps.setString(1, pedido.getCodigo());
            // ps.setInt(2, pedido.getId_empleado());
            ps.setString(3, pedido.getEstado_pedido());
            ps.setDate(4, pedido.getFecha());
            ps.setString(5, pedido.getDireccion_pedido());
            ps.setString(6, pedido.getCanton());
            ps.setString(7, pedido.getProvincia());
            // ps.setInt(8, pedido.getId_producto());
            ps.setInt(9, pedido.getCantidad());
            // ps.setInt(10, pedido.getFactura());
            
            ps.setInt(11, pedido.getId_pedido());
            
            
            modificar = ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return (modificar == 1);
    }

    public static void main(String[] args) {
        // Pedido p = new Pedido();
    //    // p.setId_Factura(11);
    //     p.setId_empleado(1);
    //    // p.setId_producto(1);

    //     LocalDate ld = LocalDate.of(2024, 4, 15);
    //     p.setFecha(Date.valueOf(ld));
    //     p.setEstado_pedido("completado");
    //     p.setDireccion_pedido("puerto viejo, sarapiqui, heredia");

    //     //f.setFecha(Date.valueOf(ld));
    //     //System.out.println(new DataPedido().Insertar(p));
        //p.setId_pedido(2);
        //new DataPedido().Eliminar(p);.
        //new DataPedido().Actualizar(p);
    }
}
