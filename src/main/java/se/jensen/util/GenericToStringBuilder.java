package se.jensen.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;

public class GenericToStringBuilder {
    public static String toString(Object o) {
        List<String> fields = getFieldRepresentations(o, o.getClass(), Lists.newArrayList());
        return String.format("%s%s%s ( %s )",Colour.BLUE,o.getClass().getName() ,Colour.NO, Joiner.on(",").join(fields));
    }

    private static List<String> getFieldRepresentations(Object o, Class clazz, List<String> fields) {
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            String value;
            try {
                value = field.get(o) == null ? "null" : field.get(o).toString();
            } catch (Exception e) {
                StackTraceElement last = e.getStackTrace()[0];
                value = String.format("<<REFLECTION CAUSED %s IN %s:%d>>", e.getClass().getSimpleName(),
                        last.getFileName(), last.getLineNumber());
            }
            fields.add(String.format("%s%s%s = %s", Colour.BLUE, field.getName(), Colour.NO, value));
        }
        if (clazz.getSuperclass() != null) {
            getFieldRepresentations(o, clazz.getSuperclass(), fields);
        }

        fields.sort(ID_FIRST_COMPARATOR);
        return fields;
    }

    private static final Comparator<String> ID_FIRST_COMPARATOR = (String s1, String s2) ->
    {
        if (s1.startsWith("id=")) {
            return -1;
        }
        if (s2.startsWith("id=")) {
            return 1;
        }
        return 0;
    };
}
