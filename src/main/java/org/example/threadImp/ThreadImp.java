package org.example.threadImp;

import org.example.processKil.KillWindowsSystem;

public class ThreadImp implements Runnable {

    @Override
    public void run() {
        KillWindowsSystem.killCommandCMD();
    }
}
