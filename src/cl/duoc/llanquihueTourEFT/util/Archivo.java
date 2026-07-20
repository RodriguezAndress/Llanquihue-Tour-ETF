package cl.duoc.llanquihueTourEFT.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archivo {
    private static final String CARPETA = "src/resources/";

    private Archivo() {
    }

    /** Agrega una línea al final del archivo .txt (modo append, no borra lo anterior). */
    public static void guardarLinea(String nombreArhivo, String linea) {
        File carpeta = new File(CARPETA);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // crea la carpeta si no existe
        }

        try (FileWriter fw = new FileWriter(CARPETA + nombreArhivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(linea);
        } catch (IOException e) {
            System.out.println("No se pudo guardar en el archivo: " + e.getMessage());
        }
    }

    public static List<String> leerLineas(String  nombreArhivo) {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(CARPETA + nombreArhivo);

        if (!archivo.exists()) {
            return lineas; // aún no se ha guardado nada, no es un error
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }

        return lineas;
    }

}
