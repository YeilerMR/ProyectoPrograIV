/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Apartado;

import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.services.ApartadosServices;
import cr.ac.una.proyecto_progra4.services.ProductoServices;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author kinco
 */
@Controller
@RequestMapping("/apartados")
public class ApartadosController {
    
    @Autowired
    private ProductoServices productoServices;

        
    @GetMapping("/agregar")
    public String crearOferta() {
        return "apartados/form_Apartados";
    }
    
    @PostMapping({"/guardar"})
    public ResponseEntity<?> save( @RequestParam("idCliente") int idCliente,
            @RequestParam("idProducto") int idProducto, @RequestParam("fechaInicioApartado") Date fechaInicioApartado,
            @RequestParam("fechaFinalApartado") Date fechaFinalApartado, @RequestParam("abono") double abono,
            @RequestParam("estadoApartado") String estadoApartado) {
        System.out.println("fechaInicioApartado" + fechaInicioApartado);
        Apartado apartado = new Apartado(0, idCliente, idProducto, fechaInicioApartado, fechaFinalApartado, abono, estadoApartado);
        ApartadosServices.agregar(apartado);
        return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Apartado agregado exitosamente\"}");
    }
    
    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<Apartado> apartados = ApartadosServices.getApartados();
        LinkedList<Apartado> ApartadosPagina = new ApartadosServices().obtenerRegistrosPaginados(page, pageSize, apartados);
        List<Producto> productos = productoServices.getProductos();

        int ultimaPagina = (int) Math.ceil((double) apartados.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("apartados", ApartadosPagina);
        model.addAttribute("productos", productos);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "apartados/form_Apartados";
    }
    
    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable int codigo, RedirectAttributes redAttributes) {
        boolean elimino = ApartadosServices.eliminar(codigo);
        System.out.println("elimino?" + elimino);

        if (elimino) {
            redAttributes.addFlashAttribute("mensajeExitoso", "Apartado eliminado correctamente");
        } else {
            redAttributes.addFlashAttribute("mensajeError", "El Apartado no pudo ser eliminado");
            return "redirect:/error";
        }
        return "redirect:/apartados/listar";
    }
    
    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizar(@ModelAttribute Apartado apartado) {
        System.out.println("Metodo Actualizar\nCodigoApartado: " + apartado.getIdApartado() );

        
        ApartadosServices.modificar(apartado);
        return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Apartado modificado exitosamente\"}");
    }
}