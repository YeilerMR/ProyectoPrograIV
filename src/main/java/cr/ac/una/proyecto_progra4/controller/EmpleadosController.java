/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;


import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.services.EmpleadosServices;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author kinco
 */
    @Controller
@RequestMapping("/empleados")
public class EmpleadosController {
    
    private final EmpleadosServices empleadosServices;

    @Autowired
    public EmpleadosController(EmpleadosServices empleadosServices) {
        this.empleadosServices = empleadosServices;
    }
    
    @GetMapping("/agregar")
    public String agregar() {
        return "form_empleados";
    }
    
    @GetMapping("/listar")
    public String listar(Model model) {
        System.out.println("listado de empleados");
        LinkedList<Empleado> empleados = EmpleadosServices.getEmpleados();
        model.addAttribute("empleados", empleados);
        return "lista_empleados"; 
    }

    @PostMapping("/eliminar/{id}")
public String eliminar(@PathVariable int id){
    String vista = "redirect:/empleados/listar";
    System.out.println("ident= " + id);
    boolean elimino = EmpleadosServices.eliminar(id);
    System.out.println("elimino? " + elimino);
    if (!elimino){
        return "error" ;
    }
    System.out.println("vista=" + vista);
    return vista;
}

    
    @PostMapping("/insertar")
    public String insertar(@ModelAttribute Empleado empleado) {
        boolean resultado = empleadosServices.insertar(empleado);
        if (resultado) {
            return "redirect:/empleados/listar";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/guardar")
    public ModelAndView guardar(@ModelAttribute Empleado empleado) {
        ModelAndView modelAndView = new ModelAndView();
        boolean resultado = empleadosServices.insertar(empleado);
        if (resultado) {
            modelAndView.setViewName("redirect:/empleados/listar");
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
    
    @PostMapping("/editar/{id}")
public String editar(@PathVariable int id, @ModelAttribute Empleado empleado) {
    empleado.setIdEmpleado(id); 
    boolean editado = EmpleadosServices.editar(empleado);
    if (editado) {
        return "redirect:/empleados/listar"; 
    } else {
        return "error"; 
    }
}

}
