package com.pyruz.dzm.swapi.utility;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class ApplicationUtilities {

    public <T> Collection<T> asStream(final Collection<T> collection) {
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