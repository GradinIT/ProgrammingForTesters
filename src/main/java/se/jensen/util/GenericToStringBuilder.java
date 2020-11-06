package se.jensen.util;

import com.google.common.base.Joiner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GenericToStringBuilder {

    public static String toString(Object o) {
        ArrayList<String> list = new ArrayList<String>();
        GenericToStringBuilder.toString(o, o.getClass(), list);
        return String.format("%s%s%s ( %s )", Colour.BLUE, o.getClass().getName(), Colour.NO, Joiner.on(",").join(list));
    }

    private static void toString(Object o, Class<?> clazz, List<String> fields) {
        Stream.of(o.getClass().getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .forEach(System.out::println);
    }

    private static String getFieldNameAndValue(Object o, Field field) {
        try {
            return field.getName() + "=" + ((field.get(o) == null) ? "null" : field.get(o).toString());
        } catch (Exception e) {
            StackTraceElement last = e.getStackTrace()[0];
            return String.format("<<REFLECTION CAUSED %s IN %s:%d>>", e.getClass().getSimpleName(),
                    last.getFileName(), last.getLineNumber());
        }
    }
}
