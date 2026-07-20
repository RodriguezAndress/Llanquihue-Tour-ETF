package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IRegistrable;

public class Proveedor extends Persona implements IRegistrable {
    public Proveedor(String nombre, String rol, boolean esRol, Rut rut, Direccion direccion) {
        super(nombre, rol, true, rut, direccion.getCalle(), direccion.getComuna());
    }

    @Override
    public String toString() {
        return ("Nombre Proveedor: "+nombre +" | Provee: "+rol+
                " |Rut: "+rut+" | Dirección: "+getCalle()+" ,"+getComuna());
    }
    @Override
    public void mostarResumen() {
        System.out.println(toString());
    }
}
