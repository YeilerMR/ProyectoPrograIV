/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Oferta;
import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.services.OfertaServices;
import cr.ac.una.proyecto_progra4.services.ProductoServices;
import java.sql.Date;
import java.util.LinkedList;
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
 * @author yeile
 */
@Controller
@RequestMapping("/ofertas")
public class OfertaController {

    @GetMapping("/agregar")
    public String crearOferta() {
        return "ofertas/form_Ofertas";
    }

    @GetMapping({"/guardar"})
    public String save(@RequestParam("codigoOferta") String codigoOferta, @RequestParam("codigoProducto") String codigoProducto,
            @RequestParam("tipoOferta") String tipoOferta, @RequestParam("descuentoOferta") int descuentoOferta,
            @RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin,
            @RequestParam("estado") boolean estado) {
        System.out.println("Fecha inicio" + fechaInicio);
        Oferta oferta = new Oferta(codigoOferta, codigoProducto, tipoOferta, descuentoOferta, fechaInicio, fechaFin, estado);
        OfertaServices.agregarOferta(oferta);
        return "redirect:/ofertas/listar";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model, Model model2) {
        LinkedList<Oferta> ofertas = OfertaServices.getOfertas();
        LinkedList<Producto> productos= ProductoServices.getProductos();
        //System.out.println("estoy en mostrarlistaOfertas");
        model.addAttribute("ofertas", ofertas);
        model2.addAttribute("productos", productos);
        return "ofertas/form_Ofertas";
    }

    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo, RedirectAttributes redAttributes) {
        boolean elimino = OfertaServices.eliminar(codigo);
        System.out.println("elimino?" + elimino);

        if (elimino) {
            redAttributes.addFlashAttribute("mensajeExitoso", "Producto eliminado correctamente");
        } else {
            redAttributes.addFlashAttribute("mensajeError", "El producto no pudo ser eliminado");
            return "redirect:/error";
        }
        return "redirect:/ofertas/listar";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Oferta oferta) {
        System.out.println("Metodo Actualizar\nCodigoOferta: "+oferta.getCodigoOferta()+"\nCodigoProducto: "+oferta.getCodigoProducto());
        
        //Oferta oferta= new Oferta(codigoOferta, codigoProducto, tipoOferta, descuentoOferta, fechaInicio, fechaFin, estado);
        //oferta.setCodigoOferta(codigoOferta);
        OfertaServices.modificar(oferta);
        return "redirect:/ofertas/listar"; 
    }
    /*
    @GetMapping({"/guardar"})
    public String save(@RequestParam("codigoOferta") String codigoOferta, @RequestParam("codigoProducto") String codigoProducto,
            @RequestParam("tipoOferta") String tipoOferta, @RequestParam("descuentoOferta") int descuentoOferta,
            @RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin,
            @RequestParam("estado") boolean estado) {
        System.out.println("Fecha inicio" + fechaInicio);
        Oferta oferta = new Oferta(codigoOferta, codigoProducto, tipoOferta, descuentoOferta, fechaInicio, fechaFin, estado);
        OfertaServices.agregarOferta(oferta);
        return "redirect:/productos/listar";
    }*/

}
