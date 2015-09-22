//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.arquivo;

import br.jus.tjro.assinadortjro.arquivo.ArquivoItem;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ArquivoItemRemoto extends ArquivoItem {
    private File arquivoBaixado;
    private URL url;

    public ArquivoItemRemoto(URL url, String nome, HashMap<String, Object> atributos) {
        this.url = url;
        this.nome = nome;
        this.setAtributos(atributos);
    }

    public File getArquivoBaixado() {
        return this.arquivoBaixado;
    }

    public String getDescricao() {
        return this.url.toString();
    }

    public URL getUrl() {
        return this.url;
    }

    public void setArquivoBaixado(File arquivoBaixado) {
        this.arquivoBaixado = arquivoBaixado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public File getArquivoParaAssinatura() {
        return this.arquivoBaixado;
    }

    public boolean getFoiBaixadoArquivo() {
        return this.arquivoBaixado != null;
    }
}
