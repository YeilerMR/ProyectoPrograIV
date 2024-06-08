/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.services;

import cr.ac.una.proyecto_progra4.data.DataEmpleado;
import cr.ac.una.proyecto_progra4.domain.Empleado;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kinco
 */
public class EmpleadosServices {

    public static boolean agregar(Empleado empleado) {
//        try {
//             Verificar si ya existe un empleado con la misma cédula
//            Empleado empleadoExistenteCedula = getEmpleadoPorCedula(empleado.getCedula());
//             Verificar si ya existe un empleado con el mismo email
//            Empleado empleadoExistenteEmail = getEmpleadoPorEmail(empleado.getEmail());
//             Si empleadoExistenteCedula no es null, significa que ya hay un empleado con esa cédula
//            if (empleadoExistenteCedula != null) {
//                System.out.println("Error: Ya existe un empleado con la misma cédula.");
//                return false;
//            } else if (empleadoExistenteEmail != null) {
//                 Si empleadoExistenteEmail no es null, significa que ya hay un empleado con ese email
//                System.out.println("Error: El email ya está asociado a otro empleado.");
//                return false;
//            } else {
//                 Si no hay un empleado existente con la misma cédula ni con el mismo email, procede a insertar el nuevo empleado
//                return DataEmpleado.insertar(empleado);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
        return false;
    }

    // Mostrar lista empleados
    public static LinkedList<Empleado> getEmpleados() {
        LinkedList<Empleado> empleados;

        try {
            empleados = DataEmpleado.getEmpleados();
        } catch (SQLException ex) {
            empleados = null;
            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empleados;
    }

    // Actualizar empleado
    public static boolean actualizar(Empleado empleado) {
        try {
            return DataEmpleado.actualizar(empleado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Eliminar un empleado
    public static boolean eliminar(int idUsuario_Empleado) {
        try {
            return DataEmpleado.eliminar(idUsuario_Empleado);
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadosServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //--------------MÉTODOS DE VERIFICACIÓN DE DATOS DUPLICADOS-----------------
    // Obtener empleado por id
    public static Empleado getEmpleadoPorID(int idUsuario_Empleado) {
        for (Empleado empleado : getEmpleados()) {
//            if (empleado.getId() == (idUsuario_Empleado)) {
//                return empleado;
//            }
        }
        return null; // Si no se encuentra ningún empleado con el id especificado
    }

    // Obtener empleado por cédula
    public static Empleado getEmpleadoPorCedula(String cedula) {
        for (Empleado empleado : getEmpleados()) {
//            if (empleado.getCedula().equals(cedula)) {
//                return empleado;
//            }
        }
        return null; // Si no se encuentra ningún empleado con la cédula especificado
    }

    // Método para obtener un empleado por su email
    public static Empleado getEmpleadoPorEmail(String email) {
        for (Empleado empleado : getEmpleados()) {
//            if (empleado.getEmail().equals(email)) {
//                return empleado;
//            }
        }
        return null; // Si no se encuentra ningún cliente con el email especificado
    }

    // Método para obtener un empleado por su telefono
    public static Empleado getEmpleadoPorTelefono(String telefono) {
        for (Empleado empleado : getEmpleados()) {
//            if (empleado.getTelefono().equals(telefono)) {
//                return empleado;
//            }
        }
        return null; // Si no se encuentra ningún empleado con el telefono especificado
    }

    public LinkedList<Empleado> obtenerRegistrosPaginados(int numeroPagina, int tamanoPagina, LinkedList<Empleado> listaEmpleados) {
        LinkedList<Empleado> registrosPagina = new LinkedList<>();

        int inicio = numeroPagina * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaEmpleados.size());

        for (int i = inicio; i < fin; i++) {
            registrosPagina.add(listaEmpleados.get(i));
        }

        return registrosPagina;
    }

    //-------------------MÉTODOS FUNCIONALIDES PRE CRUD-------------------------
    //Verificar si alguno de los datos esta repetido antes de agregar un nuevo empleado
    public static String verificarPreAgregar(Empleado empleado) {
        Empleado empleadoExistenteCedula = new Empleado(); // = getEmpleadoPorCedula(empleado.getCedula());
        Empleado empleadoExistenteEmail = new Empleado();; // = getEmpleadoPorEmail(empleado.getEmail());
        Empleado empleadoExistenteTelefono = new Empleado();; // = getEmpleadoPorTelefono(empleado.getTelefono());

        if (empleadoExistenteCedula != null) {
            return "{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro empleado\"}";
        } else if (empleadoExistenteEmail != null) {
            return "{\"success\": false, \"message\": \"El email ya está asociado a otro empleado\"}";
        } else if (empleadoExistenteTelefono != null) {
            return "{\"success\": false, \"message\": \"El télefono está asociado a otro empleado\"}";
        } else {
            boolean agregadoExitosamente = EmpleadosServices.agregar(empleado);
            if (agregadoExitosamente) {
                return "{\"success\": true, \"message\": \"Empleado agregado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al agregar el empleado\"}";
            }
        }
    }

    public static String verificarPreModificar(Empleado empleado) {
        // Obtener el empleado existente por ID
        Empleado empleadoExistente = new Empleado(); // = getEmpleadoPorID(empleado.getId());

        // Verificar si el cliente existe
        if (empleadoExistente == null) {
            return "{\"success\": false, \"message\": \"El empleado no existe\"}";
        }

        // Verificar si los datos modificados son iguales a los datos actuales del empleado
        if (empleado.equals(empleadoExistente)) {
            // Si los datos son iguales, no es necesario hacer más verificaciones
            boolean actualizadoExitosamente = EmpleadosServices.actualizar(empleado);
            if (actualizadoExitosamente) {
                return "{\"success\": true, \"message\": \"Empleado actualizado exitosamente\"}";
            } else {
                return "{\"success\": false, \"message\": \"Error al actualizar el empleado\"}";
            }
        }

        // Verificar si algún otro empleado tiene los mismos datos
        Empleado empleadoExistenteCedula = new Empleado(); // = getEmpleadoPorCedula(empleado.getCedula());
        Empleado empleadoExistenteEmail = new Empleado();; // = getEmpleadoPorEmail(empleado.getEmail());
        Empleado empleadoExistenteTelefono = new Empleado();; // = getEmpleadoPorTelefono(empleado.getTelefono());

//        // Verificar si algún otro cliente tiene la misma cédula, email o teléfono
//        if (empleadoExistenteCedula != null && empleadoExistenteCedula.getId() != empleado.getId()) {
//            return "{\"success\": false, \"message\": \"La cédula ya se encuentra asociada a otro empleado\"}";
//        } else if (empleadoExistenteEmail != null && empleadoExistenteEmail.getId() != empleado.getId()) {
//            return "{\"success\": false, \"message\": \"El email ya está asociado a otro empleado\"}";
//        } else if (empleadoExistenteTelefono != null && empleadoExistenteTelefono.getId() != empleado.getId()) {
//            return "{\"success\": false, \"message\": \"El teléfono está asociado a otro empleado\"}";
//        } else {
//            // Si todos los datos están bien, procede con la actualización del empleado
//            boolean actualizadoExitosamente = EmpleadosServices.actualizar(empleado);
//            if (actualizadoExitosamente) {
//                return "{\"success\": true, \"message\": \"Empleado actualizado exitosamente\"}";
//            } else {
//                return "{\"success\": false, \"message\": \"Error al actualizar el empleado\"}";
//            }
//        }
        return "{\"success\": false, \"message\": \"Error al actualizar el empleado\"}";
    }

}
