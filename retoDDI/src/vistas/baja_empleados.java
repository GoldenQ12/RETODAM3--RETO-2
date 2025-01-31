package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDAO.empleadosDAO;
import modeloDAO.fichajesDAO;
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
	private static fichajesDAO fich = new fichajesDAO();
	
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
				lblBajaDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
				lblBajaDeEmpleados.setBounds(0, 0, 720, 87);
				contentPanel.add(lblBajaDeEmpleados);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblNombre.setBounds(32, 154, 84, 30);
				contentPanel.add(lblNombre);
			}
			{
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblApellido.setBounds(32, 214, 84, 30);
				contentPanel.add(lblApellido);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblEmail.setBounds(32, 269, 71, 30);
				contentPanel.add(lblEmail);
			}
			{
				JLabel lblNTelfono = new JLabel("Nº Teléfono:");
				lblNTelfono.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblNTelfono.setBounds(361, 160, 121, 30);
				contentPanel.add(lblNTelfono);
			}
			{
				JLabel lblContrasea = new JLabel("Contraseña:");
				lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblContrasea.setBounds(361, 217, 121, 30);
				contentPanel.add(lblContrasea);
			}
			{
				JLabel lblCargo = new JLabel("Cargo:");
				lblCargo.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblCargo.setBounds(361, 269, 121, 30);
				contentPanel.add(lblCargo);
			}
			
			{
				lblnomb = new JLabel("");
				lblnomb.setEnabled(false);
				lblnomb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblnomb.setBackground(new Color(98, 98, 98));
				lblnomb.setOpaque(true);
				lblnomb.setBounds(129, 157, 205, 30);
				contentPanel.add(lblnomb);
			}
			{
				lblapelb = new JLabel("");
				lblapelb.setEnabled(false);
				lblapelb.setOpaque(true);
				lblapelb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblapelb.setBackground(new Color(98, 98, 98));
				lblapelb.setBounds(129, 214, 205, 30);
				contentPanel.add(lblapelb);
			}
			{
				lblemailb = new JLabel("");
				lblemailb.setEnabled(false);
				lblemailb.setOpaque(true);
				lblemailb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblemailb.setBackground(new Color(98, 98, 98));
				lblemailb.setBounds(129, 269, 225, 30);
				contentPanel.add(lblemailb);
			}
			{
				lblnumb = new JLabel("");
				lblnumb.setEnabled(false);
				lblnumb.setOpaque(true);
				lblnumb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblnumb.setBackground(new Color(98, 98, 98));
				lblnumb.setBounds(481, 157, 205, 30);
				contentPanel.add(lblnumb);
			}
			{
				lblpassb = new JLabel("");
				lblpassb.setEnabled(false);
				lblpassb.setOpaque(true);
				lblpassb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblpassb.setBackground(new Color(98, 98, 98));
				lblpassb.setBounds(481, 214, 205, 30);
				contentPanel.add(lblpassb);
			}
			{
				lblcargob = new JLabel("");
				lblcargob.setEnabled(false);
				lblcargob.setOpaque(true);
				lblcargob.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblcargob.setBackground(new Color(98, 98, 98));
				lblcargob.setBounds(481, 269, 205, 30);
				contentPanel.add(lblcargob);
			}
			{
				txtcod = new JTextField();
				txtcod.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtcod.setColumns(10);
				txtcod.setBounds(304, 113, 126, 30);
				contentPanel.add(txtcod);
			}
			
			{
				JLabel lblCod = new JLabel("Código:");
				lblCod.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblCod.setBounds(304, 82, 71, 30);
				contentPanel.add(lblCod);
			}
			lblestadob = new JLabel("");
			lblestadob.setEnabled(false);
			lblestadob.setOpaque(true);
			lblestadob.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblestadob.setBackground(new Color(98, 98, 98));
			lblestadob.setBounds(129, 330, 140, 30);
			contentPanel.add(lblestadob);
			
			lblfechab = new JLabel("");
			lblfechab.setEnabled(false);
			lblfechab.setOpaque(true);
			lblfechab.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblfechab.setBackground(new Color(98, 98, 98));
			lblfechab.setBounds(481, 330, 205, 30);
			contentPanel.add(lblfechab);

			JCheckBox chckEstado = new JCheckBox("Estado");
			chckEstado.setHorizontalAlignment(SwingConstants.LEFT);
			chckEstado.setForeground(new Color(0, 0, 0));
			chckEstado.setBackground(new Color(249, 220, 92));
			chckEstado.setFont(new Font("Dialog", Font.PLAIN, 18));
			chckEstado.setBounds(13, 330, 110, 30);
			contentPanel.add(chckEstado);
			
			{
				JButton btnBuscar = new JButton("BUSCAR");
				btnBuscar.setForeground(new Color(128, 128, 255));
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
				btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 24));
				btnBuscar.setActionCommand("Cancel");
				btnBuscar.setBounds(197, 393, 157, 60);
				contentPanel.add(btnBuscar);
			}
			{
				JButton btnBaja = new JButton("ELIMINAR");
				btnBaja.setForeground(new Color(255, 0, 0));
				btnBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (lblnomb.getText().isEmpty() || lblapelb.getText().isEmpty() || lblemailb.getText().isEmpty() || lblnumb.getText().isEmpty() || lblcargob.getText().isEmpty() || lblfechab.getText().isEmpty() || lblestadob.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No hay ningún empleado que se pueda eliminar", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
						
						empleadosDAO emp = new empleadosDAO();
						int cod = Integer.parseInt(txtcod.getText());
						if (fich.borrarEmp(cod) && emp.borrar(cod)) {
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
				btnBaja.setFont(new Font("Tahoma", Font.BOLD, 24));
				btnBaja.setActionCommand("Cancel");
				btnBaja.setBounds(361, 393, 178, 60);
				contentPanel.add(btnBaja);
			}
			
			JLabel lblFechaDeAlta = new JLabel("Fecha de alta:");
			lblFechaDeAlta.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblFechaDeAlta.setBounds(361, 330, 121, 30);
			contentPanel.add(lblFechaDeAlta);		
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setForeground(Color.BLACK);
				btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 24));
				btnCerrar.setBackground(new Color(231, 24, 24));
				btnCerrar.setBounds(588, 452, 117, 39);
				contentPanel.add(btnCerrar);
			}
		}
	}
}
