/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.services.ProductoServices;
//import java.util.LinkedList;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired
    private ProductoServices productoServices;

    //direccion a la pagina que se quiera cargar.
    @GetMapping("/agregar")
    public String crearProducto() {
        return "productos/form_Productos";
    }
    //metodo que se carga

    @GetMapping({"/guardar"})
    public String save(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria, @RequestParam("calificacion") int calificacion,
            @RequestParam("precio") double precio, @RequestParam("venta") boolean venta,
            @RequestParam("stock") int stock, @RequestParam("codigo") String codigo) {

        System.out.println("Codigo Producto: " + codigo + "\nVenta: " + venta);
        //Producto producto = new Producto(codigo, nombre, descripcion, categoria, calificacion, precio, venta);
        Producto producto = new Producto(nombre, descripcion, precio, categoria, calificacion, venta, stock, codigo);
        //ProductoServices.agregarProductos(producto);
        productoServices.agregarProductos(producto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/listar")
    public String mostrarLista(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        //LinkedList<Producto> productos = ProductoServices.getProductos();
        List<Producto> productos = productoServices.getProductos();
        List<Producto> productosPagina = productoServices.obtenerRegistrosPaginados(page, pageSize);

        int ultimaPagina = (int) Math.ceil((double) productos.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("productos", productosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        //model.addAttribute("productos", productos);
        return "productos/form_Productos";
    }

    @GetMapping("/buscar")
    public String buscar(Model modelo, @RequestParam(value = "textoBuscar", required = true) String textoBuscar) {

        //LinkedList<Producto> productos;
        List<Producto> productos;
        if (textoBuscar == null || textoBuscar.isBlank()) {
            //productos = ProductoServices.getProductos();
            productos = productoServices.getProductos();
        } else {
            // productos = ProductoServices.busquedaProductos(textoBuscar);
            productos = productoServices.buscarProductos(textoBuscar);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterruptedException.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.addAttribute("productos", productos);
        //System.out.println("El resultado de la busqueda es: "+productos.get(0).getNombre());
        return "productos/resultadoBusqueda";
    }

    @PostMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo, RedirectAttributes redAttributes) {
        //String vista = "redirect:/productos/listar";
        //System.out.println("cod= "+codigo);
        System.out.println("\n\nEntra al metodo eliminar de la controller\n\n");
        // boolean elimino= ProductoServices.eliminar(codigo);
        boolean elimino = productoServices.eliminar(codigo);
        System.out.println("elimino?" + elimino);

        if (elimino) {
            redAttributes.addFlashAttribute("mensajeExitoso", "Producto eliminado correctamente");
        } else {
            redAttributes.addFlashAttribute("mensajeError", "El producto no pudo ser eliminado");
            return "redirect:/error";
        }
        //System.out.println("Vista= "+vista);
        return "redirect:/productos/listar";
    }

    //falta mappin para listar los productos.
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Producto producto) {
        //System.out.println("Metodo actualizar producto");
        //ProductoServices.modificar(producto);
        //sirve tambien para modificar.
        //productoServices.agregarProductos(aproducto);
        System.out.println("Metodo Actualizar: Disponible[" + producto.isDisponible() + "]");
        productoServices.modificar(producto);
        return "redirect:/productos/listar";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto(@RequestParam("codigo") String codigo) {
        System.out.println("Entra a eliminarProducto");
        //boolean elimino= ProductoServices.eliminar(codigo);
        boolean elimino = productoServices.eliminar(codigo);
        if (elimino) {
            return "redirect:/productos/listar";
        } else {
            return "clientes";
        }
    }

}
