/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import vista.JFrameVentanaUsuario;

/**
 *
 * @author kira
 */
public class ControladorVentanaUsuario {

    private final JFrameVentanaUsuario vistaVentanaUsuario;
    private Point puntoInicial;

    public ControladorVentanaUsuario(JFrameVentanaUsuario vistaVentanaUsuario) {
        this.vistaVentanaUsuario = vistaVentanaUsuario;
        iniciarAcciones();
    }

    public void iniciarAcciones() {

        // Acción para obtener la posicion actual del puntero
        vistaVentanaUsuario.getjPanelFondo().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial = e.getPoint(); // Devuelve un objeto de tipo 'Point' con las coordenadas X e Y
            }
        });

        // Acción para simular que se esta arrastrando
        vistaVentanaUsuario.getjPanelFondo().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { // Se usa muentras mantienes
                Point posicionPantalla = e.getLocationOnScreen(); // Devuelve un objeto de tipo 'Point' con
                // las coordenadas X e Y de toda la pantalla
                // restamos las coordenadas para evitar que 'salte' cuando arrastras desde cualquierparte del panel.
                vistaVentanaUsuario.setLocation(posicionPantalla.x - puntoInicial.x, posicionPantalla.y - puntoInicial.y);
            }
        });

        // Acciones con el botón 'cerrar'
        vistaVentanaUsuario.getjLabelCerrar().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("imagenes/cerrarMarcado.png"));
                vistaVentanaUsuario.getjLabelCerrar().setIcon(imageIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("imagenes/cerrar.png"));
                vistaVentanaUsuario.getjLabelCerrar().setIcon(imageIcon);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

        });

        // Acciones con el botón 'minimizar'
        vistaVentanaUsuario.getjLabelMinimizar().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("imagenes/minimizarMarcado.png"));
                vistaVentanaUsuario.getjLabelMinimizar().setIcon(imageIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("imagenes/minimizar.png"));
                vistaVentanaUsuario.getjLabelMinimizar().setIcon(imageIcon);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                vistaVentanaUsuario.setState(JFrameVentanaUsuario.ICONIFIED);
            }

        });

    }

}
