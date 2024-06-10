/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.domain.Cliente;
import cr.ac.una.proyecto_progra4.domain.Usuario;
import cr.ac.una.proyecto_progra4.jpa.ClienteRepository;
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
 * @author Aaron
 */
@Service
@Primary
public class ClienteServices implements IClienteServices {

    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private UsuarioServices userServices;

    @Override
    public boolean agregar(Cliente cliente) {
        Usuario usuarioGuardado = userServices.agregar(cliente.getUsuario());
        if (usuarioGuardado != null) {
            clienteRepo.save(cliente);
            return true;
        }
        return false;
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.getClientes();
    }

    @Override
    @Transactional
    public boolean eliminar(int idCliente) {
        try {
            // Buscar el cliente por su ID
            Optional<Cliente> clienteOptional = clienteRepo.findById(idCliente);

            // Verificar si el cliente existe
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();

                // Obtener el usuario asociado al cliente
                Usuario usuario = cliente.getUsuario();

                // Eliminar el usuario
                userServices.eliminar(usuario.getId());

                // Eliminar el cliente
                clienteRepo.delete(cliente);

                return true; // Se eliminó correctamente
            } else {
                return false; // Cliente no encontrado
            }
        } catch (Exception e) {
            return false; // Error al eliminar
        }
    }

    @Override
    public Cliente getClientePorCedula(String cedula) {
        return clienteRepo.findByCedula(cedula).orElse(null);
    }

    @Override
    public Cliente getClientePorID(int idUsuario) {
        return clienteRepo.findById(idUsuario).orElse(null);
    }

    @Override
    public Cliente getClientePorEmail(String email) {
        return clienteRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Cliente getClientePorTelefono(String telefono) {
        return clienteRepo.findByTelefono(telefono).orElse(null);
    }

    @Override
    public List<Cliente> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina) {
        Page<Cliente> paginaCliente = clienteRepo.getClientesPages(PageRequest.of(numeroPagina, tamanoPagina));
        return paginaCliente.getContent();
    }

    @Override
    public List<Cliente> getClientesConEnvios() {
        return clienteRepo.findClientesConEnvios();
    }
    
     @Override
    public List<Cliente> getClientesConApartados() {
        return clienteRepo.findClientesConApartados();
    }

}
