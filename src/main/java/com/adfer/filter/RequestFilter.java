package com.adfer.filter;

import com.adfer.services.AppSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adrianferenc on 29.10.2016.
 */
@Component
public class RequestFilter implements javax.servlet.Filter {

    private boolean isAvailable;

    private List<String> allowedPaths = Arrays.asList("/templates", "/login", "/unavailable");

    @Autowired
    private AppSettingsService appSettingsService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String servletPath = httpRequest.getServletPath();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = false;
        if(auth!=null) {
            isAdmin = auth.getAuthorities().stream().filter(a -> a != null).anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        }
        if(appSettingsService!=null) {
            isAvailable = appSettingsService.isAvailable();
        }

        if (isAdmin || isAvailable) {
            chain.doFilter(request, response);
        } else if (servletPath.contains("/admin") && !isAdmin) {
            httpResponse.sendRedirect("/login");
        } else if (allowedPaths.stream().anyMatch(p->servletPath.contains(p))) {
            chain.doFilter(request, response);
        } else if (!isAvailable) {
            httpResponse.sendRedirect("/unavailable");
        }
    }

    @Override
    public void destroy() {

    }
}
