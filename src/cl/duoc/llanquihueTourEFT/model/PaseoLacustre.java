package cl.duoc.llanquihueTourEFT.model;
import cl.duoc.llanquihueTourEFT.interfaces.IMostrarInformacion;

public class PaseoLacustre extends Tour implements IMostrarInformacion {
    private String tipoEmbarcacion;

    public PaseoLacustre(String nombre, double duracionHoras, String tipoEmbarcacion, String fecha, int cuposDisponibles) {
        super(nombre, duracionHoras, fecha, cuposDisponibles);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Tipo de Embarcacion: "+tipoEmbarcacion);

    }

    @Override
    public void mostrarDatos() {
        System.out.println("Paseo Lacustre: " + nombre + " | Duración: " + duracionHoras + " hrs" +
                " | Fecha: " + fecha + " | Cupos disponibles: " + cuposDisponibles +
                " | Embarcación: " + tipoEmbarcacion);
    }
}
