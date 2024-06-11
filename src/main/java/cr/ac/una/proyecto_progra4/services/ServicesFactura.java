package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Factura;
import cr.ac.una.proyecto_progra4.jpa.FacturaRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
/**
 * @author UNA
 */
@Service
@Primary
public class ServicesFactura implements IFacturaServices {

    @Autowired
    private FacturaRepository factRp;

    @Override
    public String Insertar_factura(Factura factura) {
        if (factura.getId_factura() == 0) {
            if (!verificar_codigo(factura.getCodigo_factura(), 0, false)) {
                Factura aux = factRp.save(factura);
                if (aux.getId_factura() != 0) {
                    return "{\"success\": true, \"message\": \"¡Factura agregado exitosamente!\"}";
                }
                return "{\"success\": false, \"message\": \"¡Error al agregar la factura!\"}";
            }
            return "{\"success\": false, \"message\": \"¡El código ya se encuentra en uso!\"}";
        } else {
            if (factRp.existsById(factura.getId_factura())) {
                if (!verificar_codigo(factura.getCodigo_factura(), factura.getId_factura(), true)) {
                    /*Factura aux =*/ factRp.save(factura);
                    return "{\"success\": true, \"message\": \"¡Factura modificada exitosamente!\"}";
                }
                return "{\"success\": false, \"message\": \"¡El código ya se encuentra en uso!\"}";
            }
            return "{\"success\": false, \"message\": \"¡La factura no existe!\"}";
        }
    }

    @Override
    public List<Factura> getFacturas() {
        return factRp.findAll();
    }

    @Override
    public boolean Eliminar_factura(int id) {
        factRp.deleteById(id);
        return factRp.existsById(id);
    }

    @Override
    public boolean verificar_codigo(String codigo, int id, boolean edit) {
        List<Factura> lista = factRp.findAll();

        for (Factura f : lista) {
            if (f.getCodigo_factura().equals(codigo)) {
                if (edit) {
                    if (f.getId_factura() != id) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public LinkedList<Factura> ObtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, List<Factura> facturas) {
        LinkedList<Factura> registrosPagina = new LinkedList<>();
        if (facturas != null) {
            int inicio = numeroPagina * tamanoPagina;
            int fin = Math.min(inicio + tamanoPagina, facturas.size());
            for (int i = inicio; i < fin; i++) {
                registrosPagina.add(facturas.get(i));
            }
        }
        return (registrosPagina.isEmpty()) ? null : registrosPagina;
    }

    @Override
    public Factura getFacturaById(int id) {
        Optional<Factura> factura =factRp.findById(id);
        if(factura.isPresent()){
            System.out.println("Obtuvo factura");
            return factura.get();
        }else{
            return null;
        }
    }
    @Override
    public String generar_Codigo() {
        Factura f = factRp.findUltimaFactura();
        String  nuevoCodigo = "AAAA-0001";
        
        if(f != null){
            String ultimoCodigo = f.getCodigo_factura();
            String[] partes = ultimoCodigo.split("-");
            String prefix = partes[0];
            int numero = Integer.parseInt(partes[1]);
            if (numero == 9999) {
                recursivo_char(prefix, 3);
                numero = 0000;
            }
            nuevoCodigo = String.format("%s-%04d", prefix, numero + 1);
            System.out.println(nuevoCodigo);
        }
        return nuevoCodigo;
    }

    @Override
    public String recursivo_char(String prefix, int index) {
        System.out.println("Prefix :"+prefix + " i :"+index);
        char ultimoCaracter = prefix.charAt(index);
        if (ultimoCaracter == 'Z') {
            prefix = prefix.substring(0, index) + "A"+prefix.substring(index+1);
            if(index > 0){
                prefix = recursivo_char(prefix, index-1);
            }
        } else {
            prefix = prefix.substring(0, index) + (char) (ultimoCaracter + 1)+prefix.substring(index+1);
        }
        return prefix;
    }
}
