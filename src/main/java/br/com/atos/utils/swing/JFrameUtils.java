//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class JFrameUtils {
    public JFrameUtils() {
    }

    public static void setCenterLocation(Component component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        component.setLocation(dim.width / 2 - component.getWidth() / 2, dim.height / 2 - component.getHeight() / 2);
    }

    public static void showAsModal(final Frame frame, final Frame owner) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                owner.setEnabled(true);
                frame.removeWindowListener(this);
            }

            public void windowOpened(WindowEvent e) {
                owner.setEnabled(false);
            }
        });
        owner.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                if(frame.isShowing()) {
                    frame.setExtendedState(0);
                    frame.toFront();
                } else {
                    owner.removeWindowListener(this);
                }

            }
        });
        frame.setVisible(true);

        try {
            (new JFrameUtils.EventPump(frame)).start();
        } catch (Throwable var3) {
            throw new RuntimeException(var3);
        }
    }

    public static void showAlerta(String titulo, String msg, Component parent) {
        showMsg(titulo, msg, parent, 2);
    }

    public static void showErro(String titulo, String msg, Component parent) {
        showMsg(titulo, msg, parent, 0);
    }

    public static void showInfo(String titulo, String msg, Component parent) {
        showMsg(titulo, msg, parent, 1);
    }

    private static void showMsg(String titulo, String msg, Component parent, int tipo) {
        JOptionPane.showMessageDialog(parent, msg, titulo, tipo);
    }

    public static String showInputPasswordDialog(String titulo, String msg) {
        JPasswordField txtPassword = new JPasswordField(10);
        txtPassword.setEchoChar('*');
        JLabel lblMsg = new JLabel(msg);
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(lblMsg);
        panel.add(txtPassword);
        JOptionPane.showMessageDialog((Component)null, panel, titulo, -1);
        return txtPassword.getText();
    }

    public static void showErro(String titulo, String msg) {
        showErro(titulo, msg, (Component)null);
    }

    static class EventPump implements InvocationHandler {
        Frame frame;

        public EventPump(Frame frame) {
            this.frame = frame;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.frame.isShowing()?Boolean.TRUE:Boolean.FALSE;
        }

        public void start() throws Exception {
            Class clazz = Class.forName("java.awt.Conditional");
            Object conditional = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
            Method pumpMethod = Class.forName("java.awt.EventDispatchThread").getDeclaredMethod("pumpEvents", new Class[]{clazz});
            pumpMethod.setAccessible(true);
            pumpMethod.invoke(Thread.currentThread(), new Object[]{conditional});
        }
    }
}
