package com.pyruz.dzm.swapi.utility;


import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class ApplicationUtilities {
    private static ApplicationUtilities applicationUtilities;

    public static ApplicationUtilities getInstance() {
        if (applicationUtilities == null) {
            applicationUtilities = new ApplicationUtilities();
        }
        return applicationUtilities;
    }

    public <T> Collection<T>

    asStream(final Collection<T> collection) {
        return Optional.ofNullable(collection).orElse(Collections.emptySet());
    }

    public HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    public static boolean isExists(String o) {
        return o != null && !o.isEmpty() && !o.trim().equalsIgnoreCase("null");
    }


}