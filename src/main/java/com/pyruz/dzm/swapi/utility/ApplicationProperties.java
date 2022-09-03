package com.pyruz.dzm.swapi.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.Objects;

@Configuration
@PropertySources(
        {
                @PropertySource(name = "application", value = "classpath:/application.properties", encoding = "UTF-8", ignoreResourceNotFound = true),
        }
)
public class ApplicationProperties {
    @Resource
    private Environment environment;

    public String getProperty(String name) {
        try {
            return Objects.requireNonNull(environment.getProperty(name)).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return name;
        }
    }
}
