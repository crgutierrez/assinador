//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.assinadortjro.gui;

import br.jus.tjro.assinadortjro.BaseAssinadorTjro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AssinadorTjroMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    private BaseAssinadorTjro assinadorTjro;
    private JMenu menuAjuda;
    private JMenu menuArquivo;
    private JMenuItem menuArquivoAdicionar;
    private JMenuItem menuArquivoAssinar;
    private JMenuItem menuArquivoExcluir;
    private JMenuItem menuArquivoVisualizar;

    public AssinadorTjroMenuBar(BaseAssinadorTjro assinadorTjro) {
        this.assinadorTjro = assinadorTjro;
        this.menuArquivo = new JMenu("Arquivo");
        this.menuArquivoAdicionar = this.menuArquivo.add(new JMenuItem("Adicionar"));
        this.menuArquivoAdicionar.setEnabled(assinadorTjro.getPermiteAdicionarArquivo());
        this.menuArquivoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().addArquivoLocal();
            }
        });
        this.menuArquivoExcluir = this.menuArquivo.add(new JMenuItem("Excluir"));
        this.menuArquivoExcluir.setToolTipText("Clique para excluir os arquivos selecionados ou simplemente aperte a tecla DELETE.");
        this.menuArquivoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().excluirArquivosSelecionados();
            }
        });
        this.menuArquivoVisualizar = this.menuArquivo.add(new JMenuItem("Visualizar"));
        this.menuArquivoVisualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().visualizarArquivoSelecionado();
            }
        });
        this.menuArquivo.addSeparator();
        this.menuArquivoAssinar = this.menuArquivo.add(new JMenuItem("Assinar"));
        this.menuArquivoAssinar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().assinarArquivos();
            }
        });
        this.add(this.menuArquivo);
        this.menuAjuda = new JMenu("Ajuda");
        JMenuItem menuAjudaConfigurar = this.menuAjuda.add(new JMenuItem("Configurar"));
        menuAjudaConfigurar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().mostrarConfiguracao();
            }
        });
        this.menuAjuda.addSeparator();
        JMenuItem menuAjudaSobre = this.menuAjuda.add(new JMenuItem("Sobre"));
        menuAjudaSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssinadorTjroMenuBar.this.getAssinadorTjro().onClickMenuAjudaSobre();
            }
        });
        this.add(this.menuAjuda);
    }

    public JMenuItem getMenuArquivoAssinar() {
        return this.menuArquivoAssinar;
    }

    public JMenuItem getMenuArquivoExcluir() {
        return this.menuArquivoExcluir;
    }

    public JMenuItem getMenuArquivoVisualizar() {
        return this.menuArquivoVisualizar;
    }

    public BaseAssinadorTjro getAssinadorTjro() {
        return this.assinadorTjro;
    }
}
