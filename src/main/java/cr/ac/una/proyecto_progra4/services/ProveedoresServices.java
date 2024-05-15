/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.ProveedoresData;
import cr.ac.una.proyecto_progra4.domain.Proveedor;
import cr.ac.una.proyecto_progra4.jpa.ProveedorRepository;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Acuña
 */
@Service
@Primary
public class ProveedoresServices implements IProveedoresService {

    public boolean crearProveedor(Proveedor proveedor) {
        return new ProveedoresData().crearProveedorBD(proveedor);
    }

    public LinkedList<Proveedor> listaProveedores() {
        return new ProveedoresData().listaProveedores();
    }

    public LinkedList<Proveedor> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, List<Proveedor> listaProveedores) {
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
    
    @Autowired
    private ProveedorRepository proveedorRep;

    @Override
    public String guardar(Proveedor proveedor) {
        proveedorRep.save(proveedor);
        return "{\"success\": true, \"message\": \"¡Proveedor agregado exitosamente!\"}";
    }

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRep.findAll();
    }

    @Override
    public void eliminar(int id) {
        proveedorRep.deleteById(id);
    }

    public void actualizarProveedor(Proveedor proveedorActualizado) {
        Proveedor proveedorExistente = proveedorRep.findById(proveedorActualizado.getIdProveedor()).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        proveedorRep.save(proveedorExistente);
    }
}
