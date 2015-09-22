//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

import java.util.Arrays;
import java.util.Comparator;

public class IntuitiveStringComparator implements Comparator<String> {
    private String str1;
    private String str2;
    private int pos1;
    private int pos2;
    private int len1;
    private int len2;

    public IntuitiveStringComparator() {
    }

    public int compare(String s1, String s2) {
        this.str1 = s1;
        this.str2 = s2;
        this.len1 = this.str1.length();
        this.len2 = this.str2.length();
        this.pos1 = this.pos2 = 0;

        int result;
        for(result = 0; result == 0 && this.pos1 < this.len1 && this.pos2 < this.len2; ++this.pos2) {
            char ch1 = this.str1.charAt(this.pos1);
            char ch2 = this.str2.charAt(this.pos2);
            if(Character.isDigit(ch1)) {
                result = Character.isDigit(ch2)?this.compareNumbers():-1;
            } else if(Character.isLetter(ch1)) {
                result = Character.isLetter(ch2)?this.compareOther(true):1;
            } else {
                result = Character.isDigit(ch2)?1:(Character.isLetter(ch2)?-1:this.compareOther(false));
            }

            ++this.pos1;
        }

        return result == 0?this.len1 - this.len2:result;
    }

    private int compareNumbers() {
        int end1;
        for(end1 = this.pos1 + 1; end1 < this.len1 && Character.isDigit(this.str1.charAt(end1)); ++end1) {
            ;
        }

        int fullLen1;
        for(fullLen1 = end1 - this.pos1; this.pos1 < end1 && this.str1.charAt(this.pos1) == 48; ++this.pos1) {
            ;
        }

        int end2;
        for(end2 = this.pos2 + 1; end2 < this.len2 && Character.isDigit(this.str2.charAt(end2)); ++end2) {
            ;
        }

        int fullLen2;
        for(fullLen2 = end2 - this.pos2; this.pos2 < end2 && this.str2.charAt(this.pos2) == 48; ++this.pos2) {
            ;
        }

        int delta = end1 - this.pos1 - (end2 - this.pos2);
        if(delta != 0) {
            return delta;
        } else {
            while(this.pos1 < end1 && this.pos2 < end2) {
                delta = this.str1.charAt(this.pos1++) - this.str2.charAt(this.pos2++);
                if(delta != 0) {
                    return delta;
                }
            }

            --this.pos1;
            --this.pos2;
            return fullLen2 - fullLen1;
        }
    }

    private int compareOther(boolean isLetters) {
        char ch1 = this.str1.charAt(this.pos1);
        char ch2 = this.str2.charAt(this.pos2);
        if(ch1 == ch2) {
            return 0;
        } else {
            if(isLetters) {
                ch1 = Character.toUpperCase(ch1);
                ch2 = Character.toUpperCase(ch2);
                if(ch1 != ch2) {
                    ch1 = Character.toLowerCase(ch1);
                    ch2 = Character.toLowerCase(ch2);
                }
            }

            return ch1 - ch2;
        }
    }

    public static void main(String[] args) {
        String[] list = new String[]{"2008-02-01-676767-1.jpg", "2008-02-01-676767-2.jpg", "2008-02-01-676767-3.jpg", "2008-02-01-676767-4.jpg", "2008-02-01-676780-1.jpg", "2008-02-01-676780-2.jpg", "2008-02-01-676780-3.jpg", "2008-02-01-676780-4.jpg", "2008-02-01-676780-10.jpg", "2008-02-01-676780-11.jpg", "2008-02-01-676790-1.jpg"};
        Arrays.sort(list, new IntuitiveStringComparator());
        String[] arr$ = list;
        int len$ = list.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String s = arr$[i$];
            System.out.println(s);
        }

    }
}
