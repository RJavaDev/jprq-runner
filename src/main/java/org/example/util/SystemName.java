package org.example.util;

import org.example.constant.SystemsName;

public class SystemName {

    private final String name = System.getProperty("os.name");

    public String getName() {
        return name;
    }

    public boolean isWindows(){
        return name.equals(SystemsName.WINDOWS);
    }

    public boolean isLinux(){
        return name.equals(SystemsName.LINUX);
    }

    public boolean isMac(){
        return name.equals(SystemsName.MOC);
    }
}
