import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;	
/**
* Clase principal para gestionar la interfaz gráfica de los empleados.
* Permite añadir, modificar, eliminar y buscar empleados almacenados en un archivo.
*
* Los empleados se almacenan en un archivo binario para persistencia de datos.
* Cada empleado tiene atributos como código, nombre, categoría, contraseña y edad.
*
*/
public class Empleados extends JFrame {
	private static final long serialVersionUID = 1L;
private JPanel contentPane;
   private JTable table;
   private DefaultTableModel tableModel;
   private JTextField tfBuscar;
   private final String rutaArchivo = "Empleados.obj";
   public static ArrayList<Empleado> listaEmpleados; // Lista de objetos Empleado
   public static ArrayList<Empleado> listaEmpleados2 = new ArrayList<>(); // Asegúrate de inicializarla
   public static long tiempoInicio;
   /**
    * Método principal que inicia la aplicación.
    */
   public static void main(String[] args) {
       tiempoInicio = System.currentTimeMillis();
       EventQueue.invokeLater(() -> {
           try {
               Empleados frame = new Empleados();
               frame.setVisible(true);
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
   }
   /**
    * Constructor de la clase Empleados.
    * Inicializa la interfaz gráfica de usuario y carga los empleados desde el archivo.
    * Configura el diseño de la tabla y botones para agregar, modificar, eliminar y buscar empleados.
    */
   
   public Empleados() {
   	setTitle("EMPLEADOS");
       listaEmpleados = new ArrayList<>(); // Inicializar el ArrayList
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(100, 100, 1018, 605);
       contentPane = new JPanel();
       contentPane.setBackground(new Color(240, 240, 240));
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);
       // Configurar columnas de la tabla
       String[] columnNames = {"Código", "Nombre", "Categoría", "Contraseña", "Edad"};
       tableModel = new DefaultTableModel(columnNames, 0);
       table = new JTable(tableModel);
       table.setFont(new Font("SansSerif", Font.PLAIN, 14));
       table.setRowHeight(25);
       JScrollPane scrollPane = new JScrollPane(table);
       scrollPane.setBounds(31, 10, 570, 439);
       contentPane.add(scrollPane);
      
      
       // Configurar el formato de fecha
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       DateFormatter dateFormatter = new DateFormatter(dateFormat);
      
       // Botón para añadir un empleado
       RoundedButton btnAna = new RoundedButton("AÑADIR EMPLEADO");
       btnAna.setBorderPainted(false);
       btnAna.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
       btnAna.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
       btnAna.setBackground(new Color(190, 231, 196));
       btnAna.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Agregar formulario = new Agregar(null, tableModel, listaEmpleados);
               guardarEmpleadosEnArchivo(rutaArchivo);
               mostrarUsuarios();
               formulario.setVisible(true);
              
           }
       });
       btnAna.setBounds(621, 10, 353, 91);
       contentPane.add(btnAna);
      
      
       // Botón para eliminar un usuario
       RoundedButton btnEli = new RoundedButton("ELIMINAR EMPLEADO");
       btnEli.setBorderPainted(false);
       btnEli.setBackground(new Color(255, 153, 153));
       btnEli.setAutoscrolls(true);
       btnEli.setAlignmentY(Component.BOTTOM_ALIGNMENT);
       btnEli.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
       btnEli.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           	Eliminar formulario = new Eliminar(listaEmpleados);
           	cargarEmpleadosDesdeArchivo();
           	guardarEmpleadosEnArchivo(rutaArchivo);
           	formulario.setVisible(true);
           }
       });
       btnEli.setBounds(621, 130, 353, 98);
       contentPane.add(btnEli);
      
      
       // Botón para modificar un usuario
       RoundedButton btnModificar = new RoundedButton("MODIFICAR EMPLEADO");
       btnModificar.setBackground(new Color(128, 214, 255));
       btnModificar.setBorderPainted(false);
       btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
       btnModificar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               Modificar formulario = new Modificar(listaEmpleados);
               cargarEmpleadosDesdeArchivo();
               guardarEmpleadosEnArchivo(rutaArchivo);
               formulario.setVisible(true);
              
           }
       });
       btnModificar.setBounds(621, 259, 353, 91);
       contentPane.add(btnModificar);
      
      
      
       // Botón para actualizar la tabla
       RoundedButton btnActualizar = new RoundedButton("ACTUALIZAR");
       btnActualizar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           	cargarEmpleadosDesdeArchivo();
               mostrarUsuarios();
               limpiarCampos();
           }
       });
       btnActualizar.setBounds(465, 467, 134, 31);
       contentPane.add(btnActualizar);
      
      
       // Label donde te dice donde te dice donde porner el nombre
       JLabel lblBuscar = new JLabel("Buscar por Nombre:");
       lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblBuscar.setBounds(31, 472, 150, 25);
       contentPane.add(lblBuscar);
       //TextField Donde pones el nombre
       tfBuscar = new JTextField();
       tfBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
       tfBuscar.setBounds(164, 472, 150, 25);
       contentPane.add(tfBuscar);
      
       // Botón para buscar usuarios
       RoundedButton btnBuscar = new RoundedButton("BUSCAR");
       btnBuscar.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               buscarPorNombre();
           }
       });
       btnBuscar.setBounds(355, 466, 100, 31);
       contentPane.add(btnBuscar);
      
      
       //Boton para salir de la aplicacion
       RoundedButton btnSalir = new RoundedButton("SALIR");
       btnSalir.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		dispose();
       	}
       });
       btnSalir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
       btnSalir.setBorderPainted(false);
       btnSalir.setBackground(new Color(255, 0, 0));
       btnSalir.setBounds(869, 495, 125, 63);
       contentPane.add(btnSalir);
       
       JLabel lblNewLabel = new JLabel("");
       //Ruta absoluta
       lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Haimar\\Desktop\\Workspace2\\Reto\\src\\logo.png"));
       lblNewLabel.setBounds(757, 360, 125, 125);
       contentPane.add(lblNewLabel);
      
       cargarEmpleadosDesdeArchivo(); // Cargar empleados desde el archivo
       mostrarUsuarios();  //Mostrar los empleados
   }
   /**
    * Calcula la edad de un empleado a partir de su fecha de nacimiento.
    *
    * @param fechaNacimiento La fecha de nacimiento en formato "dd/MM/yyyy"
    * @return La edad calculada, o -1 si la fecha es inválida
    */
   public int calcularEdad(String fechaNacimiento) {
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       try {
           Date fecha = dateFormat.parse(fechaNacimiento);
           Calendar nacimiento = Calendar.getInstance();
           nacimiento.setTime(fecha);
           Calendar actual = Calendar.getInstance();
           int edad = actual.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
           if (actual.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
               edad--;
           }
           return edad;
       } catch (ParseException e) {
           e.printStackTrace();
           return -1;
       }
   }
   /**
    * Carga los empleados desde un archivo binario y los almacena en la lista de empleados.
    */
   public void cargarEmpleadosDesdeArchivo() {
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
           listaEmpleados = (ArrayList<Empleado>) ois.readObject();
       } catch (IOException | ClassNotFoundException ex) {
           System.out.println("No se encontraron datos previos o hubo un error en la carga.");
       }
   }
  
   /**
    * Guarda la lista de empleados en un archivo.
    * @param rutaArchivo La ruta del archivo donde se guardará la lista
    */
   private void guardarEmpleadosEnArchivo(String rutaArchivo) {
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
           oos.writeObject(listaEmpleados);
       } catch (IOException ex) {
           ex.printStackTrace();
       }
   }
  
   /**
    * Muestra todos los empleados en la tabla.
    * Limpia la tabla y luego añade una fila por cada empleado.
    */
   public void mostrarUsuarios() {
       tableModel.setRowCount(0); // Limpiar tabla
       for (Empleado empleado : listaEmpleados) {
           int edad = calcularEdad(empleado.getFechaNacimiento());
           agregarFilaTabla(empleado.getCodigo(), empleado.getNombre(), empleado.getCategoria(), empleado.getContrasena(), String.valueOf(edad));
       }
   }
  
   /**
    * Limpia el campo de texto utilizado para la búsqueda de empleados.
    */
   private void limpiarCampos() {
       tfBuscar.setText("");
   }
  
   /**
    * Realiza una búsqueda en la lista de empleados por nombre y muestra los resultados en la tabla.
    */
   private void buscarPorNombre() {
       String nombreBuscar = tfBuscar.getText().trim().toLowerCase();
       if (nombreBuscar.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
           return;
       }
      
       tableModel.setRowCount(0); // Limpiar tabla
      
       for (Empleado empleado : listaEmpleados) {
           String nombre = empleado.getNombre().toLowerCase();
           if (nombre.contains(nombreBuscar)) {
               int edad = calcularEdad(empleado.getFechaNacimiento());
               agregarFilaTabla(empleado.getCodigo(), empleado.getNombre(), empleado.getCategoria(), empleado.getContrasena(), String.valueOf(edad));
           }
       }
   }
   /**
    * Agrega una fila a la tabla de empleados con los datos proporcionados.
    *
    * @param codigo     Código del empleado
    * @param nombre     Nombre del empleado
    * @param categoria  Categoría del empleado
    * @param contrasena Contraseña del empleado
    * @param edad       Edad del empleado
    */
   public void agregarFilaTabla(String codigo, String nombre, String categoria, String contrasena, String edad) {
       tableModel.addRow(new Object[]{codigo, nombre, categoria, contrasena, edad});
   }
  
   
  
   /**
    * Verifica si el usuario tiene acceso (habilitado) para iniciar el .
    *
    * @param usuario El nombre del usuario a verificar
    * @return true si el usuario está habilitado, false en caso contrario
    */
  
}