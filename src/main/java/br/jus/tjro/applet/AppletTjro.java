//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet;

import br.com.atos.utils.swing.FileChooserUtils;
import br.jus.tjro.applet.BaseAppletTjro;
import br.jus.tjro.applet.comuns.util.UIManagerUtils;
import javax.swing.JApplet;

public class AppletTjro extends JApplet {
    private static final long serialVersionUID = 1L;
    private BaseAppletTjro appletTjro;

    public AppletTjro() {
    }

    public void init() {
        super.init();
        UIManagerUtils.configurarLookAndFeel();
        FileChooserUtils.traduzirFileChooser();
        this.appletTjro = new BaseAppletTjro(this);
    }

    public void stop() {
        this.appletTjro.stop();
        super.stop();
    }

    public void criarNovoDocumento() {
        this.appletTjro.getEditorTjro().criarNovoDocumento();
    }

    public void criarNovoDocumento(String params) {
        this.appletTjro.getEditorTjro().criarNovoDocumento(params);
    }

    public void abrirDocumentoLocal() {
        this.appletTjro.getEditorTjro().abrirDocumentoLocal();
    }

    public void abrirDocumentoLocal(String params) {
        this.appletTjro.getEditorTjro().abrirDocumentoLocal(params);
    }

    public void abrirDocumentoRemoto(String arquivoNome, String arquivoUrl) {
        this.appletTjro.getEditorTjro().abrirDocumentoRemoto(arquivoNome, arquivoUrl);
    }

    public void abrirDocumentoRemoto(String arquivoNome, String arquivoUrl, String arquivoParams) {
        this.appletTjro.getEditorTjro().abrirDocumentoRemoto(arquivoNome, arquivoUrl, arquivoParams);
    }

    public String addArquivoLocal() {
        return this.appletTjro.getAssinadorTjro().addArquivoLocal();
    }

    public String addArquivoLocal(String params) {
        return this.appletTjro.getAssinadorTjro().addArquivoLocal(params);
    }

    public void addArquivoRemoto(String arquivoNome, String url) {
        this.appletTjro.getAssinadorTjro().addArquivoRemoto(arquivoNome, url);
    }

    public void addArquivoRemoto(String arquivoNome, String url, String params) {
        this.appletTjro.getAssinadorTjro().addArquivoRemoto(arquivoNome, url, params);
    }

    public void assinarArquivos() {
        this.appletTjro.getAssinadorTjro().assinarArquivos();
    }

    public void excluirArquivo(String indexValue) {
        this.appletTjro.getAssinadorTjro().excluirArquivo(indexValue);
    }

    public void excluirArquivosSelecionados() {
        this.appletTjro.getAssinadorTjro().excluirArquivosSelecionados();
    }

    public void excluirTodosArquivos() {
        this.appletTjro.getAssinadorTjro().excluirTodosArquivos();
    }

    public void mostrarConfiguracao() {
        this.appletTjro.mostrarConfiguracao();
    }

    public void visualizarArquivoSelecionado() {
        this.appletTjro.getAssinadorTjro().visualizarArquivoSelecionado();
    }
}
