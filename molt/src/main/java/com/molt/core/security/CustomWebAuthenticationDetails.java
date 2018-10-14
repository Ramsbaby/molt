package com.molt.core.security;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String saveYn;
    private final String superPassword;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        saveYn = request.getParameter("saveYn");
        superPassword = request.getParameter("superPassword");
    }

    public String getSaveYn() {
        return saveYn;
    }
    
    public String getSuperPassword() {
    	return superPassword;
    }

}