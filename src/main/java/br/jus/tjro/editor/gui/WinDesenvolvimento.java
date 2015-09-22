//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.gui;

import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.editor.BaseEditorTjro;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WinDesenvolvimento extends JFrame {
    private static final long serialVersionUID = 1L;

    public WinDesenvolvimento(final BaseEditorTjro editorTjro) {
        JButton btnAbrirLocal = new JButton("Abrir Local");
        btnAbrirLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorTjro.onClickMenuArquivoAbrirDocumentoLocal();
            }
        });
        JButton btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorTjro.criarNovoDocumento("frmFluxoProcessoId=2");
            }
        });
        JButton btnAbrirRemoto = new JButton("Abrir Remoto");
        btnAbrirRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorTjro.abrirDocumentoRemoto("Despacho teste.odt", "http://www.tjro.jus.br/concurso/doc/declaracao.doc");
            }
        });
        JButton btnAbrirRemotoParams = new JButton("Abrir Remoto Params");
        btnAbrirRemotoParams.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editorTjro.abrirDocumentoRemoto("Expediente_05-08-2010_09-07-14.odt", "http://www.tjro.jus.br/concurso/doc/declaracao.doc", "etapa=341&tipo=22");
            }
        });
        JPanel pn = new JPanel();
        pn.add(btnNovo);
        pn.add(btnAbrirLocal);
        pn.add(btnAbrirRemoto);
        pn.add(btnAbrirRemotoParams);
        this.setTitle("Janela para desenvolvimento.");
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(450, 350));
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(3);
        this.add(pn);
        JFrameUtils.setCenterLocation(this);
        this.setVisible(true);
    }
}
