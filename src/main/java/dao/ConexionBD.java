package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    // Método que intenta conectarse a la base de datos
    // Si esta se conecta muestra un mensaje indicando que se conecto correctamente
    // y si falla muestra otro mensaje distinto indicando que hubo un error
    public static Connection conectar() {
        try {
            // Ruta al archivo de la base de datos
            String url = "jdbc:sqlite:database/JustPlay.db";
            
            // Aqui intenta establecer la conexion
            Connection con = DriverManager.getConnection(url);

            // Si se conecta devolvemos el objeto 'Connection' para poder usarlo
            // en el resto del programa
            return con;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Conexión fallida a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            // Si no se pudo realizar la conexión devolvemos 'null' indicando que no se
            // ha podido realizar la conexión
            return null;
        }

    }

    public static void main(String[] args) {
        conectar();
    }
}
