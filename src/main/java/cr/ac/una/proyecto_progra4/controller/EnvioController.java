/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.domain.Envio;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.services.ClienteServices;
import cr.ac.una.proyecto_progra4.services.EnvioServices;
import cr.ac.una.proyecto_progra4.services.PedidoServices;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioServices envioServices;
    @Autowired
    private ClienteServices clienteServices;
    @Autowired
    private PedidoServices pedidoServices;

    @PostMapping("/guardar")
    public ResponseEntity<String> save(@RequestParam("codigoEnvio_Envio") String codigoEnvio,
            @RequestParam("idPedido_Envio") int idPedido,
            @RequestParam("idCliente_Envio") int idCliente,
            @RequestParam("observacion_Envio") String observacion,
            @RequestParam("direccionEnvio_Envio") String direccionEnvio,
            @RequestParam("fechaEnvio_Envio") LocalDateTime fechaDeEnvio,
            @RequestParam("estadoEnvio_Envio") String estadoEnvio) {

        System.out.println("ID CLIENTE -> " + idCliente);

        Envio envio = new Envio();
        envio.setCodigoEnvio(codigoEnvio);
        // Pedido del Envio
        Pedido pedido = new Pedido();
        pedido.setId_pedido(idPedido);
        envio.setPedido(pedido);
        // Cliente del Envio
        Cliente clienteEnvio = new Cliente();
        clienteEnvio.setIdCliente(idCliente);
        envio.setCliente(clienteEnvio);
        envio.setFechaEnvio(fechaDeEnvio);
        envio.setObservacion(observacion);
        envio.setDireccionEnvio(direccionEnvio);
        envio.setEstadoEnvio(estadoEnvio);
        return ResponseEntity.ok().body(envioServices.verificarPreAgregar(envio));
    }

    @GetMapping("/buscar")
    public String buscarEnvio(@RequestParam(value = "textoBuscar", required = true) String codigo, Model model) {
        Envio envio = envioServices.getEnvioPorCodigo(codigo);
        LinkedList<Envio> envios = new LinkedList<>();
        List<Cliente> clientesConEnvios = clienteServices.getClientesConEnvios();
        List<Cliente> clientesListaTotal = clienteServices.getClientes();
        List<Pedido> pedidosListaTotal = pedidoServices.lista_Pedido();
        if (envio != null) {
            envios.add(envio);
        }

        model.addAttribute("envios", envios);
        model.addAttribute("clientesListaTotal", clientesListaTotal);
        model.addAttribute("clientes", clientesConEnvios);
        model.addAttribute("pedidosListaTotal", pedidosListaTotal);

        return "envios/resultadoBusquedaEnvio";
    }

    @GetMapping("/listar")
    public String mostrarFormularioEnvio(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        List<Envio> envios = envioServices.getEnvios();
        List<Cliente> clientesConEnvios = clienteServices.getClientesConEnvios();
        List<Cliente> clientesListaTotal = clienteServices.getClientes();
        List<Envio> enviosPagina = envioServices.obtenerRegistrosPaginados(page, pageSize);
        List<Pedido> pedidosListaTotal = pedidoServices.lista_Pedido();

        int ultimaPagina = (int) Math.ceil((double) envios.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("envios", enviosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("clientes", clientesConEnvios);
        model.addAttribute("clientesListaTotal", clientesListaTotal);
        model.addAttribute("pedidosListaTotal", pedidosListaTotal);

        return "envios/envio";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarEnvio(@ModelAttribute("envio") Envio envio,
            @RequestParam("idPedido_Envio") int idPedido,
            @RequestParam("idCliente_Envio") int idCliente
    ) {

        Pedido pedido = new Pedido();
        pedido.setId_pedido(idPedido);
        envio.setPedido(pedido);
        envio.getPedido().setId_pedido(idPedido);
        // Cliente del Envio
        Cliente clienteEnvio = new Cliente();
        clienteEnvio.setIdCliente(idCliente);
        envio.setCliente(clienteEnvio);
        envio.getCliente().setIdCliente(idCliente);

        System.out.println("ID-> " + envio.getIdEnvio());
        System.out.println("ID PEDIDO-> " + envio.getPedido().getId_pedido());
        System.out.println("ID CLIENTE-> " + envio.getCliente().getIdCliente());
        return ResponseEntity.ok().body(envioServices.verificarPreModificar(envio));
    }

    @GetMapping("/eliminar")
    public String eliminarEnvio(@RequestParam("idEnvio") int idEnvio) {
        boolean eliminadoExitosamente = envioServices.eliminar(idEnvio);
        if (eliminadoExitosamente) {
            return "redirect:/envios/listar";
        } else {
            return "clientes";
        }
    }
}
