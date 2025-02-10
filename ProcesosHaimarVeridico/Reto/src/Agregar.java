import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Agregar representa un cuadro de JDialog para añadir un nuevo empleado a la lista de empleados.
 * 
 * Permite la captura de datos de un empleado, como código, nombre, categoría, contraseña, y fecha de nacimiento.
 * Incluye validaciones de campos obligatorios y cálculo de edad basado en la fecha de nacimiento.
 */
public class Agregar extends JDialog {

    private JPanel contentPane;
    private final JPanel contentPanel = new JPanel();
    private JTextField tfCod, tfNombre;
    private JPasswordField tfContr;
    private JFormattedTextField tfFec;
    private JComboBox<String> comboBoxCategoria;
    private final String rutaArchivo = "Empleados.obj";
    private DefaultTableModel tableModel;
    private ArrayList<Empleado> listaEmpleados;
    Empleados EmpleadoRepo = new Empleados();

    /**
     * Constructor de la clase Agregar.
     * Inicializa el cuadro de diálogo con campos para ingresar la información del nuevo empleado.
     *
     * @param owner El JFrame que es el propietario de este cuadro de diálogo
     * @param tableModel El modelo de la tabla donde se mostrará el empleado
     * @param listaEmpleados Lista de empleados a la que se añadirá el nuevo empleado
     */
    public Agregar(Frame owner, DefaultTableModel tableModel, ArrayList<Empleado> listaEmpleados) {
        super(owner, "Agregar Empleado", true);
        this.tableModel = tableModel;
        this.listaEmpleados = listaEmpleados;

        setBounds(100, 100, 450, 442);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblAgregarEmpleado = new JLabel("AGREGAR EMPLEADO");
        lblAgregarEmpleado.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblAgregarEmpleado.setBounds(100, 20, 300, 30);
        contentPanel.add(lblAgregarEmpleado);

        // Campo Código
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCodigo.setBounds(101, 70, 100, 30);
        contentPanel.add(lblCodigo);

        tfCod = new JTextField();
        tfCod.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tfCod.setBounds(207, 70, 150, 30);
        tfCod.setEditable(false);
        contentPanel.add(tfCod);

        // Generar código único al abrir el JDialog
        tfCod.setText(String.valueOf(generarCodigo()));

        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(101, 120, 100, 30);
        contentPanel.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tfNombre.setBounds(207, 120, 150, 30);
        contentPanel.add(tfNombre);

        // ComboBox para Categoría
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategoria.setBounds(101, 170, 100, 30);
        contentPanel.add(lblCategoria);

        comboBoxCategoria = new JComboBox<>(new String[]{"COCINERO", "GERENTE", "CAMARERO", "BARTENDER", "ASESOR"});
        comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxCategoria.setBounds(207, 170, 150, 30);
        contentPanel.add(comboBoxCategoria);

        // Campo Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContrasena.setBounds(101, 220, 100, 30);
        contentPanel.add(lblContrasena);

        tfContr = new JPasswordField();
        tfContr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tfContr.setBounds(207, 220, 150, 30);
        contentPanel.add(tfContr);

        // Campo Fecha de Nacimiento
        JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
        lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblFechaNacimiento.setBounds(51, 270, 150, 30);
        contentPanel.add(lblFechaNacimiento);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        tfFec = new JFormattedTextField(dateFormatter);
        tfFec.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfFec.setBounds(207, 270, 150, 30);
        contentPanel.add(tfFec);

        // Botón OK para agregar empleado
        JButton okButton = new JButton("Guardar");
        okButton.setBorderPainted(false);
        okButton.setBackground(new Color(140, 242, 140));
        okButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        okButton.setBounds(51, 347, 144, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
        contentPanel.add(okButton);

        // Botón Cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(new Color(255, 128, 128));
        cancelButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        cancelButton.setBounds(213, 347, 144, 30);
        cancelButton.addActionListener(e -> dispose());
        contentPanel.add(cancelButton);
    }

    /**
     * Genera un código único para el nuevo empleado.
     *
     * @return Un número entero aleatorio de 6 dígitos
     */
    private int generarCodigo() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    /**
     * Añade un nuevo empleado a la lista de empleados y lo guarda en el archivo obj.
     * Realiza validaciones sobre los campos obligatorios y formato de la fecha.
     * Si los datos son válidos, el empleado se añade a la lista y se muestra en la tabla de empleados.
     */
    private void agregarUsuario() {
        String codigo = tfCod.getText();
        String nombre = tfNombre.getText();
        String categoria = (String) comboBoxCategoria.getSelectedItem();
        String contrasena = new String(tfContr.getPassword());
        String fechaNacimiento = tfFec.getText();

        if (nombre.isEmpty() || categoria.isEmpty() || contrasena.isEmpty() || fechaNacimiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int edad = EmpleadoRepo.calcularEdad(fechaNacimiento);
        if (edad < 0) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Empleado nuevoEmpleado = new Empleado(codigo, nombre, categoria, contrasena, fechaNacimiento);
        listaEmpleados.add(nuevoEmpleado);  // Añadir al ArrayList

        // Actualizar tabla de inmediato
        tableModel.addRow(new Object[]{codigo, nombre, categoria, contrasena, edad});

        guardarEmpleadosEnArchivo(rutaArchivo);  // Guardar en archivo

        JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
        dispose();
    }

    /**
     * Guarda la lista de empleados en un archivo obj para persistencia.
     * @param rutaArchivo Ruta del archivo donde se guardarán los empleados
     */
    private void guardarEmpleadosEnArchivo(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
