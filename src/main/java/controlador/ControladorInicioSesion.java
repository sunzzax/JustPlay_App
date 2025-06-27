/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.UsuarioDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import modelo.ModeloUsuario;
import vista.JFrameVentanaSesion;
import vista.JFrameVentanaUsuario;

/**
 *
 * @author kira
 */
public class ControladorInicioSesion {

    public ControladorInicioSesion(JFrameVentanaSesion vistaVentanaSesion) {
        vistaVentanaSesion.getjButtonAccederIn().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                String usuario = vistaVentanaSesion.getjTextFieldUsuarioIn().getText().trim();
                String contraseña = new String(vistaVentanaSesion.getjPasswordFieldContraseñaUsuarioIn().getPassword()).trim();

                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "Por favor, rellene todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                ModeloUsuario usu = usuarioDAO.validarUsuario(usuario, contraseña);

                if (usu != null) {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "Acceso Concedido. Bienvenido " + usu.getUsuario());
                    vistaVentanaSesion.dispose();
                    JFrameVentanaUsuario ventanaVistaUsuario = new JFrameVentanaUsuario();
                    new ControladorVentanaUsuario(ventanaVistaUsuario);
                    ventanaVistaUsuario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "Usuario o Contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

}
