/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.services.ClienteServices;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("telefono") String telefono,
            @RequestParam("credencial") String credencial, @RequestParam("cedula") String cedula) {

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setTelefono(telefono);
        cliente.setCedula(cedula);
        // Cliente = 0
        // Empleado = 1
        int credencialInt = 1;
        if (credencial.equalsIgnoreCase("Cliente")) {
            credencialInt = 0;
        }
        cliente.setCredencial(credencialInt);
        return ResponseEntity.ok().body(ClienteServices.verificarPreAgregar(cliente));
    }

    @GetMapping("/buscar")
    public String buscarCliente(@RequestParam(value = "textoBuscar", required = true) String cedula, Model model) {
        Cliente cliente = ClienteServices.getClientePorCedula(cedula);
        LinkedList<Cliente> clientes = new LinkedList<>();

        if (cliente != null) {
            clientes.add(cliente);
        }

        model.addAttribute("clientes", clientes);
        return "clientes/resultadoBusquedaCliente";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<Cliente> clientes = ClienteServices.getClientes();
        LinkedList<Cliente> clientessPagina = new ClienteServices().obtenerRegistrosPaginados(page, pageSize, clientes);

        int ultimaPagina = (int) Math.ceil((double) clientes.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("clientes", clientessPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo

        return "clientes/cliente";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarCliente(@ModelAttribute("clienteItem") Cliente cliente) {
        return ResponseEntity.ok().body(ClienteServices.verificarPreModificar(cliente));
    }
    
    @GetMapping("/eliminar")
    @ResponseBody
    public Map<String, Object> eliminarCliente(@RequestParam("idUsuario_Cliente") int idUsuario_Cliente) {
        boolean eliminadoExitosamente = ClienteServices.eliminar(idUsuario_Cliente);
        Map<String, Object> response = new HashMap<>();
        response.put("success", eliminadoExitosamente);
        if (!eliminadoExitosamente) {
            response.put("message", "No se pudo eliminar porque está asociado a un envío");
        }
        return response;
    }

}
