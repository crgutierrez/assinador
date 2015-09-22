//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice;

import com.googlecode.jlibreoffice.OOoBeanProxy;
import com.googlecode.jlibreoffice.util.SystemUtils;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class JLibreOffice {
    private static final Logger log = Logger.getLogger(JLibreOffice.class);
    public static final String NEW_WRITER = "private:factory/swriter";
    public static final String UNO_AUTO_FORMAT_APPLY = ".uno:AutoFormatApply";
    public static final String UNO_AUTO_FORMAT_REDLINE_APPLY = ".uno:AutoFormatRedlineApply";
    public static final String UNO_BULLETS_AND_NUMBERING_DIALOG = ".uno:BulletsAndNumberingDialog";
    public static final String UNO_COMMON_ALIGN_BOTTOM = ".uno:CommonAlignBottom";
    public static final String UNO_COMMON_ALIGN_HORIZONTAL_CENTER = ".uno:CommonAlignHorizontalCenter";
    public static final String UNO_COMMON_ALIGN_JUSTIFIED = ".uno:CommonAlignJustified";
    public static final String UNO_COMMON_ALIGN_LEFT = ".uno:CommonAlignLeft";
    public static final String UNO_COMMON_ALIGN_RIGHT = ".uno:CommonAlignRight";
    public static final String UNO_COMMON_ALIGN_TOP = ".uno:CommonAlignTop";
    public static final String UNO_COMMON_ALIGN_VERTICAL_CENTER = ".uno:CommonAlignVerticalCenter";
    public static final String UNO_COPY = ".uno:Copy";
    public static final String UNO_CUT = ".uno:Cut";
    public static final String UNO_DELETE_COLUMNS = ".uno:DeleteColumns";
    public static final String UNO_DELETE_ROWS = ".uno:DeleteRows";
    public static final String UNO_DELETE_TABLE = ".uno:DeleteTable";
    public static final String UNO_DESIGNER_DIALOG = ".uno:DesignerDialog";
    public static final String UNO_EDIT_REGION = ".uno:EditRegion";
    public static final String UNO_ENTIRE_CELL = ".uno:EntireCell";
    public static final String UNO_ENTIRE_COLUMN = ".uno:EntireColumn";
    public static final String UNO_ENTIRE_ROW = ".uno:EntireRow";
    public static final String UNO_FONT_DIALOG = ".uno:FontDialog";
    public static final String UNO_FORMAT_COLUMNS = ".uno:FormatColumns";
    public static final String UNO_HYPERLINK_DIALOG = ".uno:HyperlinkDialog";
    public static final String UNO_INSERT_ANNOTATION = ".uno:InsertAnnotation";
    public static final String UNO_INSERT_APPLET = ".uno:InsertApplet";
    public static final String UNO_INSERT_AUTHOR_FIELD = ".uno:InsertAuthorField";
    public static final String UNO_INSERT_AUTHORITIES_ENRTY = ".uno:InsertAuthoritiesEntry";
    public static final String UNO_INSERT_AVMEDIA = ".uno:InsertAVMedia";
    public static final String UNO_INSERT_BOOKMARK = ".uno:InsertBookmark";
    public static final String UNO_INSERT_BREAK = ".uno:InsertBreak";
    public static final String UNO_INSERT_COLUMN_DIALOG = ".uno:InsertColumnDialog";
    public static final String UNO_INSERT_DATE_FIELD = ".uno:InsertDateField";
    public static final String UNO_INSERT_DOC = ".uno:InsertDoc";
    public static final String UNO_INSERT_ENVELOPE = ".uno:InsertEnvelope";
    public static final String UNO_INSERT_FIELD = ".uno:InsertField";
    public static final String UNO_INSERT_FOOTNOTE_DIALOG = ".uno:InsertFootnoteDialog";
    public static final String UNO_INSERT_FRAME = ".uno:InsertFrame";
    public static final String UNO_INSERT_GRAPHIC = ".uno:InsertGraphic";
    public static final String UNO_INSERT_GRAPHIC_RULER = ".uno:InsertGraphicRuler";
    public static final String UNO_INSERT_INDEXES_ENTRY = ".uno:InsertIndexesEntry";
    public static final String UNO_INSERT_MULTI_INDEX = ".uno:InsertMultiIndex";
    public static final String UNO_INSERT_OBJECT = ".uno:InsertObject";
    public static final String UNO_INSERT_OBJECT_CHART = ".uno:InsertObjectChart";
    public static final String UNO_INSERT_OBJECT_FLOATING_FRAME = ".uno:InsertObjectFloatingFrame";
    public static final String UNO_INSERT_OBJECT_STAR_MATH = ".uno:InsertObjectStarMath";
    public static final String UNO_INSERT_PAGE_COUNT_FIELD = ".uno:InsertPageCountField";
    public static final String UNO_INSERT_PAGE_NUMBER_FIELD = ".uno:InsertPageNumberField";
    public static final String UNO_INSERT_PLUGIN = ".uno:InsertPlugin";
    public static final String UNO_INSERT_REFERENCE_FIELD = ".uno:InsertReferenceField";
    public static final String UNO_INSERT_ROW_DIALOG = ".uno:InsertRowDialog";
    public static final String UNO_INSERT_SCRIPT = ".uno:InsertScript";
    public static final String UNO_INSERT_SECTION = ".uno:InsertSection";
    public static final String UNO_INSERT_SOUND = ".uno:InsertSound";
    public static final String UNO_INSERT_SYMBOL = ".uno:InsertSymbol";
    public static final String UNO_INSERT_TABLE = ".uno:InsertTable";
    public static final String UNO_INSERT_TIME_FIELD = ".uno:InsertTimeField";
    public static final String UNO_INSERT_TITLE_FIELD = ".uno:InsertTitleField";
    public static final String UNO_INSERT_TOPIC_FIELD = ".uno:InsertTopicField";
    public static final String UNO_INSERT_VACTIONEO = ".uno:InsertVactioneo";
    public static final String UNO_MERGE_CELLS = ".uno:MergeCells";
    public static final String UNO_MERGE_TABLE = ".uno:MergeTable";
    public static final String UNO_ONLINE_AUTO_FORMAT = ".uno:OnlineAutoFormat";
    public static final String UNO_PAGE_DIALOG = ".uno:PageDialog";
    public static final String UNO_PARAGRAPH_DIALOG = ".uno:ParagraphDialog";
    public static final String UNO_PASTE = ".uno:Paste";
    public static final String UNO_PASTE_SPECIAL = ".uno:PasteSpecial";
    public static final String UNO_PROTECT = ".uno:Protect";
    public static final String UNO_REDO = ".uno:Redo";
    public static final String UNO_REPEAT = ".uno:Repeat";
    public static final String UNO_RESET_ATTRIBUTES = ".uno:ResetAttributes";
    public static final String UNO_SAVE = ".uno:Save";
    public static final String UNO_SAVE_AS = ".uno:SaveAs";
    public static final String UNO_SELECT_ALL = ".uno:SelectAll";
    public static final String UNO_SELECT_TABLE = ".uno:SelectTable";
    public static final String UNO_SELECT_TEXT_MODE = ".uno:SelectTextMode";
    public static final String UNO_SPLIT_CELL = ".uno:SplitCell";
    public static final String UNO_SPLIT_TABLE = ".uno:SplitTable";
    public static final String UNO_TABLE_DIALOG = ".uno:TableDialog";
    public static final String UNO_TWAIN_SELECT = ".uno:TwainSelect";
    public static final String UNO_TWAIN_TRANSFER = ".uno:TwainTransfer";
    public static final String UNO_UNDO = ".uno:Undo";
    public static final String UNO_EXPORT_DIRECT_TO_PDF = ".uno:ExportDirectToPDF";
    private OOoBeanProxy bean;
    private ResourceBundle messageBundle;
    private URLClassLoader classLoader;

    public JLibreOffice(URLClassLoader classLoader) {
        this.classLoader = classLoader;
        this.init();
    }

    private void init() {
        try {
            if(SystemUtils.isOsLinux()) {
                System.setProperty("com.sun.star.officebean.Options", "--norestore");
            } else {
                System.setProperty("com.sun.star.officebean.Options", "-norestore");
            }

            this.initMessageBundle();
            this.bean = new OOoBeanProxy(this.classLoader);
            this.bean.setLayout(new BorderLayout());
            log.info("A conexao com o LibreOffice foi carregada com sucesso!");
        } catch (Exception var2) {
            this.bean = null;
            log.error("Erro ao iniciar a conexao com o LibreOffice, mensagem interna: " + (var2.getMessage() != null?var2.getMessage():var2.getClass().getSimpleName()));
        }

    }

    private void initMessageBundle() {
        try {
            this.messageBundle = ResourceBundle.getBundle("com.googlecode.jlibreoffice.resources.MessageBundle");
        } catch (Exception var5) {
            log.error(var5);

            try {
                InputStream e2 = this.getClass().getResourceAsStream("/com/googlecode/jlibreoffice/resources/MessageBundle.properties");
                InputStreamReader reader = new InputStreamReader(e2, Charset.forName("UTF-8"));
                this.messageBundle = new PropertyResourceBundle(reader);
            } catch (Exception var4) {
                log.error(var4);
            }
        }

    }

    public void open(File file) throws Exception {
        String url = getFileURL(file);
        log.debug(url);
        this.open(url);
    }

    private static String getFileURL(File file) {
        return file.getAbsoluteFile().toURI().toString().replaceFirst("\\A[Ff][Ii][Ll][Ee]:/(?=[^/]|\\z)", "file:///");
    }

    private void open(String url) throws Exception {
        try {
            this.bean.loadFromURL(url);
            this.bean.aquireSystemWindow();
        } catch (Exception var3) {
            log.error("JLibreOffice.open: " + var3.getMessage());
            var3.printStackTrace();
            throw new Exception(var3.getMessage());
        }
    }

    public String exportToPdf() throws Exception {
        return this.bean.exportToPdf();
    }

    public void execute(String cmd) throws Exception {
        this.bean.execute(cmd, (Object[])null);
    }

    public void execute(String cmd, Object[] propertyValues) throws Exception {
        this.bean.execute(cmd, propertyValues);
    }

    public OOoBeanProxy getBean() {
        return this.bean;
    }

    public void closeConnection() {
        if(this.bean != null) {
            if(this.bean.isOOoConnected()) {
                this.bean.stopOOoConnection();
                log.info("A conexao com o LibreOffice foi fechada com sucesso!");
            }

            this.bean.setVisible(false);
        }

    }

    public boolean isConnected() {
        return this.isInitialized() && this.bean.isOOoConnected();
    }

    public boolean isEditingDocument() {
        try {
            return this.bean.getDocument() != null;
        } catch (Exception var2) {
            return false;
        }
    }

    public boolean isInitialized() {
        return this.bean != null;
    }

    public void newWriterDocument() throws Exception {
        this.open("private:factory/swriter");
    }

    public void save() throws Exception {
        if(this.isConnected()) {
            this.execute(".uno:Save", (Object[])null);
        }
    }

    public void saveAs() throws Exception {
        if(this.isConnected()) {
            this.execute(".uno:SaveAs", (Object[])null);
        }
    }

    public Container getContainer() {
        return this.bean.getContainer();
    }

    public void stopOOoConnection() {
        if(this.isConnected()) {
            this.bean.stopOOoConnection();
        }
    }

    public void setMenuBarVisible(boolean value) {
        this.bean.setMenuBarVisible(value);
    }

    public void setStandardBarVisible(boolean value) {
        this.bean.setStandardBarVisible(value);
    }

    public void setToolBarVisible(boolean value) {
        this.bean.setToolBarVisible(value);
    }

    public ResourceBundle getMessageBundle() {
        return this.messageBundle;
    }

    public void clear() {
        this.bean.clear();
    }
}
