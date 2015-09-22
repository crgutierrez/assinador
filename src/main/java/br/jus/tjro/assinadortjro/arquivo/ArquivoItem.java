//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.arquivo;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public abstract class ArquivoItem {
    private HashMap<String, Object> atributos = new HashMap();
    protected String nome;

    public ArquivoItem() {
    }

    public abstract File getArquivoParaAssinatura();

    public abstract URL getUrl();

    public abstract String getDescricao();

    public String getNome() {
        return this.nome;
    }

    public HashMap<String, Object> getAtributos() {
        return this.atributos;
    }

    public void setAtributos(HashMap<String, Object> atributos) {
        this.atributos = atributos;
    }
}
