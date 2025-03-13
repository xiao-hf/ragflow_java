package com.xiao.interceptor;

import com.xiao.constants.RedisPrefix;
import com.xiao.domain.User;
import com.xiao.exception.LoginException;
import com.xiao.utils.JWTUtil;
import com.xiao.utils.RedisUtil;
import com.xiao.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info(request.getRequestURL().toString());
        System.out.println(request.getMethod());
        if (request.getMethod().equals("OPTIONS")) // 防止跨域失败
            return true;
        String token = request.getHeader("token");
        Claims claims = JWTUtil.parseJWT(token);
        long userId = Long.parseLong(claims.get("id").toString());
        String username = claims.get("username").toString();
        log.info("当前登录用户id:{} 姓名:{}", userId, username);
        String userNo = claims.get("userNo").toString();
        String key = RedisPrefix.USER_TIME_OUT + userId;
        if (!redisUtil.contains(key)) {
            throw new LoginException();
        }
        redisUtil.expire(key, 5, TimeUnit.MINUTES);
        User user = new User();
        user.setId(userId);

        ThreadLocalUtil.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("接口调用结束, 从ThreadLocal中删除用户信息");
        ThreadLocalUtil.remove();
    }
}