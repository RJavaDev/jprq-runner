package org.example.processKil;

import org.example.threadImp.ThreadImp;
import org.example.util.ButtonUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillWindowsSystem {

    private static final String TASKLIST = "tasklist";

    public static void windowsProcessKiller() {
        ThreadImp killThreadImp = new ThreadImp();

        killThreadImp.start();
        try {
            killThreadImp.join();
        } catch (InterruptedException e) {
            ButtonUtils.error(e.getMessage());
            throw new RuntimeException(e);
        }

        ButtonUtils.information("is success ");
    }

    public static void killCommandCMD() {

        try {
            int killRequest = ButtonUtils.portKill();
            while (killRequest != 0) {
                killCommandCMD();
            }
            if (isProcessRunning("jprq-windows-386.exe")) {
                killProcess("jprq-windows-386.exe");
            }
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public static void killProcess(String serviceName) throws Exception {
        Runtime.getRuntime().exec("files/killFile/Elevate64.exe TASKKILL /F /IM " + serviceName);
    }


    public static boolean isProcessRunning(String serviceName) throws Exception {
        Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.contains(serviceName)) {
                System.err.println(line);
                return true;
            }
        }
        return false;
    }
}
