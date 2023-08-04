package org.example.util;

import org.example.model.TokenAndPortModel;

import java.io.*;


public class FileUtils {

    private static final String FILE_PATH = "files/auth/auth.txt";

    private static final String DELIMITER = ",";


    public static void writeToFile(boolean isNew, TokenAndPortModel model) {

        createdFile();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println(model.getToken() + DELIMITER + model.getPort());

            if (!isNew) {
                ButtonUtils.information("Update data successfully");
            } else {
                ButtonUtils.information("data entered successfully");
            }

        } catch (IOException e) {
            ButtonUtils.error(e.getMessage());
            e.printStackTrace();
        }


    }

    public static TokenAndPortModel readFromFile() {

        File txtFileOpen = new File("files/auth", "auth.txt");
        if (txtFileOpen.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line = reader.readLine();
                if (line != null) {
                    String[] parts = line.split(DELIMITER);
                    if (parts.length == 2) {
                        TokenAndPortModel model = new TokenAndPortModel();
                        model.setToken(parts[0]);
                        model.setPort(parts[1]);
                        return model;
                    }
                }
            } catch (IOException e) {
                ButtonUtils.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }

    private static void createdFile() {

        File file = new File("files/auth");
        if (!file.exists()) {
            file.mkdirs();
        }
        File txtFileOpen = new File("files/auth", "auth.txt");
        if (!txtFileOpen.exists()) {
            try {
                txtFileOpen.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
