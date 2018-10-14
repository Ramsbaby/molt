package com.molt.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class WebUtil {
	
	public static Map<String, String> getRequestBodyMap(HttpServletRequest request) throws Exception {
	            
	    String requestSring = getRequestBodyString(request);
	    if(requestSring == null || "".equals(requestSring)){
	    	return null;
	    }
	    
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> bodyMap = mapper.readValue(requestSring, new TypeReference<HashMap<String,String>>(){});
	 
	    return bodyMap;
	}
	
	public static String getRequestBodyString(HttpServletRequest request) throws Exception {
		HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request);
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = requestWrapper.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
				
				return stringBuilder.toString();
				
			}
		} catch (Exception ex) {
			throw new Exception("Error reading the request payload", ex);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException iox) {
					// ignore
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 권한체크
	 * @param role
	 * @return
	 */
	public static boolean hasRole(String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().startsWith(role);
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}
	
	/**
	 * NULL 체크
	 * @param data
	 * @return
	 */
	public static String nvl(String data){
		if(data == null) return "";
		return data;
	}
	

	/**
	 * 숫자체크
	 * @param data
	 * @return
	 */
	public static boolean isNumber(String data){
		try{
			double number = Double.parseDouble(data);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
