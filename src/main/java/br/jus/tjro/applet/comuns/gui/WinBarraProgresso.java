//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.gui;

import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class WinBarraProgresso extends JDialog {
    private static final long serialVersionUID = 1L;
    public static final String MSG_PADRAO = "Aguarde um momento por favor...";
    private JProgressBar barraProgresso;
    private JLabel mensagem;

    public WinBarraProgresso() {
        this.setTitle("Aguarde...");
        this.setModal(false);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(400, 170));
        this.setLayout((LayoutManager)null);
        this.mensagem = new JLabel();
        this.mensagem.setBounds(20, 50, 360, 20);
        this.barraProgresso = new JProgressBar();
        this.barraProgresso.setIndeterminate(true);
        this.barraProgresso.setBounds(18, 70, 360, 20);
        this.add(this.mensagem);
        this.add(this.barraProgresso);
        this.pack();
    }

    public void imprimir(String msg) {
        this.mensagem.setText(msg);
    }

    public void imprimirMensagemPadrao() {
        this.imprimir("Aguarde um momento por favor...");
    }
}
