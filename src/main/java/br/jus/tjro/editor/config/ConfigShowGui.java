//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigShowGui extends Config<Boolean> {
    public static final String PARAM_NAME = "showGui";
    public static final String PARAM_VALUE_TRUE = "true";
    public static final String PARAM_VALUE_FALSE = "false";

    public ConfigShowGui(JApplet applet) throws InitConfigException {
        super("showGui", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            if(paramValue.equals("true")) {
                this.setValue(Boolean.valueOf(true));
            } else {
                if(!paramValue.equals("false")) {
                    throw new InitConfigException("O valores permitidos para a configuração (showGui) são [true, false]");
                }

                this.setValue(Boolean.valueOf(false));
            }
        } else {
            this.setValue(Boolean.valueOf(false));
        }

    }
}
