package main;

import controlador.ControladorVentanaSesion;
import vista.JFrameVentanaSesion;

public class PlayMain {

    public static void main(String[] args) {

        // Hago visible la ventana principal, que es la cual contiene el inicio de sesión
        JFrameVentanaSesion vistaVentanaSesion = new JFrameVentanaSesion();
        ControladorVentanaSesion controladorVentanaSesion = new ControladorVentanaSesion(vistaVentanaSesion);
        vistaVentanaSesion.setVisible(true);
    }

}
