package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.Vehiculo;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorVehiculos {
    public GestorVehiculos() {
        vehiculos = new ArrayList<>();
        cargarVehiculos();
        cargarDesdeArchivo();
    }

    private void cargarVehiculos() {
        vehiculos.add(new Vehiculo("SK-3541", "Furgoneta"));
    }

    private static final String ARCHIVO = "vehiculos.txt";
    private List<Vehiculo> vehiculos;


    private void cargarDesdeArchivo() {
        for (String linea : Archivo.leerLineas(ARCHIVO)) {
            Vehiculo v = parsearLinea(linea);
            if (v != null) vehiculos.add(v);
        }
    }

    private Vehiculo parsearLinea(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length != 2) return null;

            String patente = partes[0].substring(partes[0].indexOf(":") + 1).trim();
            String tipo = partes[1].substring(partes[1].indexOf(":") + 1).trim();

            return new Vehiculo(patente, tipo);
        } catch (Exception e) {
            System.out.println("No se pudo interpretar la línea: " + linea);
            return null;
        }
    }

    public String formatearLinea(Vehiculo v) {
        return "Patente: " + v.getPatente() + " | Tipo: " + v.getTipo();
    }

    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
        Archivo.guardarLinea(ARCHIVO, formatearLinea(v));
    }

    public List<Vehiculo> obtenerVehiculos() {
        return vehiculos;
    }
}