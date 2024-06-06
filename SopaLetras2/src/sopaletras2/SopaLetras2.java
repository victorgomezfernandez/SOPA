package sopaletras2;

import java.sql.SQLException;
//Clase principal del programa
public class SopaLetras2 {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Instanciacion de una nueva sopa
        Sopa ss = new Sopa();
        //Instanciacion de un nuevo modelo
        Modelo sm = new Modelo();
        //Instanciacion de un nuevo controlador con la sopa y modelo anteriores como parametros
        Controlador sc = new Controlador(ss,sm);
        //Instanciacion de una nueva vista con el controlador anterior
        Vista sv = new Vista(sc);
        sv.setVisible(true);
        //Ejecucion del metodo inicializarBd() del modelo para actualizar la lista de palabras
        sm.inicializarBd();
        //Ejecucion del metodo updateWordModel() de la vista para actualizar la JList de la vista
        sv.updateWordModel();
    }

}
