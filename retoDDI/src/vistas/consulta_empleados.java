package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modeloDAO.empleadosDAO;
import modeloDTO.empleadosDTO;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;

public class consulta_empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtbuscar;
	private static empleadosDAO emple = new empleadosDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			consulta_empleados dialog = new consulta_empleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public consulta_empleados() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 999, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(249, 220, 92));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblModificarDeEmpleados = new JLabel("CONSULTAR EMPLEADOS");
			lblModificarDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
			lblModificarDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
			lblModificarDeEmpleados.setBounds(0, 0, 985, 83);
			contentPanel.add(lblModificarDeEmpleados);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setBounds(10, 135, 965, 249);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Apellido/s", "Email", "Teléfono", "Cargo", "Fecha", "Estado", "Contraseña"
			}
		));
		Font font = new Font ("Tamoha", Font.PLAIN, 18);
		table.setFont(font);
		scrollPane.setViewportView(table);
		{
			JLabel lblNewLabel = new JLabel("Ordenar por:");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			lblNewLabel.setBounds(10, 80, 123, 39);
			contentPanel.add(lblNewLabel);
		}
		
		JComboBox cbOrdenar = new JComboBox();
		cbOrdenar.setFont(new Font("Dialog", Font.BOLD, 18));
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Nombre ⬆️", "Nombre ⬇️", "Cargo ⬆️", "Cargo ⬇️", "Estado ⬆️", "Estado ⬇️"}));
		cbOrdenar.setBounds(142, 80, 169, 39);
		cbOrdenar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String seleccion = (String) cbOrdenar.getSelectedItem();
		        String campo = "";
		        String orden = "";

		        switch (seleccion) {
		            case "Nombre ⬆️":
		                campo = "nombre";
		                orden = "ASC";
		                break;
		            case "Nombre ⬇️":
		                campo = "nombre";
		                orden = "DESC";
		                break;
		            case "Cargo ⬆️":
		                campo = "cargo";
		                orden = "ASC";
		                break;
		            case "Cargo ⬇️":
		                campo = "cargo";
		                orden = "DESC";
		                break;
		            case "Estado ⬆️":
		                campo = "estado";
		                orden = "ASC";
		                break;
		            case "Estado ⬇️":
		                campo = "estado";
		                orden = "DESC";
		                break;
		        }

		        cargarDatosOrdenados(campo, orden);
		    }
		});

		contentPanel.add(cbOrdenar);
		
		txtbuscar = new JTextField();
		txtbuscar.setFont(new Font("Dialog", Font.BOLD, 18));
		txtbuscar.setBounds(777, 80, 139, 44);
		txtbuscar.setColumns(10);
		contentPanel.add(txtbuscar);

		txtbuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(consulta_empleados.class.getResource("/images/searchIcon.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String textoBuscar = txtbuscar.getText().trim();
		        String opcion = "Nombre";
		        String campo = "";

		        if (textoBuscar.isEmpty()) {
		        	cargarDatosEnTabla();
		        } else {
		        	switch (opcion) {
		            case "Nombre":
		                campo = "nombre";
		                break;
		            case "Cargo":
		                campo = "cargo";
		                break;
		            default:
		                JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		        	}
		        	cargarDatosFiltrados(campo, textoBuscar);
		        }
		        

		        

		        
		    }
		});
		btnBuscar.setFont(new Font("Dialog", Font.BOLD, 18));
		btnBuscar.setActionCommand("Cancel");
		btnBuscar.setBounds(922, 80, 53, 44);
		contentPanel.add(btnBuscar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 24));
		btnCerrar.setBackground(new Color(231, 24, 24));
		btnCerrar.setBounds(856, 401, 117, 39);
		contentPanel.add(btnCerrar);
		
		JLabel lblIntroduceUnNombre = new JLabel("Introduce un nombre: ");
		lblIntroduceUnNombre.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIntroduceUnNombre.setBounds(563, 80, 204, 39);
		contentPanel.add(lblIntroduceUnNombre);
		
		cargarDatosEnTabla();
	}
	private void cargarDatosEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); 
        
        List<empleadosDTO> empleados = emple.listarTodos();
        for (empleadosDTO empleado : empleados) {
            modelo.addRow(new Object[] {
            	empleado.getNombre(),
            	empleado.getApellidos(),
            	empleado.getEmail(),
            	empleado.getTelefono(),
            	empleado.getCargo(),
            	empleado.getFechaAlta(),
            	empleado.getEstado(),
            	empleado.getPassword(),
            });
        }
    }
	private void cargarDatosOrdenados(String campo, String orden) {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    int rowCount = modelo.getRowCount();

	    // Extraer las filas actuales de la tabla
	    List<Object[]> filas = new ArrayList<>();
	    for (int i = 0; i < rowCount; i++) {
	        Object[] fila = new Object[modelo.getColumnCount()];
	        for (int j = 0; j < modelo.getColumnCount(); j++) {
	            fila[j] = modelo.getValueAt(i, j);
	        }
	        filas.add(fila);
	    }

	    final int columna;
	    switch (campo) {
	        case "nombre":
	            columna = 0; // Columna "Nombre"
	            break;
	        case "cargo":
	            columna = 4; // Columna "Cargo"
	            break;
	        case "estado":
	            columna = 6; // Columna "Estado"
	            break;
	        default:
	            columna = -1; // Si no hay un caso válido
	    }

	    if (columna != -1) {
	        filas.sort((a, b) -> {
	            Comparable valorA = (Comparable) a[columna];
	            Comparable valorB = (Comparable) b[columna];
	            return "ASC".equals(orden) ? valorA.compareTo(valorB) : valorB.compareTo(valorA);
	        });
	    }

	    // Limpiar el modelo y volver a cargar los datos ordenados
	    modelo.setRowCount(0);
	    for (Object[] fila : filas) {
	        modelo.addRow(fila);
	    }
	}

	private void cargarDatosFiltrados(String campo, String valor) {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setRowCount(0);

	    List<empleadosDTO> empleados = emple.buscarPorCriterio(campo, valor);
	    
	    if (empleados.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No se encontraron resultados para la búsqueda.", "Información", JOptionPane.INFORMATION_MESSAGE);
	        
	    }

	    for (empleadosDTO empleado : empleados) {
	        modelo.addRow(new Object[] {
	            empleado.getNombre(),
	            empleado.getApellidos(),
	            empleado.getEmail(),
	            empleado.getTelefono(),
	            empleado.getCargo(),
	            empleado.getFechaAlta(),
	            empleado.getEstado(),
	            empleado.getPassword(),
	        });
	    }
	}

}
