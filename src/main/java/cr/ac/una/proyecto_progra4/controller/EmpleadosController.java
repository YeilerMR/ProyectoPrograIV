/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.services.EmpleadosServices;
import java.util.LinkedList;
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
 * @author kinco
 */
@Controller
@RequestMapping("/empleados")
public class EmpleadosController {
    
   @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("telefono") String telefono,
            @RequestParam("credencial") String credencial, @RequestParam("cedula") String cedula) {

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        empleado.setEmail(email);
        empleado.setPassword(password);
        empleado.setTelefono(telefono);
        empleado.setCedula(cedula);
        // Cliente = 0
        // Empleado = 1
        int credencialInt =1;
        if (credencial.equalsIgnoreCase("Empleado")) {
            credencialInt = 1;
        }
        empleado.setCredencial(credencialInt);
        return ResponseEntity.ok().body(EmpleadosServices.verificarPreAgregar(empleado));

    }

       @GetMapping("/buscar")
    public String buscarEmpleado(@RequestParam(value = "textoBuscar", required = true) String cedula, Model model) {
        Empleado empleado = EmpleadosServices.getEmpleadoPorCedula(cedula);
        LinkedList<Empleado> empleados = new LinkedList<>();

        if (empleado != null) {
            empleados.add(empleado);
        }

        model.addAttribute("empleados", empleados);
        return "empleados/resultadoBusquedaEmpleado";
    }
    
    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<Empleado> empleados = EmpleadosServices.getEmpleados();
        LinkedList<Empleado> empleadosPagina = new EmpleadosServices().obtenerRegistrosPaginados(page, pageSize, empleados);

        int ultimaPagina = (int) Math.ceil((double) empleados.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("empleados", empleadosPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo

        return "empleados/empleado";
    }
    
    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarEmpleado(@ModelAttribute("empleadoItem") Empleado empleado) {
        return ResponseEntity.ok().body(EmpleadosServices.verificarPreModificar(empleado));
    }

    @GetMapping("/eliminar")
    public String eliminarEmpleado(@RequestParam("idUsuario_Empleado") int idUsuario_Empleado) {
        boolean eliminadoExitosamente = EmpleadosServices.eliminar(idUsuario_Empleado);
        if (eliminadoExitosamente) {
            return "redirect:/empleados/listar";
        } else {
            return "empleados";
        }
    }

}
