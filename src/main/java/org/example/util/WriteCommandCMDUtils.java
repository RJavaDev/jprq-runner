package org.example.util;

import org.example.ThreadImp;
import org.example.controller.CMDController;
import org.example.model.TokenAndPortModel;

import java.io.*;

public class WriteCommandCMDUtils {

    private static final String SUCCESS = "" +
            "You have opened a port through server jprq,\n" +
            "now click OK to close this port.\n" +
            "If you don't want to close the port\n" +
            "just do other things";

    public static void writeCommand(TokenAndPortModel auth) {

        String[] command1 = {"cmd", "/c", ".\\jprq-windows-386.exe", "auth", auth.getToken()};
        String[] command2 = {"cmd", "/c", ".\\jprq-windows-386.exe", "http", auth.getPort()};

            startCommond(command1, command2);

    }

    private static void startCommond(String[] command1, String[] command2) {

        try {
            ProcessBuilder processBuilder1 = new ProcessBuilder(command1);
            processBuilder1.directory(new File("src/main/resources/jprq"));
            Process process1 = processBuilder1.start();
            writeInformation(process1);
            process1.waitFor();

            ProcessBuilder processBuilder2 = new ProcessBuilder(command2);
            processBuilder2.directory(new File("src/main/resources/jprq"));
            Process process2 = processBuilder2.start();
            writeInformation(process2);
            process2.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void writeInformation(Process process) throws IOException {

        InputStream inputStream = process.getInputStream();
        InputStream errorStream = process.getErrorStream();

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

        String information = "";
        String line;
        int s = 0;
        while ((line = inputReader.readLine()) != null) {
            s++;
            System.out.println(line);
            information += line + "\n";
            if(s==5){
                information+="\n"+SUCCESS;
               ThreadImp.killRun(information);
            }
        }

        while ((line = errorReader.readLine()) != null) {
            System.out.println(line);
            information += line + "\n";
            if(line.contains("authentication failed")){
                CMDController.newAuth(false);
            }
            ButtonUtils.error(information);
        }

    }
}
