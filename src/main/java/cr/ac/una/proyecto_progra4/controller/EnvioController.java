/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.domain.Envio;
import cr.ac.una.proyecto_progra4.services.ClienteServices;
import cr.ac.una.proyecto_progra4.services.EnvioServices;
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

/**
 *
 * @author Aaron
 */
@Controller
@RequestMapping("/envios")
public class EnvioController {

    @Autowired
    private EnvioServices envioServices;

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
        envio.setIdPedido(idPedido);
        envio.setIdCliente(idCliente);
        envio.setFechaEnvio(fechaDeEnvio);
        envio.setObservacion(observacion);
        envio.setDireccionEnvio(direccionEnvio);
        envio.setEstadoEnvio(estadoEnvio);
        return ResponseEntity.ok().body(envioServices.verificarPreAgregar(envio));
    }

    @GetMapping("/buscar")
    public String buscarClientePorCedula(@RequestParam("codigoEnvio") String codigo, Model model) {
        Envio envio = envioServices.getEnvioPorCodigo(codigo);
        LinkedList<Cliente> clientesEnvios = ClienteServices.getClientesConEnvios();
        for (Cliente clienteEnvio : clientesEnvios) {
            if (clienteEnvio.getIdCliente() == envio.getIdCliente()) {
                model.addAttribute("clienteEnvio", clienteEnvio);
            }
        }
        if (envio != null) {
            model.addAttribute("envioEncontrado", envio);
            return "envios/resultadoBusquedaEnvio";
        } else {
            model.addAttribute("error", "No se encontró ningún envio con ese código.");
            return "error";
        }
    }

    @GetMapping("/listar")
    public String mostrarFormularioEnvio(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        List<Envio> envios = envioServices.getEnvios();
        LinkedList<Cliente> clientesConEnvios = ClienteServices.getClientesConEnvios();
        LinkedList<Cliente> clientesListaTotal = ClienteServices.getClientes();
        List<Envio> enviosPagina = envioServices.obtenerRegistrosPaginados(page, pageSize);
        //LinkedList<Pedido> pedidosListaTotal = PedidoServices.getPedidos();

        int ultimaPagina = (int) Math.ceil((double) envios.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("envios", enviosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("clientes", clientesConEnvios);
        model.addAttribute("clientesListaTotal", clientesListaTotal);
        //model.addAttribute("pedidosListaTotal", clientesListaTotal);

        return "envios/envio";
    }

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("idEnvio_Modificar") int idEnvio, Model model) {
        Envio envio = envioServices.getEnvioPorID(idEnvio);
        model.addAttribute("envio", envio);
        return "envios/editarEnvio";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarEnvio(@ModelAttribute("envio") Envio envio) {
        System.out.println("ID-> " + envio.getIdEnvio());
        System.out.println("ID PEDIDO-> " + envio.getIdPedido());
        System.out.println("ID CLIENTE-> " + envio.getIdCliente());
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
