/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.services.IOrdenDeCompraService;
import cr.ac.una.proyecto_progra4.services.IProveedoresService;
import cr.ac.una.proyecto_progra4.services.OrdenDeCompraServices;
import cr.ac.una.proyecto_progra4.services.PedidoServices;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adam Acuña
 */
@Controller
@RequestMapping("/ordenes_compra")
public class OredenDeCompraController {
    
    @Autowired
    private IProveedoresService proveedorSer;
    
    @Autowired
    private IOrdenDeCompraService ordenSer;
    
    private List<Proveedor> proveedores() {
        return proveedorSer.getProveedores();
    }
    
    private List<OrdenDeCompra> ordenes(){
        return ordenSer.getOrdenes();
    }
    private static LinkedList<Pedido> pedidos = new PedidoServices().lista_Pedido();
    //private static LinkedList<OrdenDeCompra> ordenes = new OrdenDeCompraServices().listaOrdenes();

    /*public void actualizarListaOrdenes() {
        ordenes = new OrdenDeCompraServices().listaOrdenes();
    }*/

    @PostMapping("registrar")
    public ResponseEntity<?> registrarOrden(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam("combobox-pedido") int idPedido, @RequestParam("combobox-proveedores") int idProveedor, @RequestParam("fecha-orden") Date fechaOrden, @RequestParam("fecha-entrega") Date fechaEntrega, @RequestParam("estado") String estado) {
        Proveedor proveedor = new Proveedor();
        Pedido pedido = new Pedido();
        proveedor.setIdProveedor(idProveedor);
        pedido.setId_pedido(idPedido);

        OrdenDeCompra orden = new OrdenDeCompra();
        orden.setIdPedido(pedido);
        orden.setIdProveedor(proveedor);
        orden.setFechaOrden(fechaOrden);
        orden.setFechaEntrega(fechaEntrega);
        orden.setEstadoOrden(estado);
        
        return ResponseEntity.ok().body(ordenSer.guardar(orden));
    }

    @GetMapping("listar")
    public String listarOrdenes(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<OrdenDeCompra> ordenDeCompraPagina = new OrdenDeCompraServices().obtenerRegistrosPaginados(page, pageSize, ordenes());

        int ultimaPagina = (int) Math.ceil((double) ordenes().size() / pageSize) - 1;
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("proveedores", proveedores());
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("ordenes", ordenDeCompraPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo

        return "/OrdenDeCompra/orden_compra";
    }

    @GetMapping("editar")
    public String editar(Model model, @RequestParam("orden") String numeroReferencia) {
        for (OrdenDeCompra orden : ordenes()) {
            //System.out.println(orden.getNumeroReferencia());
            if (orden.getNumeroReferencia().equals(numeroReferencia)) {
                model.addAttribute("orden", orden);
                model.addAttribute("pedidos", pedidos);
                model.addAttribute("proveedores", proveedores());
            }
        }
        return "OrdenDeCompra/editar_orden";
    }

    @PostMapping("editar_orden")
    public ResponseEntity<?> editarOrden(@RequestParam("combobox-pedidos") int idPedido, @RequestParam("combobox-proveedores") int idProveedor, @RequestParam("fecha-orden") Date fechaOrden, @RequestParam("fecha-entrega") Date fechaEntrega, @RequestParam("estado") String estado, @RequestParam("numero-referencia") String numeroReferencia) {
        Proveedor proveedor = new Proveedor();
        Pedido pedido = new Pedido();
        proveedor.setIdProveedor(idProveedor);
        pedido.setId_pedido(idPedido);
        /*if (new OrdenDeCompraServices().editarOrden(new OrdenDeCompra(pedido, proveedor, fechaOrden, fechaEntrega, estado, numeroReferencia))) {
            return "excito";
        } else {
            return "error";
        }*/
        return ResponseEntity.ok().body(ordenSer.actualizarOrden(numeroReferencia, new OrdenDeCompra(0, pedido, proveedor, fechaOrden, fechaEntrega, estado, numeroReferencia)));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOren(@RequestParam("orden") String numeroReferencia) {
        return ResponseEntity.ok().body(ordenSer.eliminar(numeroReferencia));
    }
}
