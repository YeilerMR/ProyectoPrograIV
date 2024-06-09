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
            @RequestParam("apellidos") String apellidos,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("telefono") String telefono,
            @RequestParam("credencial") String credencial,
            @RequestParam("cedula") String cedula) {

        // Verificar si algún otro cliente tiene los mismos datos
        Cliente clienteExistenteCedula = clienteServices.getClientePorCedula(cedula);
        Cliente clienteExistenteEmail = clienteServices.getClientePorEmail(email);
        Cliente clienteExistenteTelefono = clienteServices.getClientePorTelefono(telefono);

        if (clienteExistenteCedula != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro cliente\"}");
        } else if (clienteExistenteEmail != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El email ya está asociado a otro cliente\"}");
        } else if (clienteExistenteTelefono != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El teléfono está asociado a otro cliente\"}");
        }

        // Crear y configurar el objeto Usuario
        Usuario clienteUsuario = new Usuario();
        clienteUsuario.setNombre(nombre);
        clienteUsuario.setApellidos(apellidos);
        clienteUsuario.setEmail(email);
        clienteUsuario.setPassword(password);
        clienteUsuario.setCedula(cedula);
        clienteUsuario.setTelefono(telefono);

        // Asignar la credencial adecuada
        if (credencial.equalsIgnoreCase("Cliente")) {
            clienteUsuario.setCredencial(0);
        } else {
            clienteUsuario.setCredencial(1);
        }

        // Crear y asociar el objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setUsuario(clienteUsuario);

        // Agregar el cliente
        boolean agregadoExitosamente = clienteServices.agregar(cliente);
        if (agregadoExitosamente) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Cliente agregado exitosamente\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Error al agregar el cliente\"}");
        }
    }

    @GetMapping("/buscar")
    public String buscarCliente(@RequestParam(value = "textoBuscar", required = true) String cedula, Model model) {
        Cliente cliente = clienteServices.getClientePorCedula(cedula);
        LinkedList<Cliente> clientes = new LinkedList<>();

        if (cliente != null) {
            clientes.add(cliente);
        } else {
            // Cliente no encontrado, devuelve una lista vacía
            clientes = new LinkedList<>();
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
    public ResponseEntity<?> actualizarCliente(@ModelAttribute("clienteItem") Cliente cliente,
            @RequestParam("idCliente") int idCliente) {

        // Obtener el cliente existente por ID
        Cliente clienteExistente = clienteServices.getClientePorID(idCliente);

        // Verificar si el cliente existe
        if (clienteExistente == null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El cliente no existe\"}");
        }

        // Actualizar los datos del cliente existente con los datos recibidos
        clienteExistente.getUsuario().setNombre(cliente.getUsuario().getNombre());
        clienteExistente.getUsuario().setApellidos(cliente.getUsuario().getApellidos());
        clienteExistente.getUsuario().setEmail(cliente.getUsuario().getEmail());
        clienteExistente.getUsuario().setPassword(cliente.getUsuario().getPassword());
        clienteExistente.getUsuario().setCedula(cliente.getUsuario().getCedula());
        clienteExistente.getUsuario().setTelefono(cliente.getUsuario().getTelefono());

        // Verificar si algún otro cliente tiene los mismos datos
        Cliente clienteExistenteCedula = clienteServices.getClientePorCedula(cliente.getUsuario().getCedula());
        Cliente clienteExistenteEmail = clienteServices.getClientePorEmail(cliente.getUsuario().getEmail());
        Cliente clienteExistenteTelefono = clienteServices.getClientePorTelefono(cliente.getUsuario().getTelefono());

        // Verificar si algún otro cliente tiene la misma cédula, email o teléfono
        if (clienteExistenteCedula != null && clienteExistenteCedula.getIdCliente() != idCliente) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro cliente\"}");
        } else if (clienteExistenteEmail != null && clienteExistenteEmail.getIdCliente() != idCliente) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El email ya está asociado a otro cliente\"}");
        } else if (clienteExistenteTelefono != null && clienteExistenteTelefono.getIdCliente() != idCliente) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El teléfono está asociado a otro cliente\"}");
        }

        // Guardar los cambios en el cliente
        boolean actualizadoExitosamente = clienteServices.agregar(clienteExistente);
        if (actualizadoExitosamente) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Cliente actualizado exitosamente\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Error al actualizar el cliente\"}");
        }
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
