package com.molt.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Constant;



public class CookieUtil {

    private static final Log log = LogFactory.getLog(CookieUtil.class);
    public static final String COOKIE_DILIM = "^";
    public static final String COOKIE_PROF_DILIM = "|";

	
    /**
     * cookie path
     * @return
     */
	public static String getDefaultPath() {
		return Constant.COOKIE_PATH;
	}

	/**
	 * cookie domain
	 * @return
	 */
	public static String getDefaultDomain() {
		return Constant.COOKIE_DOMAIN;
	}
	
	/**
	 * cookie expire time
	 * @return
	 */
	public static int getDefaultExpire() {
		if(Constant.COOKIE_EXPIRE == null || "".equals(Constant.COOKIE_EXPIRE)) return -1;
		try {
			return Integer.parseInt(Constant.COOKIE_EXPIRE);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * cookie���� �����´�.
	 * @param request
	 * @return
	 */
	public static Cookie[] getCookies0(HttpServletRequest request) {
		if(request == null) return null;
		return request.getCookies();
	}
	
	/**
	 * cookie name�� �ش��ϴ� cookie�� �����´�.
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie0(HttpServletRequest request, String name) {
        Cookie [] cookies = getCookies0(request);
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }
	
	/**
	 * cookie name�� �ش��ϴ� cookie ���� �����´�.
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie0(request, name);
        return cookie == null ? "" : cookie.getValue();
    }
	
	
	/**
     * ��Ű�� �����Ͽ� response��ü�� ����
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = setCookie0(name, value, "/", "", -1, false);
        response.addCookie(cookie);
    }
    public static void setCookie(HttpServletResponse response, String name, String value, String domain) {
    	Cookie cookie = setCookie0(name, value, "/", domain, -1, false);
    	response.addCookie(cookie);
    }
    
    public static void expireAuthCookie(HttpServletResponse response) {
        CookieUtil.setCookie(response, Constant.COOKIE_AUTH_KEY, "", 0);
    }
    
    /**
     * ��Ű�� �����Ͽ� response��ü�� ����
     * @param response
     * @param name
     * @param value
     * @param expire
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int expire) {
        Cookie cookie = setCookie0(name, value, expire);
        response.addCookie(cookie);
    }
    
    public static void setCookie(HttpServletResponse response, String name, String value, String domain, int expire) {
    	Cookie cookie = setCookie0(name, value, domain, expire);
    	response.addCookie(cookie);
    }
    
    /**
     * ������ Cookie ������ �����Ѵ�.
     *
     * @param response HttpServletResponse ��ü
     * @param name ������ ������ Cookie �̸�
     */
    public static void expireCookie(HttpServletResponse response, String name) {
        CookieUtil.setCookie(response, name, "", 0);
    }	
	
    /**
     * ��Ű ���� by name, value
     * @param name
     * @param value
     * @return
     */
    public static Cookie setCookie0(String name, String value) {
        return setCookie0(name, value, getDefaultExpire());
    }

    /**
     * ��Ű ���� by name, value
     * @param name
     * @param value
     * @return
     */
    public static Cookie setCookie0(String name, String value, String domain) {
        return setCookie0(name, value, domain, getDefaultExpire());
    }
    
    /**
     * ��Ű ���� by name, value, expire
     * @param name
     * @param value
     * @param expire
     * @return
     */
    public static Cookie setCookie0(String name, String value, int expire) {
        return setCookie0(name, value, getDefaultPath(), getDefaultDomain(), expire, false);
    }
    
    /**
     * ��Ű ���� by name, value, domain, expire
     * @param name
     * @param value
     * @param domain
     * @param expire
     * @return
     */
    public static Cookie setCookie0(String name, String value, String domain, int expire) {
    	return setCookie0(name, value, getDefaultPath(), domain, expire, false);
    }

    /**
     * ��Ű ���� by name, value, path, domain, expire, secure
     * @param name
     * @param value
     * @param path
     * @param domain
     * @param expire
     * @param secure
     * @return
     */
    public static Cookie setCookie0(String name, String value, String path, String domain, int expire, boolean secure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        if(domain != null && !domain.equals("")){
        	cookie.setDomain(domain);
        }else{
        	cookie.setDomain(getDefaultDomain());
        }
        cookie.setMaxAge(expire);
        cookie.setSecure(secure);
        return cookie;
    }
    


    
}
