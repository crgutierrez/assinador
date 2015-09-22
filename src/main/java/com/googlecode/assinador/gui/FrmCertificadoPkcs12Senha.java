//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.assinador.gui;

import br.com.atos.utils.StringUtils;
import br.com.atos.utils.swing.JFrameUtils;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrmCertificadoPkcs12Senha extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private JButton btnCancelar;
    private JButton btnOk;
    private JLabel jLabel1;
    private JPasswordField pwdSenha;
    private int returnStatus = 0;

    public void iniciar() {
        this.limpar();
        JFrameUtils.setCenterLocation(this);
        this.setVisible(true);
    }

    private void limpar() {
        this.pwdSenha.setText("");
        this.btnOk.setEnabled(false);
    }

    public char[] getSenha() {
        return this.pwdSenha.getPassword();
    }

    private void concluir() {
        if(StringUtils.isNullOrEmpty(this.pwdSenha.getPassword())) {
            JFrameUtils.showErro("Erro de validação", "Por favor, digite sua senha!");
        } else {
            this.doClose(1);
        }
    }

    public FrmCertificadoPkcs12Senha(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
        String cancelName = "cancel";
        InputMap inputMap = this.getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = this.getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                FrmCertificadoPkcs12Senha.this.doClose(0);
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
        this.pwdSenha = new JPasswordField();
        this.jLabel1 = new JLabel();
        this.btnOk = new JButton();
        this.btnCancelar = new JButton();
        this.setTitle("Senha do certificado!");
        this.setModal(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                FrmCertificadoPkcs12Senha.this.formWindowClosing(evt);
            }
        });
        this.pwdSenha.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                FrmCertificadoPkcs12Senha.this.pwdSenhaKeyReleased(evt);
            }
        });
        this.jLabel1.setText("Senha:");
        this.btnOk.setText("OK");
        this.btnOk.setEnabled(false);
        this.btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmCertificadoPkcs12Senha.this.btnOkActionPerformed(evt);
            }
        });
        this.btnCancelar.setText("Cancelar");
        this.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrmCertificadoPkcs12Senha.this.btnCancelarActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.btnOk).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btnCancelar)).addComponent(this.pwdSenha, -1, 179, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.pwdSenha, -2, -1, -2).addComponent(this.jLabel1)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.btnOk).addComponent(this.btnCancelar)).addContainerGap(-1, 32767)));
        this.pack();
    }

    private void formWindowClosing(WindowEvent evt) {
        this.doClose(0);
    }

    private void btnOkActionPerformed(ActionEvent evt) {
        this.concluir();
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        this.doClose(0);
    }

    private void pwdSenhaKeyReleased(KeyEvent evt) {
        if(this.pwdSenha.getPassword() != null && this.pwdSenha.getPassword().length > 0) {
            this.btnOk.setEnabled(true);
            if(evt.getKeyCode() == 10) {
                this.concluir();
            }
        } else {
            this.btnOk.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmCertificadoPkcs12Senha dialog = new FrmCertificadoPkcs12Senha(new JFrame(), true);
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
