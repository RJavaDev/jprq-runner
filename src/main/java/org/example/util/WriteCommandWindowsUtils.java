package org.example.util;

import org.example.threadImp.ThreadImp;
import org.example.controller.AppController;
import org.example.model.TokenAndPortModel;

import java.io.*;

public class WriteCommandWindowsUtils {

    private static final String COMMAND_WRITE_PATH = "files/jprq";

    public static void writeCommand(TokenAndPortModel auth) {

        String[] command1 = {"cmd", "/c", ".\\jprq-windows-386.exe", "auth", auth.getToken()};
        String[] command2 = {"cmd", "/c", ".\\jprq-windows-386.exe", "http", auth.getPort()};

        startCommond(command1, command2);

    }

    private static void startCommond(String[] command1, String[] command2) {
        try {
            ProcessBuilder processBuilder1 = new ProcessBuilder(command1);
            processBuilder1.directory(new File(COMMAND_WRITE_PATH));
            Process process1 = processBuilder1.start();
            CommandUtils.writeInformation(process1);
            process1.waitFor();

            ProcessBuilder processBuilder2 = new ProcessBuilder(command2);
            processBuilder2.directory(new File(COMMAND_WRITE_PATH));
            Process process2 = processBuilder2.start();
            CommandUtils.writeInformation(process2);
            process2.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
