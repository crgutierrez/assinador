//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro;

import br.com.atos.utils.swing.FileChooserUtils;
import br.jus.tjro.applet.comuns.util.UIManagerUtils;
import br.jus.tjro.assinadortjro.BaseAssinadorTjro;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.swing.*;

public class AssinadorTjro extends JFrame {
    private BaseAssinadorTjro assinadorApplet;

    public AssinadorTjro() {
        init();
    }

    public String addArquivoLocal() {
        return this.assinadorApplet.addArquivoLocal();
    }

    public String addArquivoLocal(String params) {
        return this.assinadorApplet.addArquivoLocal(params);
    }

    public void addArquivoRemoto(String arquivoNome, String url) {
        this.assinadorApplet.addArquivoRemoto(arquivoNome, url);
    }

    public void addArquivoRemoto(String arquivoNome, String url, String params) {
        this.assinadorApplet.addArquivoRemoto(arquivoNome, url, params);
    }

    public void assinarArquivos() {
        this.assinadorApplet.assinarArquivos();
    }

    public void excluirArquivo(String indexValue) {
        this.assinadorApplet.excluirArquivo(indexValue);
    }

    public void excluirArquivosSelecionados() {
        this.assinadorApplet.excluirArquivosSelecionados();
    }

    public void excluirTodosArquivos() {
        this.assinadorApplet.excluirTodosArquivos();
    }

    public void init() {
      //  super.init();
        UIManagerUtils.configurarLookAndFeel();
        FileChooserUtils.traduzirFileChooser();
//        AccessController.doPrivileged(new PrivilegedAction() {
//            public Object run() {
//                AssinadorTjro.this.assinadorApplet = new BaseAssinadorTjro(AssinadorTjro.this);
//                return null;
//            }
//        });
    }

    public void mostrarConfiguracao() {
        this.assinadorApplet.mostrarConfiguracao();
    }

    public void visualizarArquivoSelecionado() {
        this.assinadorApplet.visualizarArquivoSelecionado();
    }
}
