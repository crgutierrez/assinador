//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.util;

import java.security.Permission;

public class CustomSecurityManager extends SecurityManager {
    public CustomSecurityManager() {
    }

    public void checkPermission(Permission perm) {
        this.check(perm, (Object)null);
    }

    public void checkPermission(Permission perm, Object context) {
        this.check(perm, context);
    }

    private void check(Permission perm, Object context) {
    }
}
