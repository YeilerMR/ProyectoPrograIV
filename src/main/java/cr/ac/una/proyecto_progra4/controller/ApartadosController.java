/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Apartado;
import cr.ac.una.proyecto_progra4.services.ApartadosServices;
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
@RequestMapping("/apartados")
public class ApartadosController {
    
    private final ApartadosServices apartadosServices;

    @Autowired
    public ApartadosController(ApartadosServices apartadosServices) {
        this.apartadosServices = apartadosServices;
    }
    
   @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar() {
        return "form_apartados";
    }
    
    
    @GetMapping("/listar")
  public String listar(Model model) {
    System.out.println("listado apartados");
    LinkedList<Apartado> apartados = ApartadosServices.getApartados();
    model.addAttribute("apartados", apartados);
    return "lista_apartados.html";
  }

    
    public String buscar(){
        return "vista";
    }
        
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id){
        String vista = "redirect:/apartados/listar";
        System.out.println("ident= " + id);
        boolean elimino = ApartadosServices.eliminar(id);
        System.out.println("elimino? " + elimino);
        if (!elimino){
            return "error" ;
        }
        System.out.println("vista=" + vista);
        return vista;
    }
    
    @PostMapping("/insertar")
    public String insertar(@ModelAttribute Apartado apartado) {
        boolean resultado = ApartadosServices.insertar(apartado);
        if (resultado) {
            return "redirect:/apartados/listar";
        } else {
            return "error";
        }
    }
    
    @PostMapping("/guardar")
    public ModelAndView guardar(@ModelAttribute Apartado apartado) {
        ModelAndView modelAndView = new ModelAndView();
        boolean resultado = ApartadosServices.insertar(apartado);
        if (resultado) {
            modelAndView.setViewName("redirect:/apartados/listar");
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
    
     @PostMapping("/editar/{id}")
    public String editar(@PathVariable int id, @ModelAttribute Apartado apartado) {
    apartado.setIdApartado(id); 
    boolean editado = ApartadosServices.editar(apartado);
    if (editado) {
        return "redirect:/empleados/listar"; 
    } else {
        return "error"; 
    }
}
}