package org.example.util;

import org.example.controller.AppController;
import org.example.controller.KillController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtils {

    private static final String SUCCESS = "<b style=\"color: red;\">" +
            "You have opened a port through server jprq,\n" +
            "now click OK to close this port.<br>\n" +
            "If you don't want to close the port\n" +
            "just do other things </b>";

    public static void writeInformation(Process process) throws IOException {

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
                KillController.killRun(information, openPortLink);
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
                AppController.newAuth(false);
            }
            ButtonUtils.error(information);
        }

    }
}
