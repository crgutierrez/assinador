//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.modelo;

import java.io.File;

public class ArquivoAssinado {
    private File arquivoAssinado;
    private File arquivoOriginal;

    public ArquivoAssinado(File arquivoOriginal, File arquivoAssinado) {
        this.arquivoAssinado = arquivoAssinado;
        this.arquivoOriginal = arquivoOriginal;
    }

    public File getArquivoAssinado() {
        return this.arquivoAssinado;
    }

    public File getArquivoOriginal() {
        return this.arquivoOriginal;
    }
}
