/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.jpa;

import cr.ac.una.proyecto_progra4.domain.Envio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aaron
 */
@Repository
public interface EnviosRepository extends JpaRepository<Envio, Integer> {

    @Query("SELECT e FROM Envio e")
    List<Envio> findAllEnvios();
    
    @Query("SELECT e FROM Envio e WHERE LOWER(e.codigoEnvio) = LOWER(:codigo)")
    Optional<Envio> findByCodigoEnvioIgnoreCase(@Param("codigo") String codigo);
}
