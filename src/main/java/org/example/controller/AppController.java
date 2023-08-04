package org.example.controller;

import org.example.model.TokenAndPortModel;
import org.example.util.*;
import org.example.validate.InstallJprqSystem;
import org.example.validate.Validate;

import java.util.Hashtable;
import java.util.Objects;

public class AppController {

    private static final TokenAndPortModel authToken = FileUtils.readFromFile();

    private static final SystemName systemName = new SystemName();

    public static void runCommand() {


        if (Objects.nonNull(authToken)) {
            int updateOrRun = ButtonUtils.updateOrRun();
            if (updateOrRun == 1) {

                ButtonUtils.information("the program started working on the " + systemName.getName() + " system");
                orientationSystem(false);
            } else if (updateOrRun == 0) {
                newAuth(false);
            } else {
                System.exit(0);
            }
        } else {
            MessageWithLink.openLink();
            newAuth(true);
        }

    }

    public static void newAuth(boolean isNew) {
        Hashtable<String, String> authData = ButtonUtils.authInputButton();
        if (!authData.isEmpty()) {
            TokenAndPortModel model = new TokenAndPortModel();
            model.setPort(authData.get("port"));
            model.setToken(authData.get("auth"));
            authManagement(model, isNew);
        }
    }

    public static void authManagement(TokenAndPortModel auth, boolean isNew) {

        if (isNew) {

            if (Validate.validateTokenAndPort(auth, true)) {
                FileUtils.writeToFile(true, auth);
                orientationSystem(true);
            } else {

                if (ButtonUtils.isExit() == 0) {
                    System.exit(0);
                } else {
                    AppController.newAuth(true);
                }
            }

        } else {

            TokenAndPortModel updateTokenAndPortModel = Validate.validateUpdateTokenAndPort(FileUtils.readFromFile(), auth);
            if (updateTokenAndPortModel != null) {
                FileUtils.writeToFile(false, updateTokenAndPortModel);
                orientationSystem(false);
            }

        }

    }

    public static void orientationSystem(boolean isNew) {
        TokenAndPortModel tokenAndPort = Objects.requireNonNull(FileUtils.readFromFile());
        if (systemName.isWindows()) {
            WriteCommandWindowsUtils.writeCommand(tokenAndPort);
        } else if (systemName.isLinux()) {
            if (isNew) {
                InstallJprqSystem.jprqInstalled();
            }
            WriteCommandLinuxUtils.writeCommand(tokenAndPort);
        }
    }

}
