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
public class ControladorRegistro {

    public ControladorRegistro(JFrameVentanaSesion vistaVentanaSesion) {
        vistaVentanaSesion.getjButtonRegistrarseRe().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                String usuario = vistaVentanaSesion.getjTextFieldUsuarioRe().getText().trim();
                String contraseña = new String(vistaVentanaSesion.getjPasswordFielUsuarioRe().getPassword()).trim();

                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "Por favor, rellene todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                ModeloUsuario nuevoUsuario = new ModeloUsuario(usuario, contraseña);
                boolean estaRegistrado = usuarioDAO.registrarUsuario(nuevoUsuario);

                if (estaRegistrado) {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "Acceso Concedido. Bienvenido " + nuevoUsuario.getUsuario());
                    vistaVentanaSesion.dispose();
                    JFrameVentanaUsuario ventanaVistaUsuario = new JFrameVentanaUsuario();
                    new ControladorVentanaUsuario(ventanaVistaUsuario);
                    ventanaVistaUsuario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vistaVentanaSesion, "No se pudo registrar el usuario. Intente con otro nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }
}
