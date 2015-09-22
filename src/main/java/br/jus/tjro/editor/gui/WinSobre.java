//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.gui;

import br.jus.tjro.editor.gui.WinLibreOffice;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class WinSobre extends Dialog {
    private static final long serialVersionUID = 1L;

    public WinSobre(WinLibreOffice winLibreOffice) {
        super(winLibreOffice);
        this.init();
    }

    private void init() {
        this.setModal(true);
        this.setTitle("EditorTjro - Versão: 1.4.2");
        this.setResizable(false);
        this.setSize(450, 350);
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
        JTextArea titulo = new JTextArea("EditorTjro");
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
            doc.insertString(doc.getLength(), "Editor de Texto do Tribunal de Justiça do Estado de Rondônia.\n", def);
            doc.insertString(doc.getLength(), "Esta solução envolve a integração do Editor de Texto LibreOffice integrado ao ", def);
            doc.insertString(doc.getLength(), "Assinador Digital de Documentos Eletrônicos AssinadorTjro.\n\n", def);
            doc.insertString(doc.getLength(), "Créditos\n", bold);
            doc.insertString(doc.getLength(), "Solução desenvolvida pela DIDES-JUD - TJ/RO\n\n", def);
            doc.insertString(doc.getLength(), "Compatibilidade\n", bold);
            doc.insertString(doc.getLength(), "O EditorTjro foi testado com as seguintes versões de Sistema Operacional e *Office.\n\n", def);
            doc.insertString(doc.getLength(), "MICROSOFT WINDOWS XP PROFESSIONAL\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 2.4.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.1\n", def);
            doc.insertString(doc.getLength(), " - LibreOffice 3.3.0\n\n", def);
            doc.insertString(doc.getLength(), "MICROSOFT WINDOWS VISTA BUSINESS 32 BITS\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 2.4.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.1\n\n", def);
            doc.insertString(doc.getLength(), "MICROSOFT WINDOWS 7 64 BITS\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 2.2.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 2.4.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.0.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.1.1\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.0\n", def);
            doc.insertString(doc.getLength(), " - BrOffice 3.2.1\n", def);
            doc.insertString(doc.getLength(), " - LibreOffice 3.3.0\n", def);
            doc.insertString(doc.getLength(), " - LibreOffice 3.6.7\n", def);
            doc.insertString(doc.getLength(), " - LibreOffice 4.0.1\n", def);
            doc.insertString(doc.getLength(), " - LibreOffice 4.1.0\n\n", def);
            doc.insertString(doc.getLength(), "LINUX UBUNTU 10.04\n", def);
            doc.insertString(doc.getLength(), " - OpenOffice 3.2.0\n\n", def);
            doc.insertString(doc.getLength(), "LINUX KUBUNTU 9.10\n", def);
            doc.insertString(doc.getLength(), " - OpenOffice 3.0.0\n", def);
        } catch (BadLocationException var9) {
            var9.printStackTrace();
        }

        Button btnFechar = new Button("OK");
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
