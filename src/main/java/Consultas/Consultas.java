/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Vista.Menu;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
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
//    public void seleccionarPaciente(JTable jtableListaPaciente, JComboBox id, JTextField Nombre){
//        try{
//            int fila = jtableListaPaciente.getSelectedRow();
//            
//            if (fila >= 0){
//                id.setSelectedItem((jtableListaPaciente.getValueAt(fila, 0)));
//                Nombre.setText((jtableListaPaciente.getValueAt(fila, 1).toString()));
//            }else{
//                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
//
//            }
//
//            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ e);
//            
//        }
//    }

    public void seleccionarPaciente2(JTable jtableListaPaciente, JTextField id, JTextField Nombre){
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
        String sql = "UPDATE pacientes SET nombre_completo = ? WHERE id_paciente = ?";

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
    public boolean verificarDisponibilidad(String fecha, String hora) {
        // Construir la consulta SQL para verificar la disponibilidad
        String sql = "SELECT COUNT(*) FROM cita WHERE fecha_cita = ? AND hora = ?";

        Conexion.CConexion conectar = new Conexion.CConexion();
        Connection con = conectar.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            ps.setString(1, fecha);
            ps.setString(2, hora);

            // Ejecutar la consulta
            ResultSet rs = ps.executeQuery();

            // Verificar si hay alguna fila en el resultado
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // La fecha y hora están ocupadas, mostrar mensaje de error
                    JOptionPane.showMessageDialog(null, "La fecha y hora seleccionadas ya están ocupadas. Por favor, elige otra fecha o hora.");
                    return false;
                } else {
                    // La fecha y hora están disponibles, mostrar mensaje de disponibilidad
                    JOptionPane.showMessageDialog(null, "La fecha y hora seleccionadas están disponibles.");
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad: " + e.getMessage());
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }

        // En caso de error o excepción, asumimos que la fecha y hora no están disponibles
        return false;
    }
    
    public boolean insertarCita(String fecha, String hora, String descripcion, String id_doctor, String id_Paciente) {
        // Construir la consulta SQL para insertar la cita
        String sql = "INSERT INTO cita (fecha_cita, hora, motivo_cita, id_doctor, id_paciente) VALUES (?, ?, ?, ?, ?)";

        Conexion.CConexion conectar = new Conexion.CConexion();
        Connection con = conectar.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setString(3, descripcion);
            ps.setString(4, id_doctor);
            ps.setString(5, id_Paciente);

            // Ejecutar la consulta
            int filasAfectadas = ps.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "La cita se ha creado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo crear la cita.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la cita: " + e.getMessage());
            System.out.println("Error al insertar la cita: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }

        // En caso de error o excepción, retornar false
        return false;
    }
    public void MostrarCita(JTable jtableListaPaciente){
        Conexion.CConexion conectar = new Conexion.CConexion();
        
        Connection con = conectar.getConexion();
        
        DefaultTableModel model = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(model);
        jtableListaPaciente.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        model.addColumn("Id Cita");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Motivo");
        model.addColumn("Id Doctor");
        model.addColumn("Id Paciente");


        
        jtableListaPaciente.setModel(model);
        
        sql = "Select * from cita";
        
        String [] datos = new String[6];
        
        Statement st;

        try{
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                
                model.addRow(datos);

            }
                // Asignamos el modelo a la JTable que recibimos como parámetro
            jtableListaPaciente.setModel(model);

            //JOptionPane.showMessageDialog(null, "Se dio de alta correctamente el doctor");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros: "+ e);
        }
    }
//    public void seleccionarCita(JTable jtableListaCitas, JTextField idCita, JDateChooser dateChooser, JComboBox comboBoxHora, JTextField fieldDescripcion , JComboBox comboBoxIdDoctor, JComboBox comboBoxIdPaciente) {
////            public void seleccionarCita(JTable jtableListaCitas, JTextField idCita, JTextField fieldDescripcion, JComboBox comboBoxIdDoctor, JDateChooser dateChooser, JComboBox comboBoxHora, JTextField fieldDescripcionOutput) {
//
//        try {
//            int fila = jtableListaCitas.getSelectedRow();
//
//            if (fila >= 0) {
//                idCita.setText(jtableListaCitas.getValueAt(fila, 0).toString());
//                // Obtener la fecha de la tabla y establecerla en el JDateChooser
//                Date fecha = (Date) jtableListaCitas.getValueAt(fila, 1);
//                dateChooser.setDate(fecha);
//                // Establecer la hora en el JComboBox de hora
//                comboBoxHora.setSelectedItem(jtableListaCitas.getValueAt(fila, 2));
//                // Establecer la descripción en el JTextField
//                fieldDescripcion.setText(jtableListaCitas.getValueAt(fila, 3).toString());
//                // Establecer otro campo de descripción si es necesario
//                comboBoxIdDoctor.setSelectedItem(jtableListaCitas.getValueAt(fila, 4));
//                // Seleccionar un ítem en el JComboBox de ID si es necesario
//                comboBoxIdPaciente.setSelectedItem(jtableListaCitas.getValueAt(fila, 5));
//                // Obtener la fecha de la tabla y establecerla en el JDateChooser
//            } else {
//                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e);
//            System.out.println("Error de seleccion, error: " + e);
//
//        }
//    }
    public void seleccionarCita(JTable jtableListaCitas, JTextField idCita, JDateChooser dateChooser, JComboBox comboBoxHora, JTextField fieldDescripcion, JComboBox comboBoxIdDoctor, JComboBox comboBoxIdPaciente) {
    try {
        int fila = jtableListaCitas.getSelectedRow();

        if (fila >= 0) {
            idCita.setText(jtableListaCitas.getValueAt(fila, 0).toString());
            
            // Obtener la fecha de la tabla como un String y convertirla a Date
            String fechaString = (String) jtableListaCitas.getValueAt(fila, 1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(fechaString);
            dateChooser.setDate(fecha);
            
            // Establecer la hora en el JComboBox de hora
            comboBoxHora.setSelectedItem(jtableListaCitas.getValueAt(fila, 2));
            
            // Establecer la descripción en el JTextField
            fieldDescripcion.setText(jtableListaCitas.getValueAt(fila, 3).toString());
            
            // Establecer el ID del doctor en el JComboBox
            comboBoxIdDoctor.setSelectedItem(jtableListaCitas.getValueAt(fila, 4));
            
            // Establecer el ID del paciente en el JComboBox
            comboBoxIdPaciente.setSelectedItem(jtableListaCitas.getValueAt(fila, 5));
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e);
        System.out.println("Error de seleccion, error: " + e);
    }
}   
}
