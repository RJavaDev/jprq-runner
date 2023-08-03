package org.example.validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckingJprqSystem {

    public static boolean jprqIsInstalled(){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("jprq", "--version");
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
