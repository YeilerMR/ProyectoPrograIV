package cr.ac.una.proyecto_progra4.services;


import cr.ac.una.proyecto_progra4.data.DataPedido;
import cr.ac.una.proyecto_progra4.domain.Pedido;
import java.util.LinkedList;


/**
 * @author GONZALO DORMOS RODRIGUEZ g.d.r
 */
public class PedidoServices {

    public LinkedList<Pedido> lista_Pedido() {
        return new DataPedido().Leer();
    }

    public boolean eliminar_Pedido(int id) {
        Pedido p = new Pedido();
        p.setId_pedido(id);
        return new DataPedido().Eliminar(p);
    }

    public boolean insertar_Pedido(Pedido pedido) {
        return new DataPedido().Insertar(pedido);
    }

    public boolean modificar_Pedido(Pedido pedido) {
        return new DataPedido().Actualizar(pedido);
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
        return (registrosPagina.isEmpty())?null:registrosPagina;
    }

}
