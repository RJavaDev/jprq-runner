package org.example.validate;

import org.example.util.ButtonUtils;
import org.example.util.SystemName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InstallJprqSystem {

    private static final SystemName system = new SystemName();

    public static void jprqInstalled(){
            ButtonUtils.information("jprq has started installing");
        long startTime = System.currentTimeMillis();
         if(system.isLinux()){
             installCurlForLinuxSystem();
         }
        installJprq();
        long endTime = System.currentTimeMillis();
        ButtonUtils.information("Installed successfully in "+(endTime-startTime)+" milliseconds");

    }

    public static void installJprq(){

        String[] installedJprqCommand = {"curl", "-fsSL", "https://jprq.io/install.sh"};
        writeCommand(installedJprqCommand);
    }

    public static void installCurlForLinuxSystem(){

        String[] installedCurlCommand = {"sudo", "apt", "install", "curl"};
        writeCommand(installedCurlCommand);
    }

    public static void writeCommand(String[] command){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
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
            ButtonUtils.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
