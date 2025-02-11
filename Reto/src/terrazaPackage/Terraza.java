package terrazaPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Terraza extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private static int currentTemperature, avgRain;
    private static double minTemperature, maxTemperature;
    private static String city, province, avgTemperature;
    private static JLabel lblCity, lblProvince, lblCurrentTemp, lblAvgTemp, lblMinTemp, lblMaxTemp, lblRain;
    private static InterfaceWeather obj;

    public static void main(String[] args) {
        try {
            Terraza dialog = new Terraza();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Terraza() {
        try {
            obj = (InterfaceWeather) Naming.lookup("//127.0.0.1/ObjetoReto");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        
        loadData();
        
        setBounds(100, 100, 892, 595);
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 883, 661);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(new Color(40, 40, 40)); 
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
        
        // Fecha de la previsión
        LocalDate now = LocalDate.now();
        String formattedDate = now.format(DateTimeFormatter.ISO_LOCAL_DATE);
        JLabel lblDate = new JLabel("PREVISIÓN DEL TIEMPO - " + formattedDate);
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblDate.setForeground(Color.WHITE);
        lblDate.setBounds(10, 11, 883, 50);
        contentPanel.add(lblDate);

        // Provincia
        JLabel lblTitleProvince = new JLabel("Provincia:");
        lblTitleProvince.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleProvince.setForeground(Color.WHITE);
        lblTitleProvince.setBounds(50, 80, 200, 40);
        contentPanel.add(lblTitleProvince);
        
        lblProvince = new JLabel(province);
        lblProvince.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblProvince.setForeground(Color.CYAN);
        lblProvince.setBounds(323, 80, 400, 40);
        contentPanel.add(lblProvince);

        // Ciudad
        JLabel lblTitleCity = new JLabel("Ciudad:");
        lblTitleCity.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleCity.setForeground(Color.WHITE);
        lblTitleCity.setBounds(50, 130, 200, 40);
        contentPanel.add(lblTitleCity);
        
        lblCity = new JLabel(city);
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblCity.setForeground(Color.YELLOW);
        lblCity.setBounds(323, 130, 400, 40);
        contentPanel.add(lblCity);

        // Temperatura Actual
        JLabel lblTitleCurrentTemp = new JLabel("Temp. Actual:");
        lblTitleCurrentTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleCurrentTemp.setForeground(Color.WHITE);
        lblTitleCurrentTemp.setBounds(50, 180, 200, 40);
        contentPanel.add(lblTitleCurrentTemp);
        
        lblCurrentTemp = new JLabel(currentTemperature + "ºC");
        lblCurrentTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblCurrentTemp.setForeground(Color.ORANGE);
        lblCurrentTemp.setBounds(323, 180, 200, 40);
        contentPanel.add(lblCurrentTemp);

        // Temperatura Mínima
        JLabel lblTitleMinTemp = new JLabel("Temp. Mínima:");
        lblTitleMinTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleMinTemp.setForeground(Color.WHITE);
        lblTitleMinTemp.setBounds(50, 230, 251, 40);
        contentPanel.add(lblTitleMinTemp);
        
        lblMinTemp = new JLabel(minTemperature + "ºC");
        lblMinTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblMinTemp.setForeground(Color.BLUE);
        lblMinTemp.setBounds(323, 230, 200, 40);
        contentPanel.add(lblMinTemp);

        // Temperatura Máxima
        JLabel lblTitleMaxTemp = new JLabel("Temp. Máxima:");
        lblTitleMaxTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleMaxTemp.setForeground(Color.WHITE);
        lblTitleMaxTemp.setBounds(50, 280, 251, 40);
        contentPanel.add(lblTitleMaxTemp);
        
        lblMaxTemp = new JLabel(maxTemperature + "ºC");
        lblMaxTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblMaxTemp.setForeground(Color.RED);
        lblMaxTemp.setBounds(323, 280, 200, 40);
        contentPanel.add(lblMaxTemp);

        // Temperatura Media
        JLabel lblTitleAvgTemp = new JLabel("Temp. Media:");
        lblTitleAvgTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleAvgTemp.setForeground(Color.WHITE);
        lblTitleAvgTemp.setBounds(50, 330, 200, 40);
        contentPanel.add(lblTitleAvgTemp);
        
        lblAvgTemp = new JLabel(avgTemperature + "ºC");
        lblAvgTemp.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblAvgTemp.setForeground(Color.GREEN);
        lblAvgTemp.setBounds(323, 330, 200, 40);
        contentPanel.add(lblAvgTemp);

        // Lluvia
        JLabel lblTitleRain = new JLabel("Lluvia:");
        lblTitleRain.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleRain.setForeground(Color.WHITE);
        lblTitleRain.setBounds(50, 380, 200, 40);
        contentPanel.add(lblTitleRain);
        
        lblRain = new JLabel(avgRain + "%");
        lblRain.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblRain.setForeground(Color.CYAN);
        lblRain.setBounds(323, 380, 200, 40);
        contentPanel.add(lblRain);
    }

    public static void loadData() {
        try {
            String xml = obj.recibirXML();
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(xml.getBytes());

            city = node.path("City").asText();
            province = node.path("Provincia").asText();
            maxTemperature = node.path("MaxTemperatura").asDouble();
            minTemperature = Double.parseDouble(node.path("MinTemperatura").asText().replace(",", "."));
            currentTemperature = node.path("TemperaturaActual").asInt();
            String[] parts = node.path("TemperaturaMedia").asText().split(",");
            avgTemperature = parts[0];
            avgRain = node.path("Lluvia").asInt();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
