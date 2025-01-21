import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.*;
import javax.net.ssl.*;

public class Servidor {
    private static final int PUERTO = 5000;
    private static List<PrintWriter> clientesConectados = new ArrayList<>();
    private static HashMap<String, PrintWriter> usuariosConectados = new HashMap<>();
    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    public static ArrayList<Empleado> listaEmpleados2 = new ArrayList<>();

    static {
    	try {
            // Set the system properties for trust store and password
    		System.setProperty("javax.net.ssl.keyStore", "certificados/ChatSSL");
            System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        } catch (Exception e) {
            System.out.println("Error setting up SSLContext: " + e.getMessage());
        }
    	
    }

    private static void cargarEmpleadosHabilitados() {
        listaEmpleados2.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("empleadosHabilitados.obj"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String id = datos[0];
                    String nombre = datos[1];
                    String puesto = datos[2];
                    String anionac = datos[3];
                    String contrasena = datos[5];
                    Empleado empleado = new Empleado(id, nombre, puesto, contrasena, anionac);
                    listaEmpleados2.add(empleado);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar empleados habilitados: " + e.getMessage());
        }
    }

    public void iniciar() {
        try {
            
        	SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket servidorSSL = (SSLServerSocket) sfact.createServerSocket(PUERTO);
            System.out.println("Servidor SSL iniciado en el puerto " + PUERTO);

            while (true) {
                SSLSocket socket = (SSLSocket) servidorSSL.accept();
                System.out.println("Cliente conectado desde: " + socket.getInetAddress());
                new GestorClientes(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor SSL: " + e.getMessage());
        }
    }

    private static synchronized void enviarAMensajesAClientes(String mensaje, String emisor, boolean esMensajeDeSalida) {
        System.out.println("Enviando mensaje: '" + mensaje + "' de " + emisor + " a todos los clientes.");
        
        boolean esMensajeDeIngresoSalida = mensaje.contains("ha ingresado al chat") || mensaje.contains("ha salido del chat");

        for (PrintWriter cliente : clientesConectados) {
            // Evita enviar el mensaje al cliente que lo envió, y también no enviar el emisor para mensajes de entrada/salida
            if (cliente != usuariosConectados.get(emisor) && !esMensajeDeIngresoSalida) {
                cliente.println(emisor + ":" + mensaje);  // Enviar el formato "Emisor: mensaje"
            } else if (!esMensajeDeSalida && esMensajeDeIngresoSalida) {
                // Enviamos solo el mensaje de ingreso/salida sin el emisor, alineado al centro
                cliente.println(mensaje);
            } else if (!esMensajeDeSalida) {
                // Si no es un mensaje de salida, enviamos también al emisor con el formato adecuado
                cliente.println(emisor + ":" + mensaje);  
            }
        }
    }


    public static boolean tieneAcceso(String nombreUsuario) {
        return listaEmpleados2.stream().anyMatch(emp -> emp.getNombre().equals(nombreUsuario)) ||
               listaEmpleados.stream().anyMatch(emp -> emp.getNombre().equals(nombreUsuario) &&
               (emp.getCategoria().equals("GERENTE") || emp.getCategoria().equals("CAMARERO")));
    }

    public class GestorClientes extends Thread {
        private SSLSocket socket;
        private String usuario;
        private PrintWriter output;

        public GestorClientes(SSLSocket socket) {
            this.socket = socket;
        }

        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                this.output = output;

                usuario = input.readLine();
                System.out.println("Usuario conectado: " + usuario);

                if (usuario == null || usuario.trim().isEmpty() || !tieneAcceso(usuario)) {
                    output.println("No tienes acceso al chat.");
                    socket.close();
                    return;
                }

                synchronized (clientesConectados) {
                    clientesConectados.add(output);
                    usuariosConectados.put(usuario, output);
                }

                enviarAMensajesAClientes(usuario + " ha ingresado al chat.", usuario, false);

                String mensaje;
                while ((mensaje = input.readLine()) != null) {
                    if ("Salir".equalsIgnoreCase(mensaje.trim()) || !tieneAcceso(usuario)) {
                        System.out.println("El usuario " + usuario + " ha salido.");
                        break;
                    }
                    enviarAMensajesAClientes(mensaje, usuario, false);
                    
                }

                enviarAMensajesAClientes(usuario + " ha salido del chat.", usuario, true);

            } catch (IOException e) {
                System.out.println("Error en la conexión del cliente: " + e.getMessage());
            } finally {
                synchronized (clientesConectados) {
                    if (output != null) {
                        clientesConectados.remove(output);
                    }
                    usuariosConectados.remove(usuario);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        cargarEmpleadosHabilitados();
        Servidor servidor = new Servidor();
        servidor.iniciar();
    }
}
