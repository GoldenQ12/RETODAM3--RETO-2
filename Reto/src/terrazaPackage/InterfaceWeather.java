package terrazaPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.fasterxml.jackson.databind.JsonNode;

public interface InterfaceWeather extends Remote{
	
	public String recibirXML() throws RemoteException;
	
	public String getCiudad(JsonNode node) throws RemoteException;
	
	public JsonNode getTemperatureNodes(JsonNode node) throws RemoteException;
	
	public JsonNode getRainNodes(JsonNode node) throws RemoteException;
}
