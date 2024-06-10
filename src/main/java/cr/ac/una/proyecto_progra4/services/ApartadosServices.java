/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataApartado;
import cr.ac.una.proyecto_progra4.domain.Apartado;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author kinco
 */
@Service
public class ApartadosServices {
    
    public static boolean agregar(Apartado apartado) {
        boolean resultado= true;
        try {
            resultado= DataApartado.insertarApartado(apartado);
        } catch (SQLException ex) {
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return resultado;
    }
    
    public static LinkedList<Apartado> getApartados(){
        LinkedList<Apartado> apartados;
        try {
            apartados= DataApartado.getApartados();

        } catch (SQLException ex) {
            apartados=null;
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE,null,ex);
        }
        return apartados;
    }
    
        public LinkedList<Apartado> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Apartado> listaApartados) {
        LinkedList<Apartado> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaApartados.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaApartados.get(i));
        }

        return registrosPagina;
    }
        
         public static boolean eliminar(int codigo){
        boolean resultado= true;
        try {
            resultado= DataApartado.eliminar(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static boolean modificar(Apartado apartado){
        boolean resultado= true;
        try {
            resultado= DataApartado.actualizar(apartado);
        } catch (SQLException ex) {
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
}