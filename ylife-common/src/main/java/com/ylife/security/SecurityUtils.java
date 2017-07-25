package com.ylife.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by InThEnd on 2016/12/15.
 * SecurityUtils
 */
public class SecurityUtils {

    public static String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = null;
        if (!(auth instanceof AnonymousAuthenticationToken || auth == null)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            username = userDetails.getUsername();
        }
        return username;
    }

    public static String getUsernameFromAuthentication(Authentication auth) {
        String username = null;
        if (!(auth instanceof AnonymousAuthenticationToken || auth == null)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            username = userDetails.getUsername();
        }
        return username;
    }

}
