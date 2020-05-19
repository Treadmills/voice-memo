package com.ddz.utils.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json2Utils {

    private static final ObjectMapper om;

    static {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            T data = om.readValue(json, clazz);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            String json = om.writeValueAsString(object);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    public static Map<String, Object> toMap(String json) {
        JavaType type =
                om.getTypeFactory().constructMapType(HashMap.class, String.class, String.class);
        try {
            Map<String, Object> values = om.readValue(json, type);
            return values;
        } catch (IOException e) {
            return null;
        }
    }

    public static String readString(String json, String fieldName) {
        return readField(json, fieldName, String.class);
    }

    public static Boolean readBoolean(String json, String fieldName) {
        Boolean result = readField(json, fieldName, Boolean.class);
        return (result != null && result);
    }

    public static Integer readInt(String json, String fieldName) {
        return readField(json, fieldName, Integer.class);
    }

    public static Long readLong(String json, String fieldName) {
        return readField(json, fieldName, Long.class);
    }

    public static Double readDouble(String json, String fieldName) {
        return readField(json, fieldName, Double.class);
    }

    public static JsonNode readJsonNode(String json, String fieldName) {
        return readField(json, fieldName, JsonNode.class);
    }


    @SuppressWarnings("unchecked")
    private static <T> T readField(String json, String fieldName, Class<T> fieldType) {
        try {
            JsonNode root = om.readTree(json);
            JsonNode fieldNode = StringUtils.isEmpty(fieldName) ? root : root.findValue(fieldName);
            if (fieldNode == null) {
                return null;
            }

            if (JsonNode.class.isAssignableFrom(fieldType))
                return (T) fieldNode;
            if (Boolean.class == fieldType)
                return (T) Boolean.valueOf(fieldNode.asBoolean());
            if (Integer.class == fieldType)
                return (T) Integer.valueOf(fieldNode.asInt());
            if (Long.class == fieldType)
                return (T) Long.valueOf(fieldNode.asLong());
            if (Double.class == fieldType)
                return (T) Double.valueOf(fieldNode.asDouble());
            if (String.class == fieldType) {
                if (fieldNode.isTextual())
                    return (T) fieldNode.asText();
                else
                    return (T) om.writeValueAsString(fieldNode);
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<Map<String, Object>> readMapFields(String json, String fieldName) {
        return (List) readFields(json, fieldName, Map.class);
    }

    public static <T> List<T> readFields(String json, String fieldName, Class<T> objcetClazz) {
        List<T> fieldObjects = Lists.newArrayList();
        try {
            String fieldJson = json;
            if (StringUtils.isNotEmpty(fieldName))
                fieldJson = readString(json, fieldName);

            JavaType type = om.getTypeFactory().constructCollectionType(List.class, objcetClazz);
            List<T> objects = om.readValue(fieldJson, type);

            if (CollectionUtils.isNotEmpty(objects))
                fieldObjects.addAll(objects);
            return fieldObjects;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldObjects;
    }

}
