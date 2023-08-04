package org.example.util;

import org.example.model.TokenAndPortModel;

import java.io.*;

public class WriteCommandLinuxUtils {


    public static void writeCommand(TokenAndPortModel auth) {

        String[] command1 = {"jprq", "auth", auth.getToken()};
        String[] command2 = {"jprq", "http", auth.getPort()};

        writeCommand(command1);
        writeCommand(command2);

    }


    public static void writeCommand(String[] command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            CommandUtils.writeInformation(process);
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            ButtonUtils.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
