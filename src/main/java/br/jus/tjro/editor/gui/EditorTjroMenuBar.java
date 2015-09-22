//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.gui;

import br.com.atos.utils.swing.JFrameUtils;
import br.jus.tjro.editor.BaseEditorTjro;
import com.googlecode.jlibreoffice.menu.MenuBuilder;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.Logger;

public class EditorTjroMenuBar extends MenuBar {
    private static final Logger log = Logger.getLogger(EditorTjroMenuBar.class);
    private static final long serialVersionUID = 1L;
    private BaseEditorTjro editorTjro;
    private Menu menuAjuda;
    private Menu menuArquivo;
    private MenuItem menuArquivoAbrir;
    private MenuItem menuArquivoAbrirRemoto;
    private MenuItem menuArquivoEnviar;
    private MenuItem menuArquivoNovo;
    private MenuItem menuArquivoSair;
    private MenuItem menuArquivoSalvar;
    private MenuItem menuArquivoSalvarComo;
    private MenuItem menuArquivoMostrarArquivosTemporarios;

    public EditorTjroMenuBar(BaseEditorTjro editorTjro) {
        this.editorTjro = editorTjro;
        this.menuArquivo = new Menu("Arquivo");
        this.menuArquivoNovo = this.menuArquivo.add(new MenuItem("Novo"));
        this.menuArquivoNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EditorTjroMenuBar.this.getEditorTjro().criarNovoDocumento();
            }
        });
        this.menuArquivoAbrir = this.menuArquivo.add(new MenuItem("Abrir"));
        this.menuArquivoAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditorTjroMenuBar.this.getEditorTjro().abrirDocumentoLocalAction();
            }
        });
        this.menuArquivoAbrirRemoto = this.menuArquivo.add(new MenuItem("Abrir documento remoto"));
        this.menuArquivoAbrirRemoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    EditorTjroMenuBar.this.getEditorTjro().abrirDocumentoRemotoAction();
                } catch (Exception var3) {
                    EditorTjroMenuBar.log.error(var3);
                    JFrameUtils.showErro("Erro ao Abrir um documento remoto", var3.getMessage());
                }

            }
        });
        this.menuArquivo.addSeparator();
        this.menuArquivoSalvar = this.menuArquivo.add(new MenuItem("Salvar"));
        this.menuArquivoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    EditorTjroMenuBar.this.getEditorTjro().getJLibreOffice().save();
                } catch (Exception var3) {
                    EditorTjroMenuBar.log.error(var3);
                    JFrameUtils.showErro("Erro ao salvar o documento", var3.getMessage());
                }

            }
        });
        this.menuArquivoSalvarComo = this.menuArquivo.add(new MenuItem("Salvar como..."));
        this.menuArquivoSalvarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    EditorTjroMenuBar.this.getEditorTjro().getJLibreOffice().saveAs();
                } catch (Exception var3) {
                    EditorTjroMenuBar.log.error(var3);
                    JFrameUtils.showErro("Erro ao salvar como o documento", var3.getMessage());
                }

            }
        });
        this.menuArquivo.addSeparator();
        boolean assinarEnviar = ((Boolean)this.getEditorTjro().getConfigs().getSign().getValue()).booleanValue();
        this.menuArquivoEnviar = this.menuArquivo.add(new MenuItem(assinarEnviar?"Assinar e enviar":"Enviar"));
        this.menuArquivoEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditorTjroMenuBar.this.getEditorTjro().onClickMenuArquivoEnviarDocumento();
            }
        });
        this.menuArquivo.addSeparator();
        this.menuArquivoMostrarArquivosTemporarios = this.menuArquivo.add(new MenuItem("Mostrar Arquivo(s) Temporário(s)"));
        this.menuArquivoMostrarArquivosTemporarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditorTjroMenuBar.this.getEditorTjro().onClickMenuArquivoMostrarArquivosTemporarios();
            }
        });
        this.menuArquivo.addSeparator();
        this.menuArquivoSair = this.menuArquivo.add(new MenuItem("Sair"));
        this.menuArquivoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditorTjroMenuBar.this.getEditorTjro().onClickMenuArquivoSair();
            }
        });
        this.add(this.menuArquivo);
        MenuBuilder mb = new MenuBuilder(editorTjro.getJLibreOffice());
        this.add(mb.buildEditMenu());
        this.add(mb.buildInsertMenu());
        this.add(mb.buildFormatMenu());
        this.add(mb.buildTableMenu());
        this.menuAjuda = new Menu("Ajuda");
        MenuItem menuAjudaSobre;
        if(editorTjro.getConfigs().getSign().isDeclarado() && ((Boolean)editorTjro.getConfigs().getSign().getValue()).booleanValue()) {
            menuAjudaSobre = this.menuAjuda.add(new MenuItem("Configurar"));
            menuAjudaSobre.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    EditorTjroMenuBar.this.getEditorTjro().mostrarConfiguracao();
                }
            });
            this.menuAjuda.addSeparator();
        }

        menuAjudaSobre = this.menuAjuda.add(new MenuItem("Sobre"));
        menuAjudaSobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.setCenterLocation(EditorTjroMenuBar.this.getEditorTjro().getWinSobre());
                EditorTjroMenuBar.this.getEditorTjro().getWinSobre().setVisible(true);
            }
        });
        this.add(this.menuAjuda);
    }

    public void atualizarMenu() {
        this.menuArquivoNovo.setEnabled(true);
        this.menuArquivoAbrir.setEnabled(true);
        this.menuArquivoSair.setEnabled(true);
        if(this.getEditorTjro().isDefinidoDocumentoRemoto()) {
            this.menuArquivoAbrirRemoto.setEnabled(true);
        } else {
            this.menuArquivoAbrirRemoto.setEnabled(false);
        }

        this.menuArquivoSalvar.setEnabled(false);
        this.menuArquivoSalvarComo.setEnabled(false);
        this.menuArquivoEnviar.setEnabled(false);
        if(this.getEditorTjro().isConectadoLibreOffice() && this.getEditorTjro().isEditandoDocumento()) {
            this.menuArquivoSalvar.setEnabled(true);
            this.menuArquivoSalvarComo.setEnabled(true);
            this.menuArquivoEnviar.setEnabled(true);
        }

    }

    public BaseEditorTjro getEditorTjro() {
        return this.editorTjro;
    }
}
