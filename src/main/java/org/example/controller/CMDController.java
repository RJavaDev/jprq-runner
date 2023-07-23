package org.example.controller;

import org.example.model.TokenAndPortModel;
import org.example.util.ButtonUtils;
import org.example.util.FileUtils;
import org.example.util.MessageWithLink;
import org.example.util.WriteCommandCMDUtils;
import org.example.validate.Validate;

import java.util.Hashtable;
import java.util.Objects;

public class CMDController {

    private static final TokenAndPortModel authToken = FileUtils.readFromFile();

    public static void runCommand() {


        if (Objects.nonNull(authToken)) {
            int updateOrRun = ButtonUtils.updateOrRun();
            if (updateOrRun == 1) {
                WriteCommandCMDUtils.writeCommand(authToken);
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
                WriteCommandCMDUtils.writeCommand(Objects.requireNonNull(FileUtils.readFromFile()));

            } else {

                if (ButtonUtils.isExit() == 0) {
                    System.exit(0);
                } else {
                    CMDController.newAuth(true);
                }

            }

        } else {

            TokenAndPortModel updateTokenAndPortModel = Validate.validateUpdateTokenAndPort(FileUtils.readFromFile(), auth);
            if(updateTokenAndPortModel != null){
                FileUtils.writeToFile(false, updateTokenAndPortModel);
                WriteCommandCMDUtils.writeCommand(Objects.requireNonNull(FileUtils.readFromFile()));
            }

        }

    }

}
