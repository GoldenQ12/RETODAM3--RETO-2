package terrazaPackage;


import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.swing.JLabel;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class Terraza extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static int currentTemperature;
	private static int avgTemperature;
	private static int avgRain;
	private static String city;
	private static JLabel lblNewLabel_1_2;
	private static ImageIcon icon;
	private static InterfaceWeather obj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Terraza dialog = new Terraza();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Terraza() {
		try {
			obj = (InterfaceWeather) Naming.lookup("//127.0.0.1/ObjetoReto");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData();
		setBounds(100, 100, 899, 667);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 883, 628);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblCity = new JLabel(city);
		lblCity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblCity.setBounds(255, 136, 584, 81);
		contentPanel.add(lblCity);
		
		JLabel lblCurrentTemperature = new JLabel(currentTemperature + "ºC");
		lblCurrentTemperature.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblCurrentTemperature.setBounds(467, 411, 202, 81);
		contentPanel.add(lblCurrentTemperature);
		
		JLabel lblAvgTemperature = new JLabel(avgTemperature + "ºC");
		lblAvgTemperature.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblAvgTemperature.setBounds(467, 319, 202, 81);
		contentPanel.add(lblAvgTemperature);
		
		JLabel lblAvgRain = new JLabel(avgRain + "%");
		lblAvgRain.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblAvgRain.setBounds(255, 228, 296, 81);
		contentPanel.add(lblAvgRain);
		
		lblNewLabel_1_2 = new JLabel(icon);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_2.setBounds(629, 79, 179, 106);
		contentPanel.add(lblNewLabel_1_2);	
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String formattedDate = now.format(formatter);
		JLabel lblNewLabel_1_3 = new JLabel("PREVISIÓN DEL TIEMPO - " + formattedDate);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_3.setBounds(0, 0, 883, 81);
		contentPanel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("Ciudad:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2.setBounds(75, 136, 202, 81);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Lluvia:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2_1.setBounds(75, 227, 202, 81);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Temperatura media:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2_1_1.setBounds(75, 319, 382, 81);
		contentPanel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Temperatura actual:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_2_1_1_1.setBounds(75, 411, 441, 81);
		contentPanel.add(lblNewLabel_2_1_1_1);
	}
	
	public static void loadData() {
		try {
			String xml = obj.recibirXML();
			

            XmlMapper xmlMapper = new XmlMapper();

            // Convert XML to JsonNode
            JsonNode jsonNode = xmlMapper.readTree(xml.getBytes());

            // Create ObjectMapper instance for JSON
            ObjectMapper objectMapper = new ObjectMapper();
            

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            JsonNode temperatureNodes = obj.getTemperatureNodes(jsonNode);
            JsonNode rainNodes = obj.getRainNodes(jsonNode);
            city = obj.getCiudad(jsonNode);
            ArrayList<Integer> temperatures = new ArrayList<>();
            for (JsonNode tempNode : temperatureNodes) {
                temperatures.add(tempNode.asInt());
            }
            for (Integer temperature : temperatures) {
            	avgTemperature += temperature;
            }
            avgTemperature = (int) (avgTemperature / temperatures.size());
            currentTemperature = jsonNode.path("current").path("temperature_2m").asInt();
            ArrayList<Integer> rain = new ArrayList<>();
            for (JsonNode rainNode : rainNodes) {
                // Convert the temperature to Integer and add to the list
                rain.add(rainNode.asInt());
            }
            for (Integer rainCurrent : rain) {
            	avgRain += rainCurrent;
            }
            if (avgRain == 0) {
            	avgRain = 10;
            } else {
                avgRain = (int) (avgRain / 8);
            }
            if (avgRain > 50 && avgRain < 75) {
            	icon = new ImageIcon("images/heavy-rain.png");
            } else if(avgRain > 75) {
            	icon = new ImageIcon("images/storm.png");
            } else {
            	icon = new ImageIcon("images/cloud.png");
            }
            if (avgTemperature > 15 ) {
            	icon = new ImageIcon("images/sun.png");
            }
            
            

            // Print the JSON string
            System.out.println(temperatures);
            System.out.println(city);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}