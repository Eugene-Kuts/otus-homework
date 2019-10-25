package ru.otus.hw09.utils.reflection;

import ru.otus.hw09.annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReflectionHelper {

    public static <T> Field[] getObjectFields(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
            field.setAccessible(true);
        return fields;
    }

    public static <T> Field getAnnotatedField(Class<T> clazz) {
        Field[] fields = getObjectFields(clazz);
        for (Field field : fields)
            if (field.getDeclaredAnnotation(Id.class) != null)
                return field;
        return null;
    }

    public static <T> T setObjectFields(Class<T> clazz, ResultSet resultSet) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field[] objectFields = getObjectFields(clazz);
        Constructor<T> constructor = clazz.getConstructor();
        Optional<T> obj = Optional.of(constructor.newInstance());
        for (Field field : objectFields){
            field.set(obj.get(), resultSet.getObject(field.getName()));}
        return obj.isPresent() ? obj.get() : null;
    }
}
