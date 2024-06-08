/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.domain.Usuario;
import cr.ac.una.proyecto_progra4.services.ClienteServices;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Aaron
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping("/cliente")
    public String get() {
        List<Cliente> clientes = clienteServices.getClientes();
        for (Cliente c : clientes) {
            System.out.println("---------------------------/n");
            System.out.println("ID CLIENTE: " + c.getIdCliente());
            System.out.println("ID USUARIO: " + c.getUsuario().getId());
            System.out.println("NOMBRE USUARIO: " + c.getUsuario().getNombre());
            System.out.println("---------------------------/n");
        }

        return "HOLA";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("telefono") String telefono,
            @RequestParam("credencial") String credencial, @RequestParam("cedula") String cedula) {

        Cliente cliente = new Cliente();
        Usuario clienteUsuario = new Usuario();
        clienteUsuario.setNombre(nombre);
        clienteUsuario.setApellidos(apellidos);
        clienteUsuario.setEmail(email);
        clienteUsuario.setPassword(password);
        clienteUsuario.setCedula(cedula);
        clienteUsuario.setTelefono(telefono);
        if (credencial.equalsIgnoreCase("Cliente")) {
            clienteUsuario.setCredencial(0);
        } else {
            clienteUsuario.setCredencial(1);
        }
        return ResponseEntity.ok().body(clienteServices.verificarPreAgregar(cliente));
    }

    @GetMapping("/buscar")
    public String buscarCliente(@RequestParam(value = "textoBuscar", required = true) String cedula, Model model) {
        System.out.println("TEXTO" + cedula);
        Cliente cliente = clienteServices.getClientePorCedula(cedula);

        System.out.println(cliente.getUsuario().getNombre());
        LinkedList<Cliente> clientes = new LinkedList<>();

        if (cliente != null) {
            clientes.add(cliente);
        }

        model.addAttribute("clientes", clientes);
        return "clientes/resultadoBusquedaCliente";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        List<Cliente> clientes = clienteServices.getClientes();
        List<Cliente> clientessPagina = clienteServices.obtenerRegistrosPaginados(page, pageSize);
        int ultimaPagina = (int) Math.ceil((double) clientes.size() / pageSize) - 1;
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("clientes", clientessPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "clientes/cliente";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarCliente(@ModelAttribute("clienteItem") Cliente cliente, @RequestParam("idCliente") int idCliente) {

        //System.out.println("CLIENTE USUARIO CEDULA= " +cliente.getUsuario().getCedula());
        System.out.println("CLIENTE  ID= " + idCliente);
        System.out.println("CLIENTE  ID= " + cliente.getUsuario().getNombre());

        System.out.println((clienteServices.getClientePorCedula(cliente.getUsuario().getCedula())).getUsuario().getCedula());

        return ResponseEntity.ok().body(clienteServices.verificarPreModificar(cliente));
    }

    @GetMapping("/eliminar")
    @ResponseBody
    public Map<String, Object> eliminarCliente(@RequestParam("id_Cliente") int id_Cliente) {
        boolean eliminadoExitosamente = clienteServices.eliminar(id_Cliente);
        Map<String, Object> response = new HashMap<>();
        response.put("success", eliminadoExitosamente);
        if (!eliminadoExitosamente) {
            response.put("message", "No se pudo eliminar porque está asociado a un envío");
        }
        return response;
    }
}
