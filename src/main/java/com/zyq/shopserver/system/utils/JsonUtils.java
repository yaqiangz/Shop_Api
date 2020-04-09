package com.zyq.shopserver.system.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtils {
    public static String filterFastjsonFields(Object src, Class<?> clazz, String... args) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, args);
        return JSON.toJSONString(src, filter);
    }
}
