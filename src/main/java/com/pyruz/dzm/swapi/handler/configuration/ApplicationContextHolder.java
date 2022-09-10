package com.pyruz.dzm.swapi.handler.configuration;

import com.pyruz.dzm.swapi.utility.ApplicationMessages;
import com.pyruz.dzm.swapi.utility.ApplicationProperties;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder {

    protected Logger logger;
    protected ApplicationProperties applicationProperties;
    protected ApplicationMessages applicationMessages;
    protected RestTemplateUtility restTemplateUtility;

    @Autowired
    public void context(ApplicationContext context) {
        logger = context.getBean(Logger.class);
        this.applicationProperties = context.getBean(ApplicationProperties.class);
        this.applicationMessages = context.getBean(ApplicationMessages.class);
        this.restTemplateUtility = context.getBean(RestTemplateUtility.class);
    }
}
