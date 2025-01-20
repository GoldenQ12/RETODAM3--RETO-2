	import java.io.*;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

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
	    	try {
	    		System.setProperty("javax.net.ssl.trustStore", "certificados/ChatSSL");
	            System.setProperty("javax.net.ssl.trustStorePassword", "1234567");
	            
	            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	            try (FileInputStream trustStoreStream = new FileInputStream("path_to_your_truststore_file")) {
	                trustStore.load(trustStoreStream, "truststore_password".toCharArray());
	            }

	            // Set up a TrustManagerFactory to use the custom trust store
	            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	            trustManagerFactory.init(trustStore);

	            // Create an SSLContext with the custom TrustManager
	            SSLContext sslContext = SSLContext.getInstance("TLS");
	            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

	            // Set the default SSLContext to the custom one
	            SSLContext.setDefault(sslContext);



	    	}
	    	catch (Exception ex) {
	    		ex.getMessage();
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
	
	            // Enviar el nombre de usuario al servidor
	            output.println(usuario);
	
	            // Hilo para escuchar mensajes del servidor
	            new Thread(() -> {
	                try {
	                	InputStream certInputStream = getResources().openRawResource(R.raw.certificado);
	                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
	                    Certificate certificate = certificateFactory.generateCertificate(certInputStream);
	                    certInputStream.close();

	                    // Create a KeyStore containing the trusted certificate
	                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	                    keyStore.load(null, null);
	                    keyStore.setCertificateEntry("ca", certificate);

	                    // Create a TrustManager that trusts the certificate in our KeyStore
	                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	                    trustManagerFactory.init(keyStore);

	                    // Create an SSLContext that uses our TrustManager
	                    SSLContext sslContext = SSLContext.getInstance("TLS");
	                    sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

	                    // Create an SSLSocketFactory from our SSLContext
	                    SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

	                    // Create an SSLSocket using the SSLSocketFactory
	                    //socket = (SSLSocket) sslSocketFactory.createSocket("172.20.10.3", 6000); // Agenor's Iphone
	                    socket = (SSLSocket) sslSocketFactory.createSocket("192.168.1.254", 6000); // Saioa's home Iphone
	                    out = new PrintWriter(socket.getOutputStream(), true);
	                    in = new Scanner(socket.getInputStream());

	                    serverListenerThread = new Thread(new ServerListener());
	                    serverListenerThread.start();

	                    // Send initial message to join the chat
	                    out.println("1:" + HomeFragment.logedUser.getId() + ":" + getString(R.string.chat_join_message));

	                    // Add database log
	                    DatabaseHelper.chatConnectionInsertQuery(HomeFragment.logedUser.getId());
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
