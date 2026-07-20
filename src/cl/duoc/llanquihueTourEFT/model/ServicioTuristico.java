package cl.duoc.llanquihueTourEFT.model;

public class ServicioTuristico {
    protected String nombre;
    protected double duracionHoras;

    public ServicioTuristico(String nombre, double duracionHoras) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getDuracionHoras() { return duracionHoras; }

    public void setDuracionHoras(double duracionHoras) { this.duracionHoras = duracionHoras; }

   /* public String mostrar(){
        return "Servicio Turistico: " + nombre + "|" + duracionHoras + "hrs";
    }*/

    @Override
    public String toString() {
        return "ServicioTuristico," + nombre + "," + duracionHoras;
    }
}
