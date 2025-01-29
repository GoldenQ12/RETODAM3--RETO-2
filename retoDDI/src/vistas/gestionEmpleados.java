package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

<<<<<<< Updated upstream
=======
import conexion.conexion;
import modeloDAO.TiempoMunicipio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

>>>>>>> Stashed changes
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class gestionEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			gestionEmpleados dialog = new gestionEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public gestionEmpleados() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 799, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(249, 220, 92));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 406, 785, 38);
			contentPanel.add(buttonPane);
			buttonPane.setBackground(new Color(249, 220, 92));
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setBounds(676, 0, 84, 33);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel lblGestinDeEmpleados = new JLabel("GESTIÓN DE EMPLEADOS");
			lblGestinDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestinDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblGestinDeEmpleados.setBounds(217, 10, 398, 87);
			contentPanel.add(lblGestinDeEmpleados);
		}
		{
			JButton btnNewButton = new JButton("Alta empleados");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					alta_empleados alta_empleados = new alta_empleados();
					alta_empleados.setVisible(true);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton.setBounds(121, 148, 189, 45);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnBajaEmpleados = new JButton("Baja empleados");
			btnBajaEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					baja_empleados baja_empleados = new baja_empleados();
					baja_empleados.setVisible(true);
				}
			});
			btnBajaEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnBajaEmpleados.setBounds(512, 148, 189, 45);
			contentPanel.add(btnBajaEmpleados);
		}
		{
			JButton btnModificarempleados = new JButton("Modificar empleados");
			btnModificarempleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificar_empleados modificar_empleados = new modificar_empleados();
					modificar_empleados.setVisible(true);
				}
			});
			btnModificarempleados.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnModificarempleados.setBounds(121, 264, 189, 45);
			contentPanel.add(btnModificarempleados);
		}
		{
			JButton btnConsultarEmpleados = new JButton("Consultar empleados");
			btnConsultarEmpleados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					consulta_empleados consulta_empleados = new consulta_empleados();
					consulta_empleados.setVisible(true);
				}
			});
			btnConsultarEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnConsultarEmpleados.setBounds(512, 264, 189, 45);
			contentPanel.add(btnConsultarEmpleados);
<<<<<<< Updated upstream
=======
			
			TiempoMunicipio tiempo = new TiempoMunicipio(48,48020);
			tiempo.cargarDatos();
			
			{
				JLabel lblTiempo = new JLabel(tiempo.getEstadoCielo());
				lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTiempo.setBounds(99, 358, 580, 38);
				contentPanel.add(lblTiempo);
			}
>>>>>>> Stashed changes
		}
		
		JButton btnConsultarFichajes = new JButton("Consultar fichajes");
		btnConsultarFichajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta_fichajes consulta_fichajes = new consulta_fichajes();
				consulta_fichajes.setVisible(true);
			}
		});
		btnConsultarFichajes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsultarFichajes.setBounds(316, 202, 189, 45);
		contentPanel.add(btnConsultarFichajes);
		
<<<<<<< Updated upstream
		JButton btnModificarempleados = new JButton("Modificar empleados");
		btnModificarempleados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnModificarempleados.setBounds(316, 331, 189, 45);
		contentPanel.add(btnModificarempleados);
=======
		JButton btnInformeDeCargas = new JButton("Informe de cargas horarias");
		btnInformeDeCargas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre el visor de Jasper Reports que nos permite descargarlo
				System.setProperty("net.sf.jasperreports.debug", "true");

				JasperPrint jasperPrintWindow = null;
				try {
				    jasperPrintWindow = JasperFillManager.fillReport("src\\informes\\InformeCargaHoraria.jasper", null, conexion.getInstancia().getCon());
				} catch (JRException e1) {
				    System.err.println("Error al generar el informe: " + e1.getMessage());
				    e1.printStackTrace();
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
		btnInformeDeCargas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInformeDeCargas.setBounds(290, 321, 242, 27);
		contentPanel.add(btnInformeDeCargas);
>>>>>>> Stashed changes
	}
}
