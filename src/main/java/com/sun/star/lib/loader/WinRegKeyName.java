//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sun.star.lib.loader;

public class WinRegKeyName {
    public static final String HKEY_CURRENT_USER = "HKEY_CURRENT_USER";
    public static final String HKEY_LOCAL_MACHINE = "HKEY_LOCAL_MACHINE";
    private String rootKeyName;
    private String subKeyName;

    public WinRegKeyName(String rootKeyName, String subKeyName) {
        this.rootKeyName = rootKeyName;
        this.subKeyName = subKeyName;
    }

    public String getRootKeyName() {
        return this.rootKeyName;
    }

    public String getSubKeyName() {
        return this.subKeyName;
    }

    public void setRootKeyName(String rootKeyName) {
        this.rootKeyName = rootKeyName;
    }

    public void setSubKeyName(String subKeyName) {
        this.subKeyName = subKeyName;
    }
}
