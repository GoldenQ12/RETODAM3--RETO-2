import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import javax.net.ssl.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private static PrintWriter output;
    private static BufferedReader input;
    private static SSLSocket socket;
    private String mensaje;

    static {
        try {
            System.setProperty("javax.net.ssl.trustStore", "certificados/ChatSSL");
            System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

            // Send the username to the server
            output.println(usuario);

            // Thread for receiving messages
            new Thread(() -> {
                try {
                    while ((mensaje = input.readLine()) != null) {
                        System.out.println("Mensaje recibido: " + mensaje);
                        if (mensaje.startsWith("file:")) {
                            chat.recibirImagen(mensaje);
                        } else {
                            chat.recibirMensaje(mensaje);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error leyendo del servidor: " + e.getMessage());
                }
            }).start();

            // Action for the "Send" button in chat
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
