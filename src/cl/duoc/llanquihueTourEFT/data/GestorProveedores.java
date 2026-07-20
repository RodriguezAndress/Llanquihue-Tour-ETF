package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.*;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorProveedores {
    public GestorProveedores() {
        proveedores = new ArrayList<>();
        cargarProveedores();
        cargarDesdeArchivo();
    }

    private void cargarProveedores() {
        proveedores.add(new Proveedor("Carnes Fresh", "Carnes", true, new Rut("23.223.223-k"), new Direccion("Morande", "Llanquihue")));
    }

    private static final String ARCHIVO = "proveedores.txt";
    private List<Proveedor> proveedores;

    private void cargarDesdeArchivo() {
        for (String linea : Archivo.leerLineas(ARCHIVO)) {
            Proveedor p = parsearLinea(linea);
            if (p != null) proveedores.add(p);
        }
    }

    private Proveedor parsearLinea(String linea) {
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

            return new Proveedor(nombre, rol, true, rut, direccion);
        } catch (Exception e) {
            System.out.println("No se pudo interpretar la línea: " + linea);
            return null;
        }
    }

    public String formatearLinea(Proveedor p) {
        return "Nombre: " + p.getNombre() + " | Provee: " + p.getRol()
                + " | Rut: " + p.getRut().getNumRut()
                + " | Direccion: " + p.getCalle() + "," + p.getComuna();
    }

    public void agregarProveedor(Proveedor p) {
        proveedores.add(p);
        Archivo.guardarLinea(ARCHIVO, formatearLinea(p));
    }

    public List<Proveedor> obtenerProveedores() {
        return proveedores;
    }
}