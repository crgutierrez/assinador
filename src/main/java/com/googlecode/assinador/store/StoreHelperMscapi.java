//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.store;

import com.googlecode.assinador.store.StoreHelper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class StoreHelperMscapi extends StoreHelper {
    public static final String TYPE = "Windows-MY";
    public static final String PROVIDER = "SunMSCAPI";

    private static void _fixAliases(KeyStore keyStore) throws Exception {
        try {
            Field field = keyStore.getClass().getDeclaredField("keyStoreSpi");
            field.setAccessible(true);
            KeyStoreSpi keyStoreVeritable = (KeyStoreSpi)field.get(keyStore);
            if("sun.security.mscapi.KeyStore$MY".equals(keyStoreVeritable.getClass().getName())) {
                field = keyStoreVeritable.getClass().getEnclosingClass().getDeclaredField("entries");
                field.setAccessible(true);
                Collection e = (Collection)field.get(keyStoreVeritable);
                Iterator var8 = e.iterator();

                while(var8.hasNext()) {
                    Object entry = var8.next();
                    field = entry.getClass().getDeclaredField("certChain");
                    field.setAccessible(true);
                    X509Certificate[] certificates = (X509Certificate[])field.get(entry);
                    String hashCode = Integer.toString(certificates[0].hashCode());
                    field = entry.getClass().getDeclaredField("alias");
                    field.setAccessible(true);
                    String alias = (String)field.get(entry);
                    if(!alias.equals(hashCode)) {
                        field.set(entry, alias.concat(" - ").concat(hashCode));
                    }
                }
            }

        } catch (Exception var9) {
            var9.printStackTrace();
            throw new Exception(MessageFormat.format("Erro ao realizar o unificação dos alias dos certificados da MsCAPI, {0}", new Object[]{var9.getMessage()}));
        }
    }

    public static List<X509Certificate> getCertificadosDisponiveis() throws Exception {
        try {
            ArrayList e = new ArrayList();
            KeyStore keyStore = newKeyStoreInstance();
            Enumeration aliases = keyStore.aliases();

            while(aliases.hasMoreElements()) {
                String alias = (String)aliases.nextElement();
                if(keyStore.isKeyEntry(alias)) {
                    e.add((X509Certificate)keyStore.getCertificate(alias));
                }
            }

            return e;
        } catch (KeyStoreException var4) {
            throw new Exception("O KeyStore nao foi inicializado corretamente!\n" + var4);
        }
    }

    private static KeyStore newKeyStoreInstance() throws Exception {
        try {
            KeyStore e = KeyStore.getInstance("Windows-MY");
            e.load((InputStream)null, (char[])null);
            _fixAliases(e);
            return e;
        } catch (KeyStoreException var1) {
            throw new Exception("O KeyStore nao foi inicializado corretamente!\n" + var1);
        } catch (NoSuchAlgorithmException var2) {
            throw new Exception("Algoritmo não suportado na CAPI!\n" + var2);
        } catch (IOException var3) {
            throw new Exception("Erro na comunicação com a CAPI!\n" + var3);
        }
    }

    public StoreHelperMscapi(X509Certificate certificado) throws Exception {
        try {
            this.keyStore = newKeyStoreInstance();
            String e = this.keyStore.getCertificateAlias(certificado);
            if(e != null && !e.isEmpty()) {
                this.certificado = certificado;
                this.privateKey = (PrivateKey)this.keyStore.getKey(e, (char[])null);
                this.cadeiaCertificados = this.keyStore.getCertificateChain(e);
            } else {
                throw new Exception("Não existe o certificado " + certificado.getSubjectDN() + " no repositório Windows-MsCapi!");
            }
        } catch (KeyStoreException var3) {
            throw new Exception("O KeyStore nao foi inicializado corretamente!\n" + var3);
        } catch (Exception var4) {
            throw new Exception("Erro ao obter certificado!\n" + var4);
        }
    }
}
