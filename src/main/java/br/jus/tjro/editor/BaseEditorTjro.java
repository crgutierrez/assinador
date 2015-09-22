//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor;

import br.com.atos.utils.StringUtils;
import br.com.atos.utils.arquivo.ArquivoMetadado;
import br.com.atos.utils.arquivo.ArquivoUtils;
import br.com.atos.utils.swing.FileNameExtensionFilter;
import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.applet.comuns.gui.WinBarraProgresso;
import br.jus.tjro.applet.comuns.vo.Param;
import br.jus.tjro.editor.config.Configs;
import br.jus.tjro.editor.gui.EditorTjroMenuBar;
import br.jus.tjro.editor.gui.WinArquivoTemporario;
import br.jus.tjro.editor.gui.WinDesenvolvimento;
import br.jus.tjro.editor.gui.WinLibreOffice;
import br.jus.tjro.editor.gui.WinSobre;
import com.googlecode.assinador.Assinador;
import com.googlecode.assinador.interfaces.AssinadorLog;
import com.googlecode.assinador.interfaces.AssinadorProgresso;
import com.googlecode.assinador.modelo.ArquivoAssinado;
import com.googlecode.jlibreoffice.JLibreOffice;
import com.googlecode.jlibreoffice.OOoBeanProxy;
import com.googlecode.jlibreoffice.installation.JLibreOfficeInstallation;
import com.googlecode.jlibreoffice.util.CustomSecurityManager;
import java.awt.Component;
import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ProxySelector;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.KeyStore;
import java.security.PrivilegedAction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class BaseEditorTjro implements AssinadorProgresso, AssinadorLog {
    private static final Logger log = Logger.getLogger(BaseEditorTjro.class);
    public static final String EDITOR_NOME = "EditorTjro";
    public static final String EDITOR_VERSAO = "1.4.2";
    public static final String EDITOR_TITULO = "EditorTjro - Versão: 1.4.2";
    public static final String PARAM_ARQUIVO_NAME = "arquivo";
    public static final String REQUEST_RESULTADO_ERRO = "Erro:";
    public static final String REQUEST_RESULTADO_ERRO_ANTIGO = "EX:";
    public static final String REQUEST_RESULTADO_SUCESSO = "OK";
    private JApplet applet;
    private File arquivoRemoto;
    private Assinador assinador;
    private Configs configs;
    private JLibreOffice jLibreOffice;
    private EditorTjroMenuBar menuBar;
    private List<Param> params;
    private WinBarraProgresso winBarraProgresso;
    private WinLibreOffice winLibreOffice;
    private WinSobre winSobre;
    private boolean sessaoAberta;
    private boolean inicializado;
    private long sessaoNumeroVerificacao;
    private boolean executarManterSessao;
    private Thread threadManterSessao;

    public BaseEditorTjro(JApplet applet) {
        this(applet, (WinBarraProgresso)null, (Assinador)null);
    }

    public BaseEditorTjro(JApplet applet, WinBarraProgresso winBarraProgresso, Assinador assinador) {
        this.sessaoAberta = true;
        this.inicializado = false;
        this.sessaoNumeroVerificacao = 1L;
        this.executarManterSessao = true;
        this.applet = applet;
        log.info("---------------------------------------------");
        log.info("--- EditorTjro - Versão: 1.4.2");
        log.info("---------------------------------------------");
        this.assinador = assinador;
        this.winBarraProgresso = winBarraProgresso;

        try {
            this.configs = new Configs(applet);
            this.configs.showConfigs();
            if(((Boolean)this.getConfigs().getDevelopment().getValue()).booleanValue()) {
                new WinDesenvolvimento(this);
            }
        } catch (Exception var5) {
            log.error(var5);
            JFrameUtils.showErro("Erro na inicialização da configuração", var5.getMessage());
        }

    }

    private String getMensagemSalvarCopiarSeguranca() {
        return "Por favor, na próxima janela especifique um local para salvar uma cópia de segurança do arquivo atual para você não perder seu trabalho!";
    }

    private void manterSessaoAtualizada() {
        log.info("Executando o procedimento para manter sessão atualizada, número da verificação: " + this.sessaoNumeroVerificacao);
        boolean erro = true;

        try {
            SystemDefaultRoutePlanner msg = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
            CloseableHttpClient e = HttpClients.custom().setRoutePlanner(msg).build();
            String url = ((URL)this.getConfigs().getServerSession().getValue()).toString() + "&__snv=" + this.sessaoNumeroVerificacao++;
            HttpGet httpGet = new HttpGet(url);
            if(this.getConfigs().getCookie().isDeclarado()) {
                httpGet.setHeader("Cookie", (String)this.configs.getCookie().getValue());
            }

            CloseableHttpResponse httpResponse = e.execute(httpGet);
            String resultado = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            httpResponse.close();
            e.close();
            if(!StringUtils.isNullOrEmpty(resultado)) {
                if(resultado.equals("OK")) {
                    log.info("A Sessão foi atualizada com sucesso!");
                    erro = false;
                } else if(resultado.startsWith("Erro:")) {
                    log.error("Erro ao manter a sessão atualizada, mensagem interna: " + resultado.substring(5).trim());
                } else {
                    log.error("Erro ao manter a sessão atualizada, mensagem interna: " + resultado);
                }
            } else {
                log.error("Erro ao manter a sessão atualizada.");
            }
        } catch (Exception var9) {
            log.error("Erro ao manter a sessão atualizada, mensagem interna: " + var9.getMessage());
        }

        if(erro) {
            String msg1 = "Atenção aconteceu um erro ao manter a sua sessão atualizada, possívelmente sua sessão com o servidor expirou ou aconteceu algum problema com o servidor!\n";
            msg1 = msg1 + this.getMensagemSalvarCopiarSeguranca();
            this.sessaoAberta = false;

            try {
                if(this.getJLibreOffice().isConnected() && this.getJLibreOffice().isEditingDocument() && this.getWinLibreOffice().isVisible()) {
                    JFrameUtils.showErro("EditorTjro - Versão: 1.4.2", msg1);
                    this.getJLibreOffice().saveAs();
                    this.getJLibreOffice().clear();
                    this.getWinLibreOffice().setVisible(false);
                }
            } catch (Exception var8) {
                log.error(var8);
            }
        }

    }

    private void abrirDocumentoAction(File arquivo) throws Exception {
        this.getJLibreOffice().open(arquivo);
        this.atualizarMenu();
        this.fecharBarraProgresso();
    }

    public void abrirDocumentoLocal() {
        this.abrirDocumentoLocal((String)null);
    }

    public void abrirDocumentoLocal(final String params) {
        if(this.verificarEditorTjro()) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Void run() {
                    BaseEditorTjro.this.limpar();
                    BaseEditorTjro.this.preprararParams(params);
                    BaseEditorTjro.this.abrirDocumentoLocalAction();
                    return null;
                }
            });
        }
    }

    public void abrirDocumentoLocalAction() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(0);
        fc.setAcceptAllFileFilterUsed(false);
        if(!this.getConfigs().getFileFilters().isDeclarado()) {
            FileNameExtensionFilter retorno = new FileNameExtensionFilter("Documento de texto", new String[]{"txt", "odt", "doc", "rtf", "docx"});
            fc.addChoosableFileFilter(retorno);
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Documento de texto ODF (*.odt)", new String[]{"odt"}));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word 97/2000/XP (*.doc)", new String[]{"doc"}));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Rich Text Format (*.rtf)", new String[]{"rtf"}));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Texto (*.txt)", new String[]{"txt"}));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Documento HTML (*.html)", new String[]{"html"}));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word 2007 (*.docx)", new String[]{"docx"}));
            fc.setFileFilter(retorno);
        } else {
            List retorno1 = (List)this.configs.getFileFilters().getValue();
            Iterator e = retorno1.iterator();

            while(e.hasNext()) {
                FileNameExtensionFilter file = (FileNameExtensionFilter)e.next();
                fc.addChoosableFileFilter(file);
            }
        }

        int retorno2 = fc.showOpenDialog((Component)null);
        if(retorno2 == 0) {
            File file1 = fc.getSelectedFile();
            this.mostrarInterfaceSeNecessario();

            try {
                this.abrirDocumentoAction(file1);
            } catch (Exception var5) {
                log.error(var5);
                JFrameUtils.showErro("Erro ao abrir o documento: " + file1.getName(), var5.getMessage());
            }
        }

    }

    public void abrirDocumentoRemoto(String arquivoNome, String arquivoUrl) {
        this.abrirDocumentoRemoto(arquivoNome, arquivoUrl, (String)null);
    }

    public void abrirDocumentoRemoto(final String arquivoNome, final String arquivoUrl, final String arquivoParams) {
        if(this.verificarEditorTjro()) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Void run() {
                    BaseEditorTjro.this.iniciarBarraProgresso();
                    Thread thread = new Thread(new Runnable() {
                        public void run() {
                            try {
                                BaseEditorTjro.this.abrirDocumentoRemotoAction(arquivoNome, arquivoUrl, arquivoParams);
                            } catch (Exception var2) {
                                BaseEditorTjro.this.fecharBarraProgresso();
                                BaseEditorTjro.log.error(var2);
                                JFrameUtils.showErro("Erro ao Abrir um documento remoto", var2.getMessage());
                            }

                        }
                    });
                    thread.run();
                    return null;
                }
            });
        }
    }

    public void abrirDocumentoRemotoAction() throws Exception {
        this.mostrarInterfaceSeNecessario();
        this.abrirDocumentoAction(this.arquivoRemoto);
    }

    private void abrirDocumentoRemotoAction(String arquivoNome, String arquivoUrl, String params) throws Exception {
        this.limpar();
        this.preprararParams(params);
        String arquivoNomeSemExtensao = ArquivoUtils.getArquivoNomeSemExtensao(arquivoNome);
        String arquivoExtensao = ArquivoUtils.getExtensao(arquivoNome);
        if(arquivoExtensao != null && !arquivoExtensao.equals("") && arquivoNomeSemExtensao != null && !arquivoNomeSemExtensao.equals("")) {
            URL url = new URL(arquivoUrl);
            URLConnection urlConn = url.openConnection();
            if(this.getConfigs().getCookie().isDeclarado()) {
                urlConn.setRequestProperty("Cookie", (String)this.getConfigs().getCookie().getValue());
            }

            this.getWinBarraProgresso().imprimir("Aguarde o Download do Arquivo.");
            InputStream is = urlConn.getInputStream();
            log.info("ABRIR DOCUMENTO REMOTO:");
            log.info(" - Antes de Criar Arquivo Temporário.");
            log.info(" - Nome do Arquivo Completo: " + arquivoNome);
            log.info(" - Nome do Arquivo: " + arquivoNomeSemExtensao);
            log.info(" - Extensão do Arquivo: " + arquivoExtensao);
            File dirTmp = ArquivoUtils.getDiretorioTemporario();
            log.info(" - Diretório de Arquivos Temporários: " + dirTmp.getAbsolutePath());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date dt = new Date();
            String data = sdf.format(dt);
            String arqNomePrefixo = arquivoNomeSemExtensao;
            File arquivo = new File(dirTmp, ArquivoMetadado.gerarNomeComExtensaoSeguro(arquivoNomeSemExtensao + "_" + data, arquivoExtensao));
            log.info(" - Arquivo Nome: " + arquivo.getAbsolutePath());
            if(arquivo.exists()) {
                while(arquivo.exists()) {
                    dt = new Date();
                    data = sdf.format(dt);
                    arquivo = new File(dirTmp, ArquivoMetadado.gerarNomeComExtensaoSeguro(arqNomePrefixo + "_" + data, arquivoExtensao));
                    log.info(" - Arquivo com Nome único: " + arquivo.getAbsolutePath());
                }
            }

            FileOutputStream fos = new FileOutputStream(arquivo);
            this.getWinBarraProgresso().imprimir("Aguarde a Gravação do Arquivo");
            log.info(" - Inicia Escrita do Arquivo!");

            int umByte;
            while((umByte = is.read()) != -1) {
                fos.write(umByte);
            }

            is.close();
            fos.close();
            log.info(" - Escrita do Arquivo Finalizado");
            String arquivoContentType = ArquivoUtils.getContentType(arquivo);
            log.info(" - Pega o Tipo de Conteudo do Arquivo: " + arquivoContentType);
            this.arquivoRemoto = arquivo;
            log.info(" - Arquivo Final: " + arquivo.getAbsolutePath());
            this.getWinBarraProgresso().imprimir("Aguarde o Carregamento do Arquivo.");
            this.abrirDocumentoRemotoAction();
        } else {
            throw new Exception("O nome do arquivo é inválido!");
        }
    }

    private void atualizarMenu() {
        this.menuBar.atualizarMenu();
    }

    public void criarNovoDocumento() {
        this.criarNovoDocumento((String)null);
    }

    public void criarNovoDocumento(final String params) {
        if(this.verificarEditorTjro()) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Void run() {
                    try {
                        BaseEditorTjro.this.limpar();
                        BaseEditorTjro.this.preprararParams(params);
                        BaseEditorTjro.this.criarNovoDocumentoAction();
                    } catch (Exception var2) {
                        BaseEditorTjro.log.error(var2);
                        JFrameUtils.showErro("Erro ao criar um novo documento!", var2.getMessage());
                    }

                    return null;
                }
            });
        }
    }

    private void criarNovoDocumentoAction() throws Exception {
        this.mostrarInterfaceSeNecessario();
        this.getJLibreOffice().newWriterDocument();
        this.atualizarMenu();
    }

    public void enviarDocumentoAction() throws Exception {
        if(this.getJLibreOffice().isConnected()) {
            this.getJLibreOffice().save();
            if(this.getJLibreOffice().getBean().isModifiedDocument()) {
                throw new Exception("Para enviar o arquivo é necessário salva-lo primeiro!");
            } else {
                this.iniciarBarraProgresso();
                this.getWinBarraProgresso().imprimir("Iniciando o envio...");
                File arquivo;
                if(this.getConfigs().getConvertTo().isDeclarado()) {
                    String arquivoParaEnvio = (String)this.getConfigs().getConvertTo().getValue();
                    if(!"pdf".equals(arquivoParaEnvio)) {
                        throw new Exception("Tipo de conversão (" + arquivoParaEnvio + ") inválido!");
                    }

                    this.getWinBarraProgresso().imprimir("Aguarde a conversão do arquivo para " + arquivoParaEnvio.toUpperCase() + ".");
                    arquivo = new File(this.getJLibreOffice().exportToPdf());
                } else {
                    arquivo = new File(ArquivoUtils.getAbsolutePath(this.getJLibreOffice().getBean().getDocumentURL()));
                }

                String uploadTo;
                File arquivoParaEnvio1;
                if(((Boolean)this.getConfigs().getSign().getValue()).booleanValue()) {
                    this.fecharBarraProgresso();
                    ArquivoAssinado routePlanner = this.getAssinador().assinarArquivo(arquivo, true);
                    this.iniciarBarraProgresso();
                    arquivoParaEnvio1 = routePlanner.getArquivoAssinado();
                    uploadTo = ((URL)this.getConfigs().getUploadSignedFileTo().getValue()).toString();
                } else {
                    arquivoParaEnvio1 = arquivo;
                    uploadTo = ((URL)this.getConfigs().getUploadFileTo().getValue()).toString();
                }

                this.getWinBarraProgresso().imprimir("Aguarde o Envio do Arquivo.");
                SystemDefaultRoutePlanner routePlanner1 = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
                CloseableHttpClient httpClient;
                if(uploadTo.startsWith("https")) {
                    SSLContextBuilder httpPost = new SSLContextBuilder();
                    httpPost.loadTrustMaterial((KeyStore)null, new TrustSelfSignedStrategy());
                    SSLConnectionSocketFactory builder = new SSLConnectionSocketFactory(httpPost.build());
                    httpClient = HttpClients.custom().setSSLSocketFactory(builder).setRoutePlanner(routePlanner1).build();
                } else {
                    httpClient = HttpClients.custom().setRoutePlanner(routePlanner1).build();
                }

                HttpPost httpPost1 = new HttpPost(uploadTo);
                MultipartEntityBuilder builder1 = MultipartEntityBuilder.create();
                builder1.addPart("arquivo", new FileBody(arquivoParaEnvio1));
                Iterator resultado = this.getTodosParametros().iterator();

                while(resultado.hasNext()) {
                    Param httpResponse = (Param)resultado.next();
                    builder1.addPart(httpResponse.getName(), new StringBody(httpResponse.getValue(), ContentType.TEXT_PLAIN));
                }

                httpPost1.setEntity(builder1.build());
                if(this.getConfigs().getCookie().isDeclarado()) {
                    httpPost1.setHeader("Cookie", (String)this.configs.getCookie().getValue());
                }

                CloseableHttpResponse httpResponse1 = httpClient.execute(httpPost1);
                String resultado1 = EntityUtils.toString(httpResponse1.getEntity(), "UTF-8");
                httpResponse1.close();
                httpClient.close();
                if(!StringUtils.isNullOrEmpty(resultado1)) {
                    if(resultado1.equals("OK")) {
                        this.imprimirLog("Arquivo enviado com sucesso: " + arquivo.getName());
                        this.fecharBarraProgresso();
                        this.getJLibreOffice().clear();
                        this.getWinLibreOffice().setVisible(false);
                        if(((Boolean)this.getConfigs().getSign().getValue()).booleanValue()) {
                            this.getApplet().getAppletContext().showDocument((URL)this.getConfigs().getOnCompleteUploadSignedFile().getValue());
                        } else {
                            this.getApplet().getAppletContext().showDocument((URL)this.getConfigs().getOnCompleteUploadFile().getValue());
                        }

                    } else if(resultado1.startsWith("Erro:")) {
                        throw new Exception(resultado1.substring(5).trim());
                    } else if(resultado1.startsWith("EX:")) {
                        throw new Exception(resultado1.substring(3).trim());
                    } else {
                        log.error("Resultado do envio: " + resultado1);
                        throw new Exception("Ao realizar o envio do arquivo!!!");
                    }
                } else {
                    throw new Exception("Ao realizar o envio do arquivo!!!");
                }
            }
        }
    }

    public Assinador getAssinador() {
        return this.assinador;
    }

    private List<Param> getTodosParametros() {
        ArrayList params = new ArrayList();
        if(this.getConfigs().getParams().isDeclarado()) {
            params.addAll((Collection)this.getConfigs().getParams().getValue());
        }

        if(this.getParams() != null) {
            params.addAll(this.getParams());
        }

        return params;
    }

    public void executar(String comando) throws Exception {
        this.getJLibreOffice().execute(comando);
    }

    protected void fecharBarraProgresso() {
        this.getApplet().setCursor(Cursor.getPredefinedCursor(0));
        this.getWinBarraProgresso().setVisible(false);
    }

    protected JApplet getApplet() {
        return this.applet;
    }

    public File getArquivoRemoto() {
        return this.arquivoRemoto;
    }

    public Configs getConfigs() {
        return this.configs;
    }

    public JLibreOffice getJLibreOffice() {
        return this.jLibreOffice;
    }

    public List<Param> getParams() {
        return this.params;
    }

    public WinBarraProgresso getWinBarraProgresso() {
        return this.winBarraProgresso;
    }

    public WinLibreOffice getWinLibreOffice() {
        return this.winLibreOffice;
    }

    public WinSobre getWinSobre() {
        return this.winSobre;
    }

    public void imprimirLog(String mensagem) {
        log.info(mensagem);
    }

    public void imprimirLogEhProgresso(String msg) {
        this.imprimirLog(msg);
        this.imprimirProgresso(msg);
    }

    public void imprimirProgresso(String mensagem) {
        this.getWinBarraProgresso().imprimir(mensagem);
    }

    protected void iniciarBarraProgresso() {
        this.getApplet().setCursor(Cursor.getPredefinedCursor(3));
        JFrameUtils.setCenterLocation(this.getWinBarraProgresso());
        this.getWinBarraProgresso().setVisible(true);
        this.getWinBarraProgresso().imprimirMensagemPadrao();
    }

    public boolean isConectadoLibreOffice() {
        return this.getJLibreOffice() != null && this.getJLibreOffice().isConnected();
    }

    public boolean isDefinidoDocumentoRemoto() {
        return this.arquivoRemoto != null && !this.arquivoRemoto.equals("");
    }

    public boolean isEditandoDocumento() {
        return this.getJLibreOffice() != null && this.getJLibreOffice().isEditingDocument();
    }

    public boolean isEditandoDocumentoRemoto() {
        return this.getArquivoRemoto() != null && !this.getArquivoRemoto().equals("");
    }

    protected void limpar() {
        this.arquivoRemoto = null;
        this.params = null;
    }

    public void mostrarConfiguracao() {
        if(this.verificarEditorTjro()) {
            if(((Boolean)this.getConfigs().getSign().getValue()).booleanValue()) {
                this.assinador.mostrarConfiguracao();
            }

        }
    }

    protected void mostrarInterfaceSeNecessario() {
        if(!this.getWinLibreOffice().isVisible()) {
            this.getWinLibreOffice().setVisible(true);
        }

    }

    public void onClickMenuArquivoAbrirDocumentoLocal() {
        this.abrirDocumentoLocal();
    }

    public void onClickMenuArquivoEnviarDocumento() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    BaseEditorTjro.this.enviarDocumentoAction();
                } catch (Exception var2) {
                    BaseEditorTjro.this.fecharBarraProgresso();
                    BaseEditorTjro.log.error(var2);
                    JFrameUtils.showErro("Erro ao Enviar o documento", var2.getMessage());
                }

            }
        });
        thread.run();
    }

    public void onClickMenuArquivoSair() {
        this.onClosingWinLibreOffice();
    }

    public void onClosingWinLibreOffice() {
        try {
            OOoBeanProxy e = this.getJLibreOffice().getBean();
            if(e != null && e.getDocument() != null) {
                if(e.isModifiedDocument()) {
                    int resultado = JOptionPane.showOptionDialog(this.winLibreOffice, "O documento atual foi modificado.\nDeseja salvar as alterações?", "EditorTjro", 1, 3, (Icon)null, new String[]{"Salvar", "Descartar", "Cancelar"}, "Salvar");
                    switch(resultado) {
                        case 0:
                            this.getJLibreOffice().save();
                            this.getJLibreOffice().clear();
                            this.getWinLibreOffice().setVisible(false);
                            return;
                        case 1:
                            this.getJLibreOffice().clear();
                            this.getWinLibreOffice().setVisible(false);
                            return;
                        case 2:
                            this.getWinLibreOffice().setVisible(true);
                            return;
                    }
                } else {
                    this.getJLibreOffice().clear();
                    this.getWinLibreOffice().setVisible(false);
                }
            } else {
                this.getWinLibreOffice().setVisible(false);
            }
        } catch (NullPointerException var3) {
            this.getWinLibreOffice().setVisible(false);
        } catch (Exception var4) {
            log.error(var4);
            JFrameUtils.showErro("Erro ao fechar a janela!", var4.getMessage());
        }

    }

    private void preprararParams(String params) {
        if(params != null && params.length() != 0) {
            String[] indicesComValores = (String[])null;
            if(params.indexOf("&") != -1) {
                indicesComValores = params.split("&");
            } else {
                indicesComValores = new String[]{params};
            }

            this.params = new ArrayList();
            String[] var6 = indicesComValores;
            int var5 = indicesComValores.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String indiceComValor = var6[var4];
                String[] param = indiceComValor.split("=");
                this.params.add(new Param(param[0], param[1]));
            }
        }

    }

    public void stop() {
        log.info("");
        log.info("----------------------------");
        log.info("--- Parando o Applet !!! ---");
        log.info("----------------------------");
        if(this.getJLibreOffice() != null) {
            this.getJLibreOffice().stopOOoConnection();
        }

        this.executarManterSessao = false;
        if(this.threadManterSessao != null) {
            this.threadManterSessao.interrupt();
        }

    }

    private boolean verificarEditorTjro() {
        if(!this.inicializado) {
            this.inicializar();
        }

        String msg;
        if(this.getJLibreOffice() != null && this.getJLibreOffice().isInitialized()) {
            if(this.sessaoAberta) {
                return true;
            } else {
                msg = "Possívelmente sua sessão com o servidor expirou ou aconteceu algum problema com o servidor portanto este recurso não está disponível!\n";
                JFrameUtils.showAlerta("EditorTjro - Versão: 1.4.2", msg, this.getWinBarraProgresso());
                return false;
            }
        } else {
            msg = "O LibreOffice não foi inicializado corretamente ou não está instalado em sua máquina portanto este recurso não está disponível.\n";
            msg = msg + " - Contate o Apoio para solucionar o problema!\n";
            JFrameUtils.showAlerta("EditorTjro - Versão: 1.4.2", msg, this.getWinBarraProgresso());
            return false;
        }
    }

    private void inicializar() {
        this.inicializado = true;
        AccessController.doPrivileged(new PrivilegedAction() {
            public Void run() {
                try {
                    try {
                        JLibreOfficeInstallation.iniciar();
                    } catch (Exception var2) {
                        BaseEditorTjro.log.warn(var2.getMessage());
                        return null;
                    }

                    BaseEditorTjro.this.jLibreOffice = new JLibreOffice(JLibreOfficeInstallation.getInstance().getClassLoader());
                    BaseEditorTjro.this.jLibreOffice.setMenuBarVisible(false);
                    BaseEditorTjro.this.jLibreOffice.setStandardBarVisible(true);
                    BaseEditorTjro.this.jLibreOffice.setToolBarVisible(true);
                    System.setSecurityManager(new CustomSecurityManager());
                    if(BaseEditorTjro.this.winBarraProgresso == null) {
                        BaseEditorTjro.this.winBarraProgresso = new WinBarraProgresso();
                    }

                    BaseEditorTjro.this.winLibreOffice = new WinLibreOffice(BaseEditorTjro.this);
                    BaseEditorTjro.this.winSobre = new WinSobre(BaseEditorTjro.this.getWinLibreOffice());
                    BaseEditorTjro.this.menuBar = new EditorTjroMenuBar(BaseEditorTjro.this);
                    BaseEditorTjro.this.winLibreOffice.setMenuBar(BaseEditorTjro.this.menuBar);
                    BaseEditorTjro.this.getWinLibreOffice().add(BaseEditorTjro.this.jLibreOffice.getBean().getContainer(), "Center");
                    if(((Boolean)BaseEditorTjro.this.getConfigs().getSign().getValue()).booleanValue() && BaseEditorTjro.this.assinador == null) {
                        BaseEditorTjro.this.assinador = new Assinador();
                        BaseEditorTjro.this.assinador.setLog(BaseEditorTjro.this);
                        BaseEditorTjro.this.assinador.setProgresso(BaseEditorTjro.this);
                    }

                    if(((Boolean)BaseEditorTjro.this.getConfigs().getShowGui().getValue()).booleanValue()) {
                        BaseEditorTjro.this.getWinLibreOffice().setVisible(true);
                    }

                    if(BaseEditorTjro.this.getConfigs().getServerSession().isDeclarado()) {
                        BaseEditorTjro.this.threadManterSessao = new Thread(new Runnable() {
                            public void run() {
                                while(BaseEditorTjro.this.sessaoAberta && BaseEditorTjro.this.executarManterSessao) {
                                    BaseEditorTjro.this.manterSessaoAtualizada();

                                    try {
                                        Thread.sleep(120000L);
                                    } catch (InterruptedException var2) {
                                        BaseEditorTjro.log.error(var2);
                                    }
                                }

                            }
                        });
                        BaseEditorTjro.this.threadManterSessao.start();
                    }

                    BaseEditorTjro.this.limpar();
                } catch (Exception var3) {
                    BaseEditorTjro.log.error(var3);
                    JFrameUtils.showErro("Erro na inicialização da configuração", var3.getMessage());
                }

                return null;
            }
        });
    }

    public void onClickMenuArquivoMostrarArquivosTemporarios() {
        WinArquivoTemporario win = new WinArquivoTemporario(this.getWinLibreOffice(), this);
        win.setVisible(true);
    }

    public void abrirDocumentoTemprario(File arquivo) {
        try {
            this.abrirDocumentoAction(arquivo);
        } catch (Exception var3) {
            log.error(var3);
            JFrameUtils.showErro("Erro na inicialização da configuração", var3.getMessage());
        }

    }
}
