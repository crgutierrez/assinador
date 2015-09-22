//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro;

import br.com.atos.utils.StringUtils;
import br.com.atos.utils.arquivo.ArquivoMetadado;
import br.com.atos.utils.arquivo.ArquivoUtils;
import br.com.atos.utils.swing.FileNameExtensionFilter;
import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.applet.comuns.gui.WinBarraProgresso;
import br.jus.tjro.applet.comuns.vo.Param;
import br.jus.tjro.assinadortjro.arquivo.ArquivoAtributo;
import br.jus.tjro.assinadortjro.arquivo.ArquivoItem;
import br.jus.tjro.assinadortjro.arquivo.ArquivoItemLocal;
import br.jus.tjro.assinadortjro.arquivo.ArquivoItemRemoto;
import br.jus.tjro.assinadortjro.config.Configs;
import br.jus.tjro.assinadortjro.gui.AssinadorTjroMenuBar;
import br.jus.tjro.assinadortjro.gui.WinDesenvolvimento;
import br.jus.tjro.assinadortjro.gui.WinSobre;
import br.jus.tjro.assinadortjro.util.ArquivoAtributoUtils;
import com.googlecode.assinador.Assinador;
import com.googlecode.assinador.interfaces.AssinadorLog;
import com.googlecode.assinador.interfaces.AssinadorProgresso;
import com.googlecode.assinador.modelo.ArquivoAssinado;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.http.client.methods.CloseableHttpResponse;
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

public class BaseAssinadorTjro implements AssinadorProgresso, AssinadorLog {
    private static final Logger log = Logger.getLogger(BaseAssinadorTjro.class);
    public static final String PARAM_ARQUIVO_NAME = "arquivo";
    public static final String ASSINADOR_NOME = "AssinadorTjro";
    public static final String ASSINADOR_VERSAO = "1.2.2";
    public static final String ASSINADOR_TITULO = "AssinadorTjro - Versão: 1.2.2";
    private JApplet applet;
    private JButton btnAssinar;
    private JButton btnVisualizar;
    private Configs configs;
    private AssinadorTjroMenuBar menuBar;
    private JPanel pnRoot;
    private JTable table;
    private DefaultTableModel tableModel;
    private Assinador assinador;
    private WinBarraProgresso winBarraProgresso;
    private List<ArquivoItem> arquivosItens;
    private WinSobre winSobre;

    public BaseAssinadorTjro(JApplet applet) {
        this(applet, (WinBarraProgresso)null, (Assinador)null);
    }

    public BaseAssinadorTjro(JApplet applet, WinBarraProgresso winBarraProgresso, Assinador assinador) {
        this.arquivosItens = new ArrayList();
        this.applet = applet;
        this.assinador = assinador;
        this.winBarraProgresso = winBarraProgresso;
        log.info("---------------------------------------");
        log.info("--- AssinadorTjro - Versão: 1.2.2");
        log.info("---------------------------------------");

        try {
            this.configs = new Configs(applet);
            this.configs.showConfigs();
            if(((Boolean)this.getConfigs().getDevelopment().getValue()).booleanValue()) {
                new WinDesenvolvimento(this);
            }

            if(this.assinador == null) {
                this.assinador = new Assinador();
                this.assinador.setLog(this);
                this.assinador.setProgresso(this);
            }

            if(this.winBarraProgresso == null) {
                this.winBarraProgresso = new WinBarraProgresso();
            }

            this.winSobre = new WinSobre();
            this.iniciarPanelRoot();
            this.iniciarBotoes();
            this.iniciarTabela();
            this.atualizarInterface();
        } catch (Exception var5) {
            log.error(var5);
            JFrameUtils.showErro("Erro na inicialização da configuração", "Erro: " + var5.getMessage());
        }

    }

    public void imprimirLog(String msg) {
        log.info(msg);
    }

    public void imprimirProgresso(String msg) {
        this.winBarraProgresso.imprimir(msg);
    }

    public void imprimirLogEhProgresso(String msg) {
        this.imprimirLog(msg);
        this.imprimirProgresso(msg);
    }

    private void addArquivoItem(ArquivoItem arquivoItem) {
        Vector row = new Vector();
        row.add(arquivoItem.getNome());
        row.add(arquivoItem.getDescricao());
        Iterator var4 = this.getConfigs().getArquivoItens().getAtributos().iterator();

        while(var4.hasNext()) {
            ArquivoAtributo atributo = (ArquivoAtributo)var4.next();
            row.add(arquivoItem.getAtributos().get(atributo.getId()));
        }

        this.arquivosItens.add(arquivoItem);
        this.tableModel.addRow(row);
        this.atualizarInterface();
    }

    public String addMensagemTexto(String nome, String conteudo) {
        return String.valueOf(this.addMensagemTextoAction(nome, conteudo, (String)null));
    }

    public String addMensagemTexto(String nome, String conteudo, String params) {
        return String.valueOf(this.addMensagemTextoAction(nome, conteudo, params));
    }

    public String addArquivoLocal() {
        return String.valueOf(this.addArquivoLocalAction((String)null));
    }

    public String addArquivoLocal(String params) {
        return String.valueOf(this.addArquivoLocalAction(params));
    }

    private boolean addMensagemTextoAction(final String mensagemNome, final String mensagemConteudo, final String params) {
        return ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {
            public Boolean run() {
                try {
                    if(!StringUtils.isNullOrEmpty(mensagemNome) && !StringUtils.isNullOrEmpty(mensagemConteudo)) {
                        String e = ArquivoUtils.getArquivoNomeSemExtensao(mensagemNome);
                        String arquivoNomeExtensao = ArquivoUtils.getExtensao(mensagemNome);
                        if(arquivoNomeExtensao != null && !arquivoNomeExtensao.equals("") && e != null && !e.equals("")) {
                            File dirTmp = ArquivoUtils.getDiretorioTemporario();
                            BaseAssinadorTjro.log.info(" - Diretório de Arquivos Temporários: " + dirTmp.getAbsolutePath());
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
                            Date dt = new Date();
                            String data = sdf.format(dt);
                            String arqNomePrefixo = e;
                            File arquivo = new File(dirTmp, ArquivoMetadado.gerarNomeComExtensaoSeguro(e + "_" + data, arquivoNomeExtensao));
                            BaseAssinadorTjro.log.info(" - Arquivo Nome: " + arquivo.getAbsolutePath());
                            if(arquivo.exists()) {
                                while(arquivo.exists()) {
                                    dt = new Date();
                                    data = sdf.format(dt);
                                    arquivo = new File(dirTmp, ArquivoMetadado.gerarNomeComExtensaoSeguro(arqNomePrefixo + "_" + data, arquivoNomeExtensao));
                                    BaseAssinadorTjro.log.info(" - Arquivo com Nome único: " + arquivo.getAbsolutePath());
                                }
                            }

                            FileOutputStream fos = new FileOutputStream(arquivo);
                            fos.write(mensagemConteudo.getBytes("UTF-8"));
                            ArquivoItemLocal arquivoItemLocal = new ArquivoItemLocal(arquivo);
                            arquivoItemLocal.setAtributos(ArquivoAtributoUtils.processar(params, BaseAssinadorTjro.this.getConfigs().getArquivoItens().getAtributos()));
                            BaseAssinadorTjro.this.addArquivoItem(arquivoItemLocal);
                            return Boolean.TRUE;
                        } else {
                            throw new Exception("O nome do arquivo é inválido!");
                        }
                    } else {
                        return Boolean.FALSE;
                    }
                } catch (Exception var11) {
                    JFrameUtils.showErro("Erro ao adicionar um arquivo", "Erro: " + var11.getMessage());
                    return Boolean.FALSE;
                }
            }
        })).booleanValue();
    }

    private boolean addArquivoLocalAction(final String params) {
        return ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    JFileChooser e = new JFileChooser();
                    e.setDialogTitle("Selecione...");
                    e.setMultiSelectionEnabled(((Boolean)BaseAssinadorTjro.this.getConfigs().getSelectionMultiple().getValue()).booleanValue());
                    e.setFileSelectionMode(((Integer)BaseAssinadorTjro.this.getConfigs().getSelectionMode().getValue()).intValue());
                    if(BaseAssinadorTjro.this.getConfigs().getFileFilters().isDeclarado()) {
                        e.setAcceptAllFileFilterUsed(false);
                        List retorno = (List)BaseAssinadorTjro.this.getConfigs().getFileFilters().getValue();
                        Iterator selected = retorno.iterator();

                        while(selected.hasNext()) {
                            FileNameExtensionFilter selectedFiles = (FileNameExtensionFilter)selected.next();
                            e.addChoosableFileFilter(selectedFiles);
                        }

                        e.setFileFilter((FileFilter)retorno.get(0));
                    }

                    int var10 = e.showOpenDialog((Component)null);
                    if(var10 != 0) {
                        return Boolean.FALSE;
                    } else {
                        File[] var11 = (File[])null;
                        if(((Boolean)BaseAssinadorTjro.this.getConfigs().getSelectionMultiple().getValue()).booleanValue()) {
                            var11 = e.getSelectedFiles();
                        } else {
                            var11 = new File[]{e.getSelectedFile()};
                        }

                        File[] var7 = var11;
                        int var6 = var11.length;

                        for(int var5 = 0; var5 < var6; ++var5) {
                            File var12 = var7[var5];
                            ArquivoItemLocal arquivo = new ArquivoItemLocal(var12);
                            arquivo.setAtributos(ArquivoAtributoUtils.processar(params, BaseAssinadorTjro.this.getConfigs().getArquivoItens().getAtributos()));
                            BaseAssinadorTjro.this.addArquivoItem(arquivo);
                        }

                        return Boolean.TRUE;
                    }
                } catch (Exception var9) {
                    JFrameUtils.showErro("Erro ao adicionar um arquivo", "Erro: " + var9.getMessage());
                    return Boolean.FALSE;
                }
            }
        })).booleanValue();
    }

    public void addArquivoRemoto(String arquivoNome, String url) {
        this.addArquivoRemotoAction(arquivoNome, url, (String)null);
    }

    public void addArquivoRemoto(String arquivoNome, String url, String params) {
        this.addArquivoRemotoAction(arquivoNome, url, params);
    }

    private void addArquivoRemotoAction(String arquivoNome, String urlValue, String params) {
        try {
            URL e;
            try {
                e = new URL(urlValue);
            } catch (Exception var6) {
                throw new Exception("A URL é inválida, " + var6.getMessage());
            }

            ArquivoItemRemoto arquivoRemoto = new ArquivoItemRemoto(e, arquivoNome, ArquivoAtributoUtils.processar(params, this.getConfigs().getArquivoItens().getAtributos()));
            this.addArquivoItem(arquivoRemoto);
        } catch (Exception var7) {
            JFrameUtils.showErro("Erro ao adicionar um arquivo remoto", "Erro: " + var7.getMessage());
        }

    }

    private void atualizarInterface() {
        this.btnAssinar.setEnabled(false);
        this.btnVisualizar.setEnabled(false);
        this.menuBar.getMenuArquivoExcluir().setEnabled(false);
        this.menuBar.getMenuArquivoVisualizar().setEnabled(false);
        this.menuBar.getMenuArquivoAssinar().setEnabled(false);
        if(this.getPermiteExcluir()) {
            this.menuBar.getMenuArquivoExcluir().setEnabled(true);
        }

        if(this.getPermiteVisualizar()) {
            this.btnVisualizar.setEnabled(true);
            this.menuBar.getMenuArquivoVisualizar().setEnabled(true);
        }

        if(this.getTemArquivoParaAssinar()) {
            this.btnAssinar.setEnabled(true);
            this.menuBar.getMenuArquivoAssinar().setEnabled(true);
        }

    }

    public void excluirArquivo(String indexValue) {
        try {
            Integer e = Integer.valueOf(indexValue);
            if(e.intValue() >= this.getArquivosItens().size()) {
                throw new Exception("Não existe arquivo na posição " + indexValue + "!");
            }

            this.tableModel.removeRow(e.intValue());
            this.getArquivosItens().remove(e.intValue());
            this.atualizarInterface();
        } catch (Exception var3) {
            JFrameUtils.showErro("Erro na exclusão.", var3.getMessage());
        }

    }

    public void excluirArquivosSelecionados() {
        if(this.table.getSelectedRowCount() > 0) {
            int resultado = JOptionPane.showConfirmDialog((Component)null, "Você tem certeza que deseja excluir o(s) arquivo(s) selecionado(s)!", "Confirmar exclusão!", 0);
            if(resultado == 0) {
                int[] selectedRows = this.table.getSelectedRows();

                for(int i = selectedRows.length - 1; i >= 0; --i) {
                    int row = selectedRows[i];
                    this.tableModel.removeRow(row);
                    this.getArquivosItens().remove(row);
                }
            }

            this.atualizarInterface();
        }

    }

    public void excluirTodosArquivos() {
        try {
            this.tableModel.setRowCount(0);
            this.getArquivosItens().clear();
            this.atualizarInterface();
        } catch (Exception var2) {
            JFrameUtils.showErro("Erro na exclusão.", var2.getMessage());
        }

    }

    public List<ArquivoItem> getArquivosItens() {
        return this.arquivosItens;
    }

    protected JApplet getApplet() {
        return this.applet;
    }

    public boolean getPermiteExcluir() {
        return this.table.getSelectedRowCount() > 0;
    }

    public boolean getPermiteVisualizar() {
        return this.table.getSelectedRowCount() == 1 && this.arquivosItens.get(this.table.getSelectedRow()) instanceof ArquivoItemRemoto;
    }

    public boolean getTemArquivoParaAssinar() {
        return this.arquivosItens != null && this.arquivosItens.size() > 0;
    }

    private void iniciarBotoes() {
        JPanel pnBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setEnabled(((Boolean)this.getConfigs().getPermiteAddArquivo().getValue()).booleanValue());
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    BaseAssinadorTjro.this.addArquivoLocal();
                } catch (Exception var3) {
                    JFrameUtils.showErro("Erro", var3.getMessage());
                }

            }
        });
        this.btnVisualizar = new JButton("Visualizar");
        this.btnVisualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BaseAssinadorTjro.this.visualizarArquivoSelecionado();
            }
        });
        this.btnAssinar = new JButton("Assinar");
        this.btnAssinar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BaseAssinadorTjro.this.assinarArquivos();
            }
        });
        pnBotoes.add(btnAdicionar);
        pnBotoes.add(this.btnVisualizar);
        pnBotoes.add(this.btnAssinar);
        this.pnRoot.add(pnBotoes, "South");
    }

    private void iniciarPanelRoot() {
        this.pnRoot = new JPanel(new BorderLayout());
        this.pnRoot.setBorder(BorderFactory.createEtchedBorder());
        this.menuBar = new AssinadorTjroMenuBar(this);
        this.pnRoot.add(this.menuBar, "North");
        this.applet.add(this.pnRoot);
    }

    private void iniciarTabela() {
        final Vector dados = new Vector();
        final Vector colunas = new Vector();
        colunas.add("Nome");
        colunas.add("URL");
        if(this.getConfigs().getArquivoItens().isDefinidoAtributos()) {
            Iterator scrollPane = this.getConfigs().getArquivoItens().getAtributos().iterator();

            while(scrollPane.hasNext()) {
                ArquivoAtributo pnTabela = (ArquivoAtributo)scrollPane.next();
                colunas.add(pnTabela.getNome());
            }
        }

        JPanel pnTabela1 = new JPanel();
        pnTabela1.setLayout(new BorderLayout());
        pnTabela1.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        this.table = new JTable(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table.setAutoResizeMode(0);
        this.table.setFillsViewportHeight(true);
        this.table.setColumnSelectionAllowed(false);
        this.table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                BaseAssinadorTjro.this.atualizarInterface();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
        this.table.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == 127) {
                    BaseAssinadorTjro.this.excluirArquivosSelecionados();
                }

            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyTyped(KeyEvent arg0) {
            }
        });
        if(colunas.size() == 2) {
            this.table.getColumnModel().getColumn(0).setPreferredWidth(240);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(250);
        } else {
            this.table.getColumnModel().getColumn(0).setPreferredWidth(180);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(240);
        }

        this.tableModel = (DefaultTableModel)this.table.getModel();
        if(this.getConfigs().getArquivoItens().isDefinidoArquivos()) {
            Iterator var5 = this.getConfigs().getArquivoItens().getArquivos().iterator();

            while(var5.hasNext()) {
                ArquivoItem scrollPane1 = (ArquivoItem)var5.next();
                this.addArquivoItem(scrollPane1);
            }
        }

        JScrollPane scrollPane2 = new JScrollPane(this.table);
        scrollPane2.setAutoscrolls(true);
        pnTabela1.add(this.table.getTableHeader(), "First");
        pnTabela1.add(scrollPane2, "Center");
        this.pnRoot.add(pnTabela1, "Center");
    }

    public void mostrarConfiguracao() {
        this.assinador.mostrarConfiguracao();
    }

    public void visualizarArquivoSelecionado() {
        if(this.getPermiteVisualizar()) {
            this.visualizarArquivoSelecionadoAction();
        }

    }

    private void visualizarArquivoSelecionadoAction() {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ArquivoItem arquivoItem = (ArquivoItem)BaseAssinadorTjro.this.getArquivosItens().get(BaseAssinadorTjro.this.table.getSelectedRow());
                BaseAssinadorTjro.this.imprimirLog("Visualizando o arquivo: " + arquivoItem.getNome() + ", url: " + arquivoItem.getUrl());
                BaseAssinadorTjro.this.getApplet().getAppletContext().showDocument(arquivoItem.getUrl(), "_blank");
                return null;
            }
        });
    }

    private void realizarDownloadArquivos() throws Exception {
        this.imprimirLogEhProgresso("Baixando os arquivos remotos...");
        Iterator var2 = this.getArquivosItens().iterator();

        while(var2.hasNext()) {
            ArquivoItem arquivo = (ArquivoItem)var2.next();
            if(arquivo instanceof ArquivoItemRemoto) {
                ArquivoItemRemoto arquivoRemoto = (ArquivoItemRemoto)arquivo;
                if(!arquivoRemoto.getFoiBaixadoArquivo()) {
                    this.realizarDownloadArquivo(arquivoRemoto);
                }
            }
        }

    }

    private void realizarDownloadArquivo(ArquivoItemRemoto arquivoRemoto) throws Exception {
        String arquivoNomeSemExtensao = ArquivoUtils.getArquivoNomeSemExtensao(arquivoRemoto.getNome());
        String arquivoExtensao = ArquivoUtils.getExtensao(arquivoRemoto.getNome());
        if(arquivoExtensao != null && !arquivoExtensao.equals("") && arquivoNomeSemExtensao != null && !arquivoNomeSemExtensao.equals("")) {
            this.imprimirLogEhProgresso("Baixando: " + arquivoRemoto.getNome());
            URLConnection urlConn = arquivoRemoto.getUrl().openConnection();
            if(this.getConfigs().getCookie().isDeclarado()) {
                urlConn.setRequestProperty("Cookie", (String)this.getConfigs().getCookie().getValue());
            }

            InputStream is = urlConn.getInputStream();
            this.imprimirLog("ABRIR DOCUMENTO REMOTO:");
            this.imprimirLog(" - Antes de Criar Arquivo Temporário.");
            this.imprimirLog(" - Nome do Arquivo Completo: " + arquivoRemoto.getNome());
            this.imprimirLog(" - Nome do Arquivo: " + arquivoNomeSemExtensao);
            this.imprimirLog(" - Extensão do Arquivo: " + arquivoExtensao);
            File dirTmp = new File(System.getProperty("java.io.tmpdir"));
            this.imprimirLog(" - Diretório de Arquivos Temporários: " + dirTmp.getAbsolutePath());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date dt = new Date();
            String data = sdf.format(dt);
            File arquivoBaixado = new File(dirTmp, "Documento_" + data + "." + arquivoExtensao);
            this.imprimirLog(" - Arquivo Nome: " + arquivoBaixado.getAbsolutePath());
            if(arquivoBaixado.exists()) {
                while(arquivoBaixado.exists()) {
                    dt = new Date();
                    data = sdf.format(dt);
                    arquivoBaixado = new File(dirTmp, "Documento_" + data + "." + arquivoExtensao);
                    this.imprimirLog(" - Arquivo com Nome único: " + arquivoBaixado.getAbsolutePath());
                }
            }

            FileOutputStream fos = new FileOutputStream(arquivoBaixado);
            this.imprimirLog(" - Inicia Escrita do Arquivo!");

            int umByte;
            for(long i = 1L; (umByte = is.read()) != -1; ++i) {
                String atual = ArquivoUtils.getTamanhoFormatado(Long.valueOf(i));
                this.imprimirProgresso("Baixando: " + arquivoRemoto.getNome() + " - " + atual);
                fos.write(umByte);
            }

            is.close();
            fos.close();
            this.imprimirLog(" - Finaliza a Escrita do Arquivo");
            arquivoRemoto.setArquivoBaixado(arquivoBaixado);
        } else {
            throw new Exception("O nome do arquivo é inválido!");
        }
    }

    private void assinarArquivosAction() throws Exception {
        this.iniciarBarraProgresso();
        this.realizarDownloadArquivos();
        Iterator var2 = this.getArquivosItens().iterator();

        while(true) {
            while(var2.hasNext()) {
                ArquivoItem arquivoItem = (ArquivoItem)var2.next();
                this.fecharBarraProgresso();
                ArquivoAssinado arquivoAssinado = this.assinador.assinarArquivo(arquivoItem.getArquivoParaAssinatura(), ((Boolean)this.getConfigs().getAtached().getValue()).booleanValue());
                this.iniciarBarraProgresso();
                if(((Boolean)this.getConfigs().getDevelopment().getValue()).booleanValue()) {
                    FileInputStream uploadSignedFileTo1 = new FileInputStream(arquivoAssinado.getArquivoAssinado());
                    byte[] routePlanner1 = new byte[uploadSignedFileTo1.available()];
                    uploadSignedFileTo1.read(routePlanner1);
                    JFileChooser httpClient1 = new JFileChooser();
                    httpClient1.setDialogTitle("Selecione...");
                    httpClient1.setDialogType(1);
                    httpClient1.setFileSelectionMode(1);
                    int httpPost2 = httpClient1.showSaveDialog((Component)null);
                    if(httpPost2 == 0) {
                        File builder1 = httpClient1.getSelectedFile();
                        FileOutputStream params1 = new FileOutputStream(new File(builder1, arquivoAssinado.getArquivoAssinado().getName()));
                        params1.write(routePlanner1);
                        params1.close();
                    }
                } else {
                    this.imprimirLogEhProgresso("Enviando: " + arquivoItem.getNome());
                    String uploadSignedFileTo = ((URL)this.getConfigs().getUploadSignedFileTo().getValue()).toString();
                    SystemDefaultRoutePlanner routePlanner = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
                    CloseableHttpClient httpClient;
                    if(uploadSignedFileTo.startsWith("https")) {
                        SSLContextBuilder httpPost = new SSLContextBuilder();
                        httpPost.loadTrustMaterial((KeyStore)null, new TrustSelfSignedStrategy());
                        SSLConnectionSocketFactory builder = new SSLConnectionSocketFactory(httpPost.build());
                        httpClient = HttpClients.custom().setSSLSocketFactory(builder).setRoutePlanner(routePlanner).build();
                    } else {
                        httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();
                    }

                    HttpPost httpPost1 = new HttpPost(uploadSignedFileTo);
                    MultipartEntityBuilder builder2 = MultipartEntityBuilder.create();
                    builder2.addPart("arquivo", new FileBody(arquivoAssinado.getArquivoAssinado()));
                    ArrayList params = new ArrayList();
                    if(this.getConfigs().getParams().isDeclarado()) {
                        params.addAll((Collection)this.getConfigs().getParams().getValue());
                    }

                    Iterator resultado;
                    if(this.getConfigs().getArquivoItens().isDefinidoAtributos()) {
                        resultado = arquivoItem.getAtributos().keySet().iterator();

                        while(resultado.hasNext()) {
                            String httpResponse = (String)resultado.next();
                            String value = arquivoItem.getAtributos().get(httpResponse).toString();
                            params.add(new Param(httpResponse, value));
                        }
                    }

                    resultado = params.iterator();

                    while(resultado.hasNext()) {
                        Param httpResponse1 = (Param)resultado.next();
                        builder2.addPart(httpResponse1.getName(), new StringBody(httpResponse1.getValue(), ContentType.TEXT_PLAIN));
                    }

                    httpPost1.setEntity(builder2.build());
                    if(this.getConfigs().getCookie().isDeclarado()) {
                        httpPost1.setHeader("Cookie", (String)this.getConfigs().getCookie().getValue());
                    }

                    CloseableHttpResponse httpResponse2 = httpClient.execute(httpPost1);
                    String resultado1 = EntityUtils.toString(httpResponse2.getEntity(), "UTF-8");
                    httpResponse2.close();
                    httpClient.close();
                    if(resultado1.startsWith("Erro:")) {
                        throw new Exception(resultado1.substring(5).trim());
                    }

                    this.imprimirLog("Arquivo enviado com sucesso: " + arquivoItem.getNome());
                }
            }

            this.imprimirLogEhProgresso("Assinatura concluída.");
            this.fecharBarraProgresso();
            if(((Boolean)this.getConfigs().getDevelopment().getValue()).booleanValue()) {
                JFrameUtils.showInfo("Concluido", "Assinatura digital efetuada com sucesso!", (Component)null);
            } else {
                this.getApplet().getAppletContext().showDocument((URL)this.getConfigs().getOnCompleteUploadSignedFile().getValue());
            }

            return;
        }
    }

    public void assinarArquivos() {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Void run() {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            BaseAssinadorTjro.this.assinarArquivosAction();
                        } catch (Exception var2) {
                            var2.printStackTrace();
                            BaseAssinadorTjro.this.fecharBarraProgresso();
                            JFrameUtils.showErro("Erro ao realizar a assinatura", "Erro: " + var2.getMessage());
                        }

                    }
                });
                thread.start();
                return null;
            }
        });
    }

    protected void iniciarBarraProgresso() {
        this.getApplet().setCursor(Cursor.getPredefinedCursor(3));
        JFrameUtils.setCenterLocation(this.winBarraProgresso);
        this.winBarraProgresso.setVisible(true);
        this.winBarraProgresso.imprimirMensagemPadrao();
    }

    private void fecharBarraProgresso() {
        this.getApplet().setCursor(Cursor.getPredefinedCursor(0));
        this.winBarraProgresso.setVisible(false);
    }

    public boolean getPermiteAdicionarArquivo() {
        return ((Boolean)this.getConfigs().getPermiteAddArquivo().getValue()).booleanValue();
    }

    public void onClickMenuAjudaSobre() {
        JFrameUtils.setCenterLocation(this.winSobre);
        this.winSobre.setVisible(true);
    }

    public Configs getConfigs() {
        return this.configs;
    }

    public Assinador getAssinador() {
        return this.assinador;
    }
}
