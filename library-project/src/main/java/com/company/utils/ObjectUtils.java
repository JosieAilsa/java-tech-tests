package com.company.utils;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.*;

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
                            throw new NullPointerException("Not a valid book!");
                        }
                    });
            return map;
        } catch (IntrospectionException e) {
            // and here, too
            return Collections.emptyMap();
        }
    }
}
