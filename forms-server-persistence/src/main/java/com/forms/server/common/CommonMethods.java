package com.forms.server.common;

import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMethods {

    public static boolean isEmpty(Integer value) {
        boolean flag = true;
        //TODO return false in case of null also
        if (value != null && value > 0) {
            flag = false;
        }
        return flag;
    }

    public static boolean isEmpty(String name) {
        boolean flag = true;
        if (name != null && !name.trim().isEmpty()) {
            flag = false;
        }
        return flag;
    }

    private static final Logger logger = LoggerFactory.getLogger(CommonMethods.class);

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return jsonMapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return jsonMapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
