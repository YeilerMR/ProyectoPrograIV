/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Envio;
import cr.ac.una.proyecto_progra4.jpa.EnviosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aaron
 */
@Service
@Primary
public class EnvioServices implements IEnvioServices {

    @Autowired
    private EnviosRepository envioRepo;

    @Override
    public boolean agregar(Envio envio) {
        try {
            envioRepo.save(envio);
            return true; // Si el guardado tiene éxito, devuelve true
        } catch (Exception e) {
            e.toString();
            return false;
        }
    }

    @Override
    public List<Envio> getEnvios() {
        return envioRepo.findAll();
    }

    @Override
    public Envio getEnvioPorCodigo(String codigo) {
        Optional<Envio> optionalEnvio = envioRepo.findByCodigoEnvioIgnoreCase(codigo);
        return optionalEnvio.orElse(null);
    }

    @Override
    public Envio getEnvioPorID(int idEnvio) {
        Optional<Envio> optionalEnvio = envioRepo.findById(idEnvio);
        return optionalEnvio.orElse(null);
    }

    @Override
    public boolean eliminar(int idEnvio) {
        try {
            envioRepo.deleteById(idEnvio);
            return true; // Se eliminó correctamente
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Envio> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina) {
        Page<Envio> paginaEnvios = envioRepo.findAll(PageRequest.of(numeroPagina, tamanoPagina));
        return paginaEnvios.getContent();
    }

    @Override
    public String verificarPreAgregar(Envio envio) {
        // Verificar si el código del envío ya existe
        Optional<Envio> envioExistente = envioRepo.findByCodigoEnvioIgnoreCase(envio.getCodigoEnvio());
        if (envioExistente.isPresent()) {
            return "{\"success\": false, \"message\": \"El código de envío ya existe\"}";
        } else {
            try {
                envioRepo.save(envio);
                return "{\"success\": true, \"message\": \"Envío agregado exitosamente\"}";
            } catch (Exception e) {
                return "{\"success\": false, \"message\": \"Error al agregar el envío\"}";
            }
        }
    }

    @Override
    public String verificarPreModificar(Envio envio) {
        // Verificar si el código del envío ya existe y no pertenece al mismo envío
        Optional<Envio> envioExistente = envioRepo.findByCodigoEnvioIgnoreCase(envio.getCodigoEnvio());
        if (envioExistente.isPresent() && !Integer.valueOf(envioExistente.get().getIdEnvio()).equals(envio.getIdEnvio())) {
            return "{\"success\": false, \"message\": \"El código de envío ya existe\"}";
        } else {
            try {
                envioRepo.save(envio);
                return "{\"success\": true, \"message\": \"Envío modificado exitosamente\"}";
            } catch (Exception e) {
                return "{\"success\": false, \"message\": \"Error al modificar el envío\"}";
            }
        }
    }

    @Override
    public List<Envio> getEnviosByClienteId(int idCliente) {
        return envioRepo.findEnviosByClienteId(idCliente);
    }
}
