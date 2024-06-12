/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Envio;
import java.util.List;

/**
 *
 * @author Aaron
 */
public interface IEnvioServices {

    public boolean agregar(Envio envio);

    public List<Envio> getEnvios();

    public Envio getEnvioPorCodigo(String codigo);

    public Envio getEnvioPorID(int idEnvio);

    public boolean eliminar(int idEnvio);

    public List<Envio> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina);

    public String verificarPreAgregar(Envio envio);

    public String verificarPreModificar(Envio envio);
    
    public List<Envio> getEnviosByClienteId(int idCliente);
}
