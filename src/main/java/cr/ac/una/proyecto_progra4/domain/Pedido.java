
package cr.ac.una.proyecto_progra4.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
/**
 * @author GONZALO DORMOS RODRIGUEZ g.d.r
 */
/*----- CREANDO PEDIDO -------*/
@Entity
@Table(name="pedido")
public class Pedido{ 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_Pedido")
    private int id_pedido;
    
    @Column(name="codigo_Pedido")
    private String codigo;   
//    @OneToOne
//    @JoinColumn(name = "usuario_id") Object obj;
    
//    @Column(name="idEmpleado_Pedido")
    private int id_empleado;
    
    @Column(name="estadoPedido_Pedido")
    private String estado_pedido;
    
    @Column(name="fechaPedido__Pedido")
//    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name="direccionEnvioPedido_Pedido")
    private String direccion_pedido;
    
    @Column(name="provinciaEnvioPedido_pedido")
    private String Provincia;
    
    @Column(name="cantonEnvio_Pedido")
    private String canton;
    
    
    //@Column(name="idProducto_Pedido")
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name ="id_Producto")
    private int id_producto;
    
    @Column(name="cantidadProductos_pedido")
    private int cantidad;
    
//  @Column(name="idFactura_Pedido")
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name ="id_Facturacion")
    private int factura;
    @ManyToOne
    @JoinColumn(name ="idProducto_Pedido")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name ="idEmpleado_Pedido")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name ="idFactura_Pedido")
    private Factura facturaObjt;
    
    
    public Pedido() {
    }

    public Pedido(int id_pedido, String codigo, int id_empleado, String estado_pedido, Date fecha, String direccion_pedido, String Provincia, String canton, int id_producto, int cantidad, int factura) {
        this.id_pedido = id_pedido;
        this.codigo = codigo;
        this.id_empleado = id_empleado;
        this.estado_pedido = estado_pedido;
        this.fecha = fecha;
        this.direccion_pedido = direccion_pedido;
        this.Provincia = Provincia;
        this.canton = canton;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Factura getFacturaObjt() {
        return facturaObjt;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setFacturaObjt(Factura facturaObjt) {
        this.facturaObjt = facturaObjt;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public void setEstado_pedido(String estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDireccion_pedido(String direccion_pedido) {
        this.direccion_pedido = direccion_pedido;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public String getEstado_pedido() {
        return estado_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDireccion_pedido() {
        return direccion_pedido;
    }

    public String getProvincia() {
        return Provincia;
    }

    public String getCanton() {
        return canton;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getFactura() {
        return factura;
    }  
}
