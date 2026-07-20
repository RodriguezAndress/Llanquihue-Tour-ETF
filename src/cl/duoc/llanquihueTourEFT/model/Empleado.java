package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IRegistrable;

public class Empleado extends Persona implements IRegistrable {
    public Empleado(String nombre, String especialidad, Rut rut, Direccion direccion) {
        super(nombre, especialidad, rut, direccion.getCalle(), direccion.getComuna());
    }

    @Override
    public String toString() {
        return ("Nombre Empleado: "+nombre+" | Area de Especialidad: "+especialidad+
                                " | Rut: "+rut+" | Direccion: "+getCalle()+" ,"+getComuna());
    }
    @Override
    public void mostarResumen() {
        System.out.println(toString());
    }

}
