package cl.duoc.llanquihueTourEFT.model;
import cl.duoc.llanquihueTourEFT.exceptions.RutInvalidoException;

public class Rut {
    private String numRut;

    public Rut(String numRut){
        setNumRut(numRut);
    }

    public void setNumRut(String numRut) {
        /*Uso del exception para verificar que el rut sea ingresado
        en el formato correcto, por eso se hace la verificacion dentro
        del setter */
        if (numRut == null) {
            throw new RutInvalidoException("El RUT no puede ser nulo.");
        }
        if (!numRut.matches(".*-[0-9kK]$")) {
            throw new RutInvalidoException("Error: El RUT debe terminar con un guion y un dígito/K (Ej: 12345678-9).");
        }

        this.numRut = numRut;
    }
    public String getNumRut() {
        return numRut;
    }

    @Override
    public String toString() {
        return numRut;
    }
}
