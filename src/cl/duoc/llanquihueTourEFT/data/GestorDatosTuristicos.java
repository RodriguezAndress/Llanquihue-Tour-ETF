package cl.duoc.llanquihueTourEFT.data;
import cl.duoc.llanquihueTourEFT.model.*;
import cl.duoc.llanquihueTourEFT.model.Tour;
import cl.duoc.llanquihueTourEFT.model.Reserva;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorDatosTuristicos {
    private static final String ARCHIVO_TOURS = "tours.txt";
    private List<ServicioTuristico> servicios;

    public GestorDatosTuristicos(){
        servicios = new ArrayList<>();
        cargarServicios();
        cargarToursDesdeArchivo();
    }

    private void cargarServicios(){
        //Agg obj del padre
        servicios.add(new ServicioTuristico("Recorrido Centro Historico", 1.45));

        //Agg obj de hijos
        servicios.add(new RutaGastronomica("Recorrido Paseo Montalban", 2.45, 5, "2026-09-05", 6));
        servicios.add(new RutaGastronomica("Camino Historico Precolonial", 2.30, 6, "2026-08-25", 7));

        //agg obj hijo paseoLacustre
        servicios.add(new PaseoLacustre("Lago Negro", 1.45, "Canoa", "2026-08-28", 8));
        servicios.add(new PaseoLacustre("Laguna Piedra Azul", 2.15, "Canoa", "2026-09-15", 8));

        //Agg obj hijo ExcursionCultural
        servicios.add(new ExcursionCultural("Cerro San Cristobal", 3.15, "Santiago", "2026-09-24", 9));
        servicios.add(new ExcursionCultural("Teatro Municipal de Santiago", 1.45, "Teatro", "2026-09-13", 9));

        //Agg a los tour que se pueden reservar
        servicios.add(new Reserva("Rapel Cascada El Silencio", 2.5, "2026-07-25", 8));
        servicios.add(new Reserva("Vuelo en Parapente", 1.30, "2026-08-02", 5));

    }
    private void cargarToursDesdeArchivo(){
        for(String linea : Archivo.leerLineas(ARCHIVO_TOURS)){
            Reserva tour = parsearLineaTour(linea);
            if(tour != null)servicios.add(tour);
        }
    }
    private Reserva parsearLineaTour(String linea){
        try{
            String[] partes = linea.split("\\|");
            if(partes.length != 4)return null;

            String nombre = partes[0].substring(partes[0].indexOf(":")+1).trim();
            double duracion = Double.parseDouble(partes[1].substring(partes[1].indexOf(":")+1).trim());
            String fecha = partes[2].substring(partes[2].indexOf(":")+1).trim();
            int cupos =  Integer.parseInt(partes[3].substring(partes[3].indexOf(":")+1).trim());

            return new Reserva(nombre, duracion, fecha, cupos);
        } catch (Exception e){
            System.out.println("No se pudo interpretar la linea "+linea);
            return null;
        }
    }
    public String formatearLineaTour(Reserva tour){
        return "Nombre: "+tour.getNombre()+" | Duración: "+tour.getDuracionHoras()+
                " | Fecha:"+tour.getFecha()+" | Cupos: "+tour.getCuposDisponibles();
    }
    public void agregarTour(Reserva tour){
        servicios.add(tour);
        Archivo.guardarLinea(ARCHIVO_TOURS, formatearLineaTour(tour));
    }
    public List<ServicioTuristico> obtenerServicios() {
        return servicios;
    }

    public List<Tour> obtenerTours() {
        List<Tour> tours = new ArrayList<>();
        for (ServicioTuristico servicio : servicios) {
            if (servicio instanceof Tour tour) {
                tours.add(tour);
            }
        }
        return tours;
    }

}

