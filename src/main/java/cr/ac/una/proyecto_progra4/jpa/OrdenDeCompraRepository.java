/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adam Acuña
 */
@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {
    
    @Query("SELECT o FROM OrdenDeCompra o WHERE o.idProveedor.idProveedor = :proveedorId")
    List<OrdenDeCompra> findByProveedorId(@Param("proveedorId") int proveedorId);

}
