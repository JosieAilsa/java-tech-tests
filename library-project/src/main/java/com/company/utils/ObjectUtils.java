package com.company.utils;

import com.company.Book;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ObjectUtils {
    private static boolean isGetter(Method method){
        // identify get methods
        if((method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getParameterCount() == 0 && !method.getReturnType().equals(void.class)){
            return true;
        }
        return false;
    }

    public static ArrayList<Object> getAllGetters(Object object) {// get all the methods of the class
        ArrayList<Object> values = new ArrayList<>();
        Method[] methods = object.getClass().getDeclaredMethods();
        // Initially calling all the set methods to set values
        for (Method method : methods) {
            if (isGetter(method)) {
                try {
                System.out.println(method.invoke(object));
                values.add(method.invoke(object));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

}
