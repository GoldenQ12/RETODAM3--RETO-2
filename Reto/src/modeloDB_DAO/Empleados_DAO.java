package modeloDB_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import modeloDB_DTO.Empleados_DTO;

public class Empleados_DAO {
	
	private static final String SQL_SELECT_COLOR= "SELECT Color FROM empleados WHERE Nombre = ?";
	private static final String SQL_SELECT_COLORFONDO	= "SELECT colorFondo FROM empleados WHERE Nombre = ?";
	private static final String SQL_UPDATE_COLOR = "UPDATE empleados SET Color = ? WHERE Nombre = ?";
	private static final String SQL_UPDATE_COLORFONDO = "UPDATE empleados SET colorFondo = ? WHERE Nombre = ?";
	
	private Conexion con = Conexion.getInstancia();
	
	public boolean actualizarColor(Empleados_DTO t) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_UPDATE_COLOR);
			ps.setString(2, t.getNombre());
			ps.setString(1, t.getColor());
			if(ps.executeUpdate()>0) return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public String obtenerColor(String nombre) throws SQLException {
        String color = null;
        String sql = "SELECT Color FROM empleados WHERE Nombre = ?";

        try (PreparedStatement statement = con.getCon().prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    color = resultSet.getString("Color");
                }
            }
        }

        return color;
    }
	
	public boolean actualizarColorFondo(Empleados_DTO t) {
		PreparedStatement ps=null;
		try {
			ps=con.getCon().prepareStatement(SQL_UPDATE_COLORFONDO);
			ps.setString(2, t.getNombre());
			ps.setString(1, t.getColorFondo());
			if(ps.executeUpdate()>0) return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)ps.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public String obtenerColorFondo(String nombre) throws SQLException {
        String color = null;
        String sql = SQL_SELECT_COLORFONDO;

        try (PreparedStatement statement = con.getCon().prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    color = resultSet.getString("colorFondo");
                }
            }
        }

        return color;
    }
	
	
}
