package org.example.threadImp;

import org.example.processKil.KillWindowsSystem;

public class ThreadImp extends Thread {

    @Override
    public void run() {
        KillWindowsSystem.killCommandCMD();
    }
}
