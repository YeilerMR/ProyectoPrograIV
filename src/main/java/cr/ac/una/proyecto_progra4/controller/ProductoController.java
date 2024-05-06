/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.services.ProductoServices;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/productos")
public class ProductoController {

    //direccion a la pagina que se quiera cargar.
    @GetMapping("/agregar")
    public String crearProducto() {
        return "productos/form_Productos";
    }
    //metodo que se carga

    @GetMapping({"/guardar"})
    public String save(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria, @RequestParam("calificacion") int calificacion,
            @RequestParam("precio") double precio, @RequestParam("venta") boolean venta, @RequestParam("codigo") String codigo) {

        System.out.println("Codigo Producto: " + codigo + "\nVenta: " + venta);
        Producto producto = new Producto(codigo, nombre, descripcion, categoria, calificacion, precio, venta);
        ProductoServices.agregarProductos(producto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model) {
        LinkedList<Producto> productos = ProductoServices.getProductos();
        System.out.println("estoy en mostrar lista" + productos.get(0).getNombre());
        model.addAttribute("productos", productos);
        return "productos/form_Productos";
    }

    public String buscar() {
        return "";
    }

    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo,RedirectAttributes redAttributes){
        //String vista = "redirect:/productos/listar";
        //System.out.println("cod= "+codigo);
        System.out.println("\n\nEntra al metodo eliminar de la controller\n\n");
        boolean elimino= ProductoServices.eliminar(codigo);
        
        System.out.println("elimino?"+elimino);
        
        if (elimino) {
            redAttributes.addFlashAttribute("mensajeExitoso", "Producto eliminado correctamente");
        }else{
            redAttributes.addFlashAttribute("mensajeError","El producto no pudo ser eliminado");
            return "redirect:/error";
        }
        //System.out.println("Vista= "+vista);
        return "redirect:/productos/listar";
    }
    //falta mappin para listar los productos.
    
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Producto producto){
        System.out.println("Metodo actualizar producto");
        ProductoServices.modificar(producto);
        return "redirect:/productos/listar";
    }
    
    @GetMapping("/eliminarProducto")
    public String eliminarProducto(@RequestParam("codigo") String codigo) {
        System.out.println("Entra a eliminarProducto");
        boolean elimino= ProductoServices.eliminar(codigo);
        if (elimino) {
            return "redirect:/productos/listar";
        } else {
            return "clientes";
        }
    }
}   
