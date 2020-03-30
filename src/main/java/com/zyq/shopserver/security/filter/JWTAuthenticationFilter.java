package com.zyq.shopserver.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyq.shopserver.security.constants.SecurityConstants;
import com.zyq.shopserver.security.entity.JwtUser;
import com.zyq.shopserver.security.entity.LoginUser;
import com.zyq.shopserver.security.utils.JwtTokenUtils;
import com.zyq.shopserver.system.entity.Manager;
import com.zyq.shopserver.system.service.ManagerService;
import com.zyq.shopserver.system.service.ManagerServiceImp;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 认证过滤器
 * 如果用户名和密码正确，那么过滤器将创建一个JWT Token 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;
    @Autowired
    private ManagerService managerService;
    private Map<String, Object> meta = new LinkedHashMap<>();
    private Map<String, Object> data = new LinkedHashMap<>();
    private Map<String, Object> metaMap = new LinkedHashMap<>();
    private Map<String, Object> dataMap = new LinkedHashMap<>();
    private Map<String, Object> result = new LinkedHashMap<>();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl(SecurityConstants.BASE_URL + SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
//        try {
            // 获取登录信息
            String password = request.getParameter("password");
            String username = request.getParameter("username");
            LoginUser loginUser = new LoginUser(username, password);
//            LoginUser loginUser = objectMapper.readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(SecurityConstants.REMEMBER_ME);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
            return authenticationManager.authenticate(authRequest);  // 会跳转至CustomUserDetailsService的loadUserByUsername方法进行验证
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//            return null;
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        List<String> roles = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 创建token
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles, rememberMe.get());

        response.setHeader(SecurityConstants.TOKEN_HEADER, token);


        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        data.put("id", jwtUser.getId());
        data.put("rid", jwtUser.getRid());
        data.put("username", jwtUser.getUsername());
        data.put("mobile", jwtUser.getMobile());
        data.put("email", jwtUser.getEmail());
        data.put("token", token);
        meta.put("msg", "登录成功");
        meta.put("status", HttpServletResponse.SC_OK);

        result.put("data", data);
        result.put("meta", meta);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        meta.put("msg", "密码错误");
        meta.put("status", HttpServletResponse.SC_UNAUTHORIZED);

        result.put("data", null);
        result.put("meta", meta);

        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        writer.flush();
        writer.close();
    }
}
