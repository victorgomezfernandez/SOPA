package sopaletras2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Modelo {

    //Lista enlazada para almacenar las palabras de la base de datos
    public LinkedList<String> listaPalabras = new LinkedList<>();
    public Connection con;
    //URL de la base de datos usando mariaDB
    public String sURL = "jdbc:mariadb://localhost:3306/wordsdb";

    public Modelo() throws ClassNotFoundException, SQLException {
        inicializarBd();
    }

    //Metodo para conectarse a la base de datos y actualizar la lista enlazada con los datos de la base de datos
    public void inicializarBd() throws ClassNotFoundException, SQLException {
        //Eliminar los registros (si los habia) de la lista enlazada
        listaPalabras.clear();
        try {
            //Conexion con la base de datos
            con = DriverManager.getConnection(sURL, "root", "");
            //Comando para la base de datos
            Statement s = con.createStatement();
            ResultSet rs;
            //Seleccionara el campo "word" de la tabla "words"
            String comando = "SELECT `word` FROM `words`;";
            rs = s.executeQuery(comando);
            while (rs.next()) {
                //Guardado de las palabras de la base de datos en la lista enlazada
                this.listaPalabras.add(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Base de datos no conectada");
        }

    }

    //Metodo para agregar una palabra a la base de datos
    public void agregarPalabra(String palabra) {
        //Transformacion de la palabra recogida a mayusculas para su guardado en la lista enlazada
        this.listaPalabras.add(palabra.toUpperCase());
        try {
            //Conexion con la base de datos
            con = DriverManager.getConnection(sURL, "root", "");
            Statement s = con.createStatement();
            //Comando para la insercion de la palabra
            String comando = "INSERT INTO `words` (word) VALUES ('" + palabra.toUpperCase() + "');";
            //Ejecucion del comando
            s.executeUpdate(comando);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Base de datos no conectada");
        }
    }

    //Metodo para eliminar una palabra de la base de datos
    public void eliminarPalabra(String palabra) {
        //Eliminacion de la palabra recogida de la lista enlazada
        this.listaPalabras.remove(palabra.toUpperCase());
        try {
            //Conexion con la base de datos
            con = DriverManager.getConnection(sURL, "root", "");
            Statement s = con.createStatement();
            //Comando para el borrado de la palabra
            String comando = "DELETE FROM `words` WHERE `word` = '" + palabra.toUpperCase() + "';";
            //Ejecucion del comando
            s.executeUpdate(comando);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Base de datos no conectada");
        }
    }

    //Funcion para devolver un array de String con las palabras de la lista enlazada
    public String[] palabrasSopa() {
        String[] palabras = new String[listaPalabras.size()];
        for (int i = 0; i < listaPalabras.size(); i++) {
            palabras[i] = listaPalabras.get(i);
        }
        return palabras;
    }

}
