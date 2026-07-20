package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IRegistrable;

public class Vehiculo implements IRegistrable {
    private String patente;
    private String tipo;

    public Vehiculo(String patente, String tipo) {
        this.patente = patente;
        this.tipo = tipo;
    }

    public String getPatente() {return patente;}

    public void setPatente(String patente) {this.patente = patente;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    @Override
    public String toString() {

        return ("Patente: "+patente + " | Tipo de vehiculo: "+tipo);
    }
    @Override
    public void mostarResumen() {
        System.out.println(toString());
    }
}
