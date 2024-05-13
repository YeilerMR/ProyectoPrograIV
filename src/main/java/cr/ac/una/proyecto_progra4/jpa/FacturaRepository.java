package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author UNA
 */
public interface FacturaRepository extends JpaRepository<Factura,Integer> {
    
}
