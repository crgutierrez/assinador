//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet;

import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.applet.comuns.gui.WinBarraProgresso;
import br.jus.tjro.assinadortjro.BaseAssinadorTjro;
import br.jus.tjro.editor.BaseEditorTjro;
import com.googlecode.assinador.Assinador;
import com.googlecode.assinador.interfaces.AssinadorLog;
import com.googlecode.assinador.interfaces.AssinadorProgresso;
import javax.swing.JApplet;
import org.apache.log4j.Logger;

public class BaseAppletTjro implements AssinadorProgresso, AssinadorLog {
    private static final Logger log = Logger.getLogger(BaseEditorTjro.class);
    public static final String NOME = "AppletTjro";
    public static final String VERSAO = "1.1.6";
    public static final String TITULO = "AppletTjro - Versão: 1.1.6";
    private Assinador assinador;
    private BaseAssinadorTjro assinadorTjro;
    private BaseEditorTjro editorTjro;
    private WinBarraProgresso winBarraProgresso = new WinBarraProgresso();

    public BaseAppletTjro(JApplet applet) {
        log.info("---------------------------------------------");
        log.info("--- AppletTjro - Versão: 1.1.6");
        log.info("---------------------------------------------");

        try {
            this.assinador = new Assinador();
            this.winBarraProgresso = new WinBarraProgresso();
            this.editorTjro = new BaseEditorTjro(applet, this.winBarraProgresso, this.assinador);
            this.assinadorTjro = new BaseAssinadorTjro(applet, this.winBarraProgresso, this.assinador);
        } catch (Exception var3) {
            log.error(var3);
            JFrameUtils.showErro("Erro na inicialização da configuração", var3.getMessage());
        }

    }

    public BaseAssinadorTjro getAssinadorTjro() {
        return this.assinadorTjro;
    }

    public BaseEditorTjro getEditorTjro() {
        return this.editorTjro;
    }

    public void stop() {
        if(this.getEditorTjro() != null) {
            this.getEditorTjro().stop();
        }

    }

    public void mostrarConfiguracao() {
        this.assinador.mostrarConfiguracao();
    }

    public void imprimirLog(String mensagem) {
        log.info(mensagem);
    }

    public void imprimirProgresso(String mensagem) {
        this.winBarraProgresso.imprimir(mensagem);
    }
}
