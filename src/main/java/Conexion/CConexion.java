/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Fer
 */
public class CConexion {
    public static final String URL = "jdbc:mysql://localhost:3306/evidenciadoctores";

    public static final String USER = "root";
    public static final String CLAVE = "Flipout11";
     
    public Connection getConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
//            JOptionPane.showMessageDialog(null, "Conexión establecida con éxito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar, error: "+e.getMessage() );
            //System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}
