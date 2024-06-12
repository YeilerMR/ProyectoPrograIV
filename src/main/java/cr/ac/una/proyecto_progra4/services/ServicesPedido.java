package cr.ac.una.proyecto_progra4.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cr.ac.una.proyecto_progra4.domain.Pedido;
import cr.ac.una.proyecto_progra4.jpa.PedidoRepository;


@Service
@Primary
public class ServicesPedido implements IPedidoServices{

    @Autowired
    private PedidoRepository pedidoRp;


    @Override
    public boolean Eliminar_pedido(int id) {
        pedidoRp.deleteById(id);
        return pedidoRp.existsById(id);
    }

    @Override
    public String Insertar_pedido(Pedido pedido) {
        if (pedido.getId_pedido() == 0) {
            if (!verificar_codigo(pedido.getCodigo(), 0, false)) {
                Pedido aux = pedidoRp.save(pedido);
                if (aux.getId_pedido() != 0) {
                    return "{\"success\": true, \"message\": \"¡Pedido agregado exitosamente!\"}";
                }
                return "{\"success\": false, \"message\": \"¡Error al agregar el pedido!\"}";
            }
            return "{\"success\": false, \"message\": \"¡El código ya se encuentra en uso!\"}";
        } else {
            if (pedidoRp.existsById(pedido.getId_pedido())) {
                if (!verificar_codigo(pedido.getCodigo(), pedido.getId_pedido(), true)) {
                    /*Factura aux =*/ pedidoRp.save(pedido);
                    return "{\"success\": true, \"message\": \"¡Pedido modificada exitosamente!\"}";
                }
                return "{\"success\": false, \"message\": \"¡El código ya se encuentra en uso!\"}";
            }
            return "{\"success\": false, \"message\": \"¡El pedido no existe!\"}";
        }
    }

    @Override
    public LinkedList<Pedido> ObtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, List<Pedido> pedidos) {
        LinkedList<Pedido> registrosPagina = new LinkedList<>();
        System.out.println("Entro a paginado >>"+pedidos.size());
        if (pedidos != null) {
            int inicio = numeroPagina * tamanoPagina;
            int fin = Math.min(inicio + tamanoPagina, pedidos.size());
            for (int i = inicio; i < fin; i++) {
                registrosPagina.add(pedidos.get(i));
            }
        }
        return (registrosPagina.isEmpty()) ? null : registrosPagina;
    }

    @Override
    public Pedido getPedidoById(int id) {
        Optional<Pedido> pedido =pedidoRp.findById(id);
        return (pedido.isPresent())?pedido.get():null;
    }

    @Override
    public List<Pedido> getPedidos() {        
        return pedidoRp.findAll();
    }

    @Override
    public boolean verificar_codigo(String codigo, int id, boolean edit) {
        List<Pedido> lista = pedidoRp.findAll();

        for (Pedido p : lista) {
            if (p.getCodigo().equals(codigo)) {
                if (edit) {
                    if (p.getId_pedido() != id) {
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
    public String generar_Codigo() {
        Pedido p = pedidoRp.findUltimaPedido();
        String  nuevoCodigo = "AAAA-0001";
        
        if(p != null){
            String ultimoCodigo = p.getCodigo();
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
