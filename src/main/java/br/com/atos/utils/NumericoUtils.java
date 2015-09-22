//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

public class NumericoUtils {
    public NumericoUtils() {
    }

    public static boolean isInteger(String str) {
        boolean isInteger = true;

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException var3) {
            isInteger = false;
        }

        return isInteger;
    }
}
