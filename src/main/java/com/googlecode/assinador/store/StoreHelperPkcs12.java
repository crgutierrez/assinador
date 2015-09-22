//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.store;

import com.googlecode.assinador.store.StoreHelper;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.crypto.BadPaddingException;

public class StoreHelperPkcs12 extends StoreHelper {
    private void init(char[] senha) throws Exception {
        try {
            boolean e = false;
            Enumeration aliases = this.keyStore.aliases();

            String keyEntry;
            do {
                keyEntry = (String)aliases.nextElement();
                if(this.keyStore.isKeyEntry(keyEntry)) {
                    e = true;
                    break;
                }
            } while(aliases.hasMoreElements());

            if(e) {
                this.certificado = (X509Certificate)this.keyStore.getCertificate(keyEntry);
                this.privateKey = (PrivateKey)this.keyStore.getKey(keyEntry, senha);
                this.cadeiaCertificados = this.keyStore.getCertificateChain(keyEntry);
            } else {
                throw new Exception("Nao foi encontrado nenhum certificado principal no KeyStore!");
            }
        } catch (KeyStoreException var5) {
            throw new Exception("O KeyStore nao foi inicializado corretamente!\n" + var5);
        } catch (NoSuchAlgorithmException var6) {
            throw new Exception("Nao foi possivel encontrar o algoritmo de recuperacaode Chave Privada para o provedor criptografico especificado\n" + var6);
        } catch (UnrecoverableKeyException var7) {
            throw new Exception("Nao foi possivel recuperar a Chave Privada! Verifique se a senha esta correta.\n" + var7);
        }
    }

    public StoreHelperPkcs12(InputStream file, char[] senha) throws Exception {
        try {
            this.keyStore = KeyStore.getInstance("PKCS12");
            this.keyStore.load(file, senha);
            this.init(senha);
        } catch (KeyStoreException var4) {
            throw new Exception("O tipo de KeyStore especificado nao esta disponivel para este provedor criptografico!\n" + var4);
        } catch (NoSuchAlgorithmException var5) {
            throw new Exception("Nao foi possivel utilizar o algoritmo de verificacao de integridade do KeyStore!\n" + var5);
        } catch (CertificateException var6) {
            throw new Exception("Nao foi possivel carregar algum dos certificados existentes no KeyStore!\n" + var6);
        } catch (IOException var7) {
            if(var7.getCause() instanceof BadPaddingException) {
                throw new Exception("A senha do certificado está incorreta!\n" + var7);
            } else {
                throw new Exception("Ocorreu um problema ao ler o arquivo de KeyStore especificado!\n" + var7);
            }
        }
    }
}
