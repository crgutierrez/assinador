//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.config;

import br.jus.tjro.applet.comuns.config.Config;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import javax.swing.JApplet;

public class ConfigSelectionMode extends Config<Integer> {
    public static final String PARAM_NAME = "selectionMode";
    public static final String PARAM_VALUE_FILES = "files";
    public static final String PARAM_VALUE_DIRECTORIES = "directories";
    public static final String PARAM_VALUE_FILES_AND_DIRECTORIES = "files_and_directories";

    public ConfigSelectionMode(JApplet applet) throws InitConfigException {
        super("selectionMode", applet);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        String paramValue = this.getParamValueFormatado();
        if(paramValue != null) {
            if(paramValue.equals("files")) {
                this.setValue(Integer.valueOf(0));
            } else if(paramValue.equals("directories")) {
                this.setValue(Integer.valueOf(1));
            } else {
                if(!paramValue.equals("files_and_directories")) {
                    throw new InitConfigException("O valores permitidos para a configuração (selectionMode) são [files, directories, files_and_directories]");
                }

                this.setValue(Integer.valueOf(2));
            }
        } else {
            this.setValue(Integer.valueOf(0));
        }

    }
}
