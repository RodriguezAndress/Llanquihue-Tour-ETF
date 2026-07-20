package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.*;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorEmpleados {
    public GestorEmpleados() {
        empleados = new ArrayList<>();
        cargarEmpleados();
        cargarDesdeArchivo();
    }

    private void cargarEmpleados() {
        empleados.add(new Empleado("Henry Paez", "Recepcionista", new Rut("44.444.444-k"), new Direccion("San Ignacio", "Llanquihue")));
        empleados.add(new Empleado("Juan Landaeta", "Guía Turístico - Kayak", new Rut("22.222.222-6"), new Direccion("Alamos", "Llanquihue")));
    }

    private static final String ARCHIVO = "empleados.txt";
    private List<Empleado> empleados;

    private void cargarDesdeArchivo() {
        for (String linea : Archivo.leerLineas(ARCHIVO)) {
            Empleado e = parsearLinea(linea);
            if (e != null) empleados.add(e);
        }
    }

    private Empleado parsearLinea(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length != 4) return null;

            String nombre = partes[0].substring(partes[0].indexOf(":") + 1).trim();
            String especialidad = partes[1].substring(partes[1].indexOf(":") + 1).trim();
            String rutStr = partes[2].substring(partes[2].indexOf(":") + 1).trim();
            String dirStr = partes[3].substring(partes[3].indexOf(":") + 1).trim();

            Rut rut = new Rut(rutStr);
            String[] dir = dirStr.split(",");
            Direccion direccion = new Direccion(dir[0].trim(), dir[1].trim());

            return new Empleado(nombre, especialidad, rut, direccion);
        } catch (Exception ex) {
            System.out.println("No se pudo interpretar la línea: " + linea);
            return null;
        }
    }

    public String formatearLinea(Empleado e) {
        return "Nombre: " + e.getNombre() + " | Especialidad: " + e.getEspecialidad()
                + " | Rut: " + e.getRut().getNumRut()
                + " | Direccion: " + e.getCalle() + "," + e.getComuna();
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
        Archivo.guardarLinea(ARCHIVO, formatearLinea(e));
    }

    public List<Empleado> obtenerEmpleados() {
        return empleados;
    }
}