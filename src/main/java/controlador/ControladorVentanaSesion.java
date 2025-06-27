/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import vista.JFrameVentanaSesion;

/**
 *
 * @author kira
 */
public class ControladorVentanaSesion {

    private final JFrameVentanaSesion vistaVentanaSesion;
    private Point puntoInicial; // Se usa para representar posiciones en 2 dimensiones

    public ControladorVentanaSesion(JFrameVentanaSesion vistaVentanaSesion) {
        this.vistaVentanaSesion = vistaVentanaSesion;
        inicarAcciones();
        iniciarControladoresSecundarios();
    }

    // Colores para los botones, textos y demás
    private static final Color COLOR_MORADO = Color.decode("#8A2FEB");
    private static final Color COLOR_MORADO_CLARO = Color.decode("#8E4FF1");
    private static final Color COLOR_MORADO_OSCURO = Color.decode("#7E36F0");
    private static final Color COLOR_GRIS = Color.decode("#8D8E92");
    private static final Color COLOR_TRANSPARENTE = new Color(0, 0, 0, 0);

    // Variable que uso para manejar cual es la pestaActiva y asi evitar que la linea inferior desaparezca
    private int pestañaActiva = 0; // 0 es iniciar sesion y 1 Registrarse

    private void iniciarControladoresSecundarios() {
        new ControladorInicioSesion(vistaVentanaSesion);
        new ControladorRegistro(vistaVentanaSesion);
    }

    private void inicarAcciones() {

        // Lo dejo marcado porqu epor defecto quiero que sea iniciar sesion
        vistaVentanaSesion.getjLabelTextoIniciarSesión().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_MORADO));

        // Acción para obtener la posicion actual del puntero
        vistaVentanaSesion.getjPanelFondo().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial = e.getPoint(); // Devuelve un objeto de tipo 'Point' con las coordenadas X e Y
            }
        });

        // Acción para simular que se esta arrastrando
        vistaVentanaSesion.getjPanelFondo().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { // Se usa muentras mantienes
                Point posicionPantalla = e.getLocationOnScreen(); // Devuelve un objeto de tipo 'Point' con
                // las coordenadas X e Y de toda la pantalla
                // restamos las coordenadas para evitar que 'salte' cuando arrastras desde cualquierparte del panel.
                vistaVentanaSesion.setLocation(posicionPantalla.x - puntoInicial.x, posicionPantalla.y - puntoInicial.y);
            }
        });

        // Acciones de jLabel al pasar por encima o hacer clic en el texto 'INICIAR SESIÓN'
        vistaVentanaSesion.getjLabelTextoIniciarSesión().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                vistaVentanaSesion.getjLabelTextoIniciarSesión().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_MORADO));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (pestañaActiva != 0) {
                    vistaVentanaSesion.getjLabelTextoIniciarSesión().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_TRANSPARENTE));
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pestañaActiva = 0;
                vistaVentanaSesion.getjTabbedPaneIniciaroRegistrar().setSelectedIndex(0);
                vistaVentanaSesion.getjLabelTextoIniciarSesión().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_MORADO));
                vistaVentanaSesion.getjLabelTextoRegistrarse().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_TRANSPARENTE));
            }

        });

        // Acciones de jLabel al pasar por encima o hacer clic en el texto 'REGISTRARSE'
        vistaVentanaSesion.getjLabelTextoRegistrarse().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                vistaVentanaSesion.getjLabelTextoRegistrarse().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_MORADO));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (pestañaActiva != 1) {
                    vistaVentanaSesion.getjLabelTextoRegistrarse().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_TRANSPARENTE));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                pestañaActiva = 1;
                vistaVentanaSesion.getjTabbedPaneIniciaroRegistrar().setSelectedIndex(1);
                vistaVentanaSesion.getjLabelTextoRegistrarse().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_MORADO));
                vistaVentanaSesion.getjLabelTextoIniciarSesión().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, COLOR_TRANSPARENTE));
            }

        });

        // Acciones de jLabel al pasar por encima o hacer clic en el texto 'Salir del programa'
        vistaVentanaSesion.getjLabelSalirPrograma().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                vistaVentanaSesion.getjLabelSalirPrograma().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_GRIS));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                vistaVentanaSesion.getjLabelSalirPrograma().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_TRANSPARENTE));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

        });

        // Acciones de jButton al pasar por encima o hacer clic en el botón 'ACCEDER'
        vistaVentanaSesion.getjButtonAccederIn().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                vistaVentanaSesion.getjButtonAccederIn().setBackground(COLOR_MORADO_CLARO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                vistaVentanaSesion.getjButtonAccederIn().setBackground(COLOR_MORADO_OSCURO);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                vistaVentanaSesion.getjButtonAccederIn().setBackground(COLOR_MORADO_OSCURO);
            }

        });

        // Acciones de jButton al pasar por encima o hacer clic en el botón 'REGISTRARSE'
        vistaVentanaSesion.getjButtonRegistrarseRe().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                vistaVentanaSesion.getjButtonRegistrarseRe().setBackground(COLOR_MORADO_CLARO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                vistaVentanaSesion.getjButtonRegistrarseRe().setBackground(COLOR_MORADO_OSCURO);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                vistaVentanaSesion.getjButtonRegistrarseRe().setBackground(COLOR_MORADO_OSCURO);
            }

        });

    }

}
