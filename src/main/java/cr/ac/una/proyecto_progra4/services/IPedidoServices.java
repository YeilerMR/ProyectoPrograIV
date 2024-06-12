package cr.ac.una.proyecto_progra4.services;

import java.util.LinkedList;
import java.util.List;

import cr.ac.una.proyecto_progra4.domain.Pedido;
/*
 * @author Gonzalo Dormos Rodriguez. 
*/
public interface IPedidoServices {
    public String Insertar_pedido(Pedido pedido);
    
    public List<Pedido> getPedidos();
    
    public boolean Eliminar_pedido(int id);
    
    public boolean verificar_codigo(String codigo, int id, boolean edit);
    
    public LinkedList<Pedido> ObtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, List<Pedido> pedidos);

    public Pedido getPedidoById(int id);
    
    public String generar_Codigo();

    public String recursivo_char(String prefix, int index);
}
