import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryAPI {

	private static Cloudinary cloudinary;
	
	public CloudinaryAPI() {

        
    	Dotenv dotenv = Dotenv.load();
    	cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    	System.out.println(cloudinary.config.cloudName);
	}
	
	public void uploadImage(String imagePath) {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(new File(imagePath), ObjectUtils.emptyMap());
            System.out.println("Image uploaded successfully: " + uploadResult.get("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		try {
			CloudinaryAPI cloudinary = new CloudinaryAPI();
			if (args.length > 0) {
				cloudinary.uploadImage(args[0]);
				System.out.println(cloudinary.cloudinary.config.cloudName);
				
			} else {
				cloudinary.uploadImage("prueba.png");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}