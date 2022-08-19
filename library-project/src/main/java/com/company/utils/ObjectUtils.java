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
import java.util.stream.Stream;

public abstract class ObjectUtils {
    public static Map<String, String> beanProperties(Object bean) {
        try {
            Map<String, String> map = new HashMap<>();
            Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class)
                            .getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .forEach(pd -> { // invoke method to get value
                        try {
                            Object value = pd.getReadMethod().invoke(bean);
                            if (value != null) {
                                map.put(pd.getName(), value.toString());
                            }
                        } catch (Exception e) {
                            // add proper error handling here
                        }
                    });
            return map;
        } catch (IntrospectionException e) {
            // and here, too
            return Collections.emptyMap();
        }
    }
}
