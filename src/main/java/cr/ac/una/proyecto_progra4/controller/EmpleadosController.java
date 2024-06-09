/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.domain.Usuario;
import cr.ac.una.proyecto_progra4.services.EmpleadoServices;
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
@RequestMapping("/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadoServices empleadoServices;

    @GetMapping("/empleado")
    public String get() {
        List<Empleado> empleados = empleadoServices.getEmpleados();
        for (Empleado c : empleados) {
            System.out.println("---------------------------/n");
            System.out.println("ID Empleado: " + c.getIdEmpleado());
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
        Empleado empleadoExistenteCedula = empleadoServices.getEmpleadoPorCedula(cedula);
        Empleado empleadoExistenteEmail = empleadoServices.getEmpleadoPorEmail(email);
        Empleado empleadoExistenteTelefono = empleadoServices.getEmpleadoPorTelefono(telefono);

        if (empleadoExistenteCedula != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro empleado\"}");
        } else if (empleadoExistenteEmail != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El email ya está asociado a otro empleado\"}");
        } else if (empleadoExistenteTelefono != null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El teléfono está asociado a otro empleado\"}");
        }

        // Crear y configurar el objeto Usuario
        Usuario empleadoUsuario = new Usuario();
        empleadoUsuario.setNombre(nombre);
        empleadoUsuario.setApellidos(apellidos);
        empleadoUsuario.setEmail(email);
        empleadoUsuario.setPassword(password);
        empleadoUsuario.setCedula(cedula);
        empleadoUsuario.setTelefono(telefono);

        // Asignar la credencial adecuada
        if (credencial.equalsIgnoreCase("Empleado")) {
            empleadoUsuario.setCredencial(1);
        } else {
            empleadoUsuario.setCredencial(0);
        }

        // Crear y asociar el objeto Cliente
        Empleado empleado = new Empleado();
        empleado.setUsuario(empleadoUsuario);

        // Agregar el cliente
        boolean agregadoExitosamente = empleadoServices.agregar(empleado);
        if (agregadoExitosamente) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Empleado agregado exitosamente\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Error al agregar el empleado\"}");
        }
    }

    @GetMapping("/buscar")
    public String buscarEmpleado(@RequestParam(value = "textoBuscar", required = true) String cedula, Model model) {
        Empleado empleado = empleadoServices.getEmpleadoPorCedula(cedula);
        LinkedList<Empleado> empleados = new LinkedList<>();

        if (empleado != null) {
            empleados.add(empleado);
        } else {
            // Cliente no encontrado, devuelve una lista vacía
            empleados = new LinkedList<>();
        }

        model.addAttribute("empleados", empleados);
        return "empleados/resultadoBusquedaEmpleado";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        List<Empleado> empleados = empleadoServices.getEmpleados();
        List<Empleado> empleadosPagina = empleadoServices.obtenerRegistrosPaginados(page, pageSize);
        int ultimaPagina = (int) Math.ceil((double) empleados.size() / pageSize) - 1;
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("empleados", empleadosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "clientes/cliente";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarCliente(@ModelAttribute("empleadpItem") Empleado empleado,
            @RequestParam("idEmpleado") int idEmpleado) {

        // Obtener el empleado existente por ID
        Empleado empleadoExistente = empleadoServices.getEmpleadoPorID(idEmpleado);

        // Verificar si el cliente existe
        if (empleadoExistente == null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El empleado no existe\"}");
        }

        // Actualizar los datos del cliente existente con los datos recibidos
        empleadoExistente.getUsuario().setNombre(empleado.getUsuario().getNombre());
        empleadoExistente.getUsuario().setApellidos(empleado.getUsuario().getApellidos());
        empleadoExistente.getUsuario().setEmail(empleado.getUsuario().getEmail());
        empleadoExistente.getUsuario().setPassword(empleado.getUsuario().getPassword());
        empleadoExistente.getUsuario().setCedula(empleado.getUsuario().getCedula());
        empleadoExistente.getUsuario().setTelefono(empleado.getUsuario().getTelefono());

        // Verificar si algún otro cliente tiene los mismos datos
        Empleado empleadoExistenteCedula = empleadoServices.getEmpleadoPorCedula(empleado.getUsuario().getCedula());
        Empleado empleadoExistenteEmail = empleadoServices.getEmpleadoPorEmail(empleado.getUsuario().getEmail());
        Empleado empleadoExistenteTelefono = empleadoServices.getEmpleadoPorTelefono(empleado.getUsuario().getTelefono());

        // Verificar si algún otro empleado tiene la misma cédula, email o teléfono
        if (empleadoExistenteCedula != null && empleadoExistenteCedula.getIdEmpleado() != idEmpleado) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro empleado\"}");
        } else if (empleadoExistenteEmail != null && empleadoExistenteEmail.getIdEmpleado() != idEmpleado) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El email ya está asociado a otro empleado\"}");
        } else if (empleadoExistenteTelefono != null && empleadoExistenteTelefono.getIdEmpleado() != idEmpleado) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"El teléfono está asociado a otro empleado\"}");
        }

        // Guardar los cambios en el cliente
        boolean actualizadoExitosamente = empleadoServices.agregar(empleadoExistente);
        if (actualizadoExitosamente) {
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Empleado actualizado exitosamente\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Error al actualizar el empleado\"}");
        }
    }

    @GetMapping("/eliminar")
    @ResponseBody
    public Map<String, Object> eliminarEmpleado(@RequestParam("id_Empleado") int id_Empleado) {
        boolean eliminadoExitosamente = empleadoServices.eliminar(id_Empleado);
        Map<String, Object> response = new HashMap<>();
        response.put("success", eliminadoExitosamente);
        if (!eliminadoExitosamente) {
            response.put("message", "No se pudo eliminar porque está asociado a un envío");
        }
        return response;
    }
}

