package cl.duoc.llanquihueTourEFT.app;

import cl.duoc.llanquihueTourEFT.data.*;
import cl.duoc.llanquihueTourEFT.data.GestorReservas;
import cl.duoc.llanquihueTourEFT.exceptions.RutInvalidoException;
import cl.duoc.llanquihueTourEFT.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Ventana extends JFrame {

    private GestorClientes gestorClientes = new GestorClientes();
    private GestorEmpleados  gestorEmpleados = new GestorEmpleados();
    private GestorProveedores  gestorProveedores = new GestorProveedores();
    private GestorColaboradores  gestorColaboradores = new GestorColaboradores();
    private GestorVehiculos  gestorVehiculos = new GestorVehiculos();
    private GestorDatosTuristicos gestorDatosTuristicos = new GestorDatosTuristicos();
    private GestorReservas servicioReservas = new GestorReservas(gestorDatosTuristicos);

    public Ventana() {
        //Estilo y botones de la ventana principal con la que interactua el usuario
        setTitle("Llanquihue Tours");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registar");
        JButton btnReserva = new JButton("Reserva");
        JButton btnMostrarPlantilla = new JButton("Mostrar Plantilla");
        JButton btnSalir = new JButton("Salir");

        btnRegistrar.addActionListener(e -> mostrarMenuRegistrar(btnRegistrar));
        btnReserva.addActionListener(e -> mostrarMenuReserva(btnReserva));
        btnMostrarPlantilla.addActionListener(e -> mostrarMenuPlantilla(btnMostrarPlantilla));
        btnSalir.addActionListener(e -> System.exit(0));

        add(btnRegistrar);
        add(btnReserva);
        add(btnMostrarPlantilla);
        add(btnSalir);

    }
    //Menu registrar
    private void mostrarMenuRegistrar(JComponent origen){
        JPopupMenu menu = new  JPopupMenu();

        JMenuItem itemCliente = new JMenuItem("Cliente");
        JMenuItem itemEmpleado = new JMenuItem("Empleado");
        JMenuItem itemColaborador = new JMenuItem("Colaborador Externo");
        JMenuItem itemProveedor = new JMenuItem("Proveedor");
        JMenuItem itemVehiculo = new JMenuItem("Vehiculo");
        JMenuItem itemTour = new JMenuItem("Tour");

        itemCliente.addActionListener(e -> registrarCliente());
        itemEmpleado.addActionListener(e -> registrarEmpleado());
        itemColaborador.addActionListener(e -> registrarColaborador());
        itemProveedor.addActionListener(e -> registrarProveedor());
        itemVehiculo.addActionListener(e -> registrarVehiculo());
        itemTour.addActionListener(e -> registrarTour());

        menu.add(itemCliente);
        menu.add(itemEmpleado);
        menu.add(itemColaborador);
        menu.add(itemProveedor);
        menu.add(itemVehiculo);
        menu.add(itemTour);

        menu.show(origen, 0, origen.getHeight());
    }
    private void registrarCliente() {
        String[] etiquetas = {"Nombre", "Freceunte/Ocasional", "Rut (12.345.678-9)", "Calle", "Comuna"};
        String[] valores = null;

        while (true) {
            valores = mostrarFormulario("Registrar Cliente", etiquetas, valores);
            if (valores == null) return; // el usuario cancelo

            try {
                if (valores[0].isBlank()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }

                Rut rut = new Rut(valores[2]);
                Direccion direccion = new Direccion(valores[3], valores[4]);
                Cliente cliente = new Cliente(valores[0], valores[1], true, rut, direccion);
                gestorClientes.agregarCliente(cliente);
                JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.");
                return; // salio bien, corta el bucle
            } catch (RutInvalidoException ex) {
                JOptionPane.showMessageDialog(this,
                        "RUT inválido: " + ex.getMessage(),
                        "Error de RUT", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error inesperado: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registrarEmpleado() {
        String[] etiquetas = {"Nombre", "Especialidad", "Rut (12.345.678-9)", "Calle", "Comuna"};
        String[] valores = null;

        while (true) {
            valores = mostrarFormulario("Registrar Empleado", etiquetas, valores);
            if (valores == null) return;

            try {
                Rut rut = new Rut(valores[2]);
                Direccion direccion = new Direccion(valores[3], valores[4]);
                Empleado empleado = new Empleado(valores[0], valores[1], rut, direccion);
                gestorEmpleados.agregarEmpleado(empleado);
                JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.");
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void registrarColaborador() {
        String[] etiquetas = {"Nombre", "Especialidad", "Rut (12.345.678-9)", "Calle", "Comuna"};
        String[] valores = null;

        while (true) {
            valores = mostrarFormulario("Registrar Colaborador Externo", etiquetas, valores);
            if (valores == null) return;

            try {
                Rut rut = new Rut(valores[2]);
                Direccion direccion = new Direccion(valores[3], valores[4]);
                ColaboradorExterno colaborador = new ColaboradorExterno(valores[0], valores[1], rut, direccion);
                gestorColaboradores.agregarColaborador(colaborador);
                JOptionPane.showMessageDialog(this, "Colaborador Externo registrado con éxito.");
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void registrarProveedor() {
            String[] etiquetas = {"Nombre", "Especialidad", "Rut (12.345.678-9)", "Calle", "Comuna"};
            String[] valores = null;

            while (true) {
                valores = mostrarFormulario("Registrar Proveedor", etiquetas, valores);
                if (valores == null) return;

                try {
                    Rut rut = new Rut(valores[2]);
                    Direccion direccion = new Direccion(valores[3], valores[4]);
                    Proveedor proveedor = new Proveedor(valores[0], valores[1], true, rut, direccion);
                    gestorProveedores.agregarProveedor(proveedor);
                    JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.");
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
    private void registrarVehiculo() {
        String[] etiquetas = {"Patente", "Tipo"};
        String[] valores = null;

        while (true) {
            valores = mostrarFormulario("Registrar Vehículo", etiquetas, valores);
            if (valores == null) return; // el usuario canceló

            try {
                Vehiculo vehiculo = new Vehiculo(valores[0], valores[1]);
                gestorVehiculos.agregarVehiculo(vehiculo);
                JOptionPane.showMessageDialog(this, "Vehículo registrado con éxito.");
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void registrarTour() {
        String[] etiquetas = {"Nombre", "Duración (horas)", "Fecha (2026-08-20)", "Cupos disponibles"};
        String[] valores = null;

        while (true) {
            valores = mostrarFormulario("Registrar Tour", etiquetas, valores);
            if (valores == null) return; // el usuario canceló

            try {
                if (valores[0].isBlank()) {
                    throw new IllegalArgumentException("El nombre del tour no puede estar vacío.");
                }

                double duracion = Double.parseDouble(valores[1]);
                int cupos = Integer.parseInt(valores[3]);
                Reserva tour = new Reserva(valores[0], duracion, valores[2], cupos);
                gestorDatosTuristicos.agregarTour(tour);
                JOptionPane.showMessageDialog(this, "Tour registrado con éxito.");
                return;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "La duración y los cupos deben ser números válidos.",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrió un error inesperado: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Menu btn Reserva
    private void mostrarMenuReserva(JComponent origen) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem itemNueva = new JMenuItem("Nueva Reserva");
        JMenuItem itemCancelar = new JMenuItem("Cancelar Reserva");

        itemNueva.addActionListener(e -> gestionarReserva(true));
        itemCancelar.addActionListener(e -> gestionarReserva(false));

        menu.add(itemNueva);
        menu.add(itemCancelar);

        menu.show(origen, 0, origen.getHeight());
    }
    private void gestionarReserva(boolean esNueva) {
        List<Tour> tours = gestorDatosTuristicos.obtenerTours();
        if (tours.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tours registrados.");
            return;
        }

        String[] nombres = tours.stream().map(Tour::getNombre).toArray(String[]::new);

        String seleccionado = (String) JOptionPane.showInputDialog(
                this,
                esNueva ? "Selecciona el tour a reservar:" : "Selecciona el tour a cancelar:",
                esNueva ? "Nueva Reserva" : "Cancelar Reserva",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccionado == null) return;

        String resultado = esNueva ? servicioReservas.reservar(seleccionado) : servicioReservas.cancelar(seleccionado);
        JOptionPane.showMessageDialog(this, resultado);
    }

    // Menu btn mostrar plantilla

    private void mostrarMenuPlantilla(JComponent origen) {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem itemClientes = new JMenuItem("Clientes");
        JMenuItem itemEmpleados = new JMenuItem("Empleados");
        JMenuItem itemColaboradores = new JMenuItem("Colaboradores Externos");
        JMenuItem itemProveedores = new JMenuItem("Proveedores");
        JMenuItem itemVehiculos = new JMenuItem("Vehículos");
        JMenuItem itemTours = new JMenuItem("Tours");

        itemClientes.addActionListener(e -> mostrarLista("Clientes", gestorClientes.obtenerClientes()));
        itemEmpleados.addActionListener(e -> mostrarLista("Empleados", gestorEmpleados.obtenerEmpleados()));
        itemColaboradores.addActionListener(e -> mostrarLista("Colaboradores Externos", gestorColaboradores.obtenerColaboradores()));
        itemProveedores.addActionListener(e -> mostrarLista("Proveedores", gestorProveedores.obtenerProveedores()));
        itemVehiculos.addActionListener(e -> mostrarLista("Vehículos", gestorVehiculos.obtenerVehiculos()));
        itemTours.addActionListener(e -> mostrarLista("Tours", gestorDatosTuristicos.obtenerTours()));

        menu.add(itemClientes);
        menu.add(itemEmpleados);
        menu.add(itemColaboradores);
        menu.add(itemProveedores);
        menu.add(itemVehiculos);
        menu.add(itemTours);

        menu.show(origen, 0, origen.getHeight());
    }

    private void mostrarLista(String titulo, List<?> elementos) {
        if (elementos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay " + titulo.toLowerCase() + " registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Object o : elementos) {
            sb.append(o.toString()).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 250));

        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // Helper: formulario genérico

    private String[] mostrarFormulario(String titulo, String[] etiquetas, String[] valoresPrevios) {
        JPanel panel = new JPanel(new GridLayout(etiquetas.length, 2, 5, 5));
        JTextField[] campos = new JTextField[etiquetas.length];

        for (int i = 0; i < etiquetas.length; i++) {
            panel.add(new JLabel(etiquetas[i] + ":"));
            campos[i] = new JTextField();
            if (valoresPrevios != null) {
                campos[i].setText(valoresPrevios[i]);
            }
            panel.add(campos[i]);
        }

        int resultado = JOptionPane.showConfirmDialog(this, panel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado != JOptionPane.OK_OPTION) return null;

        String[] valores = new String[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            valores[i] = campos[i].getText().trim();
        }
        return valores;
    }

    // Sobrecarga para la primera llamada, sin valores previos
    private String[] mostrarFormulario(String titulo, String... etiquetas) {
        return mostrarFormulario(titulo, etiquetas, null);
    }
}
