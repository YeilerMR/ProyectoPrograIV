/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.controller;

import cr.ac.una.proyecto_progra4.domain.Apartado;
import cr.ac.una.proyecto_progra4.domain.Cliente;

import cr.ac.una.proyecto_progra4.domain.Producto;
import cr.ac.una.proyecto_progra4.services.ApartadosServices;
import cr.ac.una.proyecto_progra4.services.ClienteServices;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author kinco
 */
@Controller
@RequestMapping("/apartados")
public class ApartadosController {
    
    @Autowired
    private ApartadosServices apartadoServices;
    @Autowired
    private ClienteServices clienteServices;
    @Autowired
    private ProductoServices productoServices;
    
    @PostMapping("/guardar")
    public ResponseEntity<String> save(
            @RequestParam("idCliente") int idCliente,
            @RequestParam("id") int idProducto,           
            @RequestParam("fechaInicioApartado") Date fechaInicioApartado,
            @RequestParam("fechaFinalApartado") Date fechaFinalApartado,
            @RequestParam("abono") Double abonoApartado,
            @RequestParam("estadoApartado") String estadoApartado) {

        System.out.println("ID CLIENTE -> " + idCliente);

        Apartado apartado = new Apartado();
        //apartado.setIdApartado(idApartado);

        Cliente clienteApartado = new Cliente();
        clienteApartado.setIdCliente(idCliente);
        apartado.setCliente(clienteApartado);
        
        Producto productoApartado = new Producto();
        productoApartado.setId(idProducto);
        apartado.setProducto(productoApartado);
        
        apartado.setFechaInicioApartado(fechaInicioApartado);
        apartado.setFechaFinalApartado(fechaFinalApartado);
        apartado.setAbono(abonoApartado);
        apartado.setEstadoApartado(estadoApartado);
        
        return ResponseEntity.ok().body(apartadoServices.verificarPreAgregar(apartado));
    }
    
    @GetMapping("/buscar")
    public String buscarApartado(@RequestParam(value = "textoBuscar", required = true) int codigo, Model model) {
        Apartado apartado = apartadoServices.getApartadoPorID(codigo);
        LinkedList<Apartado> apartados = new LinkedList<>();
        List<Cliente> clientesConApartados = clienteServices.getClientesConApartados();
        List<Cliente> clientesListaTotal = clienteServices.getClientes();
        List<Apartado> apartadosListaTotal = apartadoServices.getApartados();
        if (apartado != null) {
            apartados.add(apartado);
        }

        model.addAttribute("apartados", apartados);
        model.addAttribute("clientesListaTotal", clientesListaTotal);
        model.addAttribute("clientes", clientesConApartados);
        model.addAttribute("apartadosListaTotal", apartadosListaTotal);

        return "apartados/resultadoBusquedaEnvio";
    }
    
    @GetMapping("/listar")
    public String mostrarFormularioApartado(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        List<Apartado> apartados = apartadoServices.getApartados();
        List<Cliente> clientesConApartados = clienteServices.getClientesConApartados();
        List<Cliente> clientesListaTotal = clienteServices.getClientes();
        List<Apartado> apartadosPagina = apartadoServices.obtenerRegistrosPaginados(page, pageSize);

        int ultimaPagina = (int) Math.ceil((double) apartados.size() / pageSize) - 1;

        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("apartados", apartadosPagina);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("clientes", clientesConApartados);
        model.addAttribute("clientesListaTotal", clientesListaTotal);

        return "apartados/form_Apartados";
    }

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarApartado(@ModelAttribute("apartado") Apartado apartado,
            @RequestParam("idCliente") int idCliente,
            @RequestParam("id") int idProducto
    ) {

        // Cliente del Envio
        Cliente clienteApartado = new Cliente();
        clienteApartado.setIdCliente(idCliente);
        apartado.setCliente(clienteApartado);
        apartado.getCliente().setIdCliente(idCliente);

        System.out.println("ID-> " + apartado.getIdApartado());
        System.out.println("ID APARTADO-> " + apartado.getProducto().getId());
        System.out.println("ID CLIENTE-> " + apartado.getCliente().getIdCliente());
        return ResponseEntity.ok().body(apartadoServices.verificarPreModificar(apartado));
    }

    @GetMapping("/eliminar")
    public String eliminarApartado(@RequestParam("idApartado") int idApartado) {
        boolean eliminadoExitosamente = apartadoServices.eliminar(idApartado);
        if (eliminadoExitosamente) {
            return "redirect:/apartados/listar";
        } else {
            return "clientes";
        }
    }
}