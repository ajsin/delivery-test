package com.delivery.api.common.interceptor;

import com.delivery.api.common.annotation.NoSignHandler;
import com.delivery.api.common.exception.UserException;
import com.delivery.api.common.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SignInInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("path : " + request.getRequestURI());

        try{
            NoSignHandler noSignHandler = ((HandlerMethod)handler).getMethodAnnotation(NoSignHandler.class);
            if(noSignHandler != null){
                log.info("NoSignHandler - 인증 미사용 API");
                return true;
            }
        }
        catch (Exception e){
            log.info("등록되지 않은 api 호출");
            return false;
        }

        // Authorization 정보 파싱
        String uid = null;
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization != "") {
            log.info("authorization : " + authorization);
            String token = "";
            String[] authList = authorization.split(" ");
            if (authList.length == 2 && authList[0].equalsIgnoreCase("bearer")) {
                token = authList[1];
            }

            Claims claims = jwtTokenUtil.getTokenData(token);
            Object uidObj = claims.get("uid");
            if(uidObj != null) {
                uid = (String)uidObj;
            }
        }

        if(uid == null){
            throw new UserException.AuthException("인증 실패");
        }

        request.setAttribute("uid", uid);

        return true;
    }

}
