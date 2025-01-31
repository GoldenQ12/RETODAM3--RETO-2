package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

import modeloDAO.empleadosDAO;
import modeloDTO.empleadosDTO;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtemail;
	static empleadosDAO empleadoDAO = new empleadosDAO();
	private JPasswordField pfPass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioSesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InicioSesion.class.getResource("/images/icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 709, 584);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(249, 220, 92));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO DE SESIÓN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 0, 388, 165);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(135, 176, 82, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(135, 238, 177, 33);
		contentPane.add(lblNewLabel_1_1);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtemail.setBounds(265, 181, 258, 28);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.setBackground(new Color(128, 128, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtemail.getText();
				String pass = pfPass.getText();
				ArrayList<empleadosDTO> lista = new ArrayList<empleadosDTO>();
				
				if (txtemail.getText().isEmpty() || pfPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }
				
				lista = empleadoDAO.listarTodos();
				for(empleadosDTO emp : lista) {
					if (emp.getEmail().equals(email) && emp.getPassword().equals(pass)) {
						if (empleadoDAO.obtenerCargoPorEmailYPassword(email, pass).equals("gerente")) {
							gestionEmpleados gestionEmpleados = new gestionEmpleados();
							gestionEmpleados.setVisible(true);
						}else {
							gestionFichajes gestionFichajes = new gestionFichajes(email);
							gestionFichajes.setVisible(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Contraseña o usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		btnNewButton.setBounds(135, 340, 388, 98);
		contentPane.add(btnNewButton);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 0, 0));
		btnCerrar.setBackground(new Color(231, 24, 24));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(
						null, 
						"Estas seguro que quieres salir", 
						"Confirmación", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
						dispose();
					} 
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 24));
		btnCerrar.setBounds(566, 495, 117, 39);
		contentPane.add(btnCerrar);
		
		pfPass = new JPasswordField();
		pfPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pfPass.setBounds(265, 242, 258, 30);
		contentPane.add(pfPass);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(InicioSesion.class.getResource("/images/icon.png")));
		lblNewLabel_2.setBounds(533, 0, 160, 165);
		contentPane.add(lblNewLabel_2);
		
		JCheckBox cbShowPassword = new JCheckBox("Mostrar contraseña");
		cbShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pfPass.setEchoChar(cbShowPassword.isSelected() ? (char) 0 : '●');
			}
		});
		cbShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbShowPassword.setBackground(new Color(249, 220, 92));
		cbShowPassword.setBounds(135, 288, 226, 23);
		contentPane.add(cbShowPassword);
	}
}
