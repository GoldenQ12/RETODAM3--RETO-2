import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.File;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpResponse;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DriveAPI {
    private static final String APPLICATION_NAME = "Desktop App";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE_PATH = "credentials.json"; 
    private static final String[] Scopes = { DriveScopes.DRIVE_FILE };
    private Drive driveService;

    public DriveAPI() throws Exception {
        this.driveService = getDriveService();
    }
    
    private Drive getDriveService() throws Exception {
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                clientSecrets,
                Arrays.asList(Scopes)) // Pass the scopes as a list
            .setAccessType("offline")
            .build();

        // Attempt to load credentials
        Credential credential = flow.loadCredential("user");
        if (credential == null) {
            // Redirect user to authorization URL
            String redirectUri = "urn:ietf:wg:oauth:2.0:oob";
            String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
            
            System.out.println("Please open the following URL in your browser to authorize the application:");
            System.out.println(authorizationUrl);

            // Capture the authorization code from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the authorization code: ");
            String code = reader.readLine();

            // Exchange the authorization code for a Credential
            credential = flow.createAndStoreCredential(flow.newTokenRequest(code).setRedirectUri(redirectUri).execute(), "user");
        }

        return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    public String downloadFilesFromFolder() throws IOException {
        // Extract the folder ID from the Google Drive URL
        String folderId = "1z5bUxXp1oKklz1OfDLx93mGhpwXd0kE3";

        if (folderId == null || folderId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Google Drive folder URL.");
        }

        // List all files in the folder
        List<File> files = listFilesInFolder(folderId);

        // Download each file
        for (File file : files) {
            String fileId = file.getId();
            String fileName = file.getName();
            System.out.println("Downloading file: " + fileName);
            return downloadFile(fileId, fileName);
        }
		return "";
    }


    public List<File> listFilesInFolder(String folderId) throws IOException {
        String query = "'" + folderId + "' in parents and trashed = false";
        FileList fileList = driveService.files().list()
                .setQ(query)
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .execute();

        return fileList.getFiles();
    }

    private String downloadFile(String fileId, String fileName) throws IOException {
    	HttpResponse response = driveService.files().get(fileId).executeMedia();
        InputStream inputStream = response.getContent();

        // Read the content of the file into a String
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }

        return fileContent.toString();
    }
}
