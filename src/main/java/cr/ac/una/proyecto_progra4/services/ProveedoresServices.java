/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.ProveedoresData;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import java.util.LinkedList;

/**
 *
 * @author Adam Acuña
 */
public class ProveedoresServices {

    public boolean crearProveedor(Proveedor proveedor) {
        return new ProveedoresData().crearProveedorBD(proveedor);
    }

    public LinkedList<Proveedor> listaProveedores() {
        return new ProveedoresData().listaProveedores();
    }

    public LinkedList<Proveedor> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Proveedor> listaProveedores) {
        LinkedList<Proveedor> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaProveedores.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaProveedores.get(i));
        }

        return registrosPagina;
    }

    public boolean editarProveedor(Proveedor proveedor) {
        return new ProveedoresData().editarProveedorDB(proveedor);
    }

    public boolean eliminarProveedor(int proveedorID) {
        return new ProveedoresData().eliminarProveedorBD(proveedorID);
    }
}
