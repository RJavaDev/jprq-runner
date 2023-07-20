package org.example.util;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class ButtonUtils {

    public static int portKill() {
        String[] response = {"Yes"};

        return JOptionPane.showOptionDialog(
                null,
                "Do you want to close the port?",
                "close port",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                response,
                0);
    }

    public static int ok(String message){
        String[] response = {"OK"};

        return JOptionPane.showOptionDialog(
                null,
                message,
                "Info",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                response,
                0);
    }

    public static void error(String information){
        JOptionPane.showMessageDialog(null, information, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static int updateOrRun(){
        String[] response = {"update data", "Run"};

        return JOptionPane.showOptionDialog(
                null,
                "controller",
                "run or update?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                response,
                0);
    }

    public static Hashtable<String, String> authInputButton() {
        Hashtable<String, String> information = new Hashtable<>();

        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("open port", SwingConstants.RIGHT));
        label.add(new JLabel("auth token", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "login", JOptionPane.QUESTION_MESSAGE);

        information.put("port", username.getText());
        information.put("auth", new String(password.getPassword()));

        System.out.println(information.get("port") + " /" + information.get("auth"));

        return information;
    }

    public static void information(String message){
        JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int isExit() {
        String[] response = {"YES"};

        return JOptionPane.showOptionDialog(
                null,
                "System exit do you want?",
                "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                response,
                0);
    }
}
