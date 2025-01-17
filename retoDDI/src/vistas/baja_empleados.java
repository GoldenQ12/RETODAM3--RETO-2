package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDAO.empleadosDAO;
import modeloDTO.empleadosDTO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class baja_empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private static JTextField txtcod;
	private static JLabel lblnomb, lblapelb, lblemailb, lblpassb, lblnumb, lblcargob, lblestadob, lblfechab;
	private static empleadosDAO empe = new empleadosDAO();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			baja_empleados dialog = new baja_empleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public baja_empleados() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 731, 541);
		{
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setBackground(new Color(249, 220, 92));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JLabel lblBajaDeEmpleados = new JLabel("BAJA DE EMPLEADOS");
				lblBajaDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
				lblBajaDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblBajaDeEmpleados.setBounds(186, 0, 398, 87);
				contentPanel.add(lblBajaDeEmpleados);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(null);
				buttonPane.setBackground(new Color(249, 220, 92));
				buttonPane.setBounds(0, 464, 720, 40);
				contentPanel.add(buttonPane);
				{
					JButton cancelButton = new JButton("Cerrar");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
					cancelButton.setActionCommand("Cancel");
					cancelButton.setBounds(612, 0, 84, 33);
					buttonPane.add(cancelButton);
				}
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNombre.setBounds(48, 154, 71, 30);
				contentPanel.add(lblNombre);
			}
			{
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblApellido.setBounds(48, 214, 71, 30);
				contentPanel.add(lblApellido);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEmail.setBounds(48, 269, 63, 30);
				contentPanel.add(lblEmail);
			}
			{
				JLabel lblNTelfono = new JLabel("Nº Teléfono:");
				lblNTelfono.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNTelfono.setBounds(377, 160, 105, 30);
				contentPanel.add(lblNTelfono);
			}
			{
				JLabel lblContrasea = new JLabel("Contraseña:");
				lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblContrasea.setBounds(377, 217, 105, 30);
				contentPanel.add(lblContrasea);
			}
			{
				JLabel lblCargo = new JLabel("Cargo:");
				lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCargo.setBounds(377, 269, 63, 30);
				contentPanel.add(lblCargo);
			}
			
			{
				lblnomb = new JLabel("");
				lblnomb.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblnomb.setBackground(new Color(50, 205, 50));
				lblnomb.setOpaque(true);
				lblnomb.setBounds(129, 157, 205, 30);
				contentPanel.add(lblnomb);
			}
			{
				lblapelb = new JLabel("");
				lblapelb.setOpaque(true);
				lblapelb.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblapelb.setBackground(new Color(50, 205, 50));
				lblapelb.setBounds(129, 214, 205, 30);
				contentPanel.add(lblapelb);
			}
			{
				lblemailb = new JLabel("");
				lblemailb.setOpaque(true);
				lblemailb.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblemailb.setBackground(new Color(50, 205, 50));
				lblemailb.setBounds(104, 269, 230, 30);
				contentPanel.add(lblemailb);
			}
			{
				lblnumb = new JLabel("");
				lblnumb.setOpaque(true);
				lblnumb.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblnumb.setBackground(new Color(50, 205, 50));
				lblnumb.setBounds(481, 157, 205, 30);
				contentPanel.add(lblnumb);
			}
			{
				lblpassb = new JLabel("");
				lblpassb.setOpaque(true);
				lblpassb.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblpassb.setBackground(new Color(50, 205, 50));
				lblpassb.setBounds(481, 214, 205, 30);
				contentPanel.add(lblpassb);
			}
			{
				lblcargob = new JLabel("");
				lblcargob.setOpaque(true);
				lblcargob.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblcargob.setBackground(new Color(50, 205, 50));
				lblcargob.setBounds(481, 269, 205, 30);
				contentPanel.add(lblcargob);
			}
			{
				txtcod = new JTextField();
				txtcod.setColumns(10);
				txtcod.setBounds(356, 87, 84, 30);
				contentPanel.add(txtcod);
			}
			
			{
				JLabel lblCod = new JLabel("Código:");
				lblCod.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCod.setBounds(263, 84, 71, 30);
				contentPanel.add(lblCod);
			}
			lblestadob = new JLabel("");
			lblestadob.setOpaque(true);
			lblestadob.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblestadob.setBackground(new Color(50, 205, 50));
			lblestadob.setBounds(166, 332, 140, 30);
			contentPanel.add(lblestadob);
			
			lblfechab = new JLabel("");
			lblfechab.setOpaque(true);
			lblfechab.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblfechab.setBackground(new Color(50, 205, 50));
			lblfechab.setBounds(481, 332, 205, 30);
			contentPanel.add(lblfechab);

			JCheckBox chckEstado = new JCheckBox("");
			chckEstado.setEnabled(false);
			chckEstado.setBounds(118, 341, 21, 21);
			contentPanel.add(chckEstado);
			
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtcod.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor, introduzca el código del empleado que quiere buscar.", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
						
						int cod = Integer.parseInt(txtcod.getText());
						
						empleadosDTO emp = empe.buscar(cod);
						if (emp != null) {
							lblnomb.setText(emp.getNombre());
							lblapelb.setText(emp.getApellidos());
							lblnumb.setText(emp.getTelefono());
							lblemailb.setText(emp.getEmail());
							lblfechab.setText(emp.getFechaAlta().toString());
							lblcargob.setText(emp.getCargo());
							lblestadob.setText(emp.getEstado());
							lblpassb.setText(emp.getPassword());
							
							if (emp.getEstado().equals("activo")) {
							    chckEstado.setSelected(true);
							} else {
							    chckEstado.setSelected(false);
							}
						}else {
							JOptionPane.showMessageDialog(null, "El empleado con dicho codigo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnBuscar.setActionCommand("Cancel");
				btnBuscar.setBounds(222, 393, 84, 33);
				contentPanel.add(btnBuscar);
			}
			{
				JButton btnBaja = new JButton("Baja");
				btnBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (lblnomb.getText().isEmpty() || lblapelb.getText().isEmpty() || lblemailb.getText().isEmpty() || lblnumb.getText().isEmpty() || lblcargob.getText().isEmpty() || lblfechab.getText().isEmpty() || lblestadob.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningún empleado que se pueda eliminar", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
						
						empleadosDAO emp = new empleadosDAO();
						int cod = Integer.parseInt(txtcod.getText());
						if (emp.borrar(cod)) {
							JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.");
							txtcod.setText(null);
							lblnomb.setText(null);
							lblapelb.setText(null);
							lblemailb.setText(null);
							lblpassb.setText(null);
							lblnumb.setText(null);
							lblestadob.setText(null);
							lblcargob.setText(null);
							lblfechab.setText(null);
							chckEstado.setSelected(false);
						}
					}
				});
				btnBaja.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnBaja.setActionCommand("Cancel");
				btnBaja.setBounds(435, 393, 84, 33);
				contentPanel.add(btnBaja);
			}
			
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblEstado.setBounds(48, 332, 53, 30);
			contentPanel.add(lblEstado);
			
			JLabel lblFechaDeAlta = new JLabel("Fecha de alta:");
			lblFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblFechaDeAlta.setBounds(377, 332, 105, 30);
			contentPanel.add(lblFechaDeAlta);		
		}
	}
}
