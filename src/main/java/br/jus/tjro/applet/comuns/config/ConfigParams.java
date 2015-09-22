//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import br.jus.tjro.applet.comuns.vo.Param;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JApplet;

public class ConfigParams extends Config<List<Param>> {
    public static final String PARAM_NAME = "params";

    public ConfigParams(JApplet applet) throws InitConfigException {
        super("params", applet);
    }

    protected void initConfig(JApplet applet) {
        String paramValue = this.getParamValue();
        if(paramValue != null && paramValue.length() > 0) {
            ArrayList params = new ArrayList();
            String[] param;
            if(paramValue.indexOf("&") != -1) {
                param = paramValue.split("&");
                String[] var8 = param;
                int var7 = param.length;

                for(int var6 = 0; var6 < var7; ++var6) {
                    String indiceComValor = var8[var6];
                    String[] param1 = indiceComValor.split("=");
                    if(param1.length == 2) {
                        params.add(new Param(param1[0], param1[1]));
                    }
                }
            } else {
                param = paramValue.split("=");
                if(param.length == 2) {
                    params.add(new Param(param[0], param[1]));
                }
            }

            if(params.size() > 0) {
                this.setValue(params);
            }
        }

    }
}
