package cr.ac.una.proyecto_progra4.controller;


import cr.ac.una.proyecto_progra4.domain.Factura;
import cr.ac.una.proyecto_progra4.services.IFacturaServices;
import java.util.LinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/facturas")
public class controllerFactura {

    @Autowired
    private IFacturaServices ifs;
    
    public void print(String texto) {
        System.out.println(texto);
    }
    //Listando con paginado
    @GetMapping("/listar")
    public String listaProveedores(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        print("Aqui llego");
        List<Factura> auxiliar = ifs.getFacturas();//new facturaServices().lista_Factura();
        LinkedList<Factura> facturas = ifs.ObtenerRegistrosPaginados(page, pageSize, auxiliar);//new facturaServices().obtenerRegistrosPaginados(page, pageSize, auxiliar); 
        
        
        int ultimaPagina = (auxiliar != null) ? ((int) Math.ceil((double) auxiliar.size() / pageSize) - 1) : 0;
        
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("factura", facturas);
        model.addAttribute("page", page); // Asegúrate de pasar el número de página al modelo
        model.addAttribute("pageSize", pageSize); // Asegúrate de pasar el tamaño de página al modelo
        
        
        //Obtiene la fecha actual para limitar la fecha en el registro de facturas
        LocalDate localDate = LocalDate.now();
        Date fechaActual = Date.valueOf(localDate.toString());
        model.addAttribute("fechaActual", fechaActual);
        
        return "factura/factura";
    }

    /*   OTROS METODOS LIGADOS A LA PAGINA factura/factura */
    @GetMapping("eliminar")
    public String eliminar_factura(@RequestParam("id") int id) {
        
        String vista = "redirect:/facturas/listar";
        if (!ifs.Eliminar_factura(id)) {
            vista = "Error";
        }
        return vista;
    }
    //submit de editar
    @PostMapping("actualizar")
    public ResponseEntity<?> actualizar(@RequestParam("id") int id,
            @RequestParam("codigo") String codigo,
            @RequestParam("fecha") Date fecha,
            @RequestParam("precio") double precio,
            @RequestParam("descuento") double descuento,
            @RequestParam("metodo") String metodo,
            @RequestParam("observacion") String observacion,
            @RequestParam("impuesto") float impuesto) {

        Factura factura = new Factura();
        factura.setId_factura(id);
        factura.setCodigo_factura(codigo);
        factura.setFecha(fecha);
        factura.setPrecio_total(precio);
        factura.setDescuento(descuento);
        factura.setMetodo_pago(metodo);
        factura.setObservacion(observacion);
        factura.setImpuesto(impuesto);
        return ResponseEntity.ok().body(ifs.Insertar_factura(factura));
    }

    //Submit de añadir
    @PostMapping("guardar")
    public ResponseEntity<?> guardar(
            @RequestParam("codigo") String codigo,
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

        return ResponseEntity.ok().body(ifs.Insertar_factura(factura));
    }
}
