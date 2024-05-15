package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.domain.Factura;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.domain.Producto;
import static cr.ac.una.proyecto_progra4.services.ClienteServices.getClientes;
import static cr.ac.una.proyecto_progra4.services.EmpleadosServices.getEmpleadoPorID;
import static cr.ac.una.proyecto_progra4.services.EmpleadosServices.getEmpleados;
import cr.ac.una.proyecto_progra4.services.IFacturaServices;
import cr.ac.una.proyecto_progra4.services.IProductoServices;
import cr.ac.una.proyecto_progra4.services.PedidoServices;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

    //@Autowired
    // private IPedidoServices iPedido;
    @GetMapping("listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<Pedido> auxiliar = new PedidoServices().lista_Pedido();

        //Listas necesarias para los forms
        List<Producto> productos = ips.getProductos();
        List<Factura> facturas = ifs.getFacturas();
        List<Empleado> empleados = getEmpleados();

        LinkedList<Pedido> pedidos = new PedidoServices().obtenerRegistrosPaginados(page, pageSize, auxiliar);
        int ultimaPagina = (auxiliar != null) ? ((int) Math.ceil((double) auxiliar.size() / pageSize) - 1) : 0;
        
//        // LinkedList<Cliente> clientes = new LinkedList<>();
//        if (pedidos != null) {
//            for (int i = 0; i < pedidos.size(); i++) {
//                
//                print("Empleado "+getEmpleadoPorID(pedidos.get(i).getId_empleado()).getNombre());
//                print("Factura  "+ifs.getFacturaById(pedidos.get(i).getFactura()).getCodigo_factura());
//                
//                //pedidos.get(i).setEmpleado(getEmpleadoPorID(pedidos.get(i).getId_empleado()));
//                if (productos != null) {
//                    for (Producto p1 : productos) {
//                        if (p1.getId() == pedidos.get(i).getId_producto()) {
//                            print("producto agregado");
//                            pedidos.get(i).setProducto(p1);
//                            break;
//                        }
//                    }
//                }
//                //pedidos.get(i).setFacturaObjt(ifs.getFacturaById(pedidos.get(i).getFactura()));
//                
//                Pedido p1 = pedidos.get(i);
//                //print("Pedido "+p1.getEmpleado().getNombre()+" "+p1.getFacturaObjt().getCodigo_factura()+" "+p1.getProducto().getNombre());
//                
//            }
//        }
        
//        for(Pedido p1: pedidos){
//            print("Pedido "+p1.getEmpleado().getNombre()+" "+p1.getFacturaObjt().getCodigo_factura()+" "+p1.getProducto().getNombre());
//        }
//        
        
        
 
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

        Pedido p = new Pedido();
        p.setId_pedido(id);
        p.setCodigo(codigo);
        p.setId_empleado(id_empleado);
        p.setEstado_pedido(estado);
        p.setFecha(fecha);
        p.setDireccion_pedido(direccion);
        p.setCanton(canton);
        p.setProvincia(provincia);
        p.setId_producto(id_producto);
        p.setCantidad(cantidad);
        p.setFactura(id_factura);
        return ResponseEntity.ok().body(new PedidoServices().modificar_Pedido(p));
    }

    @GetMapping("eliminar")
    public String Eliminar_pedido(@RequestParam("id") int id) {
        String vista = "redirect:/pedidos/listar";
        if (!new PedidoServices().eliminar_Pedido(id)) {
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
        Pedido p = new Pedido();

        p.setCodigo(codigo);
        p.setId_empleado(id_empleado);

        p.setEstado_pedido(estado);
        p.setFecha(fecha);

        p.setProvincia(provincia);
        p.setCanton(canton);
        p.setDireccion_pedido(direccion);

        p.setCantidad(cantidad);
        p.setId_producto(id_producto);
        p.setFactura(id_factura);

        return ResponseEntity.ok().body(new PedidoServices().insertar_Pedido(p));
    }

}
