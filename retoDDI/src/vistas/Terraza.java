package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import modeloDAO.Weather_DAO;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

public class Terraza extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static Weather_DAO weather = new Weather_DAO();
	private static int currentTemperature;
	private static int avgTemperature;
	private static int avgRain;
	private static String city;
	private static JLabel lblNewLabel_1_2;
	private static ImageIcon icon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Terraza dialog = new Terraza();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Terraza() {
		setBounds(100, 100, 899, 667);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 883, 628);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(city);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(77, 79, 202, 81);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(currentTemperature + "ºC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(77, 235, 202, 81);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(avgTemperature + "ºC");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_1.setBounds(440, 235, 202, 81);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(avgRain + "%");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_1_1.setBounds(440, 91, 202, 81);
		contentPanel.add(lblNewLabel_1_1_1);
		
		lblNewLabel_1_2 = new JLabel(icon);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_2.setBounds(629, 79, 179, 106);
		contentPanel.add(lblNewLabel_1_2);	
	}
	
	
}
