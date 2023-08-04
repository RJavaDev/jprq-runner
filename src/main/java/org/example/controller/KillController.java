package org.example.controller;

import org.example.processKil.KillLinuxSystem;
import org.example.processKil.KillWindowsSystem;
import org.example.util.MessageWithLink;
import org.example.util.SystemName;

public class KillController {

    private static final SystemName system = new SystemName();

    public static void killRun(String information, String openPortLink) {
        int ok = MessageWithLink.okLink("https://" + openPortLink, openPortLink, information);
        if (ok == 0) {
            if (system.isWindows()) {
                KillWindowsSystem.windowsProcessKiller();
            } else if (system.isLinux()) {
                KillLinuxSystem.linuxProcessKiller();
            }

        } else {
            killRun(information, openPortLink);
        }
    }
}
