//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils.arquivo;

public enum TipoArquivo {
    DOCUMENTO("Documento"),
    AUDIO("Áudio"),
    VIDEO("Vídeo"),
    IMAGEM("Imagem"),
    INDEFINIDO("Indefinido"),
    ARQUIVO_ASSINADO_DIGITALMENTE("Arquivo Assinado Digitalmente");

    private final String descricao;

    private TipoArquivo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
