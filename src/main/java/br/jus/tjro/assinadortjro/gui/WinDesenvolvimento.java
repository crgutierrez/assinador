//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.gui;

import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.assinadortjro.BaseAssinadorTjro;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WinDesenvolvimento extends JFrame {
    private static final long serialVersionUID = 1L;

    public WinDesenvolvimento(final BaseAssinadorTjro assinadorTjro) {
        JButton btnAddArquivoLocalComParams = new JButton("Add arquivo local com params");
        btnAddArquivoLocalComParams.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assinadorTjro.addArquivoLocal("tipo=true&param1=xpto");
            }
        });
        JButton btnAddMensagemTexto = new JButton("Add mensagem de texto");
        btnAddMensagemTexto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assinadorTjro.addMensagemTexto("teste.html", "<html><body><h1>Mensagem de Texto Assinada</h1></body></html>");
            }
        });
        JButton btnAddArquivoRemoto = new JButton("Add arquivo remoto");
        btnAddArquivoRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assinadorTjro.addArquivoRemoto("Resulação.pdf", "http://portal.tjro.jus.br/images/formularios/DECLARA%C3%87%C3%83O_DE_FAMILIARIDADE_EOU_PARENTESCO1_-_PJA_-_010.pdf");
            }
        });
        JButton btnAddArquivoRemotoComParam = new JButton("Add arquivo remoto com params.");
        btnAddArquivoRemotoComParam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assinadorTjro.addArquivoRemoto("Resulação.pdf", "http://portal.tjro.jus.br/images/formularios/DECLARA%C3%87%C3%83O_DE_FAMILIARIDADE_EOU_PARENTESCO1_-_PJA_-_010.pdf", "tipo=false&param1=xpto");
            }
        });
        JButton btnExcluirArquivo = new JButton("Excluir arquivo no index 1.");
        btnExcluirArquivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assinadorTjro.excluirArquivo("1");
            }
        });
        JPanel pn = new JPanel();
        pn.add(btnAddArquivoLocalComParams);
        pn.add(btnAddMensagemTexto);
        pn.add(btnAddArquivoRemoto);
        pn.add(btnAddArquivoRemotoComParam);
        pn.add(btnExcluirArquivo);
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
