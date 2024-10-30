package com.syf.chat.common.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request  客户端请求的所有信息
     * @param response 向客户端发送响应
     * @param handler  处理请求的对象，通常是一个 Controller 中的方法或其映射信息(可以用来获取请求的controller)
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 在整个请求结束之后被调用，视图渲染后执行（如资源清理、日志记录）
     * @param request  客户端请求的所有信息
     * @param response 向客户端发送响应
     * @param handler  处理请求的对象，通常是一个 Controller 中的方法或其映射信息(可以用来获取请求的controller)
     * @param ex       处理过程中抛出的异常，如果没有异常发生，这个参数将为 null
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 请求处理后，视图渲染之前执行，这个方法的主要作用是用于处理异常
     * @param request  客户端请求的所有信息
     * @param response 向客户端发送响应
     * @param handler  处理请求的对象，通常是一个 Controller 中的方法或其映射信息(可以用来获取请求的controller)
     * @param modelAndView 视图和模型数据。可以通过 ModelAndView 修改返回给客户端的视图和数据模型。
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
