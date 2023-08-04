package org.example.threadImp;

import org.example.util.ButtonUtils;
import org.example.util.MessageWithLink;
import org.example.util.SystemName;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadImp extends Thread {

    private static final String TASKLIST = "tasklist";

    private static final SystemName system = new SystemName();

    @Override
    public void run() {
        killCommandCMD();
    }

    private static void killCommandCMD() {

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

    public static void killRun(String information, String openPortLink) {
        int ok = MessageWithLink.okLink("https://" + openPortLink, openPortLink, information);
        if (ok == 0) {
            if(system.isLinux()){
                linuxProcessKiller();
            }else {
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

        } else {
            killRun(information, openPortLink);
        }
    }

    public static void linuxProcessKiller() {

        String processName = "jprq"; // O'chirishni istagan protsess nomi

        try {
            Process p = Runtime.getRuntime().exec("pgrep " + processName);
            p.waitFor();

            if (p.exitValue() == 0) {
                // Process topildi, o'chirish amalga oshiriladi
                Process killProcess = Runtime.getRuntime().exec("pkill " + processName);
                killProcess.waitFor();

                if (killProcess.exitValue() == 0) {
                    System.out.println("Protsess muvaffaqiyatli o'chirildi.");
                } else {
                    System.out.println("Protsess o'chirilmadi.");
                }
                System.exit(0);
            } else {
                System.out.println("Protsess topilmadi.");
            }
        } catch (IOException | InterruptedException e) {

            ButtonUtils.error(e.getMessage());
            System.exit(0);
            e.printStackTrace();
        }
    }

}
