package cl.duoc.llanquihueTourEFT.data;

import cl.duoc.llanquihueTourEFT.model.Cliente;
import cl.duoc.llanquihueTourEFT.model.ColaboradorExterno;
import cl.duoc.llanquihueTourEFT.model.Direccion;
import cl.duoc.llanquihueTourEFT.model.Rut;
import cl.duoc.llanquihueTourEFT.util.Archivo;

import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    public GestorClientes() {
        clientes = new ArrayList<>();
        cargarClientes();
        cargarDesdeArchivo();
    }

    private void cargarClientes() {
        clientes.add(new Cliente("Lorena Marquez", "Cliente", true, new Rut("55.555.555-k"), new Direccion("Golondrinas", "Santiago")));
    }

    private static final String ARCHIVO = "/clientes.txt";
    private List<Cliente> clientes;

    private void cargarDesdeArchivo() {
        for (String linea : Archivo.leerLineas(ARCHIVO)) {
            Cliente c = parsearLinea(linea);
            if (c != null) clientes.add(c);
        }
    }

    private Cliente parsearLinea(String linea) {
        try {
            String[] partes = linea.split("\\|");
            if (partes.length != 4) return null;

            String nombre = partes[0].substring(partes[0].indexOf(":") + 1).trim();
            String rol = partes[1].substring(partes[1].indexOf(":") + 1).trim();
            String rutStr = partes[2].substring(partes[2].indexOf(":") + 1).trim();
            String dirStr = partes[3].substring(partes[3].indexOf(":") + 1).trim();

            Rut rut = new Rut(rutStr);
            String[] dir = dirStr.split(",");
            Direccion direccion = new Direccion(dir[0].trim(), dir[1].trim());

            return new Cliente(nombre, rol, true, rut, direccion);
        } catch (Exception e) {
            System.out.println("No se pudo interpretar la línea: " + linea);
            return null;
        }
    }

    public String formatearLinea(Cliente c) {
        return "Nombre: " + c.getNombre() + " | Rol: " + c.getRol()
                + " | Rut: " + c.getRut().getNumRut()
                + " | Direccion: " + c.getCalle() + "," + c.getComuna();
    }

    public void agregarCliente(Cliente c) {
        clientes.add(c);
        Archivo.guardarLinea(ARCHIVO, formatearLinea(c));
    }

    public List<Cliente> obtenerClientes() {
        return clientes;
    }

}

