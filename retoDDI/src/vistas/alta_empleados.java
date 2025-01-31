package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDTO.empleadosDTO;
import modeloDAO.empleadosDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class alta_empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnom;
	private JTextField txtapel;
	private JTextField txtemail;
	private JTextField txtnum;
	private JTextField txtpass;
	private static empleadosDAO empleadosDAO = new empleadosDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			alta_empleados dialog = new alta_empleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public alta_empleados() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 734, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(249, 220, 92));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGestinDeEmpleados = new JLabel("REGISTRAR EMPLEADO");
			lblGestinDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestinDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
			lblGestinDeEmpleados.setBounds(0, 0, 720, 87);
			contentPanel.add(lblGestinDeEmpleados);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblNombre.setBounds(53, 94, 71, 30);
			contentPanel.add(lblNombre);
		}
		{
			txtnom = new JTextField();
			txtnom.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtnom.setColumns(10);
			txtnom.setBounds(127, 97, 153, 30);
			contentPanel.add(txtnom);
		}
		{
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblApellido.setBounds(53, 154, 71, 30);
			contentPanel.add(lblApellido);
		}
		{
			txtapel = new JTextField();
			txtapel.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtapel.setColumns(10);
			txtapel.setBounds(127, 157, 153, 30);
			contentPanel.add(txtapel);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblEmail.setBounds(53, 209, 63, 30);
			contentPanel.add(lblEmail);
		}
		{
			txtemail = new JTextField();
			txtemail.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtemail.setColumns(10);
			txtemail.setBounds(127, 212, 213, 30);
			contentPanel.add(txtemail);
		}
		{
			JLabel lblNTelfono = new JLabel("Nº Teléfono:");
			lblNTelfono.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblNTelfono.setBounds(382, 100, 105, 30);
			contentPanel.add(lblNTelfono);
		}
		{
			txtnum = new JTextField();
			txtnum.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtnum.setColumns(10);
			txtnum.setBounds(497, 97, 213, 30);
			contentPanel.add(txtnum);
		}
		{
			JLabel lblContrasea = new JLabel("Contraseña:");
			lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblContrasea.setBounds(382, 157, 105, 30);
			contentPanel.add(lblContrasea);
		}
		{
			txtpass = new JTextField();
			txtpass.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtpass.setColumns(10);
			txtpass.setBounds(497, 154, 213, 30);
			contentPanel.add(txtpass);
		}
		{
			JLabel lblCargo = new JLabel("Cargo:");
			lblCargo.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblCargo.setBounds(382, 209, 63, 30);
			contentPanel.add(lblCargo);
		}
		
		JComboBox cbCargo = new JComboBox();
		cbCargo.setFont(new Font("Dialog", Font.PLAIN, 18));
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"gerente", "asesor", "camarero", "bartender"}));
		cbCargo.setBounds(497, 210, 176, 28);
		contentPanel.add(cbCargo);
		{
			JButton btnAlta = new JButton("REGISTRAR");
			btnAlta.setForeground(new Color(128, 255, 0));
			btnAlta.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        if (txtnom.getText().isEmpty() || txtapel.getText().isEmpty() || txtemail.getText().isEmpty() || cbCargo.getSelectedItem() == null || txtpass.getText().isEmpty() || txtnum.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        String email = txtemail.getText();
			        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
			        if (!email.matches(emailRegex)) {
			            JOptionPane.showMessageDialog(null, "El email no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        String num = txtnum.getText();
			        String numRegex = "\\d{9,12}";
			        if (!num.matches(numRegex)) {
			            JOptionPane.showMessageDialog(null, "El número de teléfono debe contener entre 9 y 12 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        try {
			            String nom = txtnom.getText();
			            String apel = txtapel.getText();
			            String cargo = (String) cbCargo.getSelectedItem();
			            Date fechaActual = new Date(System.currentTimeMillis());
			            String estado = "activo";
			            String pass = txtpass.getText();

			            empleadosDTO emp = new empleadosDTO(0, nom, apel, email, num, cargo, fechaActual, estado, pass);

			            if (empleadosDAO.insertar(emp)) {
			                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
			            }

			            txtnom.setText(null);
			            txtapel.setText(null);
			            txtemail.setText(null);
			            txtpass.setText(null);
			            txtnum.setText(null);
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Error en el formato de los datos. Verifique los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});

			btnAlta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
			btnAlta.setActionCommand("Cancel");
			btnAlta.setBounds(262, 270, 201, 75);
			contentPanel.add(btnAlta);
		}
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
			btnCerrar.setBounds(591, 360, 117, 39);
			contentPanel.add(btnCerrar);
		}
	}
}
