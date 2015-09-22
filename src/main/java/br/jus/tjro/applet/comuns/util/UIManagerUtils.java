//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.jus.tjro.applet.comuns.util;

import br.com.atos.utils.OsUtil;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

public class UIManagerUtils {
    private static Logger log = Logger.getLogger(UIManagerUtils.class);

    public UIManagerUtils() {
    }

    public static void configurarLookAndFeel() {
        try {
            if(OsUtil.isOsLinux()) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception var1) {
            log.warn("Erro ao configurar o Look And Feel, mensagem interna: " + var1.getMessage());
        }

    }
}
