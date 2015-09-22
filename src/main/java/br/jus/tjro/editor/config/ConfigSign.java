//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigSign extends Config<Boolean> {
    public static final String PARAM_NAME = "sign";
    public static final String PARAM_VALUE_TRUE = "true";
    public static final String PARAM_VALUE_FALSE = "false";

    public ConfigSign(JApplet applet) throws InitConfigException {
        super("sign", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            if("true".equals(paramValue.trim().toLowerCase())) {
                this.setValue(Boolean.valueOf(true));
            } else {
                if(!"false".equals(paramValue.trim().toLowerCase())) {
                    throw new InitConfigException("O valores permitidos para a configuração (sign) são [true, false] o valor padrão é true");
                }

                this.setValue(Boolean.valueOf(false));
            }
        } else {
            this.setValue(Boolean.valueOf(false));
        }

    }
}
