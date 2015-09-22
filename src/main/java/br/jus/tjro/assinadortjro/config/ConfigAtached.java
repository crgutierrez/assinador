//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigAtached extends Config<Boolean> {
    public static final String PARAM_NAME = "atached";
    public static final String PARAM_VALUE_TRUE = "true";
    public static final String PARAM_VALUE_FALSE = "false";

    public ConfigAtached(JApplet applet) throws InitConfigException {
        super("atached", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            if(paramValue.equals("true")) {
                this.setValue(Boolean.valueOf(true));
            } else {
                if(!paramValue.equals("false")) {
                    throw new InitConfigException("O valores permitidos para a configuração (atached) são [true, false]");
                }

                this.setValue(Boolean.valueOf(false));
            }
        } else {
            this.setValue(Boolean.valueOf(true));
        }

    }
}
