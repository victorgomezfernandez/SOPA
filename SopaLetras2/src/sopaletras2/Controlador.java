package sopaletras2;

//Clase controlador del programa, realizara las operaciones relacionadas con la sopa y el modelo
public class Controlador {

    static String[] WL; //Palabras que se pasarán a la sopa para su creacion
    Sopa ss;
    Modelo sm;

    public Controlador(Sopa ss, Modelo sm) {
        this.ss = ss;
        this.sm = sm;
    }

    public String sopa;

    //Metodo para actualizar el array WL usando la funcion de metodo para obtener las palabras
    public void consultarPalabras() {
        WL = sm.palabrasSopa();
    }

    //Funcion para generar la sopa de letras con el array WL
    public void generarSopa() {
        //Llamada al metodo consultarPalabras para actualizar WL
        consultarPalabras();
        //Llamada al metodo de sopa inicializarMatrices para iniciar la sopa
        ss.inicializaMatrices();
        //Comprobar longitud de WL
        if (WL.length > 0) {
            //Llamada al metodo de sopa colocarTodas para colocar las palabras de WL
            ss.colocarTodas(WL);
        }
        //Asignar a la cadena sopa el valor de la sopa llamando a la funcion almacenarMatriz
        sopa = ss.almacenarMatrizHtml();
    }

    //Metodo para llamar al modelo y agregar la palabra a la base de datos
    public void agregarPalabra(String nueva) {
        consultarPalabras();
        //Llamada al metodo de modelo
        this.sm.agregarPalabra(nueva);
    }

    //Metodo para llamar al modelo y eliminar la palabra de la base de datos
    public void eliminarPalabra(String eliminacion) {
        consultarPalabras();
        //Llamada al metodo de modelo
        this.sm.eliminarPalabra(eliminacion);
    }

    public void solucionSopa() {
        sopa = this.ss.solucionSopa();
    }

}
