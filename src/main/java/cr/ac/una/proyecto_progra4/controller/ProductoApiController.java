/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Producto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
 * @author Yeiler
 */
@Controller
@RequestMapping("/productosApi")
public class ProductoApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api-url}")
    private String urlBase;

    @GetMapping("/listar")
    public String listaProductos(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        Producto[] productosArray = restTemplate.getForObject(urlBase + "/producto", Producto[].class);
        List<Producto> productosList = Arrays.asList(productosArray);
        List<Producto> productosPaginas = productosList.stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        int ultimaPagina = (int) Math.ceil((double) productosList.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("productos", productosPaginas);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "productos/form_ProductosApi";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> registrarProducto(@RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria,
            @RequestParam("calificacion") int calificacion,
            @RequestParam("precio") double precio,
            @RequestParam("venta") boolean venta,
            @RequestParam("stock") int stock,
            @RequestParam("codigo") String codigo) {
        Producto producto = new Producto(nombre, descripcion, precio, categoria, calificacion, venta, stock, codigo);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Producto> request = new HttpEntity<>(producto, headers);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/agregarProd", HttpMethod.POST, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarProducto(@RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria,
            @RequestParam("calificacion") int calificacion,
            @RequestParam("precio") double precio,
            @RequestParam("venta") boolean venta,
            @RequestParam("stock") int stock) {

        Producto producto = new Producto(nombre, descripcion, precio, categoria, calificacion, venta, stock, codigo);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Producto> request = new HttpEntity<>(producto, headers);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/actualizarProd", HttpMethod.PUT, request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

    }

    @DeleteMapping("/eliminarProducto")
    public ResponseEntity<?> eliminarProducto(@RequestParam("codigo") String codigo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/eliminarProd/" + codigo, HttpMethod.DELETE, request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
