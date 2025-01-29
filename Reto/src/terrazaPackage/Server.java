package terrazaPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import com.fasterxml.jackson.databind.JsonNode;

public class Server extends UnicastRemoteObject implements Remote, InterfaceWeather {

	Server() throws RemoteException {
		super();
	}
	
	
	
	public static void main(String[] args) throws RemoteException {
		try {
            Server obj = new Server();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/ObjetoReto", obj);
            System.out.println("Objeto Reto registrado");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
	}



	@Override
	public String recibirXML() throws RemoteException {
        String xmlContent = null;
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            String serverUrl = "http://localhost/uploads/weather_data.xml";

            URL url = new URL(serverUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                xmlContent = response.toString();
            } else {
                throw new RemoteException("Failed to retrieve XML: HTTP error code " + responseCode);
            }
        } catch (IOException e) {
            throw new RemoteException("Error retrieving XML: " + e.getMessage(), e);
        } finally {
            // Close the reader and connection
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return xmlContent;
    }



	@Override
	public String getCiudad(JsonNode node) throws RemoteException {
		return node.findPath("City").asText();
	}



	@Override
	public JsonNode getTemperatureNodes(JsonNode node) throws RemoteException {
		return node.path("hourly").path("temperature_2m");
	}
	
	@Override
	public JsonNode getRainNodes (JsonNode node) throws RemoteException{
		return node.path("hourly").path("rain");
	}
	
	
	

}
