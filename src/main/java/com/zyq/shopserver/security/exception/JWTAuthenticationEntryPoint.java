package com.zyq.shopserver.security.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决匿名用户访问需要权限资源的异常
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        Map<String, Object> resultMap = new LinkedHashMap<>();
        Map<String, Object> metaMap = new LinkedHashMap<>();
        metaMap.put("msg", "无效token");
        metaMap.put("status", HttpServletResponse.SC_BAD_REQUEST);
        resultMap.put("data", null);
        resultMap.put("meta", metaMap);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(resultMap, SerializerFeature.WriteMapNullValue));
    }
}
