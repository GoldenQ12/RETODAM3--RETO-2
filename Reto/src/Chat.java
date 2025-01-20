import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modeloDB_DAO.Empleados_DAO;
import modeloDB_DTO.Empleados_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

public class Chat extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtEnviar;
    private RoundedButton btnNewButton;
    private String textoTotal = "";
    public String usuario;
    private String color;// Variable para almacenar el nombre de usuario
    JTextPane textPane;

    public Chat(String usuario) {
        this.usuario = usuario; // Guardamos el nombre del usuario
        
        setBounds(100, 100, 566, 799);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(50, 50));
        colorPreview.setBackground(Color.WHITE);
        
        JLabel lblNewLabel = new JLabel("CHAT");
        lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setBounds(192, 10, 95, 43);
        contentPanel.add(lblNewLabel);

        Empleados_DAO selectorcolorFondo=new Empleados_DAO();
    	try {
			color=selectorcolorFondo.obtenerColorFondo(usuario);
			if (color == null) {
				color= "";
			}
			if(!color.equals("")) {
			// Eliminar el prefijo '#' si está presente
		    if (color.startsWith("#")) {
		        color = color.substring(1);
		    }
		    
		    // Convertir el código hexadecimal a un objeto Color
		    Color colorfondo = new Color(
		        Integer.parseInt(color.substring(0, 2), 16), // Rojo
		        Integer.parseInt(color.substring(2, 4), 16), // Verde
		        Integer.parseInt(color.substring(4, 6), 16)  // Azul
		    );
			contentPanel.setBackground(colorfondo);
			}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        
        txtEnviar = new JTextField();
        txtEnviar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        txtEnviar.setBounds(76, 624, 292, 57);
        contentPanel.add(txtEnviar);
        txtEnviar.setColumns(10);
        txtEnviar.addActionListener(e -> {
            btnNewButton.doClick();
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(37, 62, 418, 551);
        contentPanel.add(scrollPane);
        
        textPane = new JTextPane();
        textPane.setFocusable(false);
        textPane.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        scrollPane.setViewportView(textPane);
        textPane.setContentType("text/html");

        btnNewButton = new RoundedButton("ENVIAR");
        btnNewButton.setText("");
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(new Color(159, 255, 159));
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("60525.png"));
        Image imagenOriginal = originalIcon.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnNewButton.setIcon(new ImageIcon(imagenRedimensionada));
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                txtEnviar.setText("");
	            }
	        });

        btnNewButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        btnNewButton.setBounds(378, 624, 114, 57);
        contentPanel.add(btnNewButton);

        // Botón para abrir el menú de emojis
        RoundedButton btnEmoji = new RoundedButton("😊");
        btnEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        btnEmoji.setBounds(3, 625, 63, 57);
        btnEmoji.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear el ComboBox con emojis
                String[] emojis = {"🤣", "👌", "🤑", "👍", "🔥", "❤️","😭","💀"};
                JComboBox<String> emojiSelector = new JComboBox<>(emojis);
                emojiSelector.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

                // Mostrar el selector de emojis en un JOptionPane
                int result = JOptionPane.showConfirmDialog(
                        contentPanel,
                        emojiSelector,
                        "Selecciona un emoji",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Si el usuario selecciona un emoji, agregarlo al campo de texto
                if (result == JOptionPane.OK_OPTION) {
                    String selectedEmoji = (String) emojiSelector.getSelectedItem();
                    txtEnviar.setText(txtEnviar.getText() + selectedEmoji);
                }
            }
        });
        contentPanel.add(btnEmoji);
        JPopupMenu menuDesplegable = new JPopupMenu();
        JMenuItem opcion1 = new JMenuItem("Opción 1: Cambiar color de fondo");
        JMenuItem opcion2 = new JMenuItem("Opción 2: Cambiar color del texto");
        JMenuItem opcion3 = new JMenuItem("Opción 3: Mostrar mensaje");
        menuDesplegable.add(opcion1);
        menuDesplegable.add(opcion2);
        menuDesplegable.add(opcion3);
        
        RoundedButton btnAjustes = new RoundedButton("");
        btnAjustes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menuDesplegable.show(btnAjustes, btnAjustes.getWidth() / 2, btnAjustes.getHeight() / 2);
        		 
        	}
        });
        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar el selector de color
                Color selectedColor = JColorChooser.showDialog(contentPanel, "Elige un color de fondo", contentPanel.getBackground());
                if (selectedColor != null) {
                	color=colorToHex(selectedColor).toString();
                	Empleados_DTO empleado= new Empleados_DTO(usuario,"","",color);
              		Empleados_DAO cambiocolorfondo=new Empleados_DAO();
              		cambiocolorfondo.actualizarColorFondo(empleado);
                    contentPanel.setBackground(selectedColor);
                }
            }
        });
        opcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Color selectedColor = JColorChooser.showDialog(contentPanel, "Elige un color", colorPreview.getBackground());
       		 if (selectedColor != null) {
       			 color=colorToHex(selectedColor).toString();
                    System.out.println(color);
                    }
       		 Empleados_DTO empleado= new Empleados_DTO(usuario,color,"","");
       		 Empleados_DAO cambiocolor=new Empleados_DAO();
       		 cambiocolor.actualizarColor(empleado);
            }
        });
        btnAjustes.setText("");
        btnAjustes.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        ImageIcon originalIconAjustes = new ImageIcon(getClass().getResource("61094.png"));
        Image imagenOriginalAjustes = originalIconAjustes.getImage();
        Image imagenRedimensionadaAjustes = imagenOriginalAjustes.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnAjustes.setIcon(new ImageIcon(imagenRedimensionadaAjustes));
        btnAjustes.setBounds(402, 8, 53, 45);
        contentPanel.add(btnAjustes);
        
        RoundedButton rndbtnAddImage = new RoundedButton("😊");
        rndbtnAddImage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fileChooser = new JFileChooser();
        		int returnValue = fileChooser.showOpenDialog(null);
        		
        		if (returnValue == JFileChooser.APPROVE_OPTION) {
        			File selectedFile = fileChooser.getSelectedFile();
        			try {
            			DriveAPI api = new DriveAPI();
            			api.uploadFile(selectedFile);
        			}
        			catch (Exception ex) {
        				ex.getMessage();
        			}
        			
        		}
        	}
        });
        rndbtnAddImage.setText("ADD IMAGE");
        rndbtnAddImage.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        rndbtnAddImage.setBounds(3, 692, 230, 57);
        contentPanel.add(rndbtnAddImage);
    }

    public void recibirMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            if (mensaje.contains("ha ingresado al chat") || mensaje.contains("ha salido del chat")) {
                textoTotal += "<p style='text-align: center; margin-bottom: 10px;'>" + mensaje + "</p>";
            } else {
                String[] partes = mensaje.split(":");
                String emisor = partes[0];
                String textoMensaje = partes[1];
                textoMensaje=escapeHtml(textoMensaje);
                textoMensaje = agregarSaltosDeLinea(textoMensaje);
                if (emisor.equals(usuario)) {
                	Empleados_DAO selectorcolor=new Empleados_DAO();
                	try {
						color=selectorcolor.obtenerColor(usuario);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                    textoTotal += "<p style='text-align: right; display: inline-block; padding: 10px;"
                            + "color:"+ color +";'>Tú: " + textoMensaje + "</p>";
                } else {
                	Empleados_DAO selectorcolor=new Empleados_DAO();
                	try {
						color=selectorcolor.obtenerColor(emisor);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                    textoTotal += "<p style='text-align: left; display: inline-block; padding: 10px; "
                            + "color:"+ color +";'>" + emisor + ": " + textoMensaje + "</p>";
                }
            }

            textPane.setText("<html><body style='margin: 0; padding: 0;'>" + textoTotal + "</body></html>");
        });
    }

    
    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
    
    private static String colorToHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    private String agregarSaltosDeLinea(String texto) {
        StringBuilder resultado = new StringBuilder();
        int longitudMaxima=50;
        int longitudActual = 0;

        for (String palabra : texto.split(" ")) {
            if (longitudActual + palabra.length() > longitudMaxima) {
                resultado.append("<br>");
                longitudActual = 0;
            }
            resultado.append(palabra).append(" ");
            longitudActual += palabra.length() + 1;
        }

        return resultado.toString().trim();
    }

    public JTextField getTxtEnviar() {
        return txtEnviar;
    }

    public JButton getBtnEnviar() {
        return btnNewButton;
    }
}
