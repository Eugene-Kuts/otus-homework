package ru.otus.hw08;

import javax.json.*;
import java.lang.reflect.*;
import java.util.*;

public class MyJsonObjectWriter {

    public String toJson(Object src) throws IllegalAccessException {
        if (Objects.isNull(src)) {return "null";}

        JsonValue jsonValue = createJsonValue(src.getClass(), src);

        if (jsonValue != null) {return jsonValue.toString();}

        JsonObject jsonObject = makeOut(src);

        return Objects.requireNonNull(jsonObject).toString();
    }

    private JsonObject makeOut(Object src) throws IllegalAccessException {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        Field[] fields = src.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            Object obj = field.get(src);
            if (obj != null && isNotStaticOrTransient(modifiers)) {
                builder.add(field.getName(), createJsonValue(field.getType(), obj));
            }
        }

        return builder.build();
    }

    private JsonValue createJsonValue(Class<?> type, Object src) {
        boolean isNull = Objects.isNull(src);

        if (isPrimitiveValue(type)) {
            return handlePrimitiveValue(src);
        } else if (type.isArray()) {
            return isNull ? JsonArray.EMPTY_JSON_ARRAY : handleArrayValue(src);
        } else if (Collection.class.isAssignableFrom(type)) {
            Collection<?> collection = (Collection<?>) src;
            return isNull ? JsonArray.EMPTY_JSON_ARRAY : handleArrayValue(collection.toArray());
        }
        return null;
    }

    private JsonArray handleArrayValue(Object src) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < Array.getLength(src); i++) {
            Object value = Array.get(src, i);
            arrayBuilder.add(createJsonValue(value.getClass(), value));
        }
        return arrayBuilder.build();
    }

    private JsonValue handlePrimitiveValue(Object src) {
        if (src instanceof  Number) {
            Number number = (Number) src;
            if (number instanceof Double || number instanceof Float) {
                return Json.createValue(number.doubleValue());
            } else {
                return Json.createValue(number.longValue());
            }
        } else if (src instanceof Boolean) {
            return src.equals(true) ? JsonValue.TRUE : JsonValue.FALSE;
        } else {
            return Json.createValue(src.toString());
        }
    }

    private boolean isNotStaticOrTransient(int modifiers) {
        return !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers);
    }

    private boolean isPrimitiveValue(Class<?> type) {
        return type.isPrimitive() ||
                Number.class.isAssignableFrom(type) ||
                String.class.isAssignableFrom(type) ||
                Boolean.class.isAssignableFrom(type) ||
                Character.class.isAssignableFrom(type);
    }
}