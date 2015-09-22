//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigConvertTo extends Config<String> {
    public static final String PARAM_NAME = "convertTo";
    public static final String PARAM_VALUE_PDF = "pdf";

    public ConfigConvertTo(JApplet applet) throws InitConfigException {
        super("convertTo", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            if(!"pdf".equals(paramValue)) {
                throw new InitConfigException("O valores permitidos para a configuração (convertTo) são [NULL, pdf]");
            }

            this.setValue("pdf");
        }

    }
}
