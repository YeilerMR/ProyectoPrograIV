/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto_progra4.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yeile
 */
public class ConectarBD {
    private final static String 
    DATABASE_NAME= "dbproyectoprogra4",
    USER= "root",
    PASS= "";
    
    private final static int PORT= 3306;
    private final static String 
    HOST="localhost",
            
    URL= ""+"jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE_NAME;
    private static Connection conexion;
    
    public static Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class for name not found"+ cnfe.getMessage());
        }
        try {
            conexion= DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Se establecio conexion con la base de datos");
        } catch (SQLException e) {
            System.out.println("Error en la conexion"+e.getMessage());
        }
        return conexion;
    }
}
