//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public abstract class Config<E> {
    private String name;
    private String paramValue;
    private E value;

    public Config(String name, JApplet applet) throws InitConfigException {
        this.name = name;
        this.paramValue = applet.getParameter(name);
        this.initConfig(applet);
    }

    public String getName() {
        return this.name;
    }

    protected abstract void initConfig(JApplet var1) throws InitConfigException;

    public String getParamValue() {
        return this.paramValue;
    }

    protected String getParamValueFormatado() {
        String paramValue = this.getParamValue();
        if(paramValue != null) {
            paramValue = paramValue.trim();
            return paramValue.length() == 0?null:paramValue.toLowerCase();
        } else {
            return null;
        }
    }

    public E getValue() {
        return this.value;
    }

    public boolean isDeclarado() {
        return this.value != null;
    }

    protected void setValue(E value) {
        this.value = value;
    }

    public String print() {
        return this.getName() + ": " + (this.getParamValue() != null && this.getParamValue().length() > 0?this.getParamValue():"Não Declarado");
    }
}
