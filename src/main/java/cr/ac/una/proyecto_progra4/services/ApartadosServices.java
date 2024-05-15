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
    
    public static LinkedList<Apartado> getApartados(){
        LinkedList<Apartado> apartados;
        try {
            apartados = new DataApartado().getApartados();
        } catch (SQLException ex) {
            apartados = null;
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return apartados;
    }
    
    public static boolean eliminar(int identificador) {
         return new DataApartado().eliminar(identificador); 
    }
    
    public static boolean insertar(Apartado apartado) {
        boolean result = false;
        try {
        new DataApartado().insertar(apartado);
        result = true;
        } catch (SQLException ex) {
            Logger.getLogger(ApartadosServices.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        }
        return result;
    }
    
    public static boolean editar(Apartado apartado) {
        try {
            return new DataApartado().editar(apartado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}