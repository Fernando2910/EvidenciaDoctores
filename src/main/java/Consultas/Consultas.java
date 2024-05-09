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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


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
    
    public void AgregarDoctor(String nombre, String especialidad){
        String sql = "Insert into doctores (nombre, especialidad) values (?,?)";
        
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
        try{
            ResultSet rs;
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            ps.setString(2, especialidad);
            
            int res = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta al Doctor, error: "+ e);
        }
    }
    
    public void MostrarDoctores(JTable jtableListaDoctores){
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
        
        DefaultTableModel model = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        jtableListaDoctores.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        model.addColumn("id_doctor");
        model.addColumn("Nombre");
        model.addColumn("Especialidad");
        
        jtableListaDoctores.setModel(model);
        
        sql = "Select * from doctores";
        
        String [] datos = new String[3];
        
        Statement st;

        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                
                model.addRow(datos);

            }
                // Asignamos el modelo a la JTable que recibimos como parámetro
            jtableListaDoctores.setModel(model);

            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros: "+ e);
        }
    }
    
    public void seleccionarDoctor(JTable jtableListaDoctores, JTextField id, JTextField Nombre, JTextField Especialidad ){
        try{
            int fila = jtableListaDoctores.getSelectedRow();
            
            if (fila >= 0){
                id.setText((jtableListaDoctores.getValueAt(fila, 0).toString()));
                Nombre.setText((jtableListaDoctores.getValueAt(fila, 1).toString()));
                Especialidad.setText((jtableListaDoctores.getValueAt(fila, 2).toString()));
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");

            }

            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ e);
            
        }
    }
    
    public void ActualizarDoctor (String id, String Nombre, String Especialidad){
        String sql = "UPDATE doctores SET nombre = ?, especialidad = ? WHERE id_doctor = ?";

        Conexion.CConexion conectar = new Conexion.CConexion();
        Connection con = conectar.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, Nombre);
            ps.setString(2, Especialidad);
            ps.setString(3, id);


            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente el doctor");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el doctor");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el doctor: " + e.getMessage());
        }
    }
        public void AgregarPaciente(String nombre){
        String sql = "Insert into pacientes (nombre_completo) values (?)";
        
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
        try{
            ResultSet rs;
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            
            int res = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se dio de alta correctamente al paciente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo dar de alta al Paciente, error: "+ e);
        }
    }
        public void MostrarPaciente(JTable jtableListaPaciente){
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
        
        DefaultTableModel model = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        jtableListaPaciente.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        model.addColumn("id_paciente");
        model.addColumn("Nombre");
        
        jtableListaPaciente.setModel(model);
        
        sql = "Select * from pacientes";
        
        String [] datos = new String[2];
        
        Statement st;

        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                model.addRow(datos);

            }
                // Asignamos el modelo a la JTable que recibimos como parámetro
            jtableListaPaciente.setModel(model);

            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros: "+ e);
        }
    }
    public void seleccionarPaciente(JTable jtableListaPaciente, JTextField id, JTextField Nombre){
        try{
            int fila = jtableListaPaciente.getSelectedRow();
            
            if (fila >= 0){
                id.setText((jtableListaPaciente.getValueAt(fila, 0).toString()));
                Nombre.setText((jtableListaPaciente.getValueAt(fila, 1).toString()));
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");

            }

            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ e);
            
        }
    }
    public void ActualizarPaciente (String id, String Nombre){
        String sql = "UPDATE pacientes SET nombre = ? WHERE id_paciente = ?";

        Conexion.CConexion conectar = new Conexion.CConexion();
        Connection con = conectar.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, Nombre);
            ps.setString(2, id);


            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente el Paciente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el Paciente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el paciente: " + e.getMessage());
        }
    }
}
