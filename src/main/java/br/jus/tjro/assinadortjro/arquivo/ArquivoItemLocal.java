//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.arquivo;

import br.jus.tjro.assinadortjro.arquivo.ArquivoItem;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ArquivoItemLocal extends ArquivoItem {
    private File arquivo;

    public String getDescricao() {
        return this.getArquivoParaAssinatura().getAbsolutePath();
    }

    public ArquivoItemLocal(File arquivo) {
        this.setArquivo(arquivo);
    }

    public File getArquivo() {
        return this.arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
        this.nome = arquivo.getName();
    }

    public File getArquivoParaAssinatura() {
        return this.arquivo;
    }

    public URL getUrl() {
        try {
            return this.arquivo.toURL();
        } catch (MalformedURLException var2) {
            return null;
        }
    }
}
