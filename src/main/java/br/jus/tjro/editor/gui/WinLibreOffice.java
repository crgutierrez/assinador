//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.gui;

import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.editor.BaseEditorTjro;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class WinLibreOffice extends JFrame {
    private static final long serialVersionUID = 1L;

    public WinLibreOffice(final BaseEditorTjro editorTjro) {
        this.setTitle("EditorTjro - Versão: 1.4.2");
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(450, 350));
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                editorTjro.onClosingWinLibreOffice();
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
        JFrameUtils.setCenterLocation(this);
    }
}
