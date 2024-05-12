/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Adam Acuña
 */
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
    
}
