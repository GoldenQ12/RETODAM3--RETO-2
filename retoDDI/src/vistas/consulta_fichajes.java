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
        setBounds(100, 100, 999, 473);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(249, 220, 92));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(null);
        buttonPane.setBackground(new Color(249, 220, 92));
        buttonPane.setBounds(0, 395, 985, 41);
        contentPanel.add(buttonPane);

        JButton cancelButton = new JButton("Cerrar");
        cancelButton.addActionListener(e -> dispose());
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cancelButton.setBounds(874, 0, 84, 33);
        buttonPane.add(cancelButton);

        JLabel lblConsultarFichjes = new JLabel("CONSULTAR FICHAJES");
        lblConsultarFichjes.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsultarFichjes.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblConsultarFichjes.setBounds(298, 10, 398, 73);
        contentPanel.add(lblConsultarFichjes);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 128, 965, 249);
        contentPanel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Empleado", "Fecha", "Hora entrada", "Hora salida", "Modo"}
        ));
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Ordenar por:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(147, 85, 94, 25);
        contentPanel.add(lblNewLabel);

        JComboBox<String> cbOrdenar = new JComboBox<>();
        cbOrdenar.setModel(new DefaultComboBoxModel<>(new String[] {"Nombre ⬆️", "Nombre ⬇️", "Fecha ⬆️", "Fecha ⬇️"}));
        cbOrdenar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        cbOrdenar.setBounds(250, 85, 169, 25);
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
        txtbuscar.setBounds(482, 82, 214, 35);
        txtbuscar.setColumns(10);
        txtbuscar.setText("Buscar por nombre");
        txtbuscar.setForeground(Color.GRAY);
        txtbuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtbuscar.getText().equals("Buscar por nombre")) {
                    txtbuscar.setText("");
                    txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtbuscar.getText().isEmpty()) {
                    txtbuscar.setText("Buscar por nombre");
                    txtbuscar.setForeground(Color.GRAY);
                }
            }
        });
        contentPanel.add(txtbuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
            String textoBuscar = txtbuscar.getText().trim();
            if (textoBuscar.isEmpty() || textoBuscar.equals("Buscar por nombre")) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un texto para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cargarDatosFiltrados("e.nombre", textoBuscar);
        });

        btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBuscar.setBounds(719, 81, 84, 33);
        contentPanel.add(btnBuscar);

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
