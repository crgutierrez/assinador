//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.gui;

import br.com.atos.utils.OsUtil;
import br.com.atos.utils.swing.FileNameExtensionFilter;
import br.com.atos.utils.swing.JFrameUtils;
import com.googlecode.assinador.modelo.Repositorio;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmTipoRepositorio extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private File pkcs12Arquivo;
    private ButtonGroup bgTipoRepositorio;
    private JButton btnSelecionarArquivoPkcs12;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JButton okButton;
    private JRadioButton rbMscapi;
    private JRadioButton rbPkcs12;
    private JTextField txtPkcs12Arquivo;
    private int returnStatus = 0;

    public void limpar() {
        this.pkcs12Arquivo = null;
        this.txtPkcs12Arquivo.setText("");
    }

    public void iniciar(Repositorio tipoRepositorio) {
        this.rbMscapi.setEnabled(false);
        this.rbMscapi.setVisible(false);
        if(OsUtil.isOsWindows()) {
            this.rbMscapi.setEnabled(true);
            this.rbMscapi.setVisible(true);
        }

        this.pack();
        this.limpar();
        if(tipoRepositorio != null) {
            if(tipoRepositorio.isTipoRepositorioPkcs12()) {
                this.rbPkcs12.setSelected(true);
                if(tipoRepositorio.isDefinidoPkcs12Arquivo()) {
                    this.pkcs12Arquivo = tipoRepositorio.getPkcs12Arquivo();
                    this.atualizarTxtPkcs12Arquivo();
                }
            } else if(tipoRepositorio.isTipoRepositorioMscapi()) {
                this.rbMscapi.setSelected(true);
            }
        }

        JFrameUtils.setCenterLocation(this);
        this.setVisible(true);
    }

    public Repositorio getTipoRepositorio() {
        if(this.bgTipoRepositorio.getSelection() != null) {
            Repositorio tipoRepositorio = new Repositorio();
            tipoRepositorio.setTipoRepositorio(this.bgTipoRepositorio.getSelection().getActionCommand());
            if("pkcs12".equals(this.bgTipoRepositorio.getSelection().getActionCommand())) {
                tipoRepositorio.setPkcs12Arquivo(this.pkcs12Arquivo);
            }

            return tipoRepositorio;
        } else {
            return null;
        }
    }

    public FrmTipoRepositorio(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        String cancelName = "cancel";
        InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                FrmTipoRepositorio.this.doClose(0);
            }
        });
    }

    public int getReturnStatus() {
        return this.returnStatus;
    }

    private void initComponents() {
        this.bgTipoRepositorio = new ButtonGroup();
        this.okButton = new JButton();
        this.cancelButton = new JButton();
        this.jPanel1 = new JPanel();
        this.rbPkcs12 = new JRadioButton();
        this.jLabel1 = new JLabel();
        this.txtPkcs12Arquivo = new JTextField();
        this.btnSelecionarArquivoPkcs12 = new JButton();
        this.rbMscapi = new JRadioButton();
        this.setTitle("Formulário de tipo de repositório");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                FrmTipoRepositorio.this.closeDialog(evt);
            }
        });
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmTipoRepositorio.this.okButtonActionPerformed(evt);
            }
        });
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmTipoRepositorio.this.cancelButtonActionPerformed(evt);
            }
        });
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("Tipo de repositório:"));
        this.bgTipoRepositorio.add(this.rbPkcs12);
        this.rbPkcs12.setText("Arquivo PKCS12");
        this.rbPkcs12.setActionCommand("pkcs12");
        this.rbPkcs12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmTipoRepositorio.this.rbPkcs12ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Arquivo:");
        this.btnSelecionarArquivoPkcs12.setText("...");
        this.btnSelecionarArquivoPkcs12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmTipoRepositorio.this.btnSelecionarArquivoPkcs12ActionPerformed(evt);
            }
        });
        this.bgTipoRepositorio.add(this.rbMscapi);
        this.rbMscapi.setText("Windows MsCapi");
        this.rbMscapi.setActionCommand("mscapi");
        this.rbMscapi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmTipoRepositorio.this.rbMscapiActionPerformed(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.rbPkcs12)).addGroup(jPanel1Layout.createSequentialGroup().addGap(49, 49, 49).addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.txtPkcs12Arquivo, -2, 229, -2).addGap(6, 6, 6).addComponent(this.btnSelecionarArquivoPkcs12)).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.rbMscapi))).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.rbPkcs12).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.txtPkcs12Arquivo, -2, -1, -2).addComponent(this.btnSelecionarArquivoPkcs12)).addGap(18, 18, 18).addComponent(this.rbMscapi).addContainerGap(-1, 32767)));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(268, 32767).addComponent(this.okButton, -2, 67, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.cancelButton).addContainerGap()));
        layout.linkSize(0, new Component[]{this.cancelButton, this.okButton});
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.cancelButton).addComponent(this.okButton)).addContainerGap(-1, 32767)));
        this.getRootPane().setDefaultButton(this.okButton);
        this.pack();
    }

    private void okButtonActionPerformed(ActionEvent evt) {
        if(this.bgTipoRepositorio.getSelection() == null) {
            JFrameUtils.showErro("Erro de validação", "Por favor, deve-se informar um tipo de repositório!");
        } else if("pkcs12".equals(this.bgTipoRepositorio.getSelection().getActionCommand()) && this.pkcs12Arquivo == null) {
            JFrameUtils.showErro("Erro de validação", "Por favor, deve-se definir o endereço do arquivo do certificado PKCS12!");
        } else {
            this.doClose(1);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        this.doClose(0);
    }

    private void closeDialog(WindowEvent evt) {
        this.doClose(0);
    }

    private void rbPkcs12ActionPerformed(ActionEvent evt) {
        this.atualizarTxtPkcs12Arquivo();
    }

    private void btnSelecionarArquivoPkcs12ActionPerformed(ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(0);
        fc.setMultiSelectionEnabled(false);
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filtroPadrao = new FileNameExtensionFilter("Arquivo PKCS12 (*.P12)", new String[]{"p12"});
        fc.setFileFilter(filtroPadrao);
        fc.addChoosableFileFilter(filtroPadrao);
        int retorno = fc.showOpenDialog((Component)null);
        if(retorno == 0) {
            this.pkcs12Arquivo = fc.getSelectedFile();
            this.rbPkcs12.setSelected(true);
            this.atualizarTxtPkcs12Arquivo();
        }

    }

    private void rbMscapiActionPerformed(ActionEvent evt) {
        this.pkcs12Arquivo = null;
        this.atualizarTxtPkcs12Arquivo();
    }

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmTipoRepositorio dialog = new FrmTipoRepositorio(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void atualizarTxtPkcs12Arquivo() {
        if(this.bgTipoRepositorio.getSelection() != null && "pkcs12".equals(this.bgTipoRepositorio.getSelection().getActionCommand()) && this.pkcs12Arquivo != null) {
            this.txtPkcs12Arquivo.setText(this.pkcs12Arquivo.getAbsolutePath());
        } else {
            this.txtPkcs12Arquivo.setText("");
        }

    }
}
