/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.services.OrdenDeCompraServices;
import cr.ac.una.proyecto_progra4.services.PedidoServices;
import cr.ac.una.proyecto_progra4.services.ProveedoresServices;
import java.sql.Date;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adam Acuña
 */
@Controller
@RequestMapping("/ordenes_compra")
public class OredenDeCompraController {

    private static LinkedList<Proveedor> proveedores = new ProveedoresServices().listaProveedores();
    private static LinkedList<Pedido> pedidos = new PedidoServices().lista_Pedido();
    private static LinkedList<OrdenDeCompra> ordenes = new OrdenDeCompraServices().listaOrdenes();

    public void actualizarListaOrdenes() {
        ordenes = new OrdenDeCompraServices().listaOrdenes();
    }

    @GetMapping("crear")
    public String crearOrdenCompra(Model model) {
        model.addAttribute("proveedores", proveedores);
        return "OrdenDeCompra/registrar_orden";
    }

    @GetMapping("registrar")
    public String registrarOrden(@RequestParam("combobox-pedido") int idPedido, @RequestParam("combobox-proveedores") int idProveedor, @RequestParam("fecha-orden") Date fechaOrden, @RequestParam("fecha-entrega") Date fechaEntrega, @RequestParam("estado") String estado, @RequestParam("numero-referencia") String numeroReferencia) {
        Proveedor proveedor = new Proveedor();
        Pedido pedido = new Pedido();
        proveedor.setIdProveedor(idProveedor);
        pedido.setId_pedido(idPedido);
        
        if (new OrdenDeCompraServices().crearOrdenCompra(new OrdenDeCompra(pedido, proveedor, fechaOrden, fechaEntrega, estado, numeroReferencia))) {
            actualizarListaOrdenes();
            return "excito";
        } else {
            return "error";
        }
    }
    
    @GetMapping("listar")
    public String listarOrdenes(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<OrdenDeCompra> ordenDeCompraPagina = new OrdenDeCompraServices().obtenerRegistrosPaginados(page, pageSize, ordenes);
        
        int ultimaPagina = (int) Math.ceil((double) ordenes.size() / pageSize) - 1;
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("ordenes", ordenDeCompraPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo
        
        return "/OrdenDeCompra/orden_compra";
    }

    @GetMapping("editar")
    public String editar(Model model, @RequestParam("orden") String numeroReferencia) {
        for (OrdenDeCompra orden : ordenes) {
            //System.out.println(orden.getNumeroReferencia());
            if (orden.getNumeroReferencia().equals(numeroReferencia)) {
                model.addAttribute("orden", orden);
            }
        }
        model.addAttribute("proveedores", proveedores);
        return "OrdenDeCompra/editar_orden";
    }

    @GetMapping("editar_orden")
    public String editarOrden(@RequestParam("combobox-pedido") int idPedido, @RequestParam("combobox-proveedores") int idProveedor, @RequestParam("fecha-orden") Date fechaOrden, @RequestParam("fecha-entrega") Date fechaEntrega, @RequestParam("estado") String estado, @RequestParam("numero-referencia") String numeroReferencia) {
        Proveedor proveedor = new Proveedor();
        Pedido pedido = new Pedido();
        proveedor.setIdProveedor(idProveedor);
        pedido.setId_pedido(idPedido);
        if (new OrdenDeCompraServices().editarOrden(new OrdenDeCompra(pedido, proveedor, fechaOrden, fechaEntrega, estado, numeroReferencia))) {
            actualizarListaOrdenes();
            return "excito";
        } else {
            return "error";
        }
    }

    @GetMapping("eliminar")
    public String eliminarOren(@RequestParam("orden") String numeroReferencia) {
        if (new OrdenDeCompraServices().eliminarOrden(numeroReferencia)) {
            actualizarListaOrdenes();
            return "excito";
        } else {
            return "error";
        }
    }
}
