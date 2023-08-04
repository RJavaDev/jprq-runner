package org.example.util;

import org.example.controller.CMDController;
import org.example.model.TokenAndPortModel;
import org.example.threadImp.ThreadImp;
import org.example.validate.InstallJprqSystem;

import java.io.*;

public class WriteCommandLinuxUtils {

    private static final String SUCCESS = "<b style=\"color: red;\">" +
            "You have opened a port through server jprq,\n" +
            "now click OK to close this port.<br>\n" +
            "If you don't want to close the port\n" +
            "just do other things </b>";


    public static void writeCommand(TokenAndPortModel auth) {
        InstallJprqSystem.jprqInstalled();

        String[] command1 = {"jprq", "auth", auth.getToken()};
        String[] command2 = {"jprq", "http", auth.getPort()};

        writeCommand(command1);
        writeCommand(command2);

    }


    private static void writeInformation(Process process) throws IOException {

        InputStream inputStream = process.getInputStream();
        InputStream errorStream = process.getErrorStream();

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

        String information = "";
        String line;
        String openPortLink = null;
        int s = 0;
        while ((line = inputReader.readLine()) != null) {
            s++;
            System.out.println(line);
            information += line + "<br>";
            if (line.contains("Forwarded")) {
                String[] searchLink = line.split(":");
                openPortLink = searchLink[1].replaceAll("\\s", "");
            }
            if (s == 5) {
                information += "<br>" + SUCCESS;
                ThreadImp.killRun(information, openPortLink);
            }
        }

        while ((line = errorReader.readLine()) != null) {
            System.out.println(line);
            information += line + "\n";
            if (line.contains("auth token has been set")) {
                continue;
            }

            if (line.contains("authentication failed") || line.contains("cannot reach server on port")) {
                ButtonUtils.error(information);
                CMDController.newAuth(false);
            }
            ButtonUtils.error(information);
        }

    }

    public static void writeCommand(String[] command){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            writeInformation(process);
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            ButtonUtils.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
