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
	private JCheckBox chckEstado;
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
				lblModificarDeEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblModificarDeEmpleados.setBounds(186, 0, 398, 87);
				contentPanel.add(lblModificarDeEmpleados);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(null);
				buttonPane.setBackground(new Color(249, 220, 92));
				buttonPane.setBounds(0, 462, 758, 40);
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
					cancelButton.setBounds(664, 0, 84, 33);
					buttonPane.add(cancelButton);
				}
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNombre.setBounds(63, 145, 71, 30);
				contentPanel.add(lblNombre);
			}
			{
				txtnom = new JTextField();
				txtnom.setColumns(10);
				txtnom.setBounds(137, 148, 153, 30);
				contentPanel.add(txtnom);
			}
			{
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblApellido.setBounds(63, 205, 71, 30);
				contentPanel.add(lblApellido);
			}
			{
				txtapel = new JTextField();
				txtapel.setColumns(10);
				txtapel.setBounds(137, 208, 153, 30);
				contentPanel.add(txtapel);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEmail.setBounds(63, 260, 63, 30);
				contentPanel.add(lblEmail);
			}
			{
				txtemail = new JTextField();
				txtemail.setColumns(10);
				txtemail.setBounds(137, 263, 213, 30);
				contentPanel.add(txtemail);
			}
			{
				JLabel lblNTelfono = new JLabel("Nº Teléfono:");
				lblNTelfono.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNTelfono.setBounds(392, 151, 105, 30);
				contentPanel.add(lblNTelfono);
			}
			{
				txtnum = new JTextField();
				txtnum.setColumns(10);
				txtnum.setBounds(507, 148, 213, 30);
				contentPanel.add(txtnum);
			}
			{
				JLabel lblContrasea = new JLabel("Contraseña:");
				lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblContrasea.setBounds(392, 208, 105, 30);
				contentPanel.add(lblContrasea);
			}
			{
				txtpass = new JTextField();
				txtpass.setColumns(10);
				txtpass.setBounds(507, 205, 213, 30);
				contentPanel.add(txtpass);
			}
			{
				JLabel lblCargo = new JLabel("Cargo:");
				lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCargo.setBounds(392, 260, 63, 30);
				contentPanel.add(lblCargo);
			}
			{
				cbCargo = new JComboBox();
				cbCargo.setModel(new DefaultComboBoxModel(new String[] {"gerente", "asesor", "camarero", "bartender"}));
				cbCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbCargo.setBounds(507, 261, 176, 28);
				contentPanel.add(cbCargo);
			}
			{
				chckEstado = new JCheckBox("");
				chckEstado.setEnabled(false);
				chckEstado.setBounds(406, 331, 21, 21);
				contentPanel.add(chckEstado);
			}
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
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnBuscar.setActionCommand("Cancel");
				btnBuscar.setBounds(220, 397, 84, 33);
				contentPanel.add(btnBuscar);
			}
			{
				JButton btnMod = new JButton("Modificar");
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
				btnMod.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnMod.setActionCommand("Cancel");
				btnMod.setBounds(453, 397, 105, 33);
				contentPanel.add(btnMod);
			}
			{
				JLabel lblCod = new JLabel("Código:");
				lblCod.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCod.setBounds(292, 91, 71, 30);
				contentPanel.add(lblCod);
			}
			{
				txtcod = new JTextField();
				txtcod.setColumns(10);
				txtcod.setBounds(373, 97, 84, 30);
				contentPanel.add(txtcod);
			}
			{
				JLabel lblEstado = new JLabel("Estado:");
				lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEstado.setBounds(332, 322, 53, 30);
				contentPanel.add(lblEstado);
			}
		}
	}

}
