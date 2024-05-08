
package com.mycompany.evidenciadoctores;

import Vista.Menu;
import Vista.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Fer
 */
public class main {
    public static void main ( String[] args){
        Login objectLogin = new Login();
        objectLogin.setVisible(true);
    }
}
