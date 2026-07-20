package cl.duoc.llanquihueTourEFT.model;
import cl.duoc.llanquihueTourEFT.interfaces.IReservable;

public abstract class Tour extends ServicioTuristico implements IReservable {
    protected String fecha;
    protected int cuposDisponibles;

    public Tour(String nombre, double duracionHoras, String fecha, int cuposDisponibles) {
        super(nombre, duracionHoras);
        this.fecha = fecha;
        this.cuposDisponibles = cuposDisponibles;
    }
    public int getCuposDisponibles() {return cuposDisponibles;}
    public String getFecha() {return fecha;}

    @Override
    public void reservarTour() {
        if(tieneCupos()){
            cuposDisponibles--;
        }else{
            System.out.println("No hay disponibilidad de cupor por el momento.");
        }
    }
    @Override
    public void cancelarReserva() {
        cuposDisponibles++;

    }
    @Override
    public boolean tieneCupos() {
        return cuposDisponibles > 0;
    }

    @Override
    public String toString() {
        return "Tour: " + nombre + " | Duración: " + duracionHoras + " hrs" +
                " | Fecha: " + fecha + " | Cupos disponibles: " + cuposDisponibles;
    }

    public abstract void mostrarDatos();

}
