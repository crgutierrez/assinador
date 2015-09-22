//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.editor.gui;

import br.com.atos.utils.arquivo.ArquivoUtils;
import br.jus.tjro.editor.BaseEditorTjro;
import br.jus.tjro.editor.gui.WinLibreOffice;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

public class WinArquivoTemporario extends JDialog {
    private static final long serialVersionUID = 1L;
    private File[] arquivosTemporarios;
    private final BaseEditorTjro editorTjro;

    public WinArquivoTemporario(WinLibreOffice winLibreOffice, BaseEditorTjro editorTjro) {
        super(winLibreOffice);
        this.editorTjro = editorTjro;
        this.init();
    }

    private void init() {
        this.setModal(true);
        this.setTitle("Lista de arquivos temporários");
        this.setResizable(true);
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(2);
        JTable tbArquivo = new JTable();
        File dirTmp = ArquivoUtils.getDiretorioTemporario();
        this.arquivosTemporarios = dirTmp.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".odt");
            }
        });
        AbstractTableModel tableModel = new AbstractTableModel() {
            private static final long serialVersionUID = 1L;
            private String[] columnsNames = new String[]{"Arquivo", "Últ. Modificação", "Tamanho"};

            public Object getValueAt(int rowIndex, int columnIndex) {
                File arquivo = WinArquivoTemporario.this.arquivosTemporarios[rowIndex];
                switch(columnIndex) {
                case 0:
                    return arquivo.getName();
                case 1:
                    return Long.valueOf(arquivo.lastModified());
                case 2:
                    return ArquivoUtils.getTamanhoFormatado(Long.valueOf(arquivo.length()));
                default:
                    return "";
                }
            }

            public int getRowCount() {
                return WinArquivoTemporario.this.arquivosTemporarios.length;
            }

            public int getColumnCount() {
                return 3;
            }

            public String getColumnName(int column) {
                return this.columnsNames[column];
            }
        };
        tbArquivo.setModel(tableModel);
        TableRowSorter rowSorter = new TableRowSorter(tableModel);
        tbArquivo.setRowSorter(rowSorter);
        tbArquivo.getColumnModel().getColumn(0).setPreferredWidth(400);
        tbArquivo.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbArquivo.getColumnModel().getColumn(2).setPreferredWidth(100);
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            private SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String value1 = this.dataFormat.format(value);
                return super.getTableCellRendererComponent(table, value1, isSelected, hasFocus, row, column);
            }
        };
        tbArquivo.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
        tbArquivo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    final JTable target = (JTable)e.getSource();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            int row = target.getSelectedRow();
                            File arquivo = WinArquivoTemporario.this.arquivosTemporarios[row];
                            WinArquivoTemporario.this.editorTjro.abrirDocumentoTemprario(arquivo);
                        }
                    });
                    WinArquivoTemporario.this.setVisible(false);
                }

            }
        });
        rowSorter.toggleSortOrder(1);
        rowSorter.toggleSortOrder(1);
        JScrollPane scrollPane = new JScrollPane(tbArquivo);
        scrollPane.setVerticalScrollBarPolicy(20);
        this.setLayout(new BorderLayout(10, 10));
        this.add(scrollPane, "Center");
        this.pack();
    }
}
