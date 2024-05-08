/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Vista.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Fer
 */
public class Consultas {
    public boolean consultarUsuario(String user, String pass)
    {
        // TODO add your handling code here:
        Conexion.CConexion conectar = new Conexion.CConexion();
        // Se inicializa a null
        String usuarioCorrecto = null;
        String passCorrecto = null;
    try {

        Connection con = conectar.getConexion();
        PreparedStatement ps = con.prepareStatement("SELECT nombre_usuario, contrasena FROM usuarios WHERE nombre_usuario = ?");
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuarioCorrecto = rs.getString(1);
            passCorrecto = rs.getString(2);
        }

        if (user.equals(usuarioCorrecto) && pass.equals(passCorrecto)) {
            JOptionPane.showMessageDialog(null, "Login correcto Bienvenido " + user);
            Menu menu = new Menu();
            menu.setVisible(true);
            return true;
            
        } else if (!user.equals(usuarioCorrecto) || !pass.equals(passCorrecto)) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error " + e);
    }
    return false;
    }
    
    public void AgregarUsuario(String nombre_usuario, String contrasena, String tipo_usuario){
        String sql = "Insert into usuarios (nombre_usuario, contrasena,tipo_usuario) values (?,?,?)";
        
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
       
        try {
            ResultSet rs;
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre_usuario);
            ps.setString(2, contrasena);
            ps.setString(3, tipo_usuario);

            
            int res = ps.executeUpdate();
            
            if(res>0){
                JOptionPane.showMessageDialog(null, "Se creo correctamente el usuario");
            }
            
            con.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el usuario, error: "+ e);
        }
    }
        
}
