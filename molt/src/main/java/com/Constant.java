package com;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
	
	@Value("#{property['service.domain']}")
	public static String SERVICE_DOMAIN;
	
	public static final String COOKIE_PATH = "";			// 회원승인
	public static final String COOKIE_DOMAIN = "safety.com";	// 회원승인
	public static final String COOKIE_EXPIRE = "-1";		// 회원승인
	
	public static final String COOKIE_AUTH_KEY = "SAFETY_AUTH_KEY";	// 회원승인
	public static final String COOKIE_AUTH_ENCKEY = "SAFETY1234!";	// 회원승인

	
}
