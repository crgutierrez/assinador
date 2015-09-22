//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils.swing;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import javax.swing.filechooser.FileFilter;

public final class FileNameExtensionFilter extends FileFilter {
    private final String description;
    private final String[] extensions;
    private final String[] lowerCaseExtensions;

    public FileNameExtensionFilter(String description, String... extensions) {
        if(extensions != null && extensions.length != 0) {
            this.description = description;
            this.extensions = new String[extensions.length];
            this.lowerCaseExtensions = new String[extensions.length];

            for(int i = 0; i < extensions.length; ++i) {
                if(extensions[i] == null || extensions[i].length() == 0) {
                    throw new IllegalArgumentException("Each extension must be non-null and not empty");
                }

                this.extensions[i] = extensions[i];
                this.lowerCaseExtensions[i] = extensions[i].toLowerCase(Locale.ENGLISH);
            }

        } else {
            throw new IllegalArgumentException("Extensions must be non-null and not empty");
        }
    }

    public boolean accept(File f) {
        if(f != null) {
            if(f.isDirectory()) {
                return true;
            }

            String fileName = f.getName();
            int i = fileName.lastIndexOf(46);
            if(i > 0 && i < fileName.length() - 1) {
                String desiredExtension = fileName.substring(i + 1).toLowerCase(Locale.ENGLISH);
                String[] arr$ = this.lowerCaseExtensions;
                int len$ = arr$.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String extension = arr$[i$];
                    if(desiredExtension.equals(extension)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getExtensions() {
        String[] result = new String[this.extensions.length];
        System.arraycopy(this.extensions, 0, result, 0, this.extensions.length);
        return result;
    }

    public String toString() {
        return super.toString() + "[description=" + this.getDescription() + " extensions=" + Arrays.asList(this.getExtensions()) + "]";
    }
}
