//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor;

import br.com.atos.utils.swing.FileChooserUtils;
import br.jus.tjro.applet.comuns.util.UIManagerUtils;
import br.jus.tjro.editor.BaseEditorTjro;
import javax.swing.JApplet;

public class EditorTjro extends JApplet {
    private BaseEditorTjro editor;

    public EditorTjro() {
    }

    public void abrirDocumentoLocal() {
        this.editor.abrirDocumentoLocal();
    }

    public void abrirDocumentoLocal(String params) {
        this.editor.abrirDocumentoLocal(params);
    }

    public void abrirDocumentoRemoto(String arquivoNome, String arquivoUrl) {
        this.editor.abrirDocumentoRemoto(arquivoNome, arquivoUrl);
    }

    public void abrirDocumentoRemoto(String arquivoNome, String arquivoUrl, String params) {
        this.editor.abrirDocumentoRemoto(arquivoNome, arquivoUrl, params);
    }

    public void criarNovoDocumento() {
        this.editor.criarNovoDocumento();
    }

    public void criarNovoDocumento(String params) {
        this.editor.criarNovoDocumento(params);
    }

    public void init() {
        super.init();
        UIManagerUtils.configurarLookAndFeel();
        FileChooserUtils.traduzirFileChooser();
        this.editor = new BaseEditorTjro(this);
    }

    public boolean isConectadoLibreOffice() {
        return this.editor.isConectadoLibreOffice();
    }

    public boolean isDefinidoDocumentoRemoto() {
        return this.editor.isDefinidoDocumentoRemoto();
    }

    public boolean isEditandoDocumento() {
        return this.editor.isEditandoDocumento();
    }

    public boolean isEditandoDocumentoRemoto() {
        return this.editor.isEditandoDocumentoRemoto();
    }

    public void stop() {
        this.editor.stop();
        super.stop();
    }

    public void mostrarConfiguracao() {
        this.editor.mostrarConfiguracao();
    }
}
