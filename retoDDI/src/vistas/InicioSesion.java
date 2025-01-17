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

public class InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtemail;
	static empleadosDAO empleadoDAO = new empleadosDAO();
	private JLabel lblError;
	private JPasswordField pfPass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
					frame.setVisible(true);
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
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 555, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(249, 220, 92));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO DE SESIÓN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(162, 10, 236, 87);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(81, 155, 81, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(81, 211, 102, 33);
		contentPane.add(lblNewLabel_1_1);
		
		txtemail = new JTextField();
		txtemail.setBounds(162, 160, 285, 28);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtemail.getText();
				String pass = pfPass.getText();
				ArrayList<empleadosDTO> lista = new ArrayList<>();
				
				if (txtemail.getText().isEmpty() || pfPass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			        return;
			    }
				
				lista = empleadoDAO.listarTodos();
				for(empleadosDTO emp : lista) {
					if (emp.getEmail().equals(email) && emp.getPassword().equals(pass)) {
						if (empleadoDAO.obtenerCargoPorEmailYPassword(email, pass).equals("gerente")) {
							lblError.setText(null);
							gestionEmpleados gestionEmpleados = new gestionEmpleados();
							gestionEmpleados.setVisible(true);
						}else {
							lblError.setText(null);
							
							gestionFichajes gestionFichajes = new gestionFichajes(email);
							gestionFichajes.setVisible(true);
						}
					}else {
						txtemail.setText(null);
						pfPass.setText(null);
						lblError.setText("Usuario o contraseña incorrectas");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(205, 306, 148, 59);
		contentPane.add(btnNewButton);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrar.setBounds(400, 384, 117, 39);
		contentPane.add(btnCerrar);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblError.setBounds(81, 254, 345, 33);
		contentPane.add(lblError);
		
		pfPass = new JPasswordField();
		pfPass.setBounds(179, 215, 288, 30);
		contentPane.add(pfPass);
	}
}
