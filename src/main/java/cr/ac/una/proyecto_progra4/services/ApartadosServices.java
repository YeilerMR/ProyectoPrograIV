/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataApartado;
import cr.ac.una.proyecto_progra4.domain.Apartado;
import cr.ac.una.proyecto_progra4.jpa.ApartadoRepository;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author kinco
 */
@Service
@Primary
public class ApartadosServices implements IApartadoServices{

    @Autowired
    private ApartadoRepository apartadoRepo;

    
    @Override
    public boolean agregar(Apartado apartado) {
        try {
            apartadoRepo.save(apartado);
            return true; // Si el guardado tiene éxito, devuelve true
        } catch (Exception e) {
            e.toString();
            return false;
        }
    }

    @Override
    public List<Apartado> getApartados() {
        return apartadoRepo.findAll();
    }

    @Override
    public boolean eliminar(int idApartado) {
       try {
            apartadoRepo.deleteById(idApartado);
            return true; // Se eliminó correctamente
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Apartado getApartadoPorID(int idApartado) {
       return apartadoRepo.findById(idApartado).orElse(null);
    }

    @Override
    public List<Apartado> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina) {
        Page<Apartado> paginaEnvios = apartadoRepo.findAll(PageRequest.of(numeroPagina, tamanoPagina));
        return paginaEnvios.getContent();
    }

    @Override
    public String verificarPreAgregar(Apartado apartado) {
        // Verificar si el código del apartado ya existe

        Optional<Apartado> apartadoExistente = apartadoRepo.findByCodigoApartadoIgnoreCase(apartado.getIdApartado());

        if (apartadoExistente.isPresent()) {
            return "{\"success\": false, \"message\": \"El código de apartado ya existe\"}";
        } else {
            try {
                apartadoRepo.save(apartado);
                return "{\"success\": true, \"message\": \"Apartado agregado exitosamente\"}";
            } catch (Exception e) {
                return "{\"success\": false, \"message\": \"Error al agregar el apartado\"}";
            }
        }
    }

    @Override
    public String verificarPreModificar(Apartado apartado) {

        Optional<Apartado> apartadoExistente = apartadoRepo.findByCodigoApartadoIgnoreCase(apartado.getIdApartado());
        if (apartadoExistente.isPresent() && !Integer.valueOf(apartadoExistente.get().getIdApartado()).equals(apartado.getIdApartado())) {
            return "{\"success\": false, \"message\": \"El código de apartado ya existe\"}";
        } else {
            try {
                apartadoRepo.save(apartado);
                return "{\"success\": true, \"message\": \"Apartado modificado exitosamente\"}";
            } catch (Exception e) {
                return "{\"success\": false, \"message\": \"Error al modificar el apartado\"}";
            }
        }
    }
    
    
}