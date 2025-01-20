package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private static Conexion instancia = null;
	private static Connection con;
	
	public Conexion () {		
		try{
			String connectionUrl = "jdbc:sqlserver://PC-ALEXANDER; databaseName=Reto; user=user; password=root; encrypt=true; trustServerCertificate=true; loginTimeout=30;";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
		 }catch (Exception e) {
			 System.out.println("Error al abrir la conexión.");
		 }
	}
	
	public static Conexion getInstancia(){
		if (instancia == null) instancia = new Conexion();
		return instancia;
	}
	
	public Connection getCon (){
		return con;
	}
	
	public void cerrarConexion() {
		try {
				con.close();
		}catch (Exception e) {
			System.out.println("Error al cerrar la conexión.");
		}
	}
}