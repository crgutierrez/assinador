//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.modelo;

import br.com.atos.utils.BeanUtil;
import br.com.atos.utils.StringUtils;
import java.io.File;

public class Repositorio {
    public static final String TIPO_REPOSITORIO_MSCAPI = "mscapi";
    public static final String TIPO_REPOSITORIO_PKCS12 = "pkcs12";
    private String tipoRepositorio;
    private File pkcs12Arquivo;
    private char[] pkcs12Senha;

    public Repositorio() {
    }

    public File getPkcs12Arquivo() {
        return this.pkcs12Arquivo;
    }

    public char[] getPkcs12Senha() {
        return this.pkcs12Senha;
    }

    public String getTipoRepositorio() {
        return this.tipoRepositorio;
    }

    public boolean isDefinidoPkcs12Arquivo() {
        return this.getPkcs12Arquivo() != null;
    }

    public boolean isDefinidoTipoRepositorio() {
        return this.tipoRepositorio != null && !this.tipoRepositorio.isEmpty();
    }

    public boolean isTipoRepositorioMscapi() {
        return "mscapi".equals(this.getTipoRepositorio());
    }

    public boolean isTipoRepositorioPkcs12() {
        return "pkcs12".equals(this.getTipoRepositorio());
    }

    public void setPkcs12Arquivo(File pkcs12Arquivo) {
        this.pkcs12Arquivo = pkcs12Arquivo;
    }

    public void setTipoRepositorio(String tipo) {
        if(BeanUtil.verificaSeHouveAlteracao(this.tipoRepositorio, tipo)) {
            this.tipoRepositorio = tipo;
            this.setPkcs12Senha((char[])null);
        }

    }

    public void setPkcs12Senha(char[] pkcs12Senha) {
        this.pkcs12Senha = pkcs12Senha;
    }

    public boolean isDeclaradoPkcs12Senha() {
        return !StringUtils.isNullOrEmpty(this.getPkcs12Senha());
    }
}
