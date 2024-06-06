/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.OrdenDeCompraData;
import cr.ac.una.proyecto_progra4.domain.OrdenDeCompra;
import cr.ac.una.proyecto_progra4.jpa.OrdenDeCompraRepository;
import jakarta.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Acuña
 */
@Service
@Primary
public class OrdenDeCompraServices implements IOrdenDeCompraService {

    public boolean crearOrdenCompra(OrdenDeCompra ordenCompra) {
        return new OrdenDeCompraData().crearOrdenCompraBD(ordenCompra);
    }

    public LinkedList<OrdenDeCompra> listaOrdenes() {
        return new OrdenDeCompraData().listaOrdenesDB();
    }

    public LinkedList<OrdenDeCompra> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, List<OrdenDeCompra> listaOrdenes) {
        LinkedList<OrdenDeCompra> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaOrdenes.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaOrdenes.get(i));
        }

        return registrosPagina;
    }

    public boolean editarOrden(OrdenDeCompra orden) {
        return new OrdenDeCompraData().editarOrdenDB(orden);
    }

    public boolean eliminarOrden(String numeroReferencia) {
        return new OrdenDeCompraData().eliminarOrdenDB(numeroReferencia);
    }

    @Autowired
    private OrdenDeCompraRepository ordenRep;

    @Override
    public String guardar(OrdenDeCompra orden) {
        if (orden.getIdProveedor() == null || orden.getIdPedido() == null
                || orden.getFechaOrden() == null || orden.getFechaEntrega() == null
                || orden.getEstadoOrden() == null || orden.getEstadoOrden().isEmpty()
                || orden.getNumeroReferencia() == null || orden.getNumeroReferencia().isEmpty()) {

            return "{\"success\": false, \"message\": \"Por favor, rellene todos los campos.\"}";
        }

        try {
            ordenRep.save(orden);
            return "{\"success\": true, \"message\": \"La orden de compra se ha registrado exitosamente.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ha ocurrido un error al registrar la orden de compra.\"}";
        }
    }

    @Override
    public List<OrdenDeCompra> getOrdenes() {
        return ordenRep.findAll();
    }
    
    @Override
    @Transactional
    public String eliminar(String numeroReferencia) {
        try {
            ordenRep.deleteByNumeroReferencia(numeroReferencia);
            return "{\"success\": true, \"message\": \"Orden de compra eliminada exitosamente.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Error al eliminar la orden de compra: " + e.getMessage() + "\"}";
        }
    }
    
    @Override
    @Transactional
    public String actualizarOrden(String numeroReferencia, OrdenDeCompra nuevaOrden) {
        try {
            Optional<OrdenDeCompra> ordenExistenteOpt = ordenRep.findByNumeroReferencia(numeroReferencia);
            if (ordenExistenteOpt.isPresent()) {
                OrdenDeCompra ordenExistente = ordenExistenteOpt.get();
                // Actualiza los campos necesarios
                ordenExistente.setIdPedido(nuevaOrden.getIdPedido());
                ordenExistente.setIdProveedor(nuevaOrden.getIdProveedor());
                ordenExistente.setFechaOrden(nuevaOrden.getFechaOrden());
                ordenExistente.setFechaEntrega(nuevaOrden.getFechaEntrega());
                ordenExistente.setEstadoOrden(nuevaOrden.getEstadoOrden());

                ordenRep.save(ordenExistente);

                return "{\"success\": true, \"message\": \"Orden de compra actualizada exitosamente.\"}";
            } else {
                return "{\"success\": false, \"message\": \"Orden de compra no encontrada.\"}";
            }
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Error al actualizar la orden de compra: " + e.getMessage() + "\"}";
        }
    }
}
