//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import java.net.URL;
import javax.swing.JApplet;

public class ConfigServerSession extends Config<URL> {
    public static final String PARAM_NAME = "serverSession";

    public ConfigServerSession(JApplet applet) throws InitConfigException {
        super("serverSession", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValue();
        if(paramValue != null && paramValue.length() > 0) {
            try {
                this.setValue(new URL(paramValue));
            } catch (Exception var4) {
                throw new InitConfigException("O valor para a configuração (serverSession) não é válido. Mensagem " + var4.getMessage());
            }
        }

    }
}
