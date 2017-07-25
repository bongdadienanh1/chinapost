package com.ylife.security;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

public class YlifeSecurityMessageSource extends ResourceBundleMessageSource {

    public YlifeSecurityMessageSource() {
        setBasename("org.springframework.security.messages");
    }

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new YlifeSecurityMessageSource());
    }
}
