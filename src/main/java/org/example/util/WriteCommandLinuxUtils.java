package org.example.util;

import org.example.model.TokenAndPortModel;
import org.example.validate.CheckingJprqSystem;

public class WriteCommandLinuxUtils {

    public static void writeCommand(TokenAndPortModel auth) {
        CheckingJprqSystem.jprqIsInstalled();
    }
}
