//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.store;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public abstract class StoreHelper {
    protected X509Certificate certificado;
    protected KeyStore keyStore;
    protected PrivateKey privateKey;
    protected Certificate[] cadeiaCertificados;

    public StoreHelper() {
    }

    public Certificate[] getCadeiaCertificados() {
        return this.cadeiaCertificados;
    }

    public X509Certificate getCertificado() {
        return this.certificado;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }
}
