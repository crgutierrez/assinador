//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public ReflectionUtils() {
    }

    public static List<Field> getFieldsRecursive(Class<?> clazz) {
        ArrayList fields = new ArrayList();
        Field[] arr$ = clazz.getDeclaredFields();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];
            fields.add(field);
        }

        if(clazz.getSuperclass() != Object.class) {
            fields.addAll(getFieldsRecursive(clazz.getSuperclass()));
        }

        return fields;
    }

    public static List<Method> getMethodsRecursive(Class<?> clazz) {
        ArrayList methods = new ArrayList();
        Method[] superclass = clazz.getDeclaredMethods();
        int len$ = superclass.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Method method = superclass[i$];
            methods.add(method);
        }

        Class var6 = clazz.getSuperclass();
        if(var6 != null && var6 != Object.class) {
            methods.addAll(getMethodsRecursive(clazz.getSuperclass()));
        }

        return methods;
    }

    public static Object getValue(Object object, Field field) {
        Object fieldValue = null;

        try {
            field.setAccessible(true);
            fieldValue = field.get(object);
        } catch (Exception var7) {
            ;
        } finally {
            field.setAccessible(false);
        }

        return fieldValue;
    }

    public static void setValue(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception var7) {
            ;
        } finally {
            field.setAccessible(false);
        }

    }
}
