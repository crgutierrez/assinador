//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.installation;

import com.googlecode.jlibreoffice.installation.DependencyPath;
import com.googlecode.jlibreoffice.util.CustomURLClassLoader;
import com.googlecode.jlibreoffice.util.SystemUtils;
import com.sun.star.lib.loader.InstallationFinder;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class JLibreOfficeInstallation {
    private static JLibreOfficeInstallation instance;
    private static Logger log = Logger.getLogger(JLibreOfficeInstallation.class);
    private CustomURLClassLoader classLoader;
    private String unoPath;

    public static JLibreOfficeInstallation getInstance() {
        return instance;
    }

    public static void iniciar() throws Exception {
        if(instance == null) {
            String unoPath = InstallationFinder.getPath();
            if(unoPath == null || unoPath.equals("")) {
                String msg = "O diretório de instalação do LibreOffice não foi encontrado!";
                log.error(msg);
                throw new Exception(msg);
            }

            log.info("UNO_PATH = " + unoPath);
            SystemUtils.putEnvVar("UNO_PATH", unoPath);
            if(SystemUtils.isOsLinux()) {
                System.setProperty("sun.awt.xembedserver", "true");
            }

            instance = new JLibreOfficeInstallation(unoPath);
        }

    }

    private JLibreOfficeInstallation(String unoPath) throws Exception {
        this.unoPath = unoPath;
        String unoPathRoot = (new File(unoPath)).getParent();
        ArrayList libs = new ArrayList();
        libs.add(new DependencyPath("officebean.jar", true));
        libs.add(new DependencyPath("unoil.jar", true));
        libs.add(new DependencyPath("jurt.jar", true));
        libs.add(new DependencyPath("ridl.jar", true));
        libs.add(new DependencyPath("java_uno.jar", true));
        libs.add(new DependencyPath("juh.jar", true));
        ArrayList dirs;
        if(SystemUtils.isOsWindows()) {
            libs.add(new DependencyPath("msvcr70.dll", false));
            libs.add(new DependencyPath("msvcr71.dll", false));
            libs.add(new DependencyPath("uwinapi.dll", false));
            libs.add(new DependencyPath("jawt.dll", false));
            libs.add(new DependencyPath("officebean.dll", true));
            libs.add(new DependencyPath("sal3.dll", true));
            libs.add(new DependencyPath("jpipe.dll", true));
            dirs = new ArrayList();
            dirs.add(new File(unoPathRoot + "\\program"));
            dirs.add(new File(unoPathRoot + "\\program\\classes"));
            dirs.add(new File(unoPathRoot + "\\Basis\\program"));
            dirs.add(new File(unoPathRoot + "\\Basis\\program\\classes"));
            dirs.add(new File(unoPathRoot + "\\URE\\bin"));
            dirs.add(new File(unoPathRoot + "\\URE\\java\\"));
            this.initializeClassloader(libs, dirs);
        } else {
            if(!SystemUtils.isOsLinux()) {
                throw new Exception("O JLibreOffice somente esta preparado para funcionar com os Sistemas Operacionais Windows e Linux Ubuntu!");
            }

            libs.add(new DependencyPath("libofficebean.so", true));
            libs.add(new DependencyPath("libjpipe.so", true));
            libs.add(new DependencyPath("libjuh.so", true));
            dirs = new ArrayList();
            dirs.add(new File(unoPathRoot + "/program/"));
            dirs.add(new File(unoPathRoot + "/program/classes/"));
            dirs.add(new File(unoPathRoot + "/ure-link/share/java/"));
            dirs.add(new File("/usr/lib/ure/lib/"));
            this.initializeClassloader(libs, dirs);
        }

    }

    private void initializeClassloader(List<DependencyPath> dependencies, List<File> dirs) throws Exception {
        HashSet jars = new HashSet();
        HashSet libs = new HashSet();
        Iterator var6 = dependencies.iterator();

        while(var6.hasNext()) {
            DependencyPath dependency = (DependencyPath)var6.next();
            File found = null;
            Iterator var9 = dirs.iterator();

            while(var9.hasNext()) {
                File dir = (File)var9.next();
                File file = new File(dir, dependency.getDependencyName());
                if(file.exists()) {
                    found = file;
                    log.debug(MessageFormat.format("A dependência ({0}) foi encontrada em: {1}", new Object[]{dependency.getDependencyName(), file.toString()}));
                    break;
                }
            }

            if(found != null) {
                if(dependency.isLib()) {
                    libs.add(found.getParentFile());
                } else {
                    jars.add(found.toURI().toURL());
                }
            } else if(dependency.isRequired()) {
                throw new Exception(MessageFormat.format("A dependência {0} não foi encontrada!", new Object[]{dependency.getDependencyName()}));
            }
        }

        this.classLoader = new CustomURLClassLoader((URL[])jars.toArray(new URL[0]), JLibreOfficeInstallation.class.getClassLoader());
        this.classLoader.setResourcePaths(libs);
    }

    public URLClassLoader getClassLoader() {
        return this.classLoader;
    }

    public String getUnoPath() {
        return this.unoPath;
    }

    public File getUnoPathFile() {
        return new File(this.getUnoPath());
    }
}
