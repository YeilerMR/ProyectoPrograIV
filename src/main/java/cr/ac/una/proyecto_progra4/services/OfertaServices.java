/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataOfertas;
import cr.ac.una.proyecto_progra4.domain.Oferta;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeile
 */
public class OfertaServices {
    
    //metodo agrega ofertas
    public static boolean agregarOferta(Oferta oferta){
        boolean resultado= true;
        try {
            resultado= DataOfertas.insertar(oferta);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServices.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        return resultado;
    }
    
    
    public static LinkedList<Oferta> getOfertas(){
        LinkedList<Oferta> ofertas;
        try {
            ofertas= DataOfertas.getOfertas();
            //System.out.println("Codigo O: "+ofertas.get(0).getFechaInicio());
        } catch (SQLException ex) {
            ofertas=null;
            Logger.getLogger(OfertaServices.class.getName()).log(Level.SEVERE,null,ex);
        }
        return ofertas;
    }
    
    public LinkedList<Oferta> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Oferta> listaOfertas) {
        LinkedList<Oferta> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaOfertas.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaOfertas.get(i));
        }

        return registrosPagina;
    }
    
    public static boolean eliminar(String codigo){
        boolean resultado= true;
        try {
            resultado= DataOfertas.eliminar(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(OfertaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static boolean modificar(Oferta oferta){
        boolean resultado= true;
        try {
            resultado= DataOfertas.modificar(oferta);
        } catch (SQLException ex) {
            Logger.getLogger(OfertaServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
