package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.*;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorColaboradores {
    public GestorColaboradores() {
        colaboradores = new ArrayList<>();
        cargarColaboradores();
        cargarDesdeArchivo();
    }

    private void cargarColaboradores() {
        colaboradores.add(new ColaboradorExterno("Carmen Saenz", "Chofer", new Rut("33.333.333-k"), new Direccion("Moneda", "Santiago")));
    }
    private static final String ARCHIVO = "colaboradores.txt";
    private List<ColaboradorExterno> colaboradores;

    private void cargarDesdeArchivo() {
        for (String linea : Archivo.leerLineas(ARCHIVO)) {
            ColaboradorExterno c = parsearLinea(linea);
            if (c != null) colaboradores.add(c);
        }
    }

    private ColaboradorExterno parsearLinea(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length != 4) return null;

            String nombre = partes[0].substring(partes[0].indexOf(":") + 1).trim();
            String rol = partes[1].substring(partes[1].indexOf(":") + 1).trim();
            String rutStr = partes[2].substring(partes[2].indexOf(":") + 1).trim();
            String dirStr = partes[3].substring(partes[3].indexOf(":") + 1).trim();

            Rut rut = new Rut(rutStr);
            String[] dir = dirStr.split(",");
            Direccion direccion = new Direccion(dir[0].trim(), dir[1].trim());

            return new ColaboradorExterno(nombre, rol, rut, direccion);
        } catch (Exception e) {
            System.out.println("No se pudo interpretar la línea: " + linea);
            return null;
        }
    }

    public String formatearLinea(ColaboradorExterno c) {
        return "Nombre: " + c.getNombre() + " | Rol: " + c.getRol()
                + " | Rut: " + c.getRut().getNumRut()
                + " | Direccion: " + c.getCalle() + "," + c.getComuna();
    }

    public void agregarColaborador(ColaboradorExterno c) {
        colaboradores.add(c);
        Archivo.guardarLinea(ARCHIVO, formatearLinea(c));
    }

    public List<ColaboradorExterno> obtenerColaboradores() {
        return colaboradores;
    }

}