package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IRegistrable;

public class Cliente extends Persona implements IRegistrable {
    public Cliente(String nombre, String rol, boolean esRol, Rut rut, Direccion direccion) {
        super(nombre, rol, esRol, rut, direccion.getCalle(), direccion.getComuna());
    }
    @Override
    public String toString() {
        return ("Nombre Cliente: "+nombre+" | Rol: "+rol+
                " |Rut: "+rut+" | Direccion: "+getCalle()+" ,"+getComuna());
    }
    @Override
    public void mostarResumen() {
        System.out.println(toString());
    }

}
