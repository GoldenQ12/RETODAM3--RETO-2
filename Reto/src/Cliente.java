	import java.io.*;
	import javax.net.ssl.*;
	import javax.swing.*;
	
	public class Cliente {
	    private static final String HOST = "localhost";
	    private static final int PUERTO = 5000;
	    private static PrintWriter output;
	    private static BufferedReader input;
	    private static SSLSocket socket;
	    private String mensaje;
	
	    static {
	        // Configurar el trustStore para validar el certificado del servidor
	        System.setProperty("javax.net.ssl.trustStore", "certificados/ChatSSL");
	        System.setProperty("javax.net.ssl.trustStorePassword", "1234567");
	    }
	
	    public static void main(String[] args) {
	        String entradaUsuario = JOptionPane.showInputDialog(null, "Introduce tu usuario:");
	        if (entradaUsuario != null && !entradaUsuario.trim().isEmpty()) {
	            new Cliente(entradaUsuario);
	        }
	    }
	    public Cliente(String usuario) {
	        try {
	            Chat chat = new Chat(usuario);
	            chat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            chat.setVisible(true);
	
	            SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
	            socket = (SSLSocket) sfact.createSocket(HOST, PUERTO);
	
	            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            output = new PrintWriter(socket.getOutputStream(), true);
	
	            // Enviar el nombre de usuario al servidor
	            output.println(usuario);
	
	            // Hilo para escuchar mensajes del servidor
	            new Thread(() -> {
	                try {
	                    while ((mensaje = input.readLine()) != null) {
	                        System.out.println("Mensaje recibido: " + mensaje);
	                        chat.recibirMensaje(mensaje);
	                    }
	                } catch (IOException e) {
	                    System.out.println("Error leyendo del servidor: " + e.getMessage());
	                }
	            }).start();
	
	            // Acción del botón Enviar
	            chat.getBtnEnviar().addActionListener(e -> {
	                mensaje = chat.getTxtEnviar().getText();
	                if (!mensaje.trim().isEmpty()) {
	                    if ("Salir".equalsIgnoreCase(mensaje.trim())) {
	                        cerrarConexion();
	                        chat.dispose();
	                    } else {
	                        output.println(mensaje);
	                    }
	                }
	            });
	
	        } catch (IOException e) {
	            System.out.println("Error de conexión: " + e.getMessage());
	        }
	    }
	
	    private void cerrarConexion() {
	        try {
	            if (output != null) {
	                output.println("Salir");
	            }
	            if (socket != null && !socket.isClosed()) {
	                socket.close();
	            }
	        } catch (IOException e) {
	            System.out.println("Error al cerrar la conexión: " + e.getMessage());
	        }
	    }
	}
