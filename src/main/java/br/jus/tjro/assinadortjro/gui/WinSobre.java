//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class WinSobre extends JDialog {
    private static final long serialVersionUID = 1L;

    public WinSobre() {
        this.setModal(true);
        this.setTitle("AssinadorTjro - Versão: 1.2.2");
        this.setResizable(false);
        this.setSize(450, 290);
        this.setBackground(Color.WHITE);
        this.addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                WinSobre.this.setVisible(false);
            }

            public void windowDeactivated(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }
        });
        JTextArea titulo = new JTextArea("AssinadorTjro");
        titulo.setFont(new Font("SansSerif", 1, 22));
        titulo.setEditable(false);
        titulo.setMargin(new Insets(10, 10, 10, 10));
        JTextPane msg = new JTextPane();
        msg.setMargin(new Insets(10, 10, 10, 10));
        StyledDocument doc = msg.getStyledDocument();
        Style def = StyleContext.getDefaultStyleContext().getStyle("default");
        Style bold = doc.addStyle("bold", def);
        StyleConstants.setBold(bold, true);

        try {
            doc.insertString(doc.getLength(), "Sobre\n", bold);
            doc.insertString(doc.getLength(), "Assinador Digital de arquivos em java.\n", def);
            doc.insertString(doc.getLength(), "Versão:1.2.2\n\n", def);
        } catch (BadLocationException var9) {
            var9.printStackTrace();
        }

        JButton btnFechar = new JButton("OK");
        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WinSobre.this.setVisible(false);
            }
        });
        JPanel pnBotoes = new JPanel();
        pnBotoes.setLayout(new FlowLayout(2));
        pnBotoes.add(btnFechar);
        JScrollPane scrollPane = new JScrollPane(msg);
        scrollPane.setVerticalScrollBarPolicy(20);
        this.add(titulo, "North");
        this.add(scrollPane, "Center");
        this.add(pnBotoes, "South");
    }
}
