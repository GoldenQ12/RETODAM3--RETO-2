package modeloDAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TiempoMunicipio {
    private int codProvincia;
    private int codMunicipio;
    private String nombreMunicipio;
    private String estadoCielo;

    // Constructor
    public TiempoMunicipio(int codProvincia, int codMunicipio) {
        this.codProvincia = codProvincia;
        this.codMunicipio = codMunicipio;
    }

    // Método para obtener datos del municipio desde la API
    public void cargarDatos() {
        try {
            String urlString = "https://www.el-tiempo.net/api/json/v2/provincias/" + codProvincia + "/municipios/" + codMunicipio;

            // Crear el objeto URL
            URL url = new URL(urlString);

            // Abrir la conexión
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsear el JSON con Gson
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

            // Extraer información del JSON
            JsonObject municipio = jsonResponse.getAsJsonObject("municipio");
            this.nombreMunicipio = municipio.get("NOMBRE").getAsString();

            JsonObject estadoCieloJson = jsonResponse.getAsJsonObject("stateSky");
            this.estadoCielo = estadoCieloJson.get("description").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public String getEstadoCielo() {
        return estadoCielo;
    }

    @Override
    public String toString() {
        return "Municipio: " + nombreMunicipio + "\nEstado del cielo: " + estadoCielo;
    }
}
