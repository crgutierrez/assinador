//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String join(String[] itens, String delimiter) {
        return join((Collection)Arrays.asList(itens), delimiter);
    }

    public static String join(Collection<String> itens, String delimiter) {
        if(itens != null && !itens.isEmpty()) {
            Iterator iter = itens.iterator();
            StringBuilder builder = new StringBuilder((String)iter.next());

            while(iter.hasNext()) {
                builder.append(delimiter).append((String)iter.next());
            }

            return builder.toString();
        } else {
            return "";
        }
    }

    public static String firstToLowerCase(String str) {
        return !isNullOrEmpty(str)?str.substring(0, 1).toLowerCase() + str.substring(1):"";
    }

    public static String firstToUpperCase(String str) {
        return !isNullOrEmpty(str)?str.substring(0, 1).toUpperCase() + str.substring(1):"";
    }

    public static String removeTudoQueNaoEhNumero(String string) {
        return !isNullOrEmpty(string)?string.replaceAll("[^0-9]", ""):string;
    }

    public static String substituiCaracteresAcentuados(String string) {
        String[][] caracteresAcento = new String[][]{{"Á", "A"}, {"á", "a"}, {"É", "E"}, {"é", "e"}, {"Í", "I"}, {"í", "i"}, {"Ó", "O"}, {"ó", "o"}, {"Ú", "U"}, {"ú", "u"}, {"À", "A"}, {"à", "a"}, {"È", "E"}, {"è", "e"}, {"Ì", "I"}, {"ì", "i"}, {"Ò", "O"}, {"ò", "o"}, {"Ù", "U"}, {"ù", "u"}, {"Â", "A"}, {"â", "a"}, {"Ê", "E"}, {"ê", "e"}, {"Î", "I"}, {"î", "i"}, {"Ô", "O"}, {"ô", "o"}, {"Û", "U"}, {"û", "u"}, {"Ä", "A"}, {"ä", "a"}, {"Ë", "E"}, {"ë", "e"}, {"Ï", "I"}, {"ï", "i"}, {"Ö", "O"}, {"ö", "o"}, {"Ü", "U"}, {"ü", "u"}, {"Ã", "A"}, {"ã", "a"}, {"Õ", "O"}, {"õ", "o"}, {"Ç", "C"}, {"ç", "c"}};

        for(int i = 0; i < caracteresAcento.length; ++i) {
            string = string.replaceAll(caracteresAcento[i][0], caracteresAcento[i][1]);
        }

        return string;
    }

    public static String removeTudoQueNaoEhNumeroEhCaractere(String string) {
        return !isNullOrEmpty(string)?string.replaceAll("[^0-9a-zA-Z]", ""):string;
    }

    public static String substituiCaracteresAcentuadosPorNaoAcentuados(String string) {
        StringBuilder cs = new StringBuilder(string);
        return Normalizer.normalize(cs, Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static boolean isNullOrEmpty(char[] password) {
        return password == null || password.length == 0;
    }
}
