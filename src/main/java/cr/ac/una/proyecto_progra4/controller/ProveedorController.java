/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.services.IProveedoresService;
import cr.ac.una.proyecto_progra4.services.ProveedoresServices;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adam Acuña
 */
@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedoresService servicePro;

    private static List<Proveedor> proveedores = new ProveedoresServices().listaProveedores();

    //new ProveedoresServices().getProveedores();

    private void actualizarListaProveedores() {
        proveedores = new ProveedoresServices().listaProveedores();
    }

    @GetMapping("/registrar")
    public String registrarProveedor(@RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono, @RequestParam("descripcion") String descripcion, @RequestParam("correo") String correo, @RequestParam("direccion") String direccion, @RequestParam("categoria") String categoria, @RequestParam("informacionadicional") String informacion) {
        Proveedor proveedor = new Proveedor(0, nombre, telefono, descripcion, correo, direccion, categoria, informacion);
        /*if (new ProveedoresServices().crearProveedor(proveedor)) {
            actualizarListaProveedores();
            return "excito";
        } else {
            return "error";
        }*/
        System.out.println(proveedor.getCategoriaServicio() + " " + proveedor.getCorreo() + " " + proveedor.getDescripcionProveedor() + " " + proveedor.getDireccionProveedor() + " " + proveedor.getNombreProveedor());
        servicePro.guardar(proveedor);

        actualizarListaProveedores();
        for (Proveedor proveedor1 : servicePro.getProveedores()) {
            System.out.println(proveedor1.getNombreProveedor());
        }
        return "excito";
    }

    @GetMapping("/crear")
    public String crearProveedor() {
        return "proveedor/registrar_proveedor";
    }

    @GetMapping("/listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        LinkedList<Proveedor> proveedoresPagina = new ProveedoresServices().obtenerRegistrosPaginados(page, pageSize, proveedores);

        int ultimaPagina = (int) Math.ceil((double) proveedores.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("proveedores", proveedoresPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo

        return "proveedor/Proveedores_admin";
    }

    @GetMapping("/editar")
    public String editarProveedor(@RequestParam("proveedor") int proveedorID, Model model) throws SQLException {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == proveedorID) {
                model.addAttribute("proveedor", proveedor);
                return "proveedor/editar_proveedor";
            }
        }
        return "error";
    }

    @GetMapping("/editar_proveedor")
    public String editarInfoProveedor(@RequestParam("proveedor") int proveedorID, @RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono, @RequestParam("descripcion") String descripcion, @RequestParam("correo") String correo, @RequestParam("direccion") String direccion, @RequestParam("categoria") String categoria, @RequestParam("informacionadicional") String informacion) {
        Proveedor proveedor = new Proveedor(proveedorID, nombre, telefono, descripcion, correo, direccion, categoria, informacion);
        if (new ProveedoresServices().editarProveedor(proveedor)) {
            actualizarListaProveedores();
            return "excito";
        } else {
            return "error";
        }
    }

    @GetMapping("/eliminar")
    public String eliminarProveedor(@RequestParam("proveedor") int proveedorID) {
        if (new ProveedoresServices().eliminarProveedor(proveedorID)) {
            actualizarListaProveedores();
            return "excito";
        } else {
            return "error";
        }
    }
}
