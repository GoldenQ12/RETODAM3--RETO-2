import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Modificar la información de un empleado.
 * Muestra un formulario para editar detalles de un empleado, como nombre, categoría, contraseña y fecha de nacimiento,
 * y ofrece opciones para guardar las modificaciones.
 */
public class Modificar extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField tfNombre;
    private JPasswordField tfContr;
    private JFormattedTextField tfFec;
    private JComboBox<String> comboBoxCategoria;
    private final String rutaArchivo = "Empleados.obj";
    private ArrayList<Empleado> listaEmpleados;
    private JTextField tfCod;
    private DefaultTableModel tableModel;
    Empleados EmpleadoRepo = new Empleados();

    /**
     * Constructor que crea el cuadro de diálogo de modificación.
     * @param listaEmpleados La lista de empleados, que se actualizará al modificar un empleado.
     */
    public Modificar(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;

        setBounds(100, 100, 450, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblModificarUsuario = new JLabel("MODIFICAR EMPLEADO");
        lblModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblModificarUsuario.setBounds(100, 20, 300, 30);
        contentPanel.add(lblModificarUsuario);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCodigo.setBounds(32, 68, 100, 30);
        contentPanel.add(lblCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(101, 120, 100, 30);
        contentPanel.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tfNombre.setBounds(207, 120, 150, 30);
        contentPanel.add(tfNombre);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategoria.setBounds(101, 170, 100, 30);
        contentPanel.add(lblCategoria);

        comboBoxCategoria = new JComboBox<>(new String[]{"COCINERO", "GERENTE", "CAMARERO", "BARTENDER", "ASESOR"});
        comboBoxCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxCategoria.setBounds(207, 170, 150, 30);
        contentPanel.add(comboBoxCategoria);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContrasena.setBounds(101, 220, 100, 30);
        contentPanel.add(lblContrasena);

        tfContr = new JPasswordField();
        tfContr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tfContr.setBounds(207, 220, 150, 30);
        contentPanel.add(tfContr);

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

        JButton okButton = new JButton("Guardar");
        okButton.setBorderPainted(false);
        okButton.setBackground(new Color(176, 255, 176));
        okButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        okButton.setBounds(51, 347, 150, 30);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ModificarUsuario(tfCod.getText(), new Empleado(tfCod.getText(), tfNombre.getText(), comboBoxCategoria.getSelectedItem().toString(), tfContr.getText(), tfFec.getText()))) {
                    JOptionPane.showMessageDialog(null, "Modificado", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                guardarEmpleadosEnArchivo(rutaArchivo);
            }
        });
        contentPanel.add(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(new Color(255, 125, 125));
        cancelButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        cancelButton.setBounds(220, 347, 155, 30);
        cancelButton.addActionListener(e -> dispose());
        contentPanel.add(cancelButton);

        tfCod = new JTextField();
        tfCod.setBounds(142, 72, 150, 30);
        contentPanel.add(tfCod);
        tfCod.setColumns(10);

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setBackground(new Color(255, 189, 157));
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cargarEmpleadosDesdeArchivo();
            	guardarEmpleadosEnArchivo(rutaArchivo);
            	EmpleadoRepo.cargarEmpleadosDesdeArchivo();
                buscarPorCodigo();
                
            }
        });
        btnBuscar.setBounds(315, 76, 85, 21);
        contentPanel.add(btnBuscar);
    }

    /**
     * Guarda la lista de empleados en un archivo.
     * @param rutaArchivo Ruta al archivo donde se guardarán los empleados.
     */
    private void guardarEmpleadosEnArchivo(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Modifica un empleado existente en la lista basado en su código.
     * @param codigo El código del empleado a modificar.
     * @param empleado Datos actualizados del empleado.
     * @return true si el empleado fue encontrado y modificado, false en caso contrario.
     */
    public boolean ModificarUsuario(String codigo, Empleado empleado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getCodigo().equals(empleado.getCodigo())) {
                listaEmpleados.set(i, empleado);
                guardarEmpleadosEnArchivo(rutaArchivo);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un empleado por su código y rellena los campos del formulario con los datos del empleado.
     */
    private void buscarPorCodigo() {
        String codBuscar = tfCod.getText().trim().toLowerCase();

        if (codBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un codigo para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Empleado empleado : listaEmpleados) {
            String Codigo = empleado.getCodigo().toLowerCase();
            if (Codigo.contains(codBuscar)) {
                tfCod.setText(empleado.getCodigo());
                tfNombre.setText(empleado.getNombre());
                comboBoxCategoria.setSelectedItem(empleado.getCategoria());
                tfContr.setText(empleado.getContrasena());
                tfFec.setText(empleado.getFechaNacimiento());
                EmpleadoRepo.calcularEdad(empleado.getFechaNacimiento());
                tfCod.setEditable(false);
            }
        }
    }
    
    public void cargarEmpleadosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            listaEmpleados = (ArrayList<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No se encontraron datos previos o hubo un error en la carga.");
        }
    }
    
    

}
