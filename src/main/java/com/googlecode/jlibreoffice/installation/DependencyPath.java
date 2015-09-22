//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.installation;

public class DependencyPath {
    private String dependencyName;
    private boolean required;

    public DependencyPath(String dependencyName, boolean required) {
        this.dependencyName = dependencyName;
        this.required = required;
    }

    public String getDependencyName() {
        return this.dependencyName;
    }

    public boolean isJar() {
        return this.getDependencyName().endsWith(".jar");
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isLib() {
        return !this.isJar();
    }
}
