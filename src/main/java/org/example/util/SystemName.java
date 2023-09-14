package org.example.util;

import org.example.constant.SystemsName;

public class SystemName {

    private final String name = System.getProperty("os.name");

    public String getName() {
        return name;
    }

    public boolean isWindows() {
        return name.contains(SystemsName.WINDOWS);
    }

    public boolean isLinux() {
        return name.contains(SystemsName.LINUX);
    }

    public boolean isMac() {
        return name.contains(SystemsName.MAC);
    }
}
