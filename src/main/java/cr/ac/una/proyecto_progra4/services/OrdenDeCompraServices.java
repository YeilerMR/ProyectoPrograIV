/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.OrdenDeCompraData;
import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import java.util.LinkedList;

/**
 *
 * @author Adam Acuña
 */
public class OrdenDeCompraServices {
    
    public boolean crearOrdenCompra(OrdenDeCompra ordenCompra){
        return new OrdenDeCompraData().crearOrdenCompraBD(ordenCompra);
    }
    
    public LinkedList<OrdenDeCompra> listaOrdenes(){
        return new OrdenDeCompraData().listaOrdenesDB();
    }
    
    public LinkedList<OrdenDeCompra> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<OrdenDeCompra> listaOrdenes) {
        LinkedList<OrdenDeCompra> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaOrdenes.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaOrdenes.get(i));
        }

        return registrosPagina;
    }
    
    public boolean editarOrden(OrdenDeCompra orden){
        return new OrdenDeCompraData().editarOrdenDB(orden);
    }
    
    public boolean eliminarOrden(String numeroReferencia){
        return new OrdenDeCompraData().eliminarOrdenDB(numeroReferencia);
    }
}
