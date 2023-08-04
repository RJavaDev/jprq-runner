package org.example.processKil;

import org.example.util.ButtonUtils;

import java.io.IOException;

public class KillLinuxSystem {

    public static void linuxProcessKiller() {

        String processName = "jprq"; // The name of the process you want to kill

        try {
            Process p = Runtime.getRuntime().exec("pgrep " + processName);
            p.waitFor();

            if (p.exitValue() == 0) {

                Process killProcess = Runtime.getRuntime().exec("pkill " + processName);
                killProcess.waitFor();

                if (killProcess.exitValue() == 0) {
                    System.out.println("The process was successfully deleted.");
                } else {
                    System.out.println("The process was not deleted.");
                }
                System.exit(0);
            } else {
                System.out.println("Process not found.");
            }
        } catch (IOException | InterruptedException e) {

            ButtonUtils.error(e.getMessage());
            System.exit(0);
            e.printStackTrace();
        }
    }
}
