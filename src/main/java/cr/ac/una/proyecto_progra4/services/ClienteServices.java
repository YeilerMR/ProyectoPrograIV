/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataCliente;
import cr.ac.una.proyecto_progra4.domain.Cliente;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class ClienteServices {

    //-----------------------------MÉTODOS CRUD---------------------------------
    // Agregar cliente con validación de cédula y email
    public static boolean agregar(Cliente cliente) {
        try {
            // Verificar si ya existe un cliente con la misma cédula
            Cliente clienteExistenteCedula = getClientePorCedula(cliente.getCedula());
            // Verificar si ya existe un cliente con el mismo email
            Cliente clienteExistenteEmail = getClientePorEmail(cliente.getEmail());
            // Si clienteExistenteCedula no es null, significa que ya hay un cliente con esa cédula
            if (clienteExistenteCedula != null) {
                System.out.println("Error: Ya existe un cliente con la misma cédula.");
                return false;
            } else if (clienteExistenteEmail != null) {
                // Si clienteExistenteEmail no es null, significa que ya hay un cliente con ese email
                System.out.println("Error: El email ya está asociado a otro cliente.");
                return false;
            } else {
                // Si no hay un cliente existente con la misma cédula ni con el mismo email, procede a insertar el nuevo cliente
                return DataCliente.insertar(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Mostrar lista clientes
    public static LinkedList<Cliente> getClientes() {
        LinkedList<Cliente> clientes;

        try {
            clientes = DataCliente.getClientes();
        } catch (SQLException ex) {
            clientes = null;
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }

    // Actualizar cliente
    public static boolean actualizar(Cliente cliente) {
        try {
            return DataCliente.actualizar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Eliminar un cliente
    public static boolean eliminar(int idUsuario_Cliente) {
        try {
            return DataCliente.eliminar(idUsuario_Cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //--------------MÉTODOS DE VERIFICACIÓN DE DATOS DUPLICADOS-----------------
    // Obtener cliente por id
    public static Cliente getClientePorID(int idUsuario_Cliente) {
        for (Cliente cliente : getClientes()) {
            if (cliente.getId() == (idUsuario_Cliente)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra ningún cliente con la cédula especificado
    }

    // Obtener cliente por cédula
    public static Cliente getClientePorCedula(String cedula) {
        for (Cliente cliente : getClientes()) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra ningún cliente con la cédula especificado
    }

    // Método para obtener un cliente por su email
    public static Cliente getClientePorEmail(String email) {
        for (Cliente cliente : getClientes()) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra ningún cliente con el email especificado
    }

    // Método para obtener un cliente por su email
    public static Cliente getClientePorTelefono(String telefono) {
        for (Cliente cliente : getClientes()) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra ningún cliente con el email especificado
    }

    //-------------------MÉTODOS FUNCIONALIDES NECESARIAS-----------------------
    // Obtener clientes con envios
    public static LinkedList<Cliente> getClientesConEnvios() {
        LinkedList<Cliente> clientes;

        try {
            clientes = DataCliente.getClientesConEnvios();
        } catch (SQLException ex) {
            clientes = null;
            Logger.getLogger(ClienteServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }
    
    public LinkedList<Cliente> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Cliente> listaClientes) {
        LinkedList<Cliente> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaClientes.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaClientes.get(i));
        }

        return registrosPagina;
    }
    //-------------------MÉTODOS FUNCIONALIDES PRE CRUD-------------------------
    //Verificar si alguno de los datos esta repetido antes de agregar un nuevo cliente
    public static String verificarPreAgregar(Cliente cliente) {
        Cliente clienteExistenteCedula = getClientePorCedula(cliente.getCedula());
        Cliente clienteExistenteEmail = getClientePorEmail(cliente.getEmail());
        Cliente clienteExistenteTelefono = getClientePorTelefono(cliente.getTelefono());

        if (clienteExistenteCedula != null) {
            return "{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro cliente\"}";
        } else if (clienteExistenteEmail != null) {
            return "{\"success\": false, \"message\": \"El email ya está asociado a otro cliente\"}";
        } else if (clienteExistenteTelefono != null) {
            return "{\"success\": false, \"message\": \"El télefono está asociado a otro cliente\"}";
        } else {
            boolean agregadoExitosamente = ClienteServices.agregar(cliente);
            if (agregadoExitosamente) {
                return "{\"success\": true, \"message\": \"Cliente agregado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al agregar el cliente\"}";
            }
        }
    }

    public static String verificarPreModificar(Cliente cliente) {
        // Obtener el cliente existente por ID
        Cliente clienteExistente = getClientePorID(cliente.getId());

        // Verificar si el cliente existe
        if (clienteExistente == null) {
            return "{\"success\": false, \"message\": \"El cliente no existe\"}";
        }

        // Verificar si los datos modificados son iguales a los datos actuales del cliente
        if (cliente.getCedula().equals(clienteExistente.getCedula())
                && cliente.getEmail().equals(clienteExistente.getEmail())
                && cliente.getTelefono().equals(clienteExistente.getTelefono())) {
            // Si los datos son iguales, no es necesario hacer más verificaciones
            boolean actualizadoExitosamente = ClienteServices.actualizar(cliente);
            if (actualizadoExitosamente) {
                return "{\"success\": true, \"message\": \"Cliente actualizado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al actualizar el cliente\"}";
            }
        }

        // Verificar si el cliente existe con la misma cédula, email y teléfono, excluyendo el cliente actual
        Cliente clienteExistenteCedula = getClientePorCedula(cliente.getCedula());
        Cliente clienteExistenteEmail = getClientePorEmail(cliente.getEmail());
        Cliente clienteExistenteTelefono = getClientePorTelefono(cliente.getTelefono());

        if (clienteExistenteCedula != null && !clienteExistenteCedula.equals(clienteExistente)) {
            return "{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro cliente\"}";
        } else if (clienteExistenteEmail != null && !clienteExistenteEmail.equals(clienteExistente)) {
            return "{\"success\": false, \"message\": \"El email ya está asociado a otro cliente\"}";
        } else if (clienteExistenteTelefono != null && !clienteExistenteTelefono.equals(clienteExistente)) {
            return "{\"success\": false, \"message\": \"El teléfono está asociado a otro cliente\"}";
        } else {
            // Si todos los datos están bien, procede con la actualización del cliente
            boolean actualizadoExitosamente = ClienteServices.actualizar(cliente);
            if (actualizadoExitosamente) {
                return "{\"success\": true, \"message\": \"Cliente actualizado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al actualizar el cliente\"}";
            }
        }
    }

}
