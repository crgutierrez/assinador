//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

public class BeanUtil {
    public BeanUtil() {
    }

    public static boolean verificaSeHouveAlteracao(Object v1, Object v2) {
        return v1 == null && v2 != null || v1 != null && !v1.equals(v2);
    }
}
