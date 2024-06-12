/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.services.ProveedoresServices;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Adam Acuña
 */
@Controller
@RequestMapping("/proveedoresApi")
public class ProveedorApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api-url}")
    private String urlBase;

    @GetMapping("/listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Proveedor[] proveedoresArray = restTemplate.getForObject(urlBase + "/listarProveedor", Proveedor[].class);
        List<Proveedor> proveedoresList = Arrays.asList(proveedoresArray);

        LinkedList<Proveedor> proveedoresPagina = new ProveedoresServices().obtenerRegistrosPaginados(page, pageSize, proveedoresList);

        int ultimaPagina = (int) Math.ceil((double) proveedoresList.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("proveedores", proveedoresPagina);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo

        return "proveedor/Proveedores_admin";
    }

    @GetMapping("/detalles")
    public String verDetalles(@RequestParam("proveedor") int proveedorID, Model model) {
        Proveedor[] proveedoresArray = restTemplate.getForObject(urlBase + "/listarProveedor", Proveedor[].class);
        List<Proveedor> proveedoresList = Arrays.asList(proveedoresArray);
        for (Proveedor proveedor : proveedoresList) {
            if (proveedor.getIdProveedor() == proveedorID) {
                model.addAttribute("proveedor", proveedor);
                return "proveedor/detalles_proveedor";
            }
        }
        return "error";
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProveedor(@RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono, @RequestParam("descripcion") String descripcion, @RequestParam("correo") String correo, @RequestParam("direccion") String direccion, @RequestParam("categoria") String categoria, @RequestParam("informacionadicional") String informacion) {
        Proveedor proveedor = new Proveedor(0, nombre, telefono, descripcion, correo, direccion, categoria, informacion);
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        HttpEntity<Proveedor> request = new HttpEntity(proveedor, header);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/registrarProveedor", HttpMethod.POST, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/editar_proveedor")
    public ResponseEntity<?> editarInfoProveedor(@RequestParam("proveedor") int proveedorID,
            @RequestParam("nombre") String nombre,
            @RequestParam("telefono") String telefono,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("correo") String correo,
            @RequestParam("direccion") String direccion,
            @RequestParam("categoria") String categoria,
            @RequestParam("informacionadicional") String informacion,
            @RequestParam("_method") String method) {
        if ("PUT".equalsIgnoreCase(method)) {
            Proveedor proveedor = new Proveedor(proveedorID, nombre, telefono, descripcion, correo, direccion, categoria, informacion);
            HttpHeaders header = new HttpHeaders();
            header.set("Content-Type", "application/json");
            HttpEntity<Proveedor> request = new HttpEntity<>(proveedor, header);
            ResponseEntity<String> response = restTemplate.exchange(urlBase + "/editarProveedor", HttpMethod.PUT, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Método no permitido");
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarProveedor(@RequestParam("proveedor") int proveedorID) {
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/eliminarProveedor?proveedor=" + proveedorID, HttpMethod.DELETE, request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
