/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.Apartado;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kinco
 */
@Repository
public interface ApartadoRepository extends JpaRepository<Apartado, Integer>{
    
    @Query("SELECT a FROM Apartado a")
    List<Apartado> findAllApartados();

    @Query("SELECT a FROM Apartado a WHERE LOWER(a.id_Apartado) = LOWER(:id_Apartado)")
    Optional<Apartado> findByCodigoApartadoIgnoreCase(@Param("id_Apartado") String codigo);

}
