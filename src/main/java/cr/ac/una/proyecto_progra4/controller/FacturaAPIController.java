package cr.ac.una.proyecto_progra4.controller;

import java.sql.Date;
import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
import org.springframework.web.util.UriComponentsBuilder;

import cr.ac.una.proyecto_progra4.Response.FacturasResponse;
import cr.ac.una.proyecto_progra4.domain.Factura;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.services.IFacturaServices;

@Controller
@RequestMapping("/FacturaApi")
public class FacturaAPIController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IFacturaServices ifs;

    @Value("${api-url}")
    private String urlBase;

    @GetMapping("/listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        String url = UriComponentsBuilder.fromHttpUrl(urlBase + "/factura/listar").queryParam("page", page).queryParam("pageSize", pageSize).toUriString();

        ResponseEntity<FacturasResponse> response = restTemplate.exchange(
            url,//Base + "/factura/listar",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<FacturasResponse>() {}
        );

        FacturasResponse respuesta = response.getBody();
        model.addAttribute("ultimaPagina", respuesta.getUltimaPagina());
        model.addAttribute("factura", respuesta.getFacturas());//Lista De facturas
        model.addAttribute("page", respuesta.getPage()); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", respuesta.getPageSize()); // Asegúrate de pasar el tamaño de página al modelo
        model.addAttribute("nuevoCodigo", respuesta.getNuevoCodigo());
        // Obtiene la fecha actual para limitar la fecha en el registro de facturas
        LocalDate localDate = LocalDate.now();
        Date fechaActual = Date.valueOf(localDate.toString());
        model.addAttribute("fechaActual", fechaActual);
        return "factura/factura";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> registrarProveedor(@RequestParam("codigo") String codigo,
            @RequestParam("fecha") Date fecha,
            @RequestParam("precio") double precio,
            @RequestParam("descuento") double descuento,
            @RequestParam("metodo") String metodo,
            @RequestParam("observacion") String observacion,
            @RequestParam("impuesto") float impuesto) {

        Factura factura = new Factura();
        factura.setCodigo_factura(codigo);
        factura.setFecha(fecha);
        factura.setPrecio_total(precio);
        factura.setDescuento(descuento);
        factura.setMetodo_pago(metodo);
        factura.setObservacion(observacion);
        factura.setImpuesto(impuesto);

        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        HttpEntity<Proveedor> request = new HttpEntity(factura, header);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/factura/registrar", HttpMethod.POST, request,String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> editarInfoProveedor(
                @RequestParam("id") int id,
                @RequestParam("codigo") String codigo,
                @RequestParam("fecha") Date fecha,
                @RequestParam("precio") double precio,
                @RequestParam("descuento") double descuento,
                @RequestParam("metodo") String metodo,
                @RequestParam("observacion") String observacion,
                @RequestParam("impuesto") float impuesto){


            Factura factura = new Factura();
            factura.setId_factura(id);
            factura.setCodigo_factura(codigo);
            factura.setFecha(fecha);
            factura.setPrecio_total(precio);
            factura.setDescuento(descuento);
            factura.setMetodo_pago(metodo);
            factura.setObservacion(observacion);
            factura.setImpuesto(impuesto);

            HttpHeaders header = new HttpHeaders();
            header.set("Content-Type", "application/json");
            HttpEntity<Factura> request = new HttpEntity<>(factura, header);
            ResponseEntity<String> response = restTemplate.exchange(urlBase + "/factura/editar", HttpMethod.PUT, request,String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/eliminar")
    public String/*ResponseEntity<?>*/ eliminarProveedor(@RequestParam("id") int id) {
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(urlBase + "/factura/eliminar?factura=" + id,HttpMethod.DELETE, request, String.class);
        String result = response.getBody();
        
        String vista = "redirect:/facturaApi/listar";
        if (result.equalsIgnoreCase("nodelete")) {
            vista = "Error";
        }
        return vista;
        
        // return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

}
