package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDAO.empleadosDAO;
import modeloDTO.empleadosDTO;
import modeloDTO.fichajesDTO;
import modeloDAO.fichajesDAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class gestionFichajes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static empleadosDAO emp = new empleadosDAO();
	private static fichajesDAO fichajesDAO = new fichajesDAO();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the dialog.
	 */
	public gestionFichajes(String email) {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 797, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(249, 220, 92));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 406, 783, 39);
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
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBounds(689, 0, 84, 33);
				buttonPane.add(cancelButton);
			}
		}
		{
			JLabel lblGestinDeFichajes = new JLabel("GESTIÓN DE FICHAJES");
			lblGestinDeFichajes.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestinDeFichajes.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblGestinDeFichajes.setBounds(204, 10, 398, 87);
			contentPanel.add(lblGestinDeFichajes);
		}
		JRadioButton rdbtnPresencial = new JRadioButton("Presencial");
		rdbtnPresencial.setBackground(new Color(249, 220, 92));
		rdbtnPresencial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonGroup.add(rdbtnPresencial);
		rdbtnPresencial.setBounds(187, 290, 103, 21);
		contentPanel.add(rdbtnPresencial);
		
		JRadioButton rdbtnTeletrabajo = new JRadioButton("Teletrabajo");
		rdbtnTeletrabajo.setBackground(new Color(249, 220, 92));
		rdbtnTeletrabajo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonGroup.add(rdbtnTeletrabajo);
		rdbtnTeletrabajo.setBounds(187, 313, 103, 21);
		contentPanel.add(rdbtnTeletrabajo);
		{
			JButton btnFichar = new JButton("Fichar");
			btnFichar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					empleadosDTO empe = emp.buscarPorEmail(email);
					int cod_emp = empe.getIdEmpleado();
					Date fechaActual = new Date(System.currentTimeMillis());
					Time horaActual = new Time(System.currentTimeMillis());
					String modo;
					if (rdbtnPresencial.isSelected()) {
						modo = "Presencial";
					}else if(rdbtnTeletrabajo.isSelected()) {
						modo = "Teletrabajo";
					}else {
						JOptionPane.showMessageDialog(null, "Por favor, elija el modo de su trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
					}	
					
					fichajesDTO fich = new fichajesDTO(cod_emp, fechaActual, horaActual, null, modo);
					
					if (fichajesDAO.insertar(fich)) {
						JOptionPane.showMessageDialog(null, "Bienvenido al trabajo", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
					}else {
		                JOptionPane.showMessageDialog(null, "Error al registrar el fichaje.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
				}
			});
			btnFichar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnFichar.setBounds(139, 164, 204, 106);
			contentPanel.add(btnFichar);
		}
		{
			JButton btnFinalizarFichaje = new JButton("Finalizar fichaje");
			btnFinalizarFichaje.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        empleadosDTO empleado = emp.buscarPorEmail(email);
			        int idEmpleado = empleado.getIdEmpleado();
			        Time horaSalida = new Time(System.currentTimeMillis());

			        if (fichajesDAO.finalizarFichaje(idEmpleado, horaSalida)) {
			            JOptionPane.showMessageDialog(null, "Fichaje finalizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al finalizar el fichaje. Verifique si ya está finalizado.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});

			btnFinalizarFichaje.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnFinalizarFichaje.setBounds(467, 164, 204, 106);
			contentPanel.add(btnFinalizarFichaje);
		}
	}
}
