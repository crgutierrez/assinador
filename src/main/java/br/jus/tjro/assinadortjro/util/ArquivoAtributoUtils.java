//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.util;

import br.jus.tjro.applet.comuns.util.ParamsUtils;
import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributo;
import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributoTipo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ArquivoAtributoUtils {
    public ArquivoAtributoUtils() {
    }

    private static ArquivoAtributo recuperarArquivoAtributoPorId(String id, List<ArquivoAtributo> arquivoAtributos) {
        Iterator var3 = arquivoAtributos.iterator();

        while(var3.hasNext()) {
            ArquivoAtributo arquivoAtributo = (ArquivoAtributo)var3.next();
            if(arquivoAtributo.getId().equals(id)) {
                return arquivoAtributo;
            }
        }

        return null;
    }

    public static HashMap<String, Object> processar(String params, List<ArquivoAtributo> arquivoAtributos) {
        HashMap atributos = new HashMap();
        HashMap paramsProcessado = ParamsUtils.processar(params);
        Iterator var5 = paramsProcessado.keySet().iterator();

        while(var5.hasNext()) {
            String paramKey = (String)var5.next();
            String paramValue = (String)paramsProcessado.get(paramKey);
            ArquivoAtributo arquivoAtributo = recuperarArquivoAtributoPorId(paramKey, arquivoAtributos);
            if(arquivoAtributo != null) {
                if(ArquivoAtributoTipo.BOOLEAN.equals(arquivoAtributo.getTipo())) {
                    atributos.put(arquivoAtributo.getId(), Boolean.valueOf(paramValue));
                } else if(ArquivoAtributoTipo.INTEGER.equals(arquivoAtributo.getTipo())) {
                    atributos.put(arquivoAtributo.getId(), Integer.valueOf(paramValue));
                } else {
                    atributos.put(arquivoAtributo.getId(), paramValue);
                }
            } else {
                atributos.put(paramKey, paramValue);
            }
        }

        return atributos;
    }
}
