package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.conexion;
import modeloDTO.fichajesDTO;

public class Weather_DAO {
	private static final String SQL_SELECT = "SELECT * FROM weather";
	
	private conexion con = conexion.getInstancia();
	
	public String getXML() {
	    String xml = "";
	    String SQL_SELECT = "SELECT _XML FROM weather"; // Make sure to define your SQL query here
	    try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT);
	         ResultSet rs = ps.executeQuery()) {
	        
	        // Move the cursor to the first row
	        if (rs.next()) {
	            xml = rs.getString("_XML");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return xml;
	}
}
