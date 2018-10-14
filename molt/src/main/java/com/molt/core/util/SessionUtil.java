/**
 * 
 */
package com.molt.core.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Project : 2017년 교육관리시스템 재구축 사업
 * @Class : SessionUtil.java
 * @Package : ems.framework.session
 * @Description :
 * @author : 4gt069
 * @since : 2014. 9. 6.
 * @version
 *
 * @Copyright (c) 2017년 교육관리시스템 재구축 사업
 *
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------   
 *     날짜              수정자             변경사유
 * 2014. 9. 6.       서대현                 최초작성
 */
public class SessionUtil {
	
	/**
     * 세션정보에서 attribute set
     * @param Stringattribute key name
     * @return void
     */
    public static void setSession(String name, Object object) throws Exception{
    	RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }    
    
    /**
     * 세션정보에서 attribute get
     * @param Stringattribute key name
     * @return Objectattribute Obj
     */
    public static Object getSession(String name) throws Exception{
    	return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
    
    /**
     * 세션정보에서 attribute delete
     * @param Stringattribute key name
     * @return void
     */
    public static void removeSession(String name) throws Exception{
    	RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

}
