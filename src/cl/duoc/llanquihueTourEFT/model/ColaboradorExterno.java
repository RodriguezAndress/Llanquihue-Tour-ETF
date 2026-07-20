package cl.duoc.llanquihueTourEFT.model;

import cl.duoc.llanquihueTourEFT.interfaces.IRegistrable;

public class ColaboradorExterno extends Persona implements IRegistrable {
    public ColaboradorExterno(String nombre, String rol, Rut rut, Direccion direccion) {
        super(nombre, rol, true, rut, direccion.getCalle(), direccion.getComuna());
    }

    @Override
    public String toString() {
        return ("Colaborador: "+nombre+" | Rol: "+rol+
                " | Rut: "+rut+" | Direccion: "+getCalle()+" ,"+getComuna());
    }
    @Override
    public void mostarResumen() {
        System.out.println(toString());
    }
}
