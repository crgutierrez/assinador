//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils.arquivo;

import br.com.atos.utils.StringUtils;
import br.com.atos.utils.arquivo.TipoArquivo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum ArquivoMetadado {
    DOC("DOC", 0, "application/msword", "doc", true, TipoArquivo.DOCUMENTO, "Microsoft Word 97/2000/XP (*.doc)"),
    DOCX("DOCX", 1, "application/msword", "docx", true, TipoArquivo.DOCUMENTO, "Microsoft Word 2007 (*.docx)"),
    FLV("FLV", 2, "video/x-flv", "flv", false, TipoArquivo.VIDEO, "FLV - Arquivo de vídeo (*.flv)"),
    MP3("MP3", 3, "audio/mpeg", "mp3", false, TipoArquivo.AUDIO, "MP3 - Arquivo de áudio (*.mp3)"),
    OCTET_STREAM("OCTET_STREAM", 4, "application/octet-stream", "", false, TipoArquivo.INDEFINIDO, ""),
    ODT("ODT", 5, "application/vnd.oasis.opendocument.text", "odt", true, TipoArquivo.DOCUMENTO, "Documento de texto ODF (*.odt)"),
    P7S("P7S", 6, "application/x-pkcs7-signature", "p7s", false, TipoArquivo.ARQUIVO_ASSINADO_DIGITALMENTE, ""),
    P7Z("P7Z", 7, "", "p7z", false, TipoArquivo.ARQUIVO_ASSINADO_DIGITALMENTE, ""),
    PDF("PDF", 8, "application/pdf", "pdf", false, TipoArquivo.DOCUMENTO, "PDF - Portable Document Format (*.pdf)"),
    RTF("RTF", 9, "text/rtf", "rtf", true, TipoArquivo.DOCUMENTO, "Rich Text Format (*.rtf)"),
    TXT("TXT", 10, "text/plain", "txt", true, TipoArquivo.DOCUMENTO, "Texto (*.txt)"),
    WMA("WMA", 11, "áudio/x-ms-wma", "wma", false, TipoArquivo.AUDIO, "WMA - Arquivo de áudio (*.wma)"),
    WMV("WMV", 12, "vídeo/x-ms-wmv", "wmv", false, TipoArquivo.VIDEO, "WMV - Arquivo de vídeo (*.wmv)"),
    JPG("JPG", 13, "", "jpg", false, TipoArquivo.IMAGEM, "JPG (*.jpg)"),
    JPEG("JPEG", 14, "", "jpeg", false, TipoArquivo.IMAGEM, "JPEG - Joint Photographic Experts Group (*.jpeg)"),
    PNG("PNG", 15, "image/png", "png", false, TipoArquivo.IMAGEM, "PNG - Portable Network Graphic (*.png)"),
    GIF("GIF", 16, "", "gif", false, TipoArquivo.IMAGEM, "GIF - Graphics Interchange Format (*.gif)"),
    BMP("BMP", 17, "", "bmp", false, TipoArquivo.IMAGEM, "BMP - Windows Bitmap (*.bmp)"),
    HTML("HTML", 18, "", "html", true, TipoArquivo.DOCUMENTO, "Documento HTML (*.html)"),
    X_OCTET_STREAM("X_OCTET_STREAM", 19, "application/x-octet-stream", "", false, TipoArquivo.INDEFINIDO, ""),
    ZIP("ZIP", 20, "application/zip", "zip", false, TipoArquivo.INDEFINIDO, "ZIP (*.zip)"),
    XML("XML", 21, "text/xml", "xml", false, TipoArquivo.DOCUMENTO, "Documento XML (*.xml)");

    private String contentType;
    private boolean editavel;
    private String extensao;
    private TipoArquivo tipoArquivo;
    private String fileFilter;




    ArquivoMetadado(String contentType,int ordem, String s, String extensao, boolean editavel, TipoArquivo tipoArquivo, String fileFilter) {
        this.contentType = contentType;
        this.extensao = extensao;
        this.editavel = editavel;
        this.tipoArquivo = tipoArquivo;
        this.fileFilter = fileFilter;
    }



    public static List<ArquivoMetadado> getEditaveis() {
        ArrayList editaveis = new ArrayList();
        ArquivoMetadado[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ArquivoMetadado arquivoMetadado = arr$[i$];
            if(arquivoMetadado.isEditavel().booleanValue()) {
                editaveis.add(arquivoMetadado);
            }
        }

        return editaveis;
    }

    public static boolean isContentTypeEditavel(String contentType, String extensao) {
        List editaveis = getEditaveis();
        Iterator i$;
        ArquivoMetadado editavel;
        if(isContentTypeOctetStreamOuXOctetStream(contentType)) {
            i$ = editaveis.iterator();

            do {
                if(!i$.hasNext()) {
                    return false;
                }

                editavel = (ArquivoMetadado)i$.next();
            } while(!editavel.isExtensao(extensao));

            return true;
        } else {
            i$ = editaveis.iterator();

            do {
                if(!i$.hasNext()) {
                    return false;
                }

                editavel = (ArquivoMetadado)i$.next();
            } while(!editavel.isContentType(contentType));

            return true;
        }
    }

    public static boolean isContentTypeOctetStreamOuXOctetStream(String contentType) {
        return OCTET_STREAM.isContentType(contentType) || X_OCTET_STREAM.isContentType(contentType);
    }

    public static String retrieveContentTypeByExtensao(String extensao) {
        ArquivoMetadado[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ArquivoMetadado arquivoMetadado = arr$[i$];
            if(arquivoMetadado.isExtensao(extensao)) {
                return arquivoMetadado.getContentType();
            }
        }

        return null;
    }


    public String getContentType() {
        return this.contentType;
    }

    public String getExtensao() {
        return this.extensao;
    }

    public TipoArquivo getTipoArquivo() {
        return this.tipoArquivo;
    }

    public boolean isContentType(String contentType) {
        return this.getContentType().equalsIgnoreCase(contentType);
    }

    public Boolean isEditavel() {
        return Boolean.valueOf(this.editavel);
    }

    public String getFileFilter() {
        return this.fileFilter;
    }

    public boolean isExtensao(String extensao) {
        return this.getExtensao().equalsIgnoreCase(extensao);
    }

    public boolean isIgual(String contentType, String nome) {
        return this.isContentType(contentType)?true:(isContentTypeOctetStreamOuXOctetStream(contentType)?this.isExtensao(getExtensao(nome)):false);
    }

    public static String gerarNomeComExtensaoSeguro(String nome, String extensao) {
        nome = StringUtils.substituiCaracteresAcentuadosPorNaoAcentuados(nome);
        nome = nome.replaceAll("[^\\w\\d\\-j\\ \\.]", "");
        nome = nome.trim();
        nome = nome.replaceAll("[\\ \\.]", "_");
        return nome + "." + extensao;
    }

    public static String gerarNomeComExtensao(String nome, String extensao) {
        return nome + "." + extensao;
    }

    public static String getExtensao(String nomeComExtensao) {
        return nomeComExtensao.length() > 3 && nomeComExtensao.lastIndexOf(".") != -1?nomeComExtensao.substring(nomeComExtensao.lastIndexOf(".") + 1, nomeComExtensao.length()).toLowerCase():null;
    }

    public static ArquivoMetadado retrieveByExtensao(String extensao) {
        ArquivoMetadado[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ArquivoMetadado arquivoMetadado = arr$[i$];
            if(arquivoMetadado.isExtensao(extensao)) {
                return arquivoMetadado;
            }
        }

        return null;
    }

    public static ArquivoMetadado retrieveByContentType(String contentType) {
        ArquivoMetadado[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            ArquivoMetadado arquivoMetadado = arr$[i$];
            if(arquivoMetadado.isContentType(contentType)) {
                return arquivoMetadado;
            }
        }

        return null;
    }

    public static ArquivoMetadado retrieveByContentTypeEhExtensao(String contentType, String extensao) {
        if(isContentTypeOctetStreamOuXOctetStream(contentType)) {
            return retrieveByExtensao(extensao);
        } else {
            ArquivoMetadado[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                ArquivoMetadado arquivoMetadado = arr$[i$];
                if(arquivoMetadado.isContentType(contentType) && arquivoMetadado.isExtensao(extensao)) {
                    return arquivoMetadado;
                }
            }

            return null;
        }
    }

    public static String gerarNomeComExtensao(String nome, ArquivoMetadado metadado) {
        return gerarNomeComExtensao(nome, metadado.getExtensao());
    }


}
