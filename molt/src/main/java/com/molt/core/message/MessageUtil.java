package com.molt.core.message;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**-----------------------------------------------------------------------
 * @Class Message.java
 * @Description 메시지 정보를 담을 VO 객체
 * @author 조준
 * @since 2013. 8. 23.
 * @version 1.0
 * 
 * @Copyright (c) 2013 4세대 국가관세종합정보망 , LG CNS 컨소시엄 All rights reserved.
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------   
 * 수정일         수정자       수정내용
 * ----------  ---------   -----------------------------------------------
 * 2013. 8. 23.  조준       최초생성
 */
public class MessageUtil {

	ReloadableResourceBundleMessageSource messageSource = null;
	
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
    public String getMessage(String code, Object[] args, Locale locale){
    	
    	String message = null;
    	Locale paramLocale = locale;
    	
    	// null일 경우 default 한글 표시
    	if(locale == null){
    		paramLocale = Locale.KOREAN;
    	}
    	
    	message = messageSource.getMessage(code, args, paramLocale);
    	
    	return message;
    }
    
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
    public String getMessage(String code, Locale locale){
    	return getMessage(code, null, locale);
    }
    
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
    public String getMessage(String code){
    	return getMessage(code, null, null);
    }        
    
}

