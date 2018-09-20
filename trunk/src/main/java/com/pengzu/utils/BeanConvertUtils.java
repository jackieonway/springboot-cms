package com.pengzu.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class BeanConvertUtils {
    public BeanConvertUtils() {
    }

    public static <T> T convert(Object source, Class<T> targetClass, String dateFormat) {
        String jsonStr = JSON.toJSONStringWithDateFormat(source, dateFormat, new SerializerFeature[0]);
        return JSON.parseObject(jsonStr, targetClass);
    }

    public static <T> T convert(Object source, Class<T> targetClass) {
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseObject(jsonStr, targetClass);
    }

    public static <T> List<T> convertList(List source, Class<T> targetClass, String dateFormat) {
        String jsonStr = JSON.toJSONStringWithDateFormat(source, dateFormat, new SerializerFeature[0]);
        return JSON.parseArray(jsonStr, targetClass);
    }

    public static <T> List<T> convertList(List source, Class<T> targetClass) {
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseArray(jsonStr, targetClass);
    }
}