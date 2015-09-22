//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.arquivo;

import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributoTipo;

public class ArquivoAtributo {
    private String id;
    private String nome;
    private ArquivoAtributoTipo tipo;

    public ArquivoAtributo(String id, String nome, ArquivoAtributoTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public ArquivoAtributoTipo getTipo() {
        return this.tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(ArquivoAtributoTipo tipo) {
        this.tipo = tipo;
    }
}
