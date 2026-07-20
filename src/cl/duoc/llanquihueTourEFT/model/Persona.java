package cl.duoc.llanquihueTourEFT.model;


public abstract class Persona extends Direccion{
    protected String nombre;
    protected String especialidad;
    protected String rol;
    protected Rut rut; // Recibe de la clse Rut como atributo, uso de la composición.
    //protected Direccion direccion;

    //Constructor para clase hija Guia Turistico
    public Persona(String nombre, String especialidad, Rut rut, String calle, String comuna) {
        super(calle, comuna);
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.rol = null;
        this.rut = rut;
        //this.direccion = direccion;
    }

    /*Constructor para clase hija Colaborador Externo
    Se usa el booleano para diferenciar los constructores y no arroje error por recibir
    los mismos parametros que el anterior.*/

    public Persona(String nombre, String rol, boolean esRol, Rut rut,  String calle, String comuna) {
        super(calle, comuna);
        this.nombre = nombre;
        this.rol = rol;
        // this.especialidad = null; // Queda vacío
        this.rut = rut;
       // this.direccion = direccion;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getEspecialidad() {return especialidad;}

    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}

    public Rut getRut() {return rut;}

    public void setRut(Rut rut) {}

    @Override
    public String getCalle() {
        return super.getCalle();
    }

    @Override
    public void setCalle(String calle) {
        super.setCalle(calle);
    }
}
