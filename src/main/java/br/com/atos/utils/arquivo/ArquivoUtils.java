//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils.arquivo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;

public class ArquivoUtils {
    public ArquivoUtils() {
    }

    public static boolean isAtiva(URL url) {
        try {
            url.openStream();
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static byte[] getArquivoBytes(File arquivo) throws Exception {
        boolean done = false;
        int bytesread = 0;
        byte[] bytes = new byte[(int)arquivo.length()];
        FileInputStream fis = new FileInputStream(arquivo);
        BufferedInputStream bis = new BufferedInputStream(fis);

        do {
            int b = bis.read();
            if(b == -1) {
                done = true;
                break;
            }

            bytes[bytesread++] = (byte)b;
        } while(!done);

        return bytes;
    }

    public static String getAbsolutePath(String url) {
        String path = url.substring(8, url.length());

        try {
            return URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException var3) {
            return path;
        }
    }

    public static String getUrl(File file) {
        return "file:///" + file.getAbsolutePath().replace('\\', '/');
    }

    public static String getArquivoNomeSemExtensao(String arg) throws Exception {
        try {
            String e = arg.substring(0, arg.lastIndexOf("."));
            return e;
        } catch (Exception var2) {
            throw new Exception("Erro ao obter o nome do arquivo (" + arg + ") sem a extensão!");
        }
    }

    public static String getExtensao(String arg) throws Exception {
        try {
            String e = arg.substring(arg.lastIndexOf(".") + 1, arg.length());
            return e;
        } catch (Exception var2) {
            throw new Exception("Erro ao obter a extensão do arquivo (" + arg + ")!");
        }
    }

    public static String getContentType(File arquivo) {
        String contentType = URLConnection.getFileNameMap().getContentTypeFor(arquivo.getAbsolutePath());
        return contentType != null && !contentType.equals("")?contentType:"application/octet-stream";
    }

    public static void copiar(File origem, File destino) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(origem);
        FileOutputStream out = new FileOutputStream(destino);
        byte[] buf = new byte[1024];

        int len;
        while((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    public static String getTamanhoFormatado(Long tamanho) {
        if(tamanho.longValue() <= 0L) {
            return "0";
        } else {
            String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
            int digitGroups = (int)(Math.log10((double)tamanho.longValue()) / Math.log10(1024.0D));
            return (new DecimalFormat("#,##0.#")).format((double)tamanho.longValue() / Math.pow(1024.0D, (double)digitGroups)) + " " + units[digitGroups];
        }
    }

    public static boolean deletarDiretorioRecursivamente(File dir) {
        if(dir.isDirectory()) {
            String[] children = dir.list();

            for(int i = 0; i < children.length; ++i) {
                boolean success = deletarDiretorioRecursivamente(new File(dir, children[i]));
                if(!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    public static File getDiretorioTemporario() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    public static File getDiretorioUsuario() {
        return new File(System.getProperty("user.home"));
    }

    public static void gravarArquivo(File arq, byte[] bytes) throws IOException {
        FileOutputStream os = new FileOutputStream(arq);
        os.write(bytes);
        os.close();
    }

    public static String gerarHash(byte[] arquivoBytes) throws Exception {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            byte[] md5 = e.digest(arquivoBytes);
            BigInteger hash = new BigInteger(1, md5);
            return hash.toString(16);
        } catch (Exception var4) {
            throw new Exception("Erro ao gerar o hash do arquivo!");
        }
    }

    public static String gerarHash(File arquivo) throws Exception {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            FileInputStream is = new FileInputStream(arquivo);
            byte[] buffer = new byte[8192];

            int read;
            while((read = is.read(buffer)) > 0) {
                e.update(buffer, 0, read);
            }

            byte[] md5 = e.digest();
            BigInteger hash = new BigInteger(1, md5);
            return hash.toString(16);
        } catch (Exception var7) {
            throw new Exception("Erro ao gerar o hash do arquivo!");
        }
    }
}
