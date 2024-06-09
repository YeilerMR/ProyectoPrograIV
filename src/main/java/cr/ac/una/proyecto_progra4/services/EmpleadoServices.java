/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Empleado;
import cr.ac.una.proyecto_progra4.domain.Usuario;
import cr.ac.una.proyecto_progra4.jpa.EmpleadoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author kinco
 */
@Service
@Primary
public class EmpleadoServices implements IEmpleadoServices{

    
    @Autowired
    private EmpleadoRepository empleadoRepo;
    @Autowired
    private UsuarioServices userServices;

    @Override
    public boolean agregar(Empleado empleado) {
        Usuario usuarioGuardado = userServices.agregar(empleado.getUsuario());
        if (usuarioGuardado != null) {
            empleadoRepo.save(empleado);
            return true;
        }
        return false;
    }

    @Override
    public List<Empleado> getEmpleados() {
        return empleadoRepo.getEmpleados();
    }

    @Override
    @Transactional
    public boolean eliminar(int idEmpleado) {
        try {
            // Buscar el cliente por su ID
            Optional<Empleado> empleadoOptional = empleadoRepo.findById(idEmpleado);

            // Verificar si el cliente existe
            if (empleadoOptional.isPresent()) {
                Empleado empleado = empleadoOptional.get();

                // Obtener el usuario asociado al cliente
                Usuario usuario = empleado.getUsuario();

                // Eliminar el usuario
                userServices.eliminar(usuario.getId());

                // Eliminar el cliente
                empleadoRepo.delete(empleado);

                return true; // Se eliminó correctamente
            } else {
                return false; // Cliente no encontrado
            }
        } catch (Exception e) {
            return false; // Error al eliminar
        }
    }

    @Override
    public Empleado getEmpleadoPorCedula(String cedula) {
        return empleadoRepo.findByCedula(cedula).orElse(null);
    }

    @Override
    public Empleado getEmpleadoPorID(int idUsuario) {
        return empleadoRepo.findById(idUsuario).orElse(null);
    }

    @Override
    public Empleado getEmpleadoPorEmail(String email) {
        return empleadoRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Empleado getEmpleadoPorTelefono(String telefono) {
        return empleadoRepo.findByTelefono(telefono).orElse(null);
    }

    @Override
    public List<Empleado> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina) {
        Page<Empleado> paginaEmpleado = empleadoRepo.getEmpleadosPages(PageRequest.of(numeroPagina, tamanoPagina));
        return paginaEmpleado.getContent();
    }


}
