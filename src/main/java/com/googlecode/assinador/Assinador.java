//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador;

import br.com.atos.utils.arquivo.ArquivoUtils;
import br.com.atos.utils.swing.JFrameUtils;
import com.googlecode.assinador.gui.FrmCertificadoPkcs12Senha;
import com.googlecode.assinador.gui.FrmSelecionarCertificadoMscapi;
import com.googlecode.assinador.gui.FrmTipoRepositorio;
import com.googlecode.assinador.interfaces.AssinadorLog;
import com.googlecode.assinador.interfaces.AssinadorProgresso;
import com.googlecode.assinador.modelo.ArquivoAssinado;
import com.googlecode.assinador.modelo.Repositorio;
import com.googlecode.assinador.store.StoreHelper;
import com.googlecode.assinador.store.StoreHelperMscapi;
import com.googlecode.assinador.store.StoreHelperPkcs12;
import com.googlecode.assinador.util.CertificadoUtils;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cms.CMSProcessableFile;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Assinador {
    private FrmCertificadoPkcs12Senha frmCertificadoPkcs12Senha;
    private FrmSelecionarCertificadoMscapi frmSelecionarCertificadoMscapi;
    private FrmTipoRepositorio frmTipoRepositorio;
    private Repositorio repositorio;
    private AssinadorProgresso progresso;
    private AssinadorLog log;

    public Assinador() throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            if(Security.getProvider("SunMSCAPI") == null) {
                System.out.println(MessageFormat.format("O provider {0} não esta instalado", new Object[]{"SunMSCAPI"}));
            }

            try {
                this.carregarConfiguracoes();
            } catch (Exception var2) {
                System.out.println(var2.getMessage());
            }

            this.frmSelecionarCertificadoMscapi = new FrmSelecionarCertificadoMscapi((Frame)null, true);
            this.frmCertificadoPkcs12Senha = new FrmCertificadoPkcs12Senha((Frame)null, true);
            this.frmTipoRepositorio = new FrmTipoRepositorio((Frame)null, true);
        } catch (Exception var3) {
            var3.printStackTrace();
            throw new Exception("Erro na inicialização do assinador, mensagem interna: " + var3.getMessage());
        }
    }

    public AssinadorProgresso getProgresso() {
        return this.progresso;
    }

    public void setProgresso(AssinadorProgresso progresso) {
        this.progresso = progresso;
    }

    public AssinadorLog getLog() {
        return this.log;
    }

    public void setLog(AssinadorLog log) {
        this.log = log;
    }

    private void imprimirLogEhProgresso(String msg) {
        if(this.progresso != null) {
            this.progresso.imprimirProgresso(msg);
        }

        this.imprimirLog(msg);
    }

    private Preferences getPreferences(String path) throws BackingStoreException {
        String[] pathItens = path.split("/");
        Preferences preferences = Preferences.userRoot();
        String[] var7 = pathItens;
        int var6 = pathItens.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String pathItem = var7[var5];
            preferences = preferences.node(pathItem);
        }

        return preferences;
    }

    private void carregarConfiguracoes() throws Exception {
        try {
            Preferences e = this.getPreferences("com/googlecode/assinador");
            if(e != null && e.keys().length != 0) {
                String tipo = e.get("tipoRepositorio", "false");
                if("mscapi".equals(tipo) || "pkcs12".equals(tipo)) {
                    this.repositorio = new Repositorio();
                    this.repositorio.setTipoRepositorio(tipo);
                    File pkcs12Arquivo = new File(e.get("pkcs12Arquivo", "false"));
                    if(pkcs12Arquivo.isFile()) {
                        this.repositorio.setPkcs12Arquivo(pkcs12Arquivo);
                    }
                }
            }

        } catch (Exception var4) {
            throw new Exception("Erro ao ler as configurações!");
        }
    }

    private ArquivoAssinado assinarArquivo(StoreHelper storeHelper, CertStore certs, boolean assinaturaAtachada, File arquivo) throws Exception {
        this.imprimirLogEhProgresso("Assinando: " + arquivo.getName());

        try {
            CMSProcessableFile e = new CMSProcessableFile(arquivo);
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            gen.addSigner(storeHelper.getPrivateKey(), storeHelper.getCertificado(), CMSSignedDataGenerator.DIGEST_SHA1);
            gen.addCertificatesAndCRLs(certs);
            CMSSignedData signedData = gen.generate(e, assinaturaAtachada, storeHelper.getKeyStore().getProvider().getName());
            ContentInfo contentInfo = signedData.getContentInfo();
            File dirTmp = new File(System.getProperty("java.io.tmpdir"));
            File arquivoAssinado = new File(dirTmp, arquivo.getName() + ".p7s");
            this.imprimirLog("Gravando arquivo assinado: " + arquivoAssinado.getName());
            FileOutputStream out = new FileOutputStream(arquivoAssinado);
            out.write(contentInfo.getDEREncoded());
            out.close();
            this.imprimirLog("Arquivo assinado gravado");
            return new ArquivoAssinado(arquivo, arquivoAssinado);
        } catch (OutOfMemoryError var12) {
            throw new Exception(var12);
        }
    }

    private void imprimirLog(String msg) {
        if(this.log != null) {
            this.log.imprimirLog(msg);
        }

    }

    private void gravaConfiguracaoes(Repositorio repositorio) throws Exception {
        this.repositorio = repositorio;
        if(repositorio != null && repositorio.isDefinidoTipoRepositorio()) {
            Preferences preferences = this.getPreferences("com/googlecode/assinador");
            preferences.clear();
            preferences.put("tipoRepositorio", repositorio.getTipoRepositorio());
            if(repositorio.isDefinidoPkcs12Arquivo()) {
                preferences.put("pkcs12Arquivo", repositorio.getPkcs12Arquivo().getAbsolutePath());
            }

            preferences.sync();
        }

    }

    public ArquivoAssinado assinarArquivo(File arquivo, boolean assinaturaAtachada) throws Exception {
        if(arquivo == null) {
            throw new Exception("Por favor, selecione algum arquivo para realizar a assinatura digital!");
        } else {
            List arquivosAssinados = this.assinarArquivos(Arrays.asList(new File[]{arquivo}), assinaturaAtachada);
            return (ArquivoAssinado)arquivosAssinados.get(0);
        }
    }

    public List<ArquivoAssinado> assinarArquivos(List<File> arquivos, boolean assinaturaAtachada) throws Exception {
        if(arquivos != null && !arquivos.isEmpty()) {
            StoreHelper storeHelper = this.instanciarRepositorio();
            this.imprimirLogEhProgresso("Iniciando o processo de assinatura...");
            this.imprimirLog("O tipo de assinatura é \"" + (assinaturaAtachada?"atachada":"detachada") + "\"");
            Certificate[] signatarioCertificadoCadeia = storeHelper.getCadeiaCertificados();
            ArrayList certList = new ArrayList();
            this.imprimirLogEhProgresso("Carregando a cadeia de certificados...");
            if(signatarioCertificadoCadeia != null && signatarioCertificadoCadeia.length != 0) {
                certList.addAll(Arrays.asList(signatarioCertificadoCadeia));
            } else {
                certList.add(storeHelper.getCertificado());
            }

            CertStore certs = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), BouncyCastleProvider.PROVIDER_NAME);
            this.imprimirLogEhProgresso("Iniciando a assinatura");
            Iterator arquivo = arquivos.iterator();

            while(arquivo.hasNext()) {
                File arquivosAssinados = (File)arquivo.next();
                byte[] mensagem = ArquivoUtils.getArquivoBytes(arquivosAssinados);
                if(this.isAssinado(mensagem)) {
                    throw new Exception(MessageFormat.format("O assinador não suporta co-assinatura! o arquivo ({0}) já foi assinado!", new Object[]{arquivosAssinados.getName()}));
                }
            }

            ArrayList arquivosAssinados1 = new ArrayList();
            Iterator mensagem1 = arquivos.iterator();

            while(mensagem1.hasNext()) {
                File arquivo1 = (File)mensagem1.next();
                arquivosAssinados1.add(this.assinarArquivo(storeHelper, certs, assinaturaAtachada, arquivo1));
            }

            return arquivosAssinados1;
        } else {
            throw new Exception("Por favor, selecione algum arquivo para realizar a assinatura digital!");
        }
    }

    public StoreHelper instanciarRepositorio() throws Exception {
        if(this.repositorio == null || !this.repositorio.isDefinidoTipoRepositorio()) {
            this.mostrarConfiguracao();
            if(this.repositorio == null || !this.repositorio.isDefinidoTipoRepositorio()) {
                throw new Exception("Por favor, para realizar a assinatura deve-se configurar o tipo de repositório!");
            }
        }

        if(this.repositorio.isTipoRepositorioPkcs12()) {
            if(!this.repositorio.isDefinidoPkcs12Arquivo()) {
                throw new Exception("Por favor, para realizar a assinatura utilizando PKCS12, deve-se definir o endereço do arquivo do certificado!");
            } else {
                char[] certificados1;
                if(!this.repositorio.isDeclaradoPkcs12Senha()) {
                    this.frmCertificadoPkcs12Senha.iniciar();
                    if(this.frmCertificadoPkcs12Senha.getReturnStatus() == 0) {
                        throw new Exception("Por favor, para realizar a assinatura utilizando PKCS12, deve-se informar a senha do certificado!");
                    }

                    certificados1 = this.frmCertificadoPkcs12Senha.getSenha();
                } else {
                    certificados1 = this.repositorio.getPkcs12Senha();
                }

                StoreHelperPkcs12 certificado1 = new StoreHelperPkcs12(new FileInputStream(this.repositorio.getPkcs12Arquivo()), certificados1);
                this.repositorio.setPkcs12Senha(certificados1);
                return certificado1;
            }
        } else if(this.repositorio.isTipoRepositorioMscapi()) {
            List certificados = StoreHelperMscapi.getCertificadosDisponiveis();
            Collections.sort(certificados, new Comparator<X509Certificate>() {
                public int compare(X509Certificate item1, X509Certificate item2) {
                    String alias1 = CertificadoUtils.getCertificadoCN(item1.getSubjectDN().getName());
                    String alias2 = CertificadoUtils.getCertificadoCN(item2.getSubjectDN().getName());
                    return alias1.compareToIgnoreCase(alias2);
                }
            });
            if(certificados.isEmpty()) {
                throw new Exception("Por favor, para realizar a assinatura utilizando Windows MsCAPI, deve-se cadastrar os certificados!");
            } else if(certificados.size() == 1) {
                return new StoreHelperMscapi((X509Certificate)certificados.get(0));
            } else {
                this.frmSelecionarCertificadoMscapi.iniciar(certificados);
                if(this.frmSelecionarCertificadoMscapi.getReturnStatus() == 0) {
                    throw new Exception("Por favor, para realizar a assinatura utilizando Windows MsCAPI, deve-se escolher algum certificado!");
                } else {
                    X509Certificate certificado = this.frmSelecionarCertificadoMscapi.getCertificado();
                    return new StoreHelperMscapi(certificado);
                }
            }
        } else {
            throw new Exception("Por favor, para realizar a assinatura deve-se configurar o tipo de repositório!");
        }
    }

    public boolean isAssinado(byte[] mensagem) {
        try {
            CMSSignedData e = new CMSSignedData(mensagem);
            e.getSignedContent();
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public void mostrarConfiguracao() {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Void run() {
                Assinador.this.frmTipoRepositorio.iniciar(Assinador.this.repositorio);
                if(Assinador.this.frmTipoRepositorio.getReturnStatus() == 1) {
                    try {
                        Assinador.this.gravaConfiguracaoes(Assinador.this.frmTipoRepositorio.getTipoRepositorio());
                    } catch (Exception var2) {
                        JFrameUtils.showErro("Erro ao gravar as configurações!", var2.getMessage(), (Component)null);
                    }
                }

                return null;
            }
        });
    }
}
