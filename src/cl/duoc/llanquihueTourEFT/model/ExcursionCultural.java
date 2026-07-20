package cl.duoc.llanquihueTourEFT.model;
import cl.duoc.llanquihueTourEFT.interfaces.IMostrarInformacion;

public class ExcursionCultural extends Tour implements IMostrarInformacion {
    private String lugarHistorico;

    public ExcursionCultural(String nombre, double duracionHoras, String lugarHistorico, String fecha, int cuposDisponibles) {
        super(nombre, duracionHoras,  fecha, cuposDisponibles);
        this.lugarHistorico = lugarHistorico;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Lugar Historico: "+lugarHistorico);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Excursión Cultural: " + nombre + " | Duración: " + duracionHoras + " hrs" +
                " | Fecha: " + fecha + " | Cupos disponibles: " + cuposDisponibles +
                " | Lugar: " + lugarHistorico);
    }
}
