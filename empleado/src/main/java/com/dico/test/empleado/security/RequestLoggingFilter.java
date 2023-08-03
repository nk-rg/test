package com.dico.test.empleado.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log the request details here
        String requestUrl = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String queryString = request.getQueryString();
        // You can log additional request information as needed

        // Log the request information
        log.info("Received request: {} {} {}", httpMethod, requestUrl, queryString);

        // Proceed with the request
        filterChain.doFilter(request, response);
    }
}