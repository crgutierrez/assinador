//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SystemUtils {
    private static final String OS_LINUX = "Linux";
    private static final String OS_WINDOWS = "Windows";
    private static final String PROPERTY_OS_NAME = "os.name";

    public SystemUtils() {
    }

    private static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getTmpDirPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static boolean isOsLinux() {
        return getOsName().startsWith("Linux");
    }

    public static boolean isOsWindows() {
        return getOsName().startsWith("Windows");
    }

    public static String getEnvVar(String envVar) {
        String path = null;

        try {
            path = System.getenv(envVar);
        } catch (SecurityException var3) {
            ;
        } catch (Error var4) {
            ;
        }

        return path;
    }

    public static void putEnvVar(String key, String value) {
        HashMap newenv = new HashMap();
        newenv.putAll(System.getenv());
        newenv.put(key, value);

        Map env;
        try {
            Class e1 = Class.forName("java.lang.ProcessEnvironment");
            Field var16 = e1.getDeclaredField("theEnvironment");
            var16.setAccessible(true);
            env = (Map)var16.get((Object)null);
            env.putAll(newenv);
            Field var17 = e1.getDeclaredField("theCaseInsensitiveEnvironment");
            var17.setAccessible(true);
            Map var18 = (Map)var17.get((Object)null);
            var18.putAll(newenv);
        } catch (NoSuchFieldException var14) {
            try {
                Class[] e2 = Collections.class.getDeclaredClasses();
                env = System.getenv();
                Class[] var9 = e2;
                int var8 = e2.length;

                for(int cienv = 0; cienv < var8; ++cienv) {
                    Class cl = var9[cienv];
                    if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                        Field field = cl.getDeclaredField("m");
                        field.setAccessible(true);
                        Object obj = field.get(env);
                        Map map = (Map)obj;
                        map.clear();
                        map.putAll(newenv);
                    }
                }
            } catch (Exception var13) {
                var13.printStackTrace();
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }
}
