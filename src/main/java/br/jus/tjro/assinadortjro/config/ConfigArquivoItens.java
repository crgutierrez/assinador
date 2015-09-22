//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.config;

import br.jus.tjro.applet.comuns.exception.InitConfigException;
import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributo;
import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributoTipo;
import br.jus.tjro.assinadortjro.arquivo.ArquivoItemRemoto;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JApplet;

public class ConfigArquivoItens {
    public static final String ATRIBUTO = "atributo#";
    public static final String ATRIBUTO_ID = "id";
    public static final String ATRIBUTO_NOME = "nome";
    public static final String ATRIBUTO_TIPO = "tipo";
    public static final String ARQUIVO = "arquivo#";
    public static final String ARQUIVO_URL = "url";
    public static final String ARQUIVO_NOME = "nome";
    public static final String SEPARADOR = ".";
    private List<ArquivoAtributo> atributos = new ArrayList();
    private List<ArquivoItemRemoto> arquivos = new ArrayList();
    private JApplet applet;

    public ConfigArquivoItens(JApplet applet) throws InitConfigException {
        this.applet = applet;
        this.initConfig(applet);
    }

    public List<ArquivoItemRemoto> getArquivos() {
        return this.arquivos;
    }

    public List<ArquivoAtributo> getAtributos() {
        return this.atributos;
    }

    private String getParamValue(String pre, int index, String pos) {
        return this.applet.getParameter(pre + index + "." + pos);
    }

    protected void initConfig(JApplet applet) throws InitConfigException {
        int i = 0;
        boolean continua = true;

        while(true) {
            String urlv;
            String nome;
            while(continua) {
                String temAtributoDeclarado = this.getParamValue("atributo#", i, "id");
                if(temAtributoDeclarado != null && !temAtributoDeclarado.isEmpty()) {
                    temAtributoDeclarado.trim();
                    urlv = this.getParamValue("atributo#", i, "nome");
                    if(urlv == null || urlv.isEmpty()) {
                        urlv = temAtributoDeclarado;
                    }

                    urlv.trim();
                    nome = this.getParamValue("atributo#", i, "tipo");
                    ArquivoAtributoTipo url;
                    if(nome != null && !nome.isEmpty()) {
                        try {
                            url = ArquivoAtributoTipo.valueOf(nome);
                        } catch (Exception var13) {
                            throw new InitConfigException("O tipo (" + nome + ") do atributo (index=" + i + ", id=" + temAtributoDeclarado + ") é inválido!");
                        }
                    } else {
                        url = ArquivoAtributoTipo.STRING;
                    }

                    this.atributos.add(new ArquivoAtributo(temAtributoDeclarado, urlv, url));
                    ++i;
                } else {
                    continua = false;
                }
            }

            i = 0;
            continua = true;
            boolean var14 = this.atributos.size() > 0;

            while(true) {
                while(continua) {
                    urlv = this.getParamValue("arquivo#", i, "url");
                    nome = this.getParamValue("arquivo#", i, "nome");
                    if(urlv != null && !urlv.isEmpty() && nome != null && !nome.isEmpty()) {
                        URL var15;
                        try {
                            var15 = new URL(urlv);
                        } catch (Exception var12) {
                            throw new InitConfigException("O arquivo de numero " + i + " está com uma url inválida!");
                        }

                        nome.trim();
                        HashMap atrs = new HashMap();
                        if(var14) {
                            Iterator var10 = this.atributos.iterator();

                            while(var10.hasNext()) {
                                ArquivoAtributo atributo = (ArquivoAtributo)var10.next();
                                String valor = this.getParamValue("arquivo#", i, atributo.getId());
                                if(valor != null) {
                                    if(ArquivoAtributoTipo.BOOLEAN.equals(atributo.getTipo())) {
                                        atrs.put(atributo.getId(), Boolean.valueOf(valor));
                                    } else if(ArquivoAtributoTipo.INTEGER.equals(atributo.getTipo())) {
                                        atrs.put(atributo.getId(), Integer.valueOf(valor));
                                    } else {
                                        atrs.put(atributo.getId(), valor);
                                    }
                                } else {
                                    atrs.put(atributo.getId(), (Object)null);
                                }
                            }
                        }

                        this.arquivos.add(new ArquivoItemRemoto(var15, nome, atrs));
                        ++i;
                    } else {
                        continua = false;
                    }
                }

                return;
            }
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n  Atributos: " + this.atributos.size() + "\n");
        int i = 0;

        Iterator var4;
        for(var4 = this.atributos.iterator(); var4.hasNext(); ++i) {
            ArquivoAtributo arquivoItem = (ArquivoAtributo)var4.next();
            sb.append("   -> atributo#" + i + "." + "id" + ": " + arquivoItem.getId() + "\n");
            sb.append("   -> atributo#" + i + "." + "nome" + ": " + arquivoItem.getNome() + "\n");
            sb.append("   -> atributo#" + i + "." + "tipo" + ": " + arquivoItem.getTipo().name() + "\n");
        }

        sb.append("  Arquivos: " + this.arquivos.size());
        i = 0;

        for(var4 = this.arquivos.iterator(); var4.hasNext(); ++i) {
            ArquivoItemRemoto var7 = (ArquivoItemRemoto)var4.next();
            sb.append("\n   -> arquivo#" + i + "." + "url" + ": " + var7.getUrl());
            sb.append("\n   -> arquivo#" + i + "." + "nome" + ": " + var7.getNome());
            Iterator var6 = this.atributos.iterator();

            while(var6.hasNext()) {
                ArquivoAtributo atributo = (ArquivoAtributo)var6.next();
                sb.append("\n   -> arquivo#" + i + "." + atributo.getId() + ": " + var7.getAtributos().get(atributo.getId()));
            }
        }

        sb.append("\n");
        return sb.toString();
    }

    public boolean isDefinidoArquivos() {
        return this.arquivos.size() > 0;
    }

    public boolean isDefinidoAtributos() {
        return this.atributos.size() > 0;
    }
}
