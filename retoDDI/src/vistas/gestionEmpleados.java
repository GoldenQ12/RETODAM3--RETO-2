package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.conexion;
import modeloDAO.TiempoMunicipio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class gestionEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtfecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			gestionEmpleados dialog = new gestionEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public gestionEmpleados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(gestionEmpleados.class.getResource("/images/icon.png")));
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 890, 533);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(249, 220, 92));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGestinDeEmpleados = new JLabel("GESTIÓN DE EMPLEADOS");
			lblGestinDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestinDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
			lblGestinDeEmpleados.setBounds(0, 0, 856, 69);
			contentPanel.add(lblGestinDeEmpleados);
		}
		{
			JButton btnNewButton = new JButton("REGISTRAR EMPLEADO");
			btnNewButton.setBackground(new Color(128, 255, 0));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					alta_empleados alta_empleados = new alta_empleados();
					alta_empleados.setVisible(true);
				}
			});
			btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			btnNewButton.setBounds(292, 128, 270, 45);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnBajaEmpleados = new JButton("ELIMINAR EMPLEADOS");
			btnBajaEmpleados.setBackground(new Color(128, 255, 0));
			btnBajaEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					baja_empleados baja_empleados = new baja_empleados();
					baja_empleados.setVisible(true);
				}
			});
			btnBajaEmpleados.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			btnBajaEmpleados.setBounds(572, 128, 274, 45);
			contentPanel.add(btnBajaEmpleados);
		}
		{
			JButton btnModificarempleados = new JButton("MODIFICAR EMPLEADOS");
			btnModificarempleados.setBackground(new Color(128, 255, 0));
			btnModificarempleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificar_empleados modificar_empleados = new modificar_empleados();
					modificar_empleados.setVisible(true);
				}
			});
			btnModificarempleados.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			btnModificarempleados.setBounds(10, 128, 272, 45);
			contentPanel.add(btnModificarempleados);
		}
		{
			JButton btnConsultarEmpleados = new JButton("CONSULTAR EMPLEADOS");
			btnConsultarEmpleados.setBackground(new Color(128, 255, 0));
			btnConsultarEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					consulta_empleados consulta_empleados = new consulta_empleados();
					consulta_empleados.setVisible(true);
				}
			});
			btnConsultarEmpleados.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
			btnConsultarEmpleados.setBounds(10, 72, 390, 45);
			contentPanel.add(btnConsultarEmpleados);
		}
		
		JButton btnConsultarFichajes = new JButton("CONSULTAR FICHAJES");
		btnConsultarFichajes.setBackground(new Color(128, 255, 0));
		btnConsultarFichajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta_fichajes consulta_fichajes = new consulta_fichajes();
				consulta_fichajes.setVisible(true);
			}
		});
		btnConsultarFichajes.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		btnConsultarFichajes.setBounds(410, 72, 436, 45);
		contentPanel.add(btnConsultarFichajes);
		
		JButton btnInformeDeCargas = new JButton("GENERAR INFORME A TRAVÉS DE FECHA");
		btnInformeDeCargas.setBackground(new Color(128, 128, 192));
		btnInformeDeCargas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = txtfecha.getText();
                if (!fecha.matches("\\d{4}-\\d{2}") || fecha.isEmpty()) { // Verifica el formato
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha válida en formato YYYY-MM", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
				// Abre el visor de Jasper Reports que nos permite descargarlo
                JasperPrint jasperPrintWindow = null;
				
				try {
					System.setProperty("net.sf.jasperreports.debug", "true");

					Map <String, Object> parametros = new HashMap();
					
					String partes[] = fecha.split("-");
					
					
					parametros.put ("anio", (Object) Integer.parseInt(partes[0]));
					parametros.put("mes", Integer.parseInt(partes[1]));
				    jasperPrintWindow = JasperFillManager.fillReport("src\\informes\\InformeCargaHoraria.jasper", parametros, conexion.getInstancia().getCon());
				} catch (JRException e1) {
				    System.err.println("Error al generar el informe: " + e1.getMessage());
				}

				/*
				 * Abrimos el visor y le pasamos el informe generado antes.
				 * Lo abrimos con la opción de 2 parámetros porque el 2º, que hemos puesto a false,
				 * indica cerrar la aplicación cuando se cierra el visor (por defecto true)
				 */
				 JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow,false);
				 jasperViewer.setVisible(true); 
			}
		});
		btnInformeDeCargas.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		btnInformeDeCargas.setBounds(209, 270, 488, 69);
		contentPanel.add(btnInformeDeCargas);
		
		txtfecha = new JTextField();
		txtfecha.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtfecha.setBounds(287, 232, 410, 27);
		contentPanel.add(txtfecha);
		txtfecha.setColumns(10);
		{
			JLabel lblNewLabel = new JLabel("Fecha:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(209, 232, 147, 27);
			contentPanel.add(lblNewLabel);
		}
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 24));
		btnCerrar.setBackground(new Color(231, 24, 24));
		btnCerrar.setBounds(747, 444, 117, 39);
		contentPanel.add(btnCerrar);
		{
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(gestionEmpleados.class.getResource("/images/icon.png")));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(0, 329, 160, 165);
			contentPanel.add(lblNewLabel_2);
		}
	}
}
