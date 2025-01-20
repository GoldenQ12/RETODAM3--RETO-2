
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

public class Terraza extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static int currentTemperature;
	private static int avgTemperature;
	private static int avgRain;
	private static String city;
	private static JLabel lblNewLabel_1_2;
	private static ImageIcon icon;

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
		loadData();
		setBounds(100, 100, 899, 667);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 883, 628);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(city);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(77, 79, 202, 81);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(currentTemperature + "ºC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(77, 235, 202, 81);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(avgTemperature + "ºC");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_1.setBounds(440, 235, 202, 81);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(avgRain + "%");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_1_1.setBounds(440, 91, 202, 81);
		contentPanel.add(lblNewLabel_1_1_1);
		
		lblNewLabel_1_2 = new JLabel(icon);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1_2.setBounds(629, 79, 179, 106);
		contentPanel.add(lblNewLabel_1_2);	
	}
	
	public static void loadData() {
		try {
			String xml = weather.getXML();
			
            // Create ObjectMapper instance

            XmlMapper xmlMapper = new XmlMapper();

            // Convert XML to JsonNode
            JsonNode jsonNode = xmlMapper.readTree(xml.getBytes());

            // Create ObjectMapper instance for JSON
            ObjectMapper objectMapper = new ObjectMapper();
            

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            JsonNode temperatureNodes = jsonNode.path("hourly").path("temperature_2m");
            JsonNode rainNodes = jsonNode.path("hourly").path("rain");
            
            city = jsonNode.findPath("City").asText();
            ArrayList<Integer> temperatures = new ArrayList<>();
            for (JsonNode tempNode : temperatureNodes) {
                // Convert the temperature to Integer and add to the list
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
                avgRain = (int) (avgRain / rain.size());
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
