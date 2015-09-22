//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.gui;

import br.com.atos.utils.swing.JFrameUtils;
import com.googlecode.assinador.util.CertificadoUtils;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class FrmSelecionarCertificadoMscapi extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private List<X509Certificate> certificados;
    private X509Certificate certificado;
    private JButton jButtonCancelar;
    private JButton jButtonOK;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTableCertificados;
    private JTextArea jTextAreaCertificado;
    private int returnStatus = 0;

    public X509Certificate getCertificado() {
        return this.certificado;
    }

    public void iniciar(List<X509Certificate> certificados) {
        this.limpar();
        this.certificados = certificados;
        DefaultTableModel tm = this.getJTableCertificadosModel();
        tm.setRowCount(0);
        Iterator var4 = certificados.iterator();

        while(var4.hasNext()) {
            X509Certificate certificado = (X509Certificate)var4.next();
            String nome = CertificadoUtils.getCertificadoCN(certificado.getSubjectDN().getName());
            String emissor = CertificadoUtils.getCertificadoCN(certificado.getIssuerX500Principal().getName());
            tm.addRow(new Object[]{nome, emissor});
        }

        JFrameUtils.setCenterLocation(this);
        this.setVisible(true);
    }

    private void limpar() {
        this.certificado = null;
        this.jTextAreaCertificado.setText("");
    }

    private DefaultTableModel getJTableCertificadosModel() {
        return (DefaultTableModel)this.jTableCertificados.getModel();
    }

    public FrmSelecionarCertificadoMscapi(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        String cancelName = "cancel";
        InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                FrmSelecionarCertificadoMscapi.this.doClose(0);
            }
        });
    }

    public int getReturnStatus() {
        return this.returnStatus;
    }

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        this.setVisible(false);
        this.dispose();
    }

    private void initComponents() {
        this.jButtonOK = new JButton();
        this.jButtonCancelar = new JButton();
        this.jLabel1 = new JLabel();
        this.jScrollPane1 = new JScrollPane();
        this.jTableCertificados = new JTable();
        this.jLabel2 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.jTextAreaCertificado = new JTextArea();
        this.setTitle("Formulário de seleção de certificado digital.");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                FrmSelecionarCertificadoMscapi.this.formWindowClosing(evt);
            }
        });
        this.jButtonOK.setText("OK");
        this.jButtonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmSelecionarCertificadoMscapi.this.jButtonOKActionPerformed(evt);
            }
        });
        this.jButtonCancelar.setText("Cancelar");
        this.jButtonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmSelecionarCertificadoMscapi.this.jButtonCancelarActionPerformed(evt);
            }
        });
        this.jLabel1.setText("Lista de certificados:");
        this.jTableCertificados.setModel(new DefaultTableModel(new Object[0][], new String[]{"Certificado do Usuário", "Certificado do Emissor"}) {
            private static final long serialVersionUID = 1L;
            Class<?>[] types = new Class[]{String.class, String.class};
            boolean[] canEdit = new boolean[2];

            public Class<?> getColumnClass(int columnIndex) {
                return this.types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jTableCertificados.setAutoResizeMode(0);
        this.jTableCertificados.setSelectionMode(0);
        this.jTableCertificados.getTableHeader().setReorderingAllowed(false);
        this.jTableCertificados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                FrmSelecionarCertificadoMscapi.this.jTableCertificadosMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.jTableCertificados);
        this.jTableCertificados.getColumnModel().getColumn(0).setPreferredWidth(188);
        this.jTableCertificados.getColumnModel().getColumn(1).setPreferredWidth(188);
        this.jLabel2.setText("Informações do certificado selecionado:");
        this.jTextAreaCertificado.setColumns(20);
        this.jTextAreaCertificado.setEditable(false);
        this.jTextAreaCertificado.setRows(5);
        this.jScrollPane2.setViewportView(this.jTextAreaCertificado);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, Alignment.TRAILING, -1, 380, 32767).addComponent(this.jLabel2).addComponent(this.jScrollPane2, -1, 380, 32767).addComponent(this.jLabel1).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButtonOK).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButtonCancelar))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 120, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -1, 115, 32767).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButtonCancelar).addComponent(this.jButtonOK)).addContainerGap()));
        this.pack();
    }

    private void jButtonOKActionPerformed(ActionEvent evt) {
        if(this.certificado == null) {
            JFrameUtils.showErro("Erro de validação", "Por favor, você deve escolher um certificado!");
        } else {
            this.doClose(1);
        }

    }

    private void jButtonCancelarActionPerformed(ActionEvent evt) {
        this.doClose(0);
    }

    private void formWindowClosing(WindowEvent evt) {
        this.doClose(0);
    }

    private void jTableCertificadosMouseClicked(MouseEvent evt) {
        if(!this.jTableCertificados.getSelectionModel().isSelectionEmpty()) {
            int row = this.jTableCertificados.getSelectedRow();
            this.certificado = (X509Certificate)this.certificados.get(row);
            if(this.certificado != null) {
                this.jTextAreaCertificado.setText("");
                String[] items = this.certificado.getSubjectDN().getName().split(",");

                for(int i = items.length - 1; i >= 0; --i) {
                    this.jTextAreaCertificado.append(items[i].trim() + "\n");
                }
            }
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmSelecionarCertificadoMscapi dialog = new FrmSelecionarCertificadoMscapi(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}
