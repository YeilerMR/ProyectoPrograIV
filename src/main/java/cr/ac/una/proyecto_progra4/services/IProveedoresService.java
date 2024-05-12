/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Proveedor;
import java.util.List;

/**
 *
 * @author Adam Acuña
 */
public interface IProveedoresService {

    public void guardar(Proveedor proveedor);

    public List<Proveedor> getProveedores();

    public void eliminar(int codigo);

}
