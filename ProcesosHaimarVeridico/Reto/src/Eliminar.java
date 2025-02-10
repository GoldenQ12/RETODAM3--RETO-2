import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 * Elimina un empleado de la lista.
 * Esta clase muestra una ventana donde se ingresa el código del empleado
 * que se desea eliminar, y lo elimina si existe en la lista.
 */
public class Eliminar extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private ArrayList<Empleado> listaEmpleados;
    private JTextField tfCod;
    private final String rutaArchivo = "Empleados.obj";
    Empleados EmpleadoRepo = new Empleados();

    /**
     * Constructor que crea la ventana de diálogo para eliminar un empleado.
     * @param listaEmpleados Lista de empleados actual, la cual se actualizará al eliminar un empleado.
     */
    public Eliminar(ArrayList<Empleado> listaEmpleados) {
    	setTitle("ELIMNAR EMPLEADO");
        this.listaEmpleados = listaEmpleados;

        setBounds(100, 100, 309, 141);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        tfCod = new JTextField();
        tfCod.setBounds(158, 26, 96, 19);
        contentPanel.add(tfCod);
        tfCod.setColumns(10);

        JLabel lblNewLabel = new JLabel("Escribe el código:");
        lblNewLabel.setBounds(34, 29, 114, 13);
        contentPanel.add(lblNewLabel);
        
                // Botón "OK" para confirmar la eliminación
                JButton okButton = new JButton("OK");
                okButton.setBorderPainted(false);
                okButton.setBackground(new Color(187, 255, 187));
                okButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
                okButton.setBounds(58, 59, 67, 21);
                contentPanel.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (EliminarEmpleado(tfCod.getText())) {
                            guardarEmpleadosEnArchivo(rutaArchivo);
                            JOptionPane.showMessageDialog(null, "Empleado eliminado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: Empleado no encontrado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                });
                okButton.setActionCommand("OK");
                getRootPane().setDefaultButton(okButton);
                
                        // Botón "Cancelar" para cerrar el cuadro de diálogo
                        JButton cancelButton = new JButton("CANCELAR");
                        cancelButton.setBackground(new Color(255, 147, 147));
                        cancelButton.setBorderPainted(false);
                        cancelButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
                        cancelButton.setBounds(144, 59, 110, 21);
                        contentPanel.add(cancelButton);
                        cancelButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                dispose();
                            }
                        });
                        cancelButton.setActionCommand("Cancel");
    }

    /**
     * Guarda la lista de empleados actualizada en un archivo.
     * @param rutaArchivo Ruta del archivo en el que se guardará la lista de empleados.
     */
    private void guardarEmpleadosEnArchivo(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Elimina un empleado de la lista según su código.
     * Si se encuentra un empleado con el código proporcionado, se elimina de la lista y se guarda la lista actualizada.
     * @param codigo Código del empleado que se desea eliminar.
     * @return true si el empleado fue eliminado exitosamente, false si no se encontró.
     */
    public boolean EliminarEmpleado(String codigo) {
        Empleado empleado = new Empleado();
        empleado.setCodigo(codigo);
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getCodigo().equals(empleado.getCodigo())) {
                listaEmpleados.remove(i);
                guardarEmpleadosEnArchivo(rutaArchivo);
                return true;
            }
        }
        return false;
    }
}
