package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.TokenAndPortModel;

import java.io.File;
import java.io.IOException;


public class FileUtils {


    public static TokenAndPortModel readFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/auth", "auth.txt");

        try {
            if (file.exists()) {
                return objectMapper.readValue(file, TokenAndPortModel.class);
            } else {
                System.out.println("JSON file does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeAuth(boolean isNew, TokenAndPortModel auth){
        try {

            File file = new File("src/main/resources/auth");
            ObjectMapper objectMapper = new ObjectMapper();

            File txtFileOpen = createdFile(file);
            objectMapper.writeValue(txtFileOpen, auth);

            if(!isNew){
                ButtonUtils.information("Update data successfully");
            }else{
                ButtonUtils.information("data entered successfully");
            }

        } catch (IOException e) {
            ButtonUtils.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private static File createdFile(File file){
        if (!file.exists()) {
            file.mkdirs();
        }
        File txtFileOpen = new File("src/main/resources/auth", "auth.txt");
        if (!txtFileOpen.exists()) {
            try {
                txtFileOpen.createNewFile();
            } catch (IOException e) {
                ButtonUtils.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return txtFileOpen;
    }

}
