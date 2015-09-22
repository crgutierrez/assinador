//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.googlecode.jlibreoffice;

import java.awt.Container;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import org.apache.log4j.Logger;

public class OOoBeanProxy {
    private static final Logger log = Logger.getLogger(OOoBeanProxy.class);
    private ClassLoader classLoader;
    private Object bean;
    private Class<?> beanClass;

    public OOoBeanProxy(ClassLoader classLoader) throws Exception {
        this.classLoader = classLoader;

        String msg;
        try {
            this.beanClass = classLoader.loadClass("com.sun.star.comp.beans.OOoBean");
        } catch (Exception var5) {
            msg = "A classe OOoBean não foi encontrada, mensagem interna: " + var5.getMessage();
            log.error(msg, var5);
            throw new Exception(msg);
        }

        try {
            this.bean = this.beanClass.newInstance();
        } catch (Exception var4) {
            msg = "Erro ao instanciar um objeto da classe OOoBean, mensagem interna: " + var4.getMessage();
            log.error(msg, var4);
            throw new Exception(msg);
        }
    }

    public void stopOOoConnection() {
        try {
            this.invoke(this.bean, "stopOOoConnection", new Object[0]);
        } catch (Exception var3) {
            String msg = "Erro ao executar OOoBean.stopOOoConnection(), mensagem interna: " + var3.getMessage();
            log.error(msg, var3);
            throw new RuntimeException(msg);
        }
    }

    public void setMenuBarVisible(boolean value) {
        try {
            this.invoke(this.bean, "setMenuBarVisible", value);
        } catch (Exception var3) {
            throw new RuntimeException("Erro ao executar OOoBean.setMenuBarVisible(), mensagem interna: " + var3.getMessage());
        }
    }

    public void setStandardBarVisible(boolean value) {
        try {
            this.invoke(this.bean, "setStandardBarVisible", value);
        } catch (Exception var3) {
            throw new RuntimeException("Erro ao executar OOoBean.setStandardBarVisible(), mensagem interna: " + var3.getMessage());
        }
    }

    public void setToolBarVisible(boolean value) {
        try {
            this.invoke(this.bean, "setToolBarVisible", value);
        } catch (Exception var3) {
            throw new RuntimeException("Erro ao executar OOoBean.setToolBarVisible(), mensagem interna: " + var3.getMessage());
        }
    }

    public boolean isOOoConnected() {
        try {
            return ((Boolean)this.invoke(this.bean, "isOOoConnected", new Object[0])).booleanValue();
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.isOOoConnected(), mensagem interna: " + var2.getMessage());
        }
    }

    public Object getDocument() {
        try {
            return this.invoke(this.bean, "getDocument", new Object[0]);
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.getDocument(), mensagem interna: " + var2.getMessage());
        }
    }

    public String getDocumentURL() {
        try {
            return (String)this.invoke(this.getDocument(), "getURL", new Object[0]);
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.getDocumentURL(), mensagem interna: " + var2.getMessage());
        }
    }

    public boolean isModifiedDocument() {
        try {
            return ((Boolean)this.invoke(this.getDocument(), "isModified", new Object[0])).booleanValue();
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.isModifiedDocument(), mensagem interna: " + var2.getMessage());
        }
    }

    public void loadFromStream(InputStream is) {
        try {
            Method e = this.beanClass.getMethod("loadFromStream", new Class[]{InputStream.class, this.getPropertyValueArrayClass()});
            e.invoke(this.bean, new Object[]{is, null});
        } catch (Exception var3) {
            throw new RuntimeException("Erro ao executar OOoBean.loadFromStream(), mensagem interna: " + var3.getMessage());
        }
    }

    public void loadFromURL(String url) {
        try {
            Method e = this.beanClass.getMethod("loadFromURL", new Class[]{String.class, this.getPropertyValueArrayClass()});
            e.invoke(this.bean, new Object[]{url, null});
        } catch (Exception var4) {
            String msg = "Erro ao executar OOoBean.loadFromURL(), mensagem interna: " + var4.getMessage();
            log.error(msg, var4);
            throw new RuntimeException(msg);
        }
    }

    public void aquireSystemWindow() {
        try {
            this.invoke(this.bean, "aquireSystemWindow", new Object[0]);
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.aquireSystemWindow(), mensagem interna: " + var2.getMessage());
        }
    }

    public Container getContainer() {
        return (Container)this.bean;
    }

    public void clear() {
        try {
            this.invoke(this.bean, "clear", new Object[0]);
        } catch (Exception var2) {
            throw new RuntimeException("Erro ao executar OOoBean.clear(), mensagem interna: " + var2.getMessage());
        }
    }

    private Object invoke(Object object, String method, boolean value) throws Exception {
        Method m;
        try {
            m = object.getClass().getMethod(method, new Class[]{Boolean.TYPE});
            return m.invoke(object, new Object[]{Boolean.valueOf(value)});
        } catch (Exception var7) {
            try {
                m = object.getClass().getDeclaredMethod(method, new Class[]{Boolean.TYPE});
                return m.invoke(object, new Object[]{Boolean.valueOf(value)});
            } catch (Exception var6) {
                throw var6;
            }
        }
    }

    private Object invoke(Object object, String method, Object... params) throws Exception {
        Class[] parameterTypes = new Class[params.length];
        int i = 0;
        Object[] var9 = params;
        int var8 = params.length;

        for(int e = 0; e < var8; ++e) {
            Object m = var9[e];
            parameterTypes[i++] = m.getClass();
        }

        Method var12;
        try {
            var12 = object.getClass().getMethod(method, parameterTypes);
            return var12.invoke(object, params);
        } catch (Exception var11) {
            try {
                var12 = object.getClass().getDeclaredMethod(method, parameterTypes);
                return var12.invoke(object, params);
            } catch (Exception var10) {
                throw var10;
            }
        }
    }

    private void set(Object object, String fieldName, Object value) throws Exception {
        try {
            object.getClass().getField(fieldName).set(fieldName, value);
        } catch (Exception var6) {
            ;
        }

        try {
            object.getClass().getDeclaredField(fieldName).set(object, value);
        } catch (Exception var5) {
            throw var5;
        }
    }

    private Class<?> getPropertyValueArrayClass() throws Exception {
        return Array.newInstance(this.classLoader.loadClass("com.sun.star.beans.PropertyValue"), 1).getClass();
    }

    public void execute(String cmd, Object[] propertyValues) throws Exception {
        try {
            Class e = this.classLoader.loadClass("com.sun.star.uno.XComponentContext");
            Class dispatchHelperClass = this.classLoader.loadClass("com.sun.star.frame.XDispatchHelper");
            Class dispatchProviderClass = this.classLoader.loadClass("com.sun.star.frame.XDispatchProvider");
            Class unoRuntimeClass = this.classLoader.loadClass("com.sun.star.uno.UnoRuntime");
            Object xCc = this.invoke(this.invoke(this.bean, "getOOoConnection", new Object[0]), "getComponentContext", new Object[0]);
            Object xFrame = this.invoke(this.invoke(this.getDocument(), "getCurrentController", new Object[0]), "getFrame", new Object[0]);
            Object sm = this.invoke(xCc, "getServiceManager", new Object[0]);
            Object dispatchHelperObject = sm.getClass().getMethod("createInstanceWithContext", new Class[]{String.class, e}).invoke(sm, new Object[]{"com.sun.star.frame.DispatchHelper", xCc});
            Object xDh = unoRuntimeClass.getMethod("queryInterface", new Class[]{Class.class, Object.class}).invoke((Object)null, new Object[]{dispatchHelperClass, dispatchHelperObject});
            Object xDispatchProvider = unoRuntimeClass.getMethod("queryInterface", new Class[]{Class.class, Object.class}).invoke((Object)null, new Object[]{dispatchProviderClass, xFrame});
            Object xWindow = this.invoke(xFrame, "getComponentWindow", new Object[0]);
            this.invoke(xWindow, "setFocus", new Object[0]);
            Method excuteDispatchMethod = xDh.getClass().getMethod("executeDispatch", new Class[]{dispatchProviderClass, String.class, String.class, Integer.TYPE, this.getPropertyValueArrayClass()});
            excuteDispatchMethod.invoke(xDh, new Object[]{xDispatchProvider, cmd, "", Integer.valueOf(0), propertyValues});
        } catch (Exception var15) {
            throw new Exception(var15.getMessage());
        }
    }

    public void setLayout(LayoutManager layout) {
        this.getContainer().setLayout(layout);
    }

    public void setVisible(boolean value) {
        this.getContainer().setVisible(value);
    }

    public String exportToPdf() throws Exception {
        String urlFile = this.getDocumentURL();
        String urlPdf = urlFile + ".pdf";
        Class propertyValueClass = this.classLoader.loadClass("com.sun.star.beans.PropertyValue");
        Object[] propertyValues = (Object[])Array.newInstance(propertyValueClass, 3);
        propertyValues[0] = propertyValueClass.newInstance();
        this.set(propertyValues[0], "Name", "URL");
        this.set(propertyValues[0], "Value", urlPdf);
        propertyValues[1] = propertyValueClass.newInstance();
        this.set(propertyValues[1], "Name", "FilterName");
        this.set(propertyValues[1], "Value", "writer_pdf_Export");
        propertyValues[2] = propertyValueClass.newInstance();
        this.set(propertyValues[2], "Name", "SelectionOnly");
        this.set(propertyValues[2], "Value", Boolean.valueOf(false));
        this.execute(".uno:ExportDirectToPDF", propertyValues);
        String path = urlPdf.substring(8, urlPdf.length());

        try {
            return URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException var7) {
            return path;
        }
    }
}
