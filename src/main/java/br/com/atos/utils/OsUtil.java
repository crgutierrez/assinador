//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

public class OsUtil {
    private static final String OS_LINUX = "Linux";
    private static final String OS_WINDOWS = "Windows";
    private static final String PROPERTY_OS_NAME = "os.name";
    private static final String PROPERTY_OS_ARCH = "os.arch";

    public OsUtil() {
    }

    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static boolean isOsLinux() {
        return getOsName().startsWith("Linux");
    }

    public static boolean isOsWindows() {
        return getOsName().startsWith("Windows");
    }
}
