package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import cr.ac.una.proyecto_progra4.domain.Pedido;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    @Query("SELECT f FROM Pedido f WHERE f.id_pedido = (SELECT MAX(f2.id_pedido) FROM Pedido f2)")
    Pedido findUltimaPedido();
}
