package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.Tour;
import cl.duoc.llanquihueTourEFT.data.GestorDatosTuristicos;

import java.util.List;

public class GestorReservas {
    private GestorDatosTuristicos gestorDatos;

    public GestorReservas(GestorDatosTuristicos gestorDatos) {
        this.gestorDatos = gestorDatos;
    }

    public List<Tour> listarTours() {
        return gestorDatos.obtenerTours();
    }

    // Busca el tour por nombre y reserva
    public String reservar(String nombreTour) {
        Tour tour = buscarTour(nombreTour);
        if (tour == null) return "Tour no encontrado.";

        if (tour.tieneCupos()) {
            tour.reservarTour();
            return "Reserva exitosa para: " + tour.getNombre() + " | Cupos restantes: " + tour.getCuposDisponibles();
        } else {
            return "No hay cupos disponibles para: " + tour.getNombre();
        }
    }

    public String cancelar(String nombreTour) {
        Tour tour = buscarTour(nombreTour);
        if (tour == null) return "Tour no encontrado.";

        tour.cancelarReserva();
        return "Reserva cancelada para: " + tour.getNombre() + " | Cupos disponibles: " + tour.getCuposDisponibles();
    }

    private Tour buscarTour(String nombre) {
        for (Tour tour : gestorDatos.obtenerTours()) {
            if (tour.getNombre().equalsIgnoreCase(nombre)) {
                return tour;
            }
        }
        return null;
    }
}
