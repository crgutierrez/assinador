//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.config;

import br.jus.tjro.applet.comuns.config.ConfigCookie;
import br.jus.tjro.applet.comuns.config.ConfigDevelopment;
import br.jus.tjro.applet.comuns.config.ConfigFileFilters;
import br.jus.tjro.applet.comuns.config.ConfigOnCompleteUploadFile;
import br.jus.tjro.applet.comuns.config.ConfigOnCompleteUploadSignedFile;
import br.jus.tjro.applet.comuns.config.ConfigParams;
import br.jus.tjro.applet.comuns.config.ConfigServerSession;
import br.jus.tjro.applet.comuns.exception.InitConfigException;

import javax.swing.JApplet;
import org.apache.log4j.Logger;

public class Configs {
    private static final Logger log = Logger.getLogger(Configs.class);
    private ConfigConvertTo convertTo;
    private ConfigCookie cookie;
    private ConfigDevelopment development;
    private ConfigFileFilters fileFilters;
    private ConfigOnCompleteUploadFile onCompleteUploadFile;
    private ConfigOnCompleteUploadSignedFile onCompleteUploadSignedFile;
    private ConfigParams params;
    private ConfigServerSession serverSession;
    private ConfigShowGui showGui;
    private ConfigSign sign;
    private ConfigUploadFileTo uploadFileTo;
    private ConfigUploadSignedFileTo uploadSignedFileTo;

    public Configs(JApplet applet) throws InitConfigException {
        this.convertTo = new ConfigConvertTo(applet);
        this.cookie = new ConfigCookie(applet);
        this.development = new ConfigDevelopment(applet);
        this.fileFilters = new ConfigFileFilters(applet);
        this.onCompleteUploadFile = new ConfigOnCompleteUploadFile(applet);
        this.onCompleteUploadSignedFile = new ConfigOnCompleteUploadSignedFile(applet);
        this.params = new ConfigParams(applet);
        this.serverSession = new ConfigServerSession(applet);
        this.showGui = new ConfigShowGui(applet);
        this.sign = new ConfigSign(applet);
        this.uploadFileTo = new ConfigUploadFileTo(applet);
        this.uploadSignedFileTo = new ConfigUploadSignedFileTo(applet);
    }

    public ConfigConvertTo getConvertTo() {
        return this.convertTo;
    }

    public ConfigCookie getCookie() {
        return this.cookie;
    }

    public ConfigDevelopment getDevelopment() {
        return this.development;
    }

    public ConfigFileFilters getFileFilters() {
        return this.fileFilters;
    }

    public ConfigOnCompleteUploadFile getOnCompleteUploadFile() {
        return this.onCompleteUploadFile;
    }

    public ConfigOnCompleteUploadSignedFile getOnCompleteUploadSignedFile() {
        return this.onCompleteUploadSignedFile;
    }

    public ConfigParams getParams() {
        return this.params;
    }

    public ConfigServerSession getServerSession() {
        return this.serverSession;
    }

    public ConfigShowGui getShowGui() {
        return this.showGui;
    }

    public ConfigSign getSign() {
        return this.sign;
    }

    public ConfigUploadFileTo getUploadFileTo() {
        return this.uploadFileTo;
    }

    public ConfigUploadSignedFileTo getUploadSignedFileTo() {
        return this.uploadSignedFileTo;
    }

    public void showConfigs() {
        log.info("");
        log.info("---------------------------------------------");
        log.info("--- Configs ");
        log.info("---------------------------------------------");
        log.info("     " + this.getConvertTo().print());
        log.info("     " + this.getCookie().print());
        log.info("     " + this.getDevelopment().print());
        log.info("     " + this.getFileFilters().print());
        log.info("     " + this.getOnCompleteUploadFile().print());
        log.info("     " + this.getOnCompleteUploadSignedFile().print());
        log.info("     " + this.getParams().print());
        log.info("     " + this.getServerSession().print());
        log.info("     " + this.getShowGui().print());
        log.info("     " + this.getSign().print());
        log.info("     " + this.getUploadFileTo().print());
        log.info("     " + this.getUploadSignedFileTo().print());
        log.info("--------------------------------------------");
        log.info("");
    }
}
