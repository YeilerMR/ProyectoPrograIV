/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.domain.Usuario;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author kinco
 */
@Controller
@RequestMapping("/empleadosApi")
public class EmpleadosApiController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api-url}")
    private String urlBase;

    @GetMapping("/listar")
    public String listaEmpleados(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Empleado[] empleadosArray = restTemplate.getForObject(urlBase + "/listarEmpleado", Empleado[].class);
        List<Empleado> empleadosList = Arrays.asList(empleadosArray);

        List<Empleado> empleadosPagina = empleadosList.stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        int ultimaPagina = (int) Math.ceil((double) empleadosList.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("empleados", empleadosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);

        return "empleados/empleado";
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarEmpleado(@RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("telefono") String telefono,
            @RequestParam("credencial") int credencial,
            @RequestParam("cedula") String cedula) {

        Usuario usuario = new Usuario(nombre, apellidos, email, password, cedula, telefono, credencial);
        Empleado empleado = new Empleado();
        empleado.setUsuario(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Empleado> request = new HttpEntity<>(empleado, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(urlBase + "/registrarEmpleado", request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarEmpleado(
            @RequestParam("idEmpleado") int idEmpleado,
            @RequestParam("usuario.nombre") String nombre,
            @RequestParam("usuario.apellidos") String apellidos,
            @RequestParam("usuario.email") String email,
            @RequestParam("usuario.password") String password,
            @RequestParam("usuario.telefono") String telefono,
            @RequestParam("usuario.credencial") int credencial,
            @RequestParam("usuario.cedula") String cedula) {

        Usuario usuario = new Usuario(nombre, apellidos, email, password, cedula, telefono, credencial);
        Empleado empleado = new Empleado();     
        empleado.setUsuario(usuario);
        empleado.setIdEmpleado(idEmpleado);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Empleado> request = new HttpEntity<>(empleado, headers);

        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/editarEmpleado?idEmpleado=" + idEmpleado, HttpMethod.PUT, request, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarEmpleado(@RequestParam("id_Empleado") int idEmpleado) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/eliminarEmpleado?id_Empleado=" + idEmpleado, HttpMethod.DELETE, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarEmpleado(@RequestParam(value = "textoBuscar", required = true) String cedula) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/buscarEmpleado?textoBuscar=" + cedula, HttpMethod.GET, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}