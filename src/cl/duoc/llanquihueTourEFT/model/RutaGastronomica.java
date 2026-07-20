package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IMostrarInformacion;

public class RutaGastronomica extends Tour implements IMostrarInformacion {
    private int numeroDeParadas;

    public RutaGastronomica(String nombre, double duracionHoras,  int numeroDeParadas, String fecha, int cuposDisponibles) {
        super(nombre, duracionHoras, fecha, cuposDisponibles);
        this.numeroDeParadas = numeroDeParadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Número de Paradas: "+numeroDeParadas);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Ruta Gastronómica: " + nombre + " | Duración: " + duracionHoras + " hrs" +
                " | Fecha: " + fecha + " | Cupos disponibles: " + cuposDisponibles +
                " | Paradas: " + numeroDeParadas);
    }
}
