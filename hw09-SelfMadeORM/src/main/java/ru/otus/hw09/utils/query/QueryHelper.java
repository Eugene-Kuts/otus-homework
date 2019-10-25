package ru.otus.hw09.utils.query;

import java.lang.reflect.Field;
import java.util.Collections;

public class QueryHelper {
    public static <T> String makeCreateQuery(Class<T> clazz, Field[] allFields, Field annotatedField) {
        StringBuilder createQuery = new StringBuilder("INSERT INTO " + clazz.getSimpleName() + "(");
        for (int i = 0; i < allFields.length; i++)
            if (!allFields[i].getName().equals(annotatedField.getName())) {
                if (i < allFields.length - 1) {
                    createQuery.append(allFields[i].getName()).append(",");
                } else {
                    createQuery.append(allFields[i].getName()).append(") VALUES (");
                }
            }
        createQuery.append(String.join(", ", Collections.nCopies(allFields.length - 1, "?"))).append(")");
        return createQuery.toString();
    }

    public static <T> String makeUpdateQuery(Class<T> clazz, Field[] fields, Field annotatedField) {
        StringBuilder updateQuery = new StringBuilder("UPDATE " + clazz.getSimpleName() + " set ");
        for (int i = 0; i < fields.length; i++)
            if (!fields[i].getName().equals(annotatedField.getName())) {
                if (i < fields.length - 1) {
                    updateQuery.append(fields[i].getName()).append(" = ?,");
                } else {
                    updateQuery.append(fields[i].getName()).append(" = ? WHERE ").append(annotatedField.getName()).append(" = ?");
                }
            }
        return updateQuery.toString();
    }

    public static <T> String makeSelectQuery(Class<T> clazz, Field annotatedField) {
        return "SELECT * FROM " + clazz.getSimpleName() + " WHERE " + annotatedField.getName() + " = ?";
    }
}
