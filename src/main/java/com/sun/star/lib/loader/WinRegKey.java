//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sun.star.lib.loader;

import com.sun.star.lib.loader.WinRegKeyException;
import com.sun.star.lib.loader.WinRegKeyName;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

final class WinRegKey {
    private WinRegKeyName keyName;

    static {
        try {
            ClassLoader e = WinRegKey.class.getClassLoader();
            InputStream is = e.getResourceAsStream("com/googlecode/jlibreoffice/lib/unowinreg.dll");
            if(is != null) {
                File dirTmp = new File(System.getProperty("java.io.tmpdir"));
                File biblioteca = new File(dirTmp, "jlibreoffice_unowinreg.dll");
                if(!biblioteca.exists()) {
                    BufferedInputStream istream = new BufferedInputStream(is);
                    BufferedOutputStream ostream = new BufferedOutputStream(new FileOutputStream(biblioteca));
                    short bsize = 2048;
                    boolean n = false;
                    byte[] buffer = new byte[bsize];

                    int n1;
                    while((n1 = istream.read(buffer, 0, bsize)) != -1) {
                        ostream.write(buffer, 0, n1);
                    }

                    istream.close();
                    ostream.close();
                }

                System.load(biblioteca.getPath());
            } else {
                System.loadLibrary("unowinreg");
            }
        } catch (Exception var9) {
            System.err.println("com.sun.star.lib.loader.WinRegKey: loading of native library failed!" + var9);
        }

    }

    private static native boolean winreg_RegOpenClassesRoot(long[] var0);

    private static native boolean winreg_RegOpenCurrentConfig(long[] var0);

    private static native boolean winreg_RegOpenCurrentUser(long[] var0);

    private static native boolean winreg_RegOpenLocalMachine(long[] var0);

    private static native boolean winreg_RegOpenUsers(long[] var0);

    private static native boolean winreg_RegOpenKeyEx(long var0, String var2, long[] var3);

    private static native boolean winreg_RegCloseKey(long var0);

    private static native boolean winreg_RegQueryValueEx(long var0, String var2, long[] var3, byte[] var4, long[] var5);

    private static native boolean winreg_RegQueryInfoKey(long var0, long[] var2, long[] var3, long[] var4, long[] var5, long[] var6, long[] var7);

    public WinRegKey(WinRegKeyName keyName) {
        this.keyName = keyName;
    }

    public String getStringValue(String valueName) throws WinRegKeyException {
        byte[] data = this.getValue(valueName);
        return new String(data, 0, data.length - 1);
    }

    private byte[] getValue(String valueName) throws WinRegKeyException {
        byte[] result = (byte[])null;
        long[] hkey = new long[1];
        boolean bRet = false;
        long[] hroot = new long[1];
        if(this.keyName.getRootKeyName().equals("HKEY_CLASSES_ROOT")) {
            bRet = winreg_RegOpenClassesRoot(hroot);
        } else if(this.keyName.getRootKeyName().equals("HKEY_CURRENT_CONFIG")) {
            bRet = winreg_RegOpenCurrentConfig(hroot);
        } else if(this.keyName.getRootKeyName().equals("HKEY_CURRENT_USER")) {
            bRet = winreg_RegOpenCurrentUser(hroot);
        } else if(this.keyName.getRootKeyName().equals("HKEY_LOCAL_MACHINE")) {
            bRet = winreg_RegOpenLocalMachine(hroot);
        } else {
            if(!this.keyName.getRootKeyName().equals("HKEY_USERS")) {
                throw new WinRegKeyException("unknown root registry key!");
            }

            bRet = winreg_RegOpenUsers(hroot);
        }

        if(!bRet) {
            throw new WinRegKeyException("opening root registry key failed!");
        } else if(!winreg_RegOpenKeyEx(hroot[0], this.keyName.getSubKeyName(), hkey)) {
            if(!winreg_RegCloseKey(hroot[0])) {
                throw new WinRegKeyException("opening registry key and releasing root registry key handle failed!");
            } else {
                throw new WinRegKeyException("opening registry key failed!");
            }
        } else {
            long[] subkeys = new long[1];
            long[] maxSubkeyLen = new long[1];
            long[] values = new long[1];
            long[] maxValueNameLen = new long[1];
            long[] maxValueLen = new long[1];
            long[] secDescriptor = new long[1];
            if(!winreg_RegQueryInfoKey(hkey[0], subkeys, maxSubkeyLen, values, maxValueNameLen, maxValueLen, secDescriptor)) {
                if(winreg_RegCloseKey(hkey[0]) && winreg_RegCloseKey(hroot[0])) {
                    throw new WinRegKeyException("retrieving information about the registry key failed!");
                } else {
                    throw new WinRegKeyException("retrieving information about the registry key and releasing registry key handles failed!");
                }
            } else {
                byte[] buffer = new byte[(int)maxValueLen[0]];
                long[] size = new long[]{(long)buffer.length};
                long[] type = new long[]{0L};
                if(!winreg_RegQueryValueEx(hkey[0], valueName, type, buffer, size)) {
                    if(winreg_RegCloseKey(hkey[0]) && winreg_RegCloseKey(hroot[0])) {
                        throw new WinRegKeyException("retrieving data for the specified value name failed!");
                    } else {
                        throw new WinRegKeyException("retrieving data for the specified value name and releasing registry key handles failed!");
                    }
                } else if(winreg_RegCloseKey(hkey[0]) && winreg_RegCloseKey(hroot[0])) {
                    result = new byte[(int)size[0]];
                    System.arraycopy(buffer, 0, result, 0, (int)size[0]);
                    return result;
                } else {
                    throw new WinRegKeyException("releasing registry key handles failed!");
                }
            }
        }
    }
}
