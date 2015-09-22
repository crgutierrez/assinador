//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.config;

import br.jus.tjro.applet.comuns.config.ConfigCookie;
import br.jus.tjro.applet.comuns.config.ConfigDevelopment;
import br.jus.tjro.applet.comuns.config.ConfigFileFilters;
import br.jus.tjro.applet.comuns.config.ConfigOnCompleteUploadSignedFile;
import br.jus.tjro.applet.comuns.config.ConfigParams;
import br.jus.tjro.applet.comuns.config.ConfigSelectionMode;
import br.jus.tjro.applet.comuns.config.ConfigSelectionMultiple;
import br.jus.tjro.applet.comuns.exception.InitConfigException;
import br.jus.tjro.assinadortjro.config.ConfigArquivoItens;
import br.jus.tjro.assinadortjro.config.ConfigAtached;
import br.jus.tjro.assinadortjro.config.ConfigPermiteAddArquivo;
import br.jus.tjro.assinadortjro.config.ConfigUploadSignedFileTo;
import javax.swing.JApplet;
import org.apache.log4j.Logger;

public class Configs {
    private static final Logger log = Logger.getLogger(Configs.class);
    private ConfigArquivoItens arquivoItens;
    private ConfigAtached atached;
    private ConfigCookie cookie;
    private ConfigDevelopment development;
    private ConfigFileFilters fileFilters;
    private ConfigOnCompleteUploadSignedFile onCompleteUploadSignedFile;
    private ConfigPermiteAddArquivo permiteAddArquivo;
    private ConfigParams params;
    private ConfigSelectionMode selectionMode;
    private ConfigSelectionMultiple selectionMultiple;
    private ConfigUploadSignedFileTo uploadSignedFileTo;

    public Configs(JApplet applet) throws InitConfigException {
        this.arquivoItens = new ConfigArquivoItens(applet);
        this.atached = new ConfigAtached(applet);
        this.cookie = new ConfigCookie(applet);
        this.development = new ConfigDevelopment(applet);
        this.fileFilters = new ConfigFileFilters(applet);
        this.onCompleteUploadSignedFile = new ConfigOnCompleteUploadSignedFile(applet);
        this.params = new ConfigParams(applet);
        this.permiteAddArquivo = new ConfigPermiteAddArquivo(applet);
        this.selectionMode = new ConfigSelectionMode(applet);
        this.selectionMultiple = new ConfigSelectionMultiple(applet);
        this.uploadSignedFileTo = new ConfigUploadSignedFileTo(applet);
    }

    public ConfigCookie getCookie() {
        return this.cookie;
    }

    public ConfigDevelopment getDevelopment() {
        return this.development;
    }

    public ConfigArquivoItens getArquivoItens() {
        return this.arquivoItens;
    }

    public ConfigAtached getAtached() {
        return this.atached;
    }

    public ConfigFileFilters getFileFilters() {
        return this.fileFilters;
    }

    public ConfigOnCompleteUploadSignedFile getOnCompleteUploadSignedFile() {
        return this.onCompleteUploadSignedFile;
    }

    public ConfigParams getParams() {
        return this.params;
    }

    public ConfigSelectionMode getSelectionMode() {
        return this.selectionMode;
    }

    public ConfigSelectionMultiple getSelectionMultiple() {
        return this.selectionMultiple;
    }

    public ConfigPermiteAddArquivo getPermiteAddArquivo() {
        return this.permiteAddArquivo;
    }

    public ConfigUploadSignedFileTo getUploadSignedFileTo() {
        return this.uploadSignedFileTo;
    }

    public void showConfigs() {
        log.info("");
        log.info("---------------------------------------------");
        log.info("--- Configs ");
        log.info("---------------------------------------------");
        log.info("     " + this.getArquivoItens().print());
        log.info("     " + this.getAtached().print());
        log.info("     " + this.getCookie().print());
        log.info("     " + this.getDevelopment().print());
        log.info("     " + this.getFileFilters().print());
        log.info("     " + this.getOnCompleteUploadSignedFile().print());
        log.info("     " + this.getParams().print());
        log.info("     " + this.getPermiteAddArquivo().print());
        log.info("     " + this.getSelectionMode().print());
        log.info("     " + this.getSelectionMultiple().print());
        log.info("     " + this.getUploadSignedFileTo().print());
        log.info("--------------------------------------------");
        log.info("");
    }
}
