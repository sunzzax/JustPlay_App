/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloUsuario;

/**
 *
 * @author kira
 */
public class UsuarioDAO {

    // Método para registrar un Usuario
    public boolean registrarUsuario(ModeloUsuario usuario) {

        // Aqui declaro las consultas que quiero hacer
        String consultarExistenciaSQL = "SELECT * FROM usuarios WHERE usuario = ?";
        String insertarSQL = "INSERT INTO usuarios(usuario, contraseña) VALUES (? , ?)";

        try (Connection con = ConexionBD.conectar(); PreparedStatement verificarExistencia = con.prepareStatement(consultarExistenciaSQL)) {
            verificarExistencia.setString(1, usuario.getUsuario());
            ResultSet resultado = verificarExistencia.executeQuery();

            if (resultado.next()) {
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre, escoja otro por favor.", "Error", JOptionPane.WARNING_MESSAGE);
                return false; // Detiene el método y sale

            }
            
            // Si no existe lo insertamos en la base de datos
            PreparedStatement insertarUsuario = con.prepareStatement(insertarSQL);
            insertarUsuario.setString(1, usuario.getUsuario());
            insertarUsuario.setString(2, usuario.getContraseña());

            insertarUsuario.executeUpdate();
            return true; // Esto me sirve para hacer comprobaciones fuera del DAO

        } catch (SQLException ex) {
            return false;
        }

    }

    // Método para iniciar sesión con un Usuario
    public ModeloUsuario validarUsuario(String usuario, String contraseña) {

        String consultaSQL = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";

        try (Connection con = ConexionBD.conectar()) {
            PreparedStatement pst = con.prepareStatement(consultaSQL);
            pst.setString(1, usuario);
            pst.setString(2, contraseña);

            ResultSet resultado = pst.executeQuery();

            if (resultado.next()) {
                return new ModeloUsuario(resultado.getString("usuario"), resultado.getString("contraseña"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
