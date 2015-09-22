//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigCookie extends Config<String> {
    public static final String PARAM_NAME = "cookie";

    public ConfigCookie(JApplet applet) throws InitConfigException {
        super("cookie", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValue();
        if(paramValue != null && !paramValue.isEmpty()) {
            this.setValue(paramValue);
        } else {
            this.setValue(null);
        }

    }
}
