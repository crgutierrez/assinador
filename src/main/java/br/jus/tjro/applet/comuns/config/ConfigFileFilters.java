//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.com.atos.utils.arquivo.ArquivoMetadado;
import br.com.atos.utils.swing.FileNameExtensionFilter;
import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JApplet;

public class ConfigFileFilters extends Config<List<FileNameExtensionFilter>> {
    public static final String PARAM_NAME = "fileFilters";

    public ConfigFileFilters(JApplet applet) throws InitConfigException {
        super("fileFilters", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            String[] exts = paramValue.split(",");
            if(exts.length > 0) {
                ArrayList filters = new ArrayList();
                if(exts.length > 1) {
                    filters.add(new FileNameExtensionFilter("Todos " + paramValue, exts));
                }

                String[] var8 = exts;
                int var7 = exts.length;

                for(int var6 = 0; var6 < var7; ++var6) {
                    String ext = var8[var6];
                    ext = ext.trim();
                    ArquivoMetadado arquivoMetadado = ArquivoMetadado.retrieveByExtensao(ext);
                    if(arquivoMetadado != null) {
                        filters.add(new FileNameExtensionFilter(arquivoMetadado.getFileFilter(), new String[]{ext}));
                    } else {
                        filters.add(new FileNameExtensionFilter(ext.toUpperCase(), new String[]{ext}));
                    }
                }

                this.setValue(filters);
            }
        }

    }
}
