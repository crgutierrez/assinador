//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.util;

public class CertificadoUtils {
    public CertificadoUtils() {
    }

    public static String getCertificadoCN(String certificado) {
        if(certificado == null) {
            return "";
        } else {
            String[] splits = certificado.split(",");
            String resultado = "";

            for(int i = 0; i < splits.length; ++i) {
                String obj = splits[i].trim();
                if(obj.startsWith("CN=")) {
                    resultado = obj.substring(obj.indexOf("CN=") + 3);
                    break;
                }
            }

            return resultado;
        }
    }
}
