package sopaletras2;

public class Sopa {
    static String[][] M = new String[20][20];
    static int[][] H = new int[20][20];

    public int azar(int limite) {
        return (int) Math.floor(Math.random() * limite);
    }
//-------------------------------------------------------------------------
    
    public String solucionSopa(){
        String matriz="<p style=\"font-family: monospace;\">";
        for (int f = 0; f < M.length; f++) {
            matriz+=comprobarFila(f);
        }
        matriz+="</p>";
        return matriz;
    }
    
    public String comprobarFila(int f){
        String fila = "";
        int columnas = M[0].length;
        for (int c = 0; c < columnas; c++) {
            if (H[f][c]==1) {
                fila += "<b>"+M[f][c]+"</b>"+"\t";
            } else {
                fila += M[f][c]+"\t";
            }
        }
        fila += "<br/>";
        return fila;
    }

    public void mostrarMatriz(String[][] M) {
        for (int f = 0; f < M.length; f++) { //M.length = rows
            mostrarFila(M, f);
        }
    }
//-------------------------------------------------------------------------

    public void mostrarFila(String[][] M, int f) {
        int columnas = M[0].length;// Vemos la longitud de la primera fila
        for (int c = 0; c < columnas; c++) {
            System.out.print(M[f][c] + " ");
        }
        System.out.println();
    }
    
    public String almacenarMatrizHtml(){
        String matriz = "<p style=\"font-family: monospace;\">";
        for (int f = 0; f < M.length; f++) {
            matriz+=almacenarFilaHtml(f);
        }
        matriz+="</p>";
        return matriz;
    }
    
    public String almacenarFilaHtml(int f){
        String fila = "";
        int columnas = M[0].length;
        for (int c = 0; c < columnas; c++) {
            fila += M[f][c]+"\t";
        }
        fila += "<br/>";
        return fila;
    }
    
    public String almacenarMatriz(){
        String matriz = "";
        for (int f = 0; f < M.length; f++) {
            matriz+=almacenarFila(f);
        }
        return matriz;
    }
    
    public String almacenarFila(int f){
        String fila = "";
        int columnas = M[0].length;
        for (int c = 0; c < columnas; c++) {
            fila += M[f][c] + " ";
        }
        fila += "\n";
        return fila;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word de izquierda a derecha

    public boolean colocarPalabra0(String[] word) {
//Seleccionar una fila al azar de 0 a número de M.length-1
        int f = azar(M.length);
//Seleccionar una columna al azar de 0 a número de columnas menos la longitud de word
        int L = word.length;
        int c = azar(M[0].length - L + 1);
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
//Si el hueco está ocupado por una letra distinta de la que quiero colocar
            if ((H[f][c + t] == 1) && (M[f][c + t] != word[t])) {
                PERMITIDO = false;
            }
        }
        if (PERMITIDO) {
//Colocamos la palabra en la matriz M
            for (int t = 0; t < L; t++) {
                M[f][c + t] = word[t];
                H[f][c + t] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word top-down

    public boolean colocarPalabra2(String[] word) {
//Seleccionar una fila al azar de 0 a número de filas menos la longitud de word
        int L = word.length;
        int f = azar(M.length - L + 1);
//Seleccionar una columna al azar de 0 a número de M[0].length-1 que es el numero de columnas -1
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
            if ((H[f + t][c] == 1) && (M[f + t][c] != word[t])) {
                PERMITIDO = false;
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c] = word[t];
                H[f + t][c] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word de derecha a izquierda

    public boolean colocarPalabra4(String[] word) {
        int f = azar(M.length);
        int L = word.length;
        int c = azar(M[0].length - L + 1);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
            if ((H[f][c + t] == 1) && (M[f][c + t] != word[L - t - 1])) {
                PERMITIDO = false;
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f][c + t] = word[L - t - 1]; //coloca la palabra al revés
                H[f][c + t] = 1;
            }
        }
        return PERMITIDO;
    }
//--------------------------------------------------------------------------
//Coloca la palabra almacenada en word down-top

    public boolean colocarPalabra6(String[] word) {
        int L = word.length;
        int f = azar(M.length - L + 1);
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
            if ((H[f + t][c] == 1) && (!M[f + t][c].equals(word[L - t - 1]))) {
                PERMITIDO = false;
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c] = word[L - t - 1];
                H[f + t][c] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word SE

    public boolean colocarPalabra1(String[] word) {
        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
//si me salgo de los límites del array
            if ((f + t >= M.length) || (c + t >= M[0].length)) {
                PERMITIDO = false;
            } else { //si el hueco está ocupado por una letra distinta de la quiero colocar
                if ((H[f + t][c + t] == 1) && (!M[f + t][c + t].equals(word[t]))) {
                    PERMITIDO = false;
                }
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c + t] = word[t];
                H[f + t][c + t] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word SE

    public boolean colocarPalabra5(String[] word) {
        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
//si me salgo de los límites del array
            if ((f + t >= M.length) || (c + t >= M[0].length)) {
                PERMITIDO = false;
            } else { //si el hueco está ocupado por una letra distinta de la quiero colocar
                if ((H[f + t][c + t] == 1) && (!M[f + t][c + t].equals(word[L - t - 1]))) {
                    PERMITIDO = false;
                }
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c + t] = word[L - t - 1];
                H[f + t][c + t] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word SE

    public boolean colocarPalabra3(String[] word) {
        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
//si me salgo de los límites del array
            if ((f + t >= M.length) || (c - t < 0)) {
                PERMITIDO = false;
            } else { //si el hueco está ocupado por una letra distinta de la quiero colocar
                if ((H[f + t][c - t] == 1) && (!M[f + t][c - t].equals(word[t]))) {
                    PERMITIDO = false;
                }
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c - t] = word[t];
                H[f + t][c - t] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------
//Coloca la palabra almacenada en word SE

    public boolean colocarPalabra7(String[] word) {
        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);
//Colocamos la palabra en la matriz M
        boolean PERMITIDO = true;
        for (int t = 0; t < L; t++) {
//si me salgo de los límites del array
            if ((f + t >= M.length) || (c - t < 0)) {
                PERMITIDO = false;
            } else { //si el hueco está ocupado por una letra distinta de la quiero colocar
                if ((H[f + t][c - t] == 1) && (!M[f + t][c - t].equals(word[L - t - 1]))) {
                    PERMITIDO = false;
                }
            }
        }
        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c - t] = word[L - t - 1];
                H[f + t][c - t] = 1;
            }
        }
        return PERMITIDO;
    }
//-------------------------------------------------------------------------

    public void colocarTodas(String [] palabra) {
        int orientacion;
        String[] word;
        int longPalabra = palabra.length;
        int cuenta = 0;
        boolean COLOCADO = false;
        do {
            word = palabra[cuenta].split("");
            orientacion = azar(8);
            COLOCADO = false;
            switch (orientacion) {
                case 0:
                    COLOCADO = colocarPalabra0(word);
                    break;
                case 1:
                    COLOCADO = colocarPalabra1(word);
                    break;
                case 2:
                    COLOCADO = colocarPalabra2(word);
                    break;
                case 3:
                    COLOCADO = colocarPalabra3(word);
                    break;
                case 4:
                    COLOCADO = colocarPalabra4(word);
                    break;
                case 5:
                    COLOCADO = colocarPalabra5(word);
                    break;
                case 6:
                    COLOCADO = colocarPalabra6(word);
                    break;
                case 7:
                    COLOCADO = colocarPalabra7(word);
                    break;
            }
            if (COLOCADO) {
                cuenta++;
            }
        } while (cuenta < longPalabra);
    }
//--------------------------------------------------------------------------

    public void inicializaMatrices() {
        String cadena = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String[] letra;
        letra = cadena.split("");
        int f, c;
        for (f = 0; f < M.length; f++) { //M.length = rows
//Rellena de letras al azar la fila f
            for (c = 0; c < M[0].length; c++) {//M[0].length = cols
                M[f][c] = letra[azar(27)];
                H[f][c] = 0;
            }
        }
    }
//--------------------------------------------------------------------------
    
}
