package cr.ac.una.proyecto_progra4.controller;



import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.domain.Factura;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.domain.Producto;
import static cr.ac.una.proyecto_progra4.services.EmpleadosServices.getEmpleados;
import cr.ac.una.proyecto_progra4.services.IFacturaServices;
import cr.ac.una.proyecto_progra4.services.IPedidoServices;
import cr.ac.una.proyecto_progra4.services.IProductoServices;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author GONZALO DORMOS RODRIGUEZ
 */
@Controller
@RequestMapping("/pedidos")
public class controllerPedidos {

    public void print(String texto) {
        System.out.println(texto);
    }

    @Autowired
    private IFacturaServices ifs;

    @Autowired
    private IProductoServices ips;

    @Autowired 
    private IPedidoServices Ipedido;

    @GetMapping("listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) throws SQLException {
        List<Pedido> auxiliar =  Ipedido.getPedidos();//new PedidoServices().lista_Pedido();
        print(">>"+auxiliar.size());
        //Listas necesarias para los forms
        List<Producto> productos = ips.getProductos();
        List<Factura> facturas = ifs.getFacturas();
        List<Empleado> empleados = getEmpleados();

        LinkedList<Pedido> pedidos = Ipedido.ObtenerRegistrosPaginados(page, pageSize, auxiliar);//new PedidoServices().obtenerRegistrosPaginados(page, pageSize, auxiliar);
        int ultimaPagina = (auxiliar != null) ? ((int) Math.ceil((double) auxiliar.size() / pageSize) - 1) : 0;
 

       print("Empleado del pedido "+pedidos.get(1).getEmpleado().getNombre());


        model.addAttribute("productos", productos);
        model.addAttribute("facturas", facturas);
        model.addAttribute("empleados", empleados);

        LocalDate localDate = LocalDate.now();
        Date fechaActual = Date.valueOf(localDate.toString());
        model.addAttribute("fechaActual", fechaActual);

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("listaP", pedidos);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo
        return "pedido/pedido";
    }

    @PostMapping("actualizar")
    public ResponseEntity<?> actualizar(
            @RequestParam("id") int id,
            @RequestParam("codigo") String codigo,
            @RequestParam("id_empleado") int id_empleado,
            @RequestParam("estado") String estado,
            @RequestParam("fecha") Date fecha,
            @RequestParam("direccion") String direccion,
            @RequestParam("provincia") String provincia,
            @RequestParam("canton") String canton,
            @RequestParam("id_p") int id_producto,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("id_f") int id_factura) {

        Empleado e = new Empleado();
        e.setIdEmpleado(id_empleado);

        Producto pd = new Producto();
        pd.setId(id_factura);

        Factura f = new Factura();
        f.setId_factura(id_factura);

        Pedido p = new Pedido();
        p.setId_pedido(id);
        p.setCodigo(codigo);

        p.setEmpleado(e);
        
        p.setEstado_pedido(estado);
        p.setFecha(fecha);
        p.setDireccion_pedido(direccion);
        p.setCanton(canton);
        p.setProvincia(provincia);
        p.setProducto(pd);
        p.setCantidad(cantidad);
        p.setFactura(f);




        return ResponseEntity.ok().body(Ipedido.Insertar_pedido(p));//new PedidoServices().modificar_Pedido(p));
    }

    @GetMapping("eliminar")
    public String Eliminar_pedido(@RequestParam("id") int id) {
        String vista = "redirect:/pedidos/listar";
        if (Ipedido.Eliminar_pedido(id)) {
            vista = "Error";
        }
        return vista;
    }

    @PostMapping("guardar")
    public ResponseEntity<?> guardar(
            @RequestParam("id_empleado") int id_empleado,
            @RequestParam("codigo") String codigo,
            @RequestParam("estado") String estado,
            @RequestParam("fecha") Date fecha,
            @RequestParam("direccion") String direccion,
            @RequestParam("provincia") String provincia,
            @RequestParam("canton") String canton,
            @RequestParam("id_p") int id_producto,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("id_f") int id_factura) {

        print("Llego a guardar");
        Empleado e = new Empleado();
        e.setIdEmpleado(id_empleado);

        Producto pd = new Producto();
        pd.setId(id_factura);

        Factura f = new Factura();
        f.setId_factura(id_factura);

        Pedido p = new Pedido();
        p.setCodigo(codigo);
        p.setEmpleado(e);
        p.setEstado_pedido(estado);
        p.setFecha(fecha);
        p.setDireccion_pedido(direccion);
        p.setCanton(canton);
        p.setProvincia(provincia);
        p.setProducto(pd);
        p.setCantidad(cantidad);
        p.setFactura(f);

        return ResponseEntity.ok().body(Ipedido.Insertar_pedido(p));//new PedidoServices().insertar_Pedido(p));
    }

}
