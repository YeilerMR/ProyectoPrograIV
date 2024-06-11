/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Apartado;
import java.util.List;
/**
 *
 * @author kinco
 */
public interface IApartadoServices {

    public boolean agregar(Apartado apartado);

    public List<Apartado> getApartados();

    public boolean eliminar(int idApartado);

    public Apartado getApartadoPorID(int idApartado);

    public List<Apartado> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina);
    
    public String verificarPreAgregar(Apartado apartado);

    public String verificarPreModificar(Apartado apartado);
}
