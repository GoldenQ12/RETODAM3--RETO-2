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
import javax.swing.DefaultComboBoxModel;

public class modificar_empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtnom;
	private JTextField txtapel;
	private JTextField txtemail;
	private JTextField txtnum;
	private JTextField txtpass;
	private JTextField txtcod;
	private JComboBox cbCargo;
	private JCheckBox  chckEstado;
	private JLabel lblestadob;
	private static empleadosDAO empe = new empleadosDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			modificar_empleados dialog = new modificar_empleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public modificar_empleados() {
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 772, 549);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setBackground(new Color(249, 220, 92));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JLabel lblModificarDeEmpleados = new JLabel("MODIFICAR EMPLEADOS");
				lblModificarDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
				lblModificarDeEmpleados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
				lblModificarDeEmpleados.setBounds(0, 0, 758, 87);
				contentPanel.add(lblModificarDeEmpleados);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblNombre.setBounds(63, 145, 71, 30);
				contentPanel.add(lblNombre);
			}
			{
				txtnom = new JTextField();
				txtnom.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtnom.setColumns(10);
				txtnom.setBounds(137, 148, 153, 30);
				contentPanel.add(txtnom);
			}
			{
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblApellido.setBounds(63, 205, 71, 30);
				contentPanel.add(lblApellido);
			}
			{
				txtapel = new JTextField();
				txtapel.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtapel.setColumns(10);
				txtapel.setBounds(137, 208, 153, 30);
				contentPanel.add(txtapel);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblEmail.setBounds(63, 260, 63, 30);
				contentPanel.add(lblEmail);
			}
			{
				txtemail = new JTextField();
				txtemail.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtemail.setColumns(10);
				txtemail.setBounds(137, 263, 213, 30);
				contentPanel.add(txtemail);
			}
			{
				JLabel lblNTelfono = new JLabel("Nº Teléfono:");
				lblNTelfono.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblNTelfono.setBounds(392, 151, 105, 30);
				contentPanel.add(lblNTelfono);
			}
			{
				txtnum = new JTextField();
				txtnum.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtnum.setColumns(10);
				txtnum.setBounds(507, 148, 213, 30);
				contentPanel.add(txtnum);
			}
			{
				JLabel lblContrasea = new JLabel("Contraseña:");
				lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblContrasea.setBounds(392, 208, 105, 30);
				contentPanel.add(lblContrasea);
			}
			{
				txtpass = new JTextField();
				txtpass.setFont(new Font("Dialog", Font.PLAIN, 18));
				txtpass.setColumns(10);
				txtpass.setBounds(507, 205, 213, 30);
				contentPanel.add(txtpass);
			}
			{
				JLabel lblCargo = new JLabel("Cargo:");
				lblCargo.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblCargo.setBounds(392, 260, 63, 30);
				contentPanel.add(lblCargo);
			}
			{
				cbCargo = new JComboBox();
				cbCargo.setModel(new DefaultComboBoxModel(new String[] {"gerente", "asesor", "camarero", "bartender"}));
				cbCargo.setFont(new Font("Dialog", Font.PLAIN, 18));
				cbCargo.setBounds(507, 261, 176, 28);
				contentPanel.add(cbCargo);
			}
			{
				JButton btnBuscar = new JButton("BUSCAR");
				btnBuscar.setForeground(new Color(128, 128, 255));
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblestadob.setText(chckEstado.isSelected() ? "Activo" : "Inactivo");
						if (txtcod.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor, introduzca el código del empleado que quiere buscar.", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
						
						int cod = Integer.parseInt(txtcod.getText());
						
						empleadosDTO emp = empe.buscar(cod);
						if (emp != null) {
							txtnom.setText(emp.getNombre());
							txtapel.setText(emp.getApellidos());
							txtnum.setText(emp.getTelefono());
							txtemail.setText(emp.getEmail());
							cbCargo.setSelectedItem(emp.getCargo());;
							txtpass.setText(emp.getPassword());
							chckEstado.setEnabled(true);
							
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
				btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
				btnBuscar.setActionCommand("Cancel");
				btnBuscar.setBounds(220, 397, 169, 57);
				contentPanel.add(btnBuscar);
			}
			
			{
				JLabel lblCod = new JLabel("Código:");
				lblCod.setFont(new Font("Dialog", Font.PLAIN, 18));
				lblCod.setBounds(292, 91, 71, 30);
				contentPanel.add(lblCod);
			}
			{
				txtcod = new JTextField();
				txtcod.setFont(new Font("Tahoma", Font.PLAIN, 18));
				txtcod.setColumns(10);
				txtcod.setBounds(373, 97, 84, 30);
				contentPanel.add(txtcod);
			}
			
			{
				lblestadob = new JLabel("");
				lblestadob.setForeground(new Color(255, 255, 255));
				lblestadob.setOpaque(true);
				lblestadob.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblestadob.setEnabled(false);
				lblestadob.setBackground(new Color(98, 98, 98));
				lblestadob.setBounds(137, 306, 140, 30);
				contentPanel.add(lblestadob);
			}
			{
				chckEstado = new JCheckBox("Estado");
				chckEstado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				chckEstado.setHorizontalAlignment(SwingConstants.LEFT);
				chckEstado.setForeground(Color.BLACK);
				chckEstado.setFont(new Font("Dialog", Font.PLAIN, 18));
				chckEstado.setBackground(new Color(249, 220, 92));
				chckEstado.setBounds(40, 306, 94, 30);
				contentPanel.add(chckEstado);
			}
			{
				JButton btnMod = new JButton("MODIFICAR");
				btnMod.setForeground(new Color(128, 255, 0));
				btnMod.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtnom.getText().isEmpty() || txtapel.getText().isEmpty() || txtemail.getText().isEmpty() || cbCargo.getSelectedItem() == null || txtpass.getText().isEmpty() || txtnum.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor, busque un empleado para modificarlo", "Error", JOptionPane.ERROR_MESSAGE);
					        return;
					    }
						
						int cod = Integer.parseInt(txtcod.getText());
						String nom = txtnom.getText();
						String apel = txtapel.getText();
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
					    String pass = txtpass.getText();
					    String cargo = (String) cbCargo.getSelectedItem();
						String estado;
						if (chckEstado.isSelected()) {
							estado = "activo";
						}else {
							estado = "inactivo";
						}
					    
						empleadosDTO emp = new empleadosDTO(cod, nom, apel, email, num, cargo, estado, pass);
						
						boolean actualizado = empe.actualizar(emp);

				        if (actualizado) {
				            JOptionPane.showMessageDialog(null, "Empleado modificado exitosamente.");
				            txtcod.setText(null);
				            txtnom.setText(null);
				            txtapel.setText(null);
				            txtemail.setText(null);
				            txtpass.setText(null);
				            txtnum.setText(null);
				            chckEstado.setSelected(false);
				            chckEstado.setEnabled(false);
				        } else {
				            JOptionPane.showMessageDialog(null, "Hubo un error al modificar el empleado.");
				        }
					}
				});
				btnMod.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
				btnMod.setActionCommand("Cancel");
				btnMod.setBounds(399, 397, 182, 57);
				contentPanel.add(btnMod);
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
				btnCerrar.setBounds(629, 460, 117, 39);
				contentPanel.add(btnCerrar);
			}
		}
	}

}
