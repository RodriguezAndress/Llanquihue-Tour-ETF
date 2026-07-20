package cl.duoc.llanquihueTourEFT.model;


public class Reserva extends Tour {
    public Reserva(String nombre, double duracionHoras, String fecha, int cuposDisponibles) {
        super(nombre, duracionHoras, fecha, cuposDisponibles);
    }

    @Override
    public String toString() {
        return "Tour: " + nombre + " | Duración: " + duracionHoras + " hrs" +
                " | Fecha: " + fecha + " | Cupos disponibles: " + cuposDisponibles;
    }
    @Override
    public void mostrarDatos() {
        System.out.println(toString());
    }

}
