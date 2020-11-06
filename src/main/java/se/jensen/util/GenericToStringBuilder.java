package se.jensen.util;

import com.google.common.base.Joiner;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericToStringBuilder {

    public static String toString(Object o) {
        ArrayList<String> list = new ArrayList<String>();
        GenericToStringBuilder.toString(o, o.getClass(), list);
        return String.format("(%s)",  list);
    }

    private static void toString(Object o, Class<?> clazz, List<String> fields) {
        if (o instanceof Map) {
            Map.class.cast(o).entrySet().stream().forEach(o1 -> {
                toString(o1,o1.getClass(),fields);
            });
        } else if (o instanceof List) {
            List.class.cast(o).stream().forEach(o1 -> {
                toString(o1,o1.getClass(),fields);
            });
        }
        List<String> fieldNamesAndValues =Stream.of(o.getClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    return getFieldNameAndValue(o,field.getDeclaringClass(),field);
                })
                .collect(Collectors.toList());
        fields.add(String.format("%s { %s }",o.getClass().getName(),fieldNamesAndValues));
    }

    private static String getFieldNameAndValue(Object o,Class clazz, Field field) {
        try {
            Method method = clazz.getDeclaredMethod("get"+setFirstLetterToUpper(field.getName()), null);
            Object returnValue = method.invoke(o, null);
            return field.getName() + "=" + returnValue;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String setFirstLetterToUpper(String name) {
        String capitalized =  StringUtils.capitalize(name);
        return capitalized;
    }
}
