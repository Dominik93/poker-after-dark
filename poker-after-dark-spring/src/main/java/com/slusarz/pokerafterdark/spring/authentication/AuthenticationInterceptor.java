package com.slusarz.pokerafterdark.spring.authentication;

import com.slusarz.pokerafterdark.spring.context.AdministrationContext;
import com.slusarz.pokerafterdark.spring.context.AnonymousContext;
import com.slusarz.pokerafterdark.spring.context.Context;
import com.slusarz.pokerafterdark.spring.context.ContextFactory;
import com.slusarz.pokerafterdark.spring.context.ContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final String AUTHENTICATION_HEADER_NAME = "Authentication";

    private ContextFactory contextFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Pre handling the request " + request.getContextPath() + request.getServletPath());
        String token = request.getHeader(AUTHENTICATION_HEADER_NAME);
        ContextHolder.setContext(contextFactory.createContext(token));
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("After handling the request " + request.getContextPath() + request.getServletPath());
        ContextHolder.clear();
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("After rendering the view");
        super.afterCompletion(request, response, handler, ex);
    }

}
