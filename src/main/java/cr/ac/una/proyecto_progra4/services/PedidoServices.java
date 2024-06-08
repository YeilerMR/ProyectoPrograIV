package cr.ac.una.proyecto_progra4.services;


import cr.ac.una.proyecto_progra4.data.DataPedido;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import java.util.LinkedList;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author GONZALO DORMOS RODRIGUEZ
 */
@Service
@Primary
public class PedidoServices {

    public LinkedList<Pedido> lista_Pedido() {
        return new DataPedido().Leer();
    }

    public boolean eliminar_Pedido(int id) {
        Pedido p = new Pedido();
        p.setId_pedido(id);
        return new DataPedido().Eliminar(p);
    }

    public String insertar_Pedido(Pedido pedido) {
        LinkedList<Pedido> lista = lista_Pedido();

        if (lista != null) {
            for (Pedido p : lista) {
                if (p.getCodigo().equals(pedido.getCodigo())) {
                    return "{\"success\": false, \"message\": \"¡El codigo de pedido ya existe!\"}";
                }
            }
            if (new DataPedido().Insertar(pedido)) {
                return "{\"success\": true, \"message\": \"¡Pedido guardado exitosamente!\"}";
            }
            return "{\"success\": false, \"message\": \"¡No se guardo el pedido!\"}";
        } else {
            if (new DataPedido().Insertar(pedido)) {
                return "{\"success\": true, \"message\": \"¡Pedido guardado exitosamente!\"}";
            }
            return "{\"success\": false, \"message\": \"¡No se guardo el pedido!\"}";

        }
    }

    public String modificar_Pedido(Pedido pedido) {
        
        LinkedList<Pedido> lista = lista_Pedido();

        if (lista != null) {
            for (Pedido p : lista) {
                if (p.getCodigo().equals(pedido.getCodigo()) && p.getId_pedido() != pedido.getId_pedido()) {
                    return "{\"success\": false, \"message\": \"¡El codigo de pedido ya existe!\"}";
                }
            }
            if (new DataPedido().Actualizar(pedido)) {
                return "{\"success\": true, \"message\": \"¡Pedido actualizado exitosamente!\"}";
            }
            return "{\"success\": false, \"message\": \"¡No se actualizo el pedido!\"}";
        } else {
            if (new DataPedido().Actualizar(pedido)) {
                return "{\"success\": true, \"message\": \"¡Pedido actualizado exitosamente!\"}";
            }
            return "{\"success\": false, \"message\": \"¡No se actualizo el pedido!\"}";

        }
        //return new DataPedido().Actualizar(pedido);
    }

    public LinkedList<Pedido> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Pedido> pedidos) {
        LinkedList<Pedido> registrosPagina = new LinkedList<>();
        if (pedidos != null) {
            int inicio = numeroPagina * tamanoPagina;
            int fin = Math.min(inicio + tamanoPagina, pedidos.size());
            for (int i = inicio; i < fin; i++) {
                registrosPagina.add(pedidos.get(i));
            }
        }
        return (registrosPagina.isEmpty()) ? null : registrosPagina;
    }

}
