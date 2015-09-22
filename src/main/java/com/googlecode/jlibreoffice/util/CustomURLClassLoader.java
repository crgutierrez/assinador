//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Set;

public class CustomURLClassLoader extends URLClassLoader {
    private Set<File> resourcePaths;

    public CustomURLClassLoader(URL[] urls, ClassLoader urlClassLoader) {
        super(urls, urlClassLoader);
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class c = this.findLoadedClass(name);
        if(c == null) {
            try {
                c = super.findClass(name);
            } catch (ClassNotFoundException var5) {
                return super.loadClass(name, resolve);
            } catch (SecurityException var6) {
                return super.loadClass(name, resolve);
            }
        }

        if(resolve) {
            this.resolveClass(c);
        }

        return c;
    }

    public void setResourcePaths(Set<File> dirs) {
        this.resourcePaths = dirs;
    }

    public URL getResource(String name) {
        if(this.resourcePaths == null) {
            return null;
        } else {
            URL result = super.getResource(name);
            if(result != null) {
                return result;
            } else {
                Iterator var4 = this.resourcePaths.iterator();

                while(var4.hasNext()) {
                    File dir = (File)var4.next();
                    File lib = new File(dir, name);

                    try {
                        if(lib.exists()) {
                            return new URL(lib.toURI().toASCIIString());
                        }
                    } catch (MalformedURLException var7) {
                        System.err.println("malformed url: " + var7.getMessage());
                    }
                }

                return null;
            }
        }
    }
}
