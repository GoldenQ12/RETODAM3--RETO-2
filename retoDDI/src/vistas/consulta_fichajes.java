package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import modeloDAO.fichajesDAO;

public class consulta_fichajes extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtbuscar;
    private JTable table;
    private fichajesDAO fichajesDAO = new fichajesDAO();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            consulta_fichajes dialog = new consulta_fichajes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public consulta_fichajes() {
        setModal(true);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 999, 505);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(249, 220, 92));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblConsultarFichjes = new JLabel("LISTA DE FICHAJES");
        lblConsultarFichjes.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsultarFichjes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
        lblConsultarFichjes.setBounds(0, 0, 983, 84);
        contentPanel.add(lblConsultarFichjes);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(8, 156, 965, 249);
        contentPanel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Empleado", "Fecha", "Hora entrada", "Hora salida", "Modo"}
        ));
        Font font = new Font ("Tamoha", Font.PLAIN, 18);
		table.setFont(font);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Ordenar por:");
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblNewLabel.setBounds(8, 95, 117, 50);
        contentPanel.add(lblNewLabel);

        JComboBox<String> cbOrdenar = new JComboBox<>();
        cbOrdenar.setModel(new DefaultComboBoxModel<>(new String[] {"Nombre ⬆️", "Nombre ⬇️", "Fecha ⬆️", "Fecha ⬇️"}));
        cbOrdenar.setFont(new Font("Dialog", Font.PLAIN, 18));
        cbOrdenar.setBounds(131, 95, 169, 50);
        cbOrdenar.addActionListener(e -> {
            String seleccion = (String) cbOrdenar.getSelectedItem();
            int columna = 0; // Por defecto, primera columna (Nombre)
            boolean ascendente = true;

            switch (seleccion) {
                case "Nombre ⬆️":
                    columna = 0; ascendente = true; break;
                case "Nombre ⬇️":
                    columna = 0; ascendente = false; break;
                case "Fecha ⬆️":
                    columna = 1; ascendente = true; break;
                case "Fecha ⬇️":
                    columna = 1; ascendente = false; break;
            }

            ordenarTablaLocalmente(columna, ascendente);
        });
        contentPanel.add(cbOrdenar);

        txtbuscar = new JTextField();
        txtbuscar.setFont(new Font("Dialog", Font.PLAIN, 18));
        txtbuscar.setBounds(627, 95, 214, 50);
        txtbuscar.setColumns(10);
        txtbuscar.setForeground(new Color(0, 0, 0));
        contentPanel.add(txtbuscar);

        JButton btnBuscar = new JButton("");
        btnBuscar.setIcon(new ImageIcon(consulta_fichajes.class.getResource("/images/searchIcon.png")));
        btnBuscar.addActionListener(e -> {
            String textoBuscar = txtbuscar.getText().trim();
            if (textoBuscar.isEmpty() || textoBuscar.equals("Buscar por nombre")) {
            	cargarDatosEnTabla();
                return;
            }
            cargarDatosFiltrados("e.nombre", textoBuscar);
        });

        btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 18));
        btnBuscar.setBounds(851, 95, 59, 50);
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
        btnCerrar.setBounds(856, 416, 117, 39);
        contentPanel.add(btnCerrar);
        
        JLabel lblIntroduceUnNombre_1 = new JLabel("Introduce un nombre de empleado: ");
        lblIntroduceUnNombre_1.setFont(new Font("Dialog", Font.BOLD, 18));
        lblIntroduceUnNombre_1.setBounds(310, 95, 331, 50);
        contentPanel.add(lblIntroduceUnNombre_1);

        cargarDatosEnTabla(); // Carga inicial
    }

    private void cargarDatosEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);

        List<Object[]> fichajes = fichajesDAO.listarFichajesConNombreEmpleado();

        for (Object[] fila : fichajes) {
            modelo.addRow(fila);
        }
    }

    private void cargarDatosFiltrados(String campo, String valor) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);

        List<Object[]> fichajes = fichajesDAO.buscarFichajesPorCriterio(campo, valor);

        for (Object[] fila : fichajes) {
            modelo.addRow(fila);
        }
    }

    private void ordenarTablaLocalmente(int columna, boolean ascendente) {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        List<Object[]> filas = new ArrayList<>();

        // Extraer filas visibles
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                fila[j] = modelo.getValueAt(i, j);
            }
            filas.add(fila);
        }

        // Ordenar filas localmente
        filas.sort((fila1, fila2) -> {
            Comparable valor1 = (Comparable) fila1[columna];
            Comparable valor2 = (Comparable) fila2[columna];
            return ascendente ? valor1.compareTo(valor2) : valor2.compareTo(valor1);
        });

        // Actualizar tabla con filas ordenadas
        modelo.setRowCount(0); // Limpiar tabla
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }
}
