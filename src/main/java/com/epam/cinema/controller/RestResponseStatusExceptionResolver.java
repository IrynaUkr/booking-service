package com.epam.cinema.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerExceptionResolver will resolve any exception thrown by the application.
 * It will also allow us to implement a uniform exception handling mechanism in our REST API.
 */
@Component
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        String message = ex.getMessage();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("message",message);
        modelAndView.addObject("url", request.getRequestURL());

        modelAndView.setViewName("error");
                return  modelAndView;
    }
}
