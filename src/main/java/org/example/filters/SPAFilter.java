package org.example.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter("/*")
public class SPAFilter implements Filter {

    private static final Pattern STATIC_RESOURCES = Pattern.compile(".*(\\.(js|css|png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot|ico))$");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();


        if (uri.startsWith(contextPath + "/api/")) {
            chain.doFilter(request, response);
            return;
        }


        if (STATIC_RESOURCES.matcher(uri).matches() || uri.equals(contextPath + "/favicon.ico")) {
            chain.doFilter(request, response);
            return;
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html");
        dispatcher.forward(request, response);
    }
}

