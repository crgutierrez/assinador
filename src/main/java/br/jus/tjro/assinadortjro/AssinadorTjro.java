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
import java.util.HashMap;
import javax.swing.JApplet;

public class AssinadorTjro extends JApplet {
    private BaseAssinadorTjro assinadorApplet;
    private HashMap<String,String> params= new HashMap<>();

    public AssinadorTjro() {
        params.put("uploadSignedFileTo", "http://localhost:4567/upload");
        params.put("cookie", "JSESSIONID=ObOqfk5To8BBNiqUvfobTDDY.sdsg2.3");
                params.put(        "onCompleteUploadSignedFile", "http://www.google.com");
                params.put(       "usuario", "segundograu");
                params.put(        "fileFilters", "RTF,PDF");
                params.put(        "arquivo#0.nome" , "idoso.pdf");
                params.put(        "arquivo#0.url" , "http://www.mpro.mp.br/documents/10180/493536/cartilha+idoso.pdf/7a162c2e-1784-4f07-973b-e5314c77ec7d");
    }


    public String getParameter(String name) {
        return params.get(name);
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
        super.init();
        UIManagerUtils.configurarLookAndFeel();
        FileChooserUtils.traduzirFileChooser();

        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                AssinadorTjro.this.assinadorApplet = new BaseAssinadorTjro(AssinadorTjro.this);
                return null;
            }
        });
    }

    public void mostrarConfiguracao() {
        this.assinadorApplet.mostrarConfiguracao();
    }

    public void visualizarArquivoSelecionado() {
        this.assinadorApplet.visualizarArquivoSelecionado();
    }
}
