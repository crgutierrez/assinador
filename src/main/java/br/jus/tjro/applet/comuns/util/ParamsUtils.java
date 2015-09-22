//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.util;

import java.util.HashMap;

public class ParamsUtils {
    public ParamsUtils() {
    }

    public static HashMap<String, String> processar(String params) {
        HashMap resultado = new HashMap();
        if(params != null && params.length() != 0) {
            String[] indicesComValores = (String[])null;
            if(params.indexOf("&") != -1) {
                indicesComValores = params.split("&");
            } else {
                indicesComValores = new String[]{params};
            }

            String[] var6 = indicesComValores;
            int var5 = indicesComValores.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String indiceComValor = var6[var4];
                if(indiceComValor.contains("=")) {
                    String[] param = indiceComValor.split("=");
                    resultado.put(param[0], param[1]);
                }
            }
        }

        return resultado;
    }
}
