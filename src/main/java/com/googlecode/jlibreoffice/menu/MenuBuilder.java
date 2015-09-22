//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice.menu;

import com.googlecode.jlibreoffice.JLibreOffice;
import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MenuBuilder {
    private JLibreOffice jLibreOffice;

    public MenuBuilder(JLibreOffice jLibreOffice) {
        this.jLibreOffice = jLibreOffice;
    }

    public JLibreOffice getJLibreOffice() {
        return this.jLibreOffice;
    }

    private String getMessageBundleLabel(String name) {
        return this.getMessageBundle().getString("viewer.menu." + name + ".label");
    }

    private Menu buildMenu(String name) {
        Menu menu = new Menu(this.getMessageBundleLabel(name));
        return menu;
    }

    private MenuItem buildMenuItem(String name, final String comando) {
        MenuItem menuItem = new MenuItem(this.getMessageBundleLabel(name));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    MenuBuilder.this.getJLibreOffice().execute(comando, (Object[])null);
                } catch (Exception var3) {
                    var3.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, "Erro: " + var3.getMessage(), "jLibreOffice - Erro", 0);
                }

            }
        });
        return menuItem;
    }

    public void buildMenuBar(MenuBar menuBar) {
        menuBar.add(this.buildEditMenu());
        menuBar.add(this.buildInsertMenu());
        menuBar.add(this.buildFormatMenu());
        menuBar.add(this.buildTableMenu());
    }

    public MenuBar buildCompleteMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.add(this.buildFileMenu());
        menuBar.add(this.buildInsertMenu());
        menuBar.add(this.buildFormatMenu());
        menuBar.add(this.buildTableMenu());
        return menuBar;
    }

    public Menu buildFileMenu() {
        Menu menu = this.buildMenu("file");
        menu.add(this.buildNewWriterMenuItem());
        menu.add(this.buildOpenMenuItem());
        menu.add(this.buildSaveMenuItem());
        menu.add(this.buildSaveAsMenuItem());
        return menu;
    }

    public Menu buildEditMenu() {
        Menu menu = this.buildMenu("edit");
        menu.add(this.buildMenuItem("edit.undo", ".uno:Undo"));
        menu.add(this.buildMenuItem("edit.redo", ".uno:Redo"));
        menu.add(this.buildMenuItem("edit.repeat", ".uno:Repeat"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("edit.cut", ".uno:Cut"));
        menu.add(this.buildMenuItem("edit.copy", ".uno:Copy"));
        menu.add(this.buildMenuItem("edit.paste", ".uno:Paste"));
        menu.add(this.buildMenuItem("edit.pasteSpecial", ".uno:PasteSpecial"));
        menu.add(this.buildMenuItem("edit.selectTextMode", ".uno:SelectTextMode"));
        menu.add(this.buildMenuItem("edit.selectAll", ".uno:SelectAll"));
        return menu;
    }

    public Menu buildInsertMenu() {
        Menu menu = this.buildMenu("insert");
        menu.add(this.buildMenuItem("insert.insertBreak", ".uno:InsertBreak"));
        Menu menuFields = this.buildMenu("insert.fields");
        menuFields.add(this.buildMenuItem("insert.fields.insertDateField", ".uno:InsertDateField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertTimeField", ".uno:InsertTimeField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertPageNumberField", ".uno:InsertPageNumberField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertPageCountField", ".uno:InsertPageCountField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertTopicField", ".uno:InsertTopicField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertTitleField", ".uno:InsertTitleField"));
        menuFields.add(this.buildMenuItem("insert.fields.insertAuthorField", ".uno:InsertAuthorField"));
        menuFields.addSeparator();
        menuFields.add(this.buildMenuItem("insert.fields.insertField", ".uno:InsertField"));
        menu.add(menuFields);
        menu.add(this.buildMenuItem("insert.insertSymbol", ".uno:InsertSymbol"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("insert.insertSection", ".uno:InsertSection"));
        menu.add(this.buildMenuItem("insert.hyperlinkDialog", ".uno:HyperlinkDialog"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("insert.headerPageDialog", ".uno:PageDialog"));
        menu.add(this.buildMenuItem("insert.footerPageDialog", ".uno:PageDialog"));
        menu.add(this.buildMenuItem("insert.insertFootnoteDialog", ".uno:InsertFootnoteDialog"));
        menu.add(this.buildMenuItem("insert.insertBookmark", ".uno:InsertBookmark"));
        menu.add(this.buildMenuItem("insert.insertReferenceField", ".uno:InsertReferenceField"));
        menu.add(this.buildMenuItem("insert.insertAnnotation", ".uno:InsertAnnotation"));
        menu.add(this.buildMenuItem("insert.insertScript", ".uno:InsertScript"));
        Menu menuIndexes = this.buildMenu("insert.indexes");
        menuIndexes.add(this.buildMenuItem("insert.indexes.insertIndexesEntry", ".uno:InsertIndexesEntry"));
        menuIndexes.add(this.buildMenuItem("insert.indexes.insertMultiIndex", ".uno:InsertMultiIndex"));
        menuIndexes.add(this.buildMenuItem("insert.indexes.insertAuthoritiesEntry", ".uno:InsertAuthoritiesEntry"));
        menu.add(menuIndexes);
        menu.addSeparator();
        menu.add(this.buildMenuItem("insert.insertEnvelope", ".uno:InsertEnvelope"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("insert.insertFrame", ".uno:InsertFrame"));
        menu.add(this.buildMenuItem("insert.insertTable", ".uno:InsertTable"));
        menu.add(this.buildMenuItem("insert.insertGraphicRuler", ".uno:InsertGraphicRuler"));
        Menu menuGraphic = this.buildMenu("insert.graphic");
        menuGraphic.add(this.buildMenuItem("insert.graphic.insertGraphic", ".uno:InsertGraphic"));
        Menu menuGraphicTwain = this.buildMenu("insert.graphic.twain");
        menuGraphicTwain.add(this.buildMenuItem("insert.graphic.twain.twainSelect", ".uno:TwainSelect"));
        menuGraphicTwain.add(this.buildMenuItem("insert.graphic.twain.twainTransfer", ".uno:TwainTransfer"));
        menuGraphic.add(menuGraphicTwain);
        menu.add(menuGraphic);
        menu.add(this.buildMenuItem("insert.insertAVMedia", ".uno:InsertAVMedia"));
        Menu menuObjeto = this.buildMenu("insert.object");
        menuObjeto.add(this.buildMenuItem("insert.object.insertObject", ".uno:InsertObject"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertPlugin", ".uno:InsertPlugin"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertSound", ".uno:InsertSound"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertVactioneo", ".uno:InsertVactioneo"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertApplet", ".uno:InsertApplet"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertObjectStarMath", ".uno:InsertObjectStarMath"));
        menuObjeto.add(this.buildMenuItem("insert.object.insertObjectChart", ".uno:InsertObjectChart"));
        menu.add(menuObjeto);
        menu.add(this.buildMenuItem("insert.insertObjectFloatingFrame", ".uno:InsertObjectFloatingFrame"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("insert.insertDoc", ".uno:InsertDoc"));
        return menu;
    }

    public Menu buildTableMenu() {
        Menu menu = this.buildMenu("table");
        Menu menuInserir = this.buildMenu("table.insert");
        menuInserir.add(this.buildMenuItem("table.insert.insertTable", ".uno:InsertTable"));
        menuInserir.add(this.buildMenuItem("table.insert.insertRowDialog", ".uno:InsertRowDialog"));
        menuInserir.add(this.buildMenuItem("table.insert.insertColumnDialog", ".uno:InsertColumnDialog"));
        menu.add(menuInserir);
        Menu menuExcluir = this.buildMenu("table.delete");
        menuExcluir.add(this.buildMenuItem("table.delete.deleteTable", ".uno:DeleteTable"));
        menuExcluir.add(this.buildMenuItem("table.delete.deleteRows", ".uno:DeleteRows"));
        menuExcluir.add(this.buildMenuItem("table.delete.deleteColumns", ".uno:DeleteColumns"));
        menu.add(menuExcluir);
        Menu menuSelecionar = this.buildMenu("table.select");
        menuSelecionar.add(this.buildMenuItem("table.select.selectTable", ".uno:SelectTable"));
        menuSelecionar.add(this.buildMenuItem("table.select.entireRow", ".uno:EntireRow"));
        menuSelecionar.add(this.buildMenuItem("table.select.entireColumn", ".uno:EntireColumn"));
        menuSelecionar.add(this.buildMenuItem("table.select.entireCell", ".uno:EntireCell"));
        menu.add(menuSelecionar);
        menu.add(this.buildMenuItem("table.mergeCells", ".uno:MergeCells"));
        menu.add(this.buildMenuItem("table.splitCell", ".uno:SplitCell"));
        menu.add(this.buildMenuItem("table.protect", ".uno:Protect"));
        menu.add(this.buildMenuItem("table.mergeTable", ".uno:MergeTable"));
        menu.add(this.buildMenuItem("table.splitTable", ".uno:SplitTable"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("table.tableDialog", ".uno:TableDialog"));
        return menu;
    }

    public MenuItem buildMenuItem(String name, ActionListener actionListener) {
        MenuItem menuItem = new MenuItem(this.getMessageBundleLabel(name));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    public MenuItem buildNewWriterMenuItem() {
        return this.buildMenuItem("file.new.writer", new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    MenuBuilder.this.getJLibreOffice().newWriterDocument();
                } catch (Exception var3) {
                    var3.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, "Erro: " + var3.getMessage(), "jLibreOffice - Erro", 0);
                }

            }
        });
    }

    public MenuItem buildSaveAsMenuItem() {
        return this.buildMenuItem("file.saveAs", new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    MenuBuilder.this.getJLibreOffice().saveAs();
                } catch (Exception var3) {
                    var3.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, "Erro: " + var3.getMessage(), "jLibreOffice - Erro", 0);
                }

            }
        });
    }

    public MenuItem buildSaveMenuItem() {
        return this.buildMenuItem("file.save", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    MenuBuilder.this.getJLibreOffice().save();
                } catch (Exception var3) {
                    var3.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, "Erro: " + var3.getMessage(), "jLibreOffice - Erro", 0);
                }

            }
        });
    }

    public MenuItem buildOpenMenuItem() {
        return this.buildMenuItem("file.open", new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JFileChooser e = new JFileChooser();
                    e.setFileSelectionMode(0);
                    int retorno = e.showOpenDialog((Component)null);
                    if(retorno == 0) {
                        File file = e.getSelectedFile();
                        MenuBuilder.this.getJLibreOffice().open(file);
                    }
                } catch (Exception var5) {
                    var5.printStackTrace();
                    JOptionPane.showMessageDialog((Component)null, "Erro: " + var5.getMessage(), "jLibreOffice - Erro", 0);
                }

            }
        });
    }

    public Menu buildFormatMenu() {
        Menu menu = this.buildMenu("format");
        menu.add(this.buildMenuItem("format.resetAttributes", ".uno:ResetAttributes"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("format.fontDialog", ".uno:FontDialog"));
        menu.add(this.buildMenuItem("format.paragraphDialog", ".uno:ParagraphDialog"));
        menu.add(this.buildMenuItem("format.bulletsAndNumberingDialog", ".uno:BulletsAndNumberingDialog"));
        menu.add(this.buildMenuItem("format.pageDialog", ".uno:PageDialog"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("format.formatColumns", ".uno:FormatColumns"));
        menu.add(this.buildMenuItem("format.editRegion", ".uno:EditRegion"));
        menu.addSeparator();
        menu.add(this.buildMenuItem("format.designerDialog", ".uno:DesignerDialog"));
        Menu menuAutoFormat = this.buildMenu("format.autoFormat");
        menuAutoFormat.add(this.buildMenuItem("format.autoFormat.onlineAutoFormat", ".uno:OnlineAutoFormat"));
        menuAutoFormat.add(this.buildMenuItem("format.autoFormat.autoFormatApply", ".uno:AutoFormatApply"));
        menuAutoFormat.add(this.buildMenuItem("format.autoFormat.autoFormatRedlineApply", ".uno:AutoFormatRedlineApply"));
        menu.add(menuAutoFormat);
        menu.addSeparator();
        Menu menuAlign = this.buildMenu("format.align");
        menuAlign.add(this.buildMenuItem("format.align.commonAlignLeft", ".uno:CommonAlignLeft"));
        menuAlign.add(this.buildMenuItem("format.align.commonAlignHorizontalCenter", ".uno:CommonAlignHorizontalCenter"));
        menuAlign.add(this.buildMenuItem("format.align.commonAlignRight", ".uno:CommonAlignRight"));
        menuAlign.add(this.buildMenuItem("format.align.commonAlignJustified", ".uno:CommonAlignJustified"));
        menuAlign.addSeparator();
        menuAlign.add(this.buildMenuItem("format.align.commonAlignTop", ".uno:CommonAlignTop"));
        menuAlign.add(this.buildMenuItem("format.align.commonAlignVerticalCenter", ".uno:CommonAlignVerticalCenter"));
        menuAlign.add(this.buildMenuItem("format.align.commonAlignBottom", ".uno:CommonAlignBottom"));
        menu.add(menuAlign);
        return menu;
    }

    public ResourceBundle getMessageBundle() {
        return this.jLibreOffice.getMessageBundle();
    }
}
