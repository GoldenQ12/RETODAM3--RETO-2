package modeloDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.conexion;
import modeloDTO.fichajesDTO;

public class Weather_DAO {
	private static final String SQL_SELECT = "SELECT * FROM weather";
	
	private conexion con = conexion.getInstancia();
	
	public String fetchXmlAsString(String fileUrl) throws Exception {
        StringBuilder result = new StringBuilder();

        // Open a connection to the URL
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Check if the response code is OK (200)
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Read the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
        } else {
            throw new Exception("Failed to fetch XML. HTTP response code: " + connection.getResponseCode());
        }

        // Disconnect the connection
        connection.disconnect();

        return result.toString();
    }
}
