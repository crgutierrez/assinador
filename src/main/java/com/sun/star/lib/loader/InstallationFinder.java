//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sun.star.lib.loader;

import com.sun.star.lib.loader.WinRegKey;
import com.sun.star.lib.loader.WinRegKeyException;
import com.sun.star.lib.loader.WinRegKeyName;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public final class InstallationFinder {
    private static final String SYSPROP_NAME = "com.sun.star.lib.loader.unopath";
    public static final String UNO_PATH = "UNO_PATH";
    public static final String SOFFICE = "soffice";
    private static final List<WinRegKeyName> WIN_REGISTRY = new ArrayList();

    static {
        WIN_REGISTRY.add(new WinRegKeyName("HKEY_CURRENT_USER", "Software\\OpenOffice.org\\UNO\\InstallPath"));
        WIN_REGISTRY.add(new WinRegKeyName("HKEY_LOCAL_MACHINE", "Software\\OpenOffice.org\\UNO\\InstallPath"));
        WIN_REGISTRY.add(new WinRegKeyName("HKEY_CURRENT_USER", "Software\\LibreOffice\\UNO\\InstallPath"));
        WIN_REGISTRY.add(new WinRegKeyName("HKEY_LOCAL_MACHINE", "Software\\LibreOffice\\UNO\\InstallPath"));
    }

    private InstallationFinder() {
    }

    public static String getPath() {
        String path = null;
        path = getPathFromProperty("com.sun.star.lib.loader.unopath");
        if(path == null) {
            path = getPathFromEnvVar("UNO_PATH");
            if(path == null) {
                String osname = null;

                try {
                    osname = System.getProperty("os.name");
                } catch (SecurityException var3) {
                    return null;
                }

                if(osname != null) {
                    if(osname.startsWith("Windows")) {
                        path = getPathFromWindowsRegistry();
                    } else {
                        path = getPathFromPathEnvVar();
                        if(path == null) {
                            path = getPathFromWhich();
                            if(path == null) {
                                path = getPathFromSVersionFile();
                            }
                        }
                    }
                }
            }
        }

        return path;
    }

    private static String getPathFromProperty(String prop) {
        String path = null;

        try {
            path = System.getProperty(prop);
        } catch (SecurityException var3) {
            ;
        }

        return path;
    }

    private static String getPathFromEnvVar(String var) {
        String path = null;

        try {
            path = System.getenv(var);
        } catch (SecurityException var3) {
            ;
        } catch (Error var4) {
            ;
        }

        return path;
    }

    private static String getPathFromWindowsRegistry() {
        Iterator var1 = WIN_REGISTRY.iterator();

        while(var1.hasNext()) {
            WinRegKeyName winRegKeyName = (WinRegKeyName)var1.next();

            try {
                WinRegKey key = new WinRegKey(winRegKeyName);
                return key.getStringValue("");
            } catch (WinRegKeyException var3) {
                ;
            }
        }

        System.err.println("Erro: O caminho de instalação do OpenOffice não foi encontrado no registro do Windows");
        return null;
    }

    private static String getPathFromPathEnvVar() {
        String PATH_ENVVAR_NAME = "PATH";
        String path = null;
        String str = null;

        try {
            str = System.getenv("PATH");
        } catch (SecurityException var6) {
            return null;
        } catch (Error var7) {
            return null;
        }

        if(str != null) {
            StringTokenizer tokens = new StringTokenizer(str, File.pathSeparator);

            while(tokens.hasMoreTokens()) {
                File file = new File(tokens.nextToken(), "soffice");

                try {
                    if(file.exists()) {
                        try {
                            path = file.getCanonicalFile().getParent();
                            if(path != null) {
                                break;
                            }
                        } catch (IOException var8) {
                            System.err.println("com.sun.star.lib.loader.InstallationFinder::getPathFromEnvVar: bad path: " + var8);
                        }
                    }
                } catch (SecurityException var9) {
                    ;
                }
            }
        }

        return path;
    }

    private static String getPathFromWhich() {
        String WHICH = "which";
        String path = null;
        String[] cmdArray = new String[]{"which", "soffice"};
        Process proc = null;
        Runtime rt = Runtime.getRuntime();

        try {
            proc = rt.exec(cmdArray);
        } catch (SecurityException var27) {
            return null;
        } catch (IOException var28) {
            System.err.println("com.sun.star.lib.loader.InstallationFinder::getPathFromWhich: which command failed: " + var28);
            return null;
        }

        InstallationFinder.StreamGobbler gobbler = new InstallationFinder.StreamGobbler(proc.getErrorStream());
        gobbler.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;

        try {
            while((line = br.readLine()) != null) {
                if(path == null) {
                    int e = line.lastIndexOf("soffice");
                    if(e != -1) {
                        int end = e + "soffice".length();

                        for(int i = 0; i <= e; ++i) {
                            File file = new File(line.substring(i, end));

                            try {
                                if(file.exists()) {
                                    path = file.getCanonicalFile().getParent();
                                    if(path != null) {
                                        break;
                                    }
                                }
                            } catch (SecurityException var29) {
                                return null;
                            }
                        }
                    }
                }
            }
        } catch (IOException var30) {
            System.err.println("com.sun.star.lib.loader.InstallationFinder::getPathFromWhich: reading which command output failed: " + var30);
            return null;
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException var25) {
                    ;
                }
            }

        }

        try {
            proc.waitFor();
        } catch (InterruptedException var26) {
            proc.destroy();
            Thread.currentThread().interrupt();
        }

        return path;
    }

    private static String getPathFromSVersionFile() {
        String SVERSION = ".sversionrc";
        String VERSIONS = "[Versions]";
        String path = null;

        try {
            File e = new File(System.getProperty("user.home"), ".sversionrc");
            if(e.exists()) {
                Vector lines = new Vector();
                BufferedReader br = null;

                try {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(e), "UTF-8"));
                    String i = null;

                    while((i = br.readLine()) != null && !i.equals("[Versions]")) {
                        ;
                    }

                    while((i = br.readLine()) != null && i.length() != 0) {
                        if(!i.startsWith(";")) {
                            lines.add(i);
                        }
                    }
                } catch (IOException var17) {
                    System.err.println("com.sun.star.lib.loader.InstallationFinder::getPathFromSVersionFile: reading .sversionrc file failed: " + var17);
                } finally {
                    if(br != null) {
                        try {
                            br.close();
                        } catch (IOException var16) {
                            ;
                        }
                    }

                }

                for(int var20 = lines.size() - 1; var20 >= 0; --var20) {
                    StringTokenizer tokens = new StringTokenizer((String)lines.elementAt(var20), "=");
                    if(tokens.countTokens() == 2) {
                        String key = tokens.nextToken();
                        String url = tokens.nextToken();
                        path = getCanonicalPathFromFileURL(url);
                        if(path != null) {
                            break;
                        }
                    }
                }
            }

            return path;
        } catch (SecurityException var19) {
            return null;
        }
    }

    private static String getCanonicalPathFromFileURL(String oooUrl) {
        String prefix = "file://";
        if(oooUrl.length() >= prefix.length() && oooUrl.substring(0, prefix.length()).toLowerCase().equals(prefix)) {
            StringBuffer buf = new StringBuffer(prefix);
            int n = oooUrl.indexOf(47, prefix.length());
            if(n < 0) {
                n = oooUrl.length();
            }

            String host = oooUrl.substring(prefix.length(), n);
            if(host.length() != 0 && !host.toLowerCase().equals("localhost")) {
                return null;
            } else {
                buf.append(host);
                if(n == oooUrl.length()) {
                    buf.append('/');
                } else {
                    label99:
                    while(n < oooUrl.length()) {
                        buf.append('/');
                        ++n;
                        int url = oooUrl.indexOf(47, n);
                        if(url < 0) {
                            url = oooUrl.length();
                        }

                        while(true) {
                            while(true) {
                                if(n >= url) {
                                    continue label99;
                                }

                                char path = oooUrl.charAt(n);
                                switch(path) {
                                    case '#':
                                        break label99;
                                    case '$':
                                    default:
                                        buf.append(path);
                                        ++n;
                                        break;
                                    case '%':
                                        byte[] fragment = new byte[(url - n) / 3];

                                        int ret;
                                        for(ret = 0; oooUrl.length() - n > 2 && oooUrl.charAt(n) == 37; n += 3) {
                                            int file = Character.digit(oooUrl.charAt(n + 1), 16);
                                            int e = Character.digit(oooUrl.charAt(n + 2), 16);
                                            if(file < 0 || e < 0) {
                                                break;
                                            }

                                            int d = 16 * file + e;
                                            if(d == 47) {
                                                return null;
                                            }

                                            fragment[ret++] = (byte)d;
                                        }

                                        String var20;
                                        try {
                                            var20 = new String(fragment, 0, ret, "UTF-8");
                                        } catch (UnsupportedEncodingException var15) {
                                            return null;
                                        }

                                        buf.append(var20);
                                }
                            }
                        }
                    }
                }

                URL var16;
                try {
                    var16 = new URL(buf.toString());
                } catch (MalformedURLException var14) {
                    return null;
                }

                String var17 = var16.getFile();
                String var18 = var16.getRef();
                if(var18 != null) {
                    var17 = var17 + '#' + var18;
                }

                String var19 = null;
                File var21 = new File(var17, "soffice");

                try {
                    if(var21.isAbsolute() && var21.exists()) {
                        try {
                            var19 = var21.getCanonicalFile().getParent();
                        } catch (IOException var12) {
                            return null;
                        }
                    }

                    return var19;
                } catch (SecurityException var13) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private static final class StreamGobbler extends Thread {
        InputStream m_istream;

        StreamGobbler(InputStream istream) {
            this.m_istream = istream;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.m_istream));

                while(br.readLine() != null) {
                    ;
                }

                br.close();
            } catch (IOException var2) {
                ;
            }

        }
    }
}
