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
        params.put("uploadFileTo", "http://localhost:4567/upload2");
                params.put(        "onCompleteUploadSignedFile", "http://www.google.com");
                params.put(       "usuario", "segundograu");
                params.put(        "fileFilters", "RTF,PDF");
                params.put(        "arquivo#0.nome" , "idoso.pdf");
                params.put(        "arquivo#0.url" , "http://www.mpro.mp.br/documents/10180/493536/cartilha+idoso.pdf/7a162c2e-1784-4f07-973b-e5314c77ec7d");
        params.put(        "arquivo#1.nome" , "idoso2.pdf");
        params.put(        "arquivo#1.url" , " http://www.mpro.mp.br/documents/10180/510155/Cartilha+do+Jurado+2014.pdf/b131601e-3864-43bb-9241-31ac8d5265b5");
        params.put(        "arquivo#2.nome" , "idoso3.pdf");
        params.put(        "arquivo#2.url" , "http://www.mpro.mp.br/documents/29274/30332/Cartilha+Lei+Maria+da+Penha+-+DIGA+NÃO+AO+MEDO+E+A+IMPUNIDADE+%28OK%29.pdf/efec8099-956d-467b-a90e-2e271147abc7");
        params.put(        "arquivo#3.nome" , "idoso4.pdf");
        params.put(        "arquivo#3.url" , "http://www.mp.ro.gov.br/documents/10180/510155/Planejamento+Estrategico+MPRO+2013-2015+OK+20-11-2013.pdf/cfdfcb08-ea02-41af-85c0-459b42efde1d");




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
