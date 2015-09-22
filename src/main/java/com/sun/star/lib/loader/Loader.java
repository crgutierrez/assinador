//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sun.star.lib.loader;

import com.sun.star.lib.loader.InstallationFinder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public final class Loader {
    private static ClassLoader m_Loader = null;

    private Loader() {
    }

    public static void main(String[] arguments) throws Exception {
        String className = null;
        Class clazz = Loader.class;
        ClassLoader loader = clazz.getClassLoader();
        Vector res = new Vector();

        try {
            Enumeration args = loader.getResources("META-INF/MANIFEST.MF");

            while(args.hasMoreElements()) {
                res.add((URL)args.nextElement());
            }

            for(int cl = res.size() - 1; cl >= 0; --cl) {
                URL c = (URL)res.elementAt(cl);

                try {
                    JarURLConnection m = (JarURLConnection)c.openConnection();
                    Manifest mf = m.getManifest();
                    Attributes attrs = mf.getAttributes("com/sun/star/lib/loader/Loader.class");
                    if(attrs != null) {
                        className = attrs.getValue("Application-Class");
                        if(className != null) {
                            break;
                        }
                    }
                } catch (IOException var11) {
                    System.err.println("com.sun.star.lib.loader.Loader::main: bad manifest file: " + var11);
                }
            }
        } catch (IOException var12) {
            System.err.println("com.sun.star.lib.loader.Loader::main: cannot get manifest resources: " + var12);
        }

        String[] var13;
        if(className == null) {
            if(arguments.length <= 0) {
                throw new IllegalArgumentException("The name of the class to be loaded must be either specified in the Main-Class attribute of the com/sun/star/lib/loader/Loader.class entry of the manifest file or as a command line argument.");
            }

            className = arguments[0];
            var13 = new String[arguments.length - 1];
            System.arraycopy(arguments, 1, var13, 0, var13.length);
        } else {
            var13 = arguments;
        }

        if(className != null) {
            ClassLoader var14 = getCustomLoader();
            Class var15 = var14.loadClass(className);
            Method var16 = var15.getMethod("main", new Class[]{String[].class});
            var16.invoke((Object)null, new Object[]{var13});
        }

    }

    public static synchronized ClassLoader getCustomLoader() {
        String CLASSESDIR = "classes";
        String JUHJAR = "juh.jar";
        if(m_Loader == null) {
            Vector vec = new Vector();
            String classpath = null;

            try {
                classpath = System.getProperty("java.class.path");
            } catch (SecurityException var14) {
                System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot get system property java.class.path: " + var14);
            }

            if(classpath != null) {
                StringTokenizer path = new StringTokenizer(classpath, File.pathSeparator);

                while(path.hasMoreTokens()) {
                    try {
                        vec.add((new File(path.nextToken())).toURL());
                    } catch (MalformedURLException var13) {
                        System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: bad java.class.path: " + var13);
                    }
                }
            }

            String var20 = InstallationFinder.getPath();
            if(var20 != null) {
                File urls = new File(var20, "classes");
                File fJuh = new File(urls, "juh.jar");
                URL[] clurls = new URL[1];

                try {
                    clurls[0] = fJuh.toURL();
                    Loader.CustomURLClassLoader e = new Loader.CustomURLClassLoader(clurls);
                    Class c = e.loadClass("com.sun.star.comp.helper.UnoInfo");
                    Method m = c.getMethod("getJars", (Class[])null);
                    URL[] jarurls = (URL[])m.invoke((Object)null, (Object[])null);

                    for(int i = 0; i < jarurls.length; ++i) {
                        vec.add(jarurls[i]);
                    }
                } catch (MalformedURLException var15) {
                    System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot add UNO jar files: " + var15);
                } catch (ClassNotFoundException var16) {
                    System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot add UNO jar files: " + var16);
                } catch (NoSuchMethodException var17) {
                    System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot add UNO jar files: " + var17);
                } catch (IllegalAccessException var18) {
                    System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot add UNO jar files: " + var18);
                } catch (InvocationTargetException var19) {
                    System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: cannot add UNO jar files: " + var19);
                }
            } else {
                System.err.println("com.sun.star.lib.loader.Loader::getCustomLoader: no UNO installation found!");
            }

            URL[] var21 = new URL[vec.size()];
            vec.toArray(var21);
            m_Loader = new Loader.CustomURLClassLoader(var21);
        }

        return m_Loader;
    }

    private static final class CustomURLClassLoader extends URLClassLoader {
        public CustomURLClassLoader(URL[] urls) {
            super(urls);
        }

        protected Class findClass(String name) throws ClassNotFoundException {
            throw new ClassNotFoundException(name);
        }

        protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
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
    }
}
