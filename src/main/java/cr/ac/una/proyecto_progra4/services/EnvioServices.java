/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataEnvio;
import cr.ac.una.proyecto_progra4.domain.Envio;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class EnvioServices {

    public static boolean agregar(Envio envio) {
        try {
            return DataEnvio.insertar(envio);
        } catch (SQLException e) {
            System.out.println("Error al insertar el envío: " + e.getMessage());
            return false;
        }
    }

    // Mostrar lista clientes
    public static LinkedList<Envio> getEnvios() {
        LinkedList<Envio> envios;
        try {
            envios = DataEnvio.getEnvios();
        } catch (SQLException ex) {
            envios = null;
            Logger.getLogger(EnvioServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return envios;
    }

    // Obtener ENVIO por código
    public static Envio getEnvioPorCodigo(String codigo) {
        Envio envio;
        try {
            envio = DataEnvio.getEnvioPorCodigo(codigo);
        } catch (SQLException ex) {
            envio = null;
            Logger.getLogger(EnvioServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return envio;
    }

    // Obtener ENVIO por ID
    public static Envio getEnvioPorID(int idEnvio) {
        for (Envio envio : getEnvios()) {
            if (envio.getIdEnvio() == (idEnvio)) {
                return envio;
            }
        }
        return null;
    }

    // Actualizar envio
    public static boolean actualizar(Envio envio) {
        try {
            return DataEnvio.actualizar(envio);
        } catch (SQLException ex) {
            Logger.getLogger(EnvioServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Eliminar un envio
    public static boolean eliminar(int idEnvio) {
        try {
            return DataEnvio.eliminar(idEnvio);
        } catch (SQLException ex) {
            Logger.getLogger(EnvioServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public LinkedList<Envio> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Envio> listaEnvios) {
        LinkedList<Envio> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaEnvios.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaEnvios.get(i));
        }

        return registrosPagina;
    }

    //-------------------MÉTODOS FUNCIONALIDES PRE CRUD-------------------------
    //Verificar si envío existe antes de agregar
    public static String verificarPreAgregar(Envio envio) {

        // Verificar si el envío ya existe
        Envio envioExistente = getEnvioPorCodigo(envio.getCodigoEnvio());
        if (envioExistente != null) {
            return "{\"success\": false, \"message\": \"El envío ya existe\"}";
        } else {
            boolean agregadoExitosamente = agregar(envio);
            if (agregadoExitosamente) {
                return "{\"success\": true, \"message\": \"Envío agregado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al agregar el envío\"}";
            }
        }
    }

    //Verificar si envío existe antes de modificar
    public static String verificarPreModificar(Envio envio) {
        // Verificar si el envío ya existe
        Envio envioExistente = getEnvioPorCodigo(envio.getCodigoEnvio());
        if (envioExistente != null && envioExistente.getCodigoEnvio().equalsIgnoreCase(envio.getCodigoEnvio()) && envioExistente.getIdEnvio() != envio.getIdEnvio()) {
            return "{\"success\": false, \"message\": \"El envío ya existe\"}";
        } else {
            boolean agregadoExitosamente = actualizar(envio);
            if (agregadoExitosamente) {
                return "{\"success\": true, \"message\": \"Envío modificado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al modificar el envío\"}";
            }
        }
    }
}
