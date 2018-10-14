package com.molt.core.message;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**-----------------------------------------------------------------------
 * @Class ReloadableDatabaseDrivenMessageSource.java
 * @Description 메시지를 최초 메모리에 상주 시키고 제어하는 클래스 
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
public class ReloadableDatabaseDrivenMessageSource extends ReloadableResourceBundleMessageSource {

	protected Logger log = LogManager.getLogger();
	
	private final Map<String, Map<String, String>> properties = new HashMap<String, Map<String,String>>();
	
    @Resource(name="messageDao")
    private MessageDao messageDao;
    
	/**-----------------------------------------------------------------------
	 * @Method resolveCode
	 * @Description 메시지 형태를 생성하는 메소드
	 * @author : 조준
	 * @since 2013. 8. 23.
	 * 
	 * @param String code 메시지 코드 값
	 * @param Locale locale 로케일 값
	 * @return MessageFormat
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2013. 8. 23.  조준       최초생성
	 */
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = getText(code, locale);
		if (StringUtils.hasText(msg)) {
			MessageFormat result = createMessageFormat(msg, locale);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		return getText(code, locale);
	}
	
	/**-----------------------------------------------------------------------
	 * @Method getText
	 * @Description 메시지 값을 리턴시키는 메소드
	 * @author : 조준
	 * @since 2013. 8. 23.
	 * 
	 * @param String code 메시지 코드 값
	 * @param Locale locale 로케일 값
	 * @return String 메시지 value
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2013. 8. 23.  조준       최초생성
	 */
	private String getText(String code, Locale locale) {
		
		if (properties.isEmpty()) {
			refreshProperties();
		}
		
		Map<String, String> localized = properties.get(locale.getLanguage());
		if (localized == null) {
			localized = properties.get(Locale.ENGLISH.getLanguage());
		}
		
		String text = null;
		
		if(localized != null) {
			text = localized.get(code);
		}
		
		return text;
	}
	
	public void reload() {
		refreshProperties();
	}
	
	/**-----------------------------------------------------------------------
	 * @Method refreshProperties
	 * @Description 메시지 값을 리프레쉬 하는 메소드
	 * @author : 조준
	 * @since 2013. 8. 23.
	 * 
	 * @param 
	 * @return void
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2013. 8. 23.  조준       최초생성
	 */
	protected void refreshProperties() {
		properties.clear();
		properties.putAll(loadProperties());
	}

	/**-----------------------------------------------------------------------
	 * @Method loadProperties
	 * @Description DB에서 메시지를 읽어 메모리에 로드 하는 메소드
	 * @author : 조준
	 * @since 2013. 8. 23.
	 * 
	 * @param 
	 * @return Map<String,Map<String,String>> 메모리에 올리는 메시지 맵
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2013. 8. 23.  조준       최초생성
	 */	
	protected Map<String, Map<String, String>> loadProperties() {
		if (logger.isDebugEnabled()) {
			logger.debug("Loading properties from database...");
		}
		
		Map<String, Map<String, String>> prop = new HashMap<String, Map<String,String>>();
		List<Message> messages = null;
		try{
			messages = messageDao.selectMessage();
		}catch (Exception e){
			//// e.printStackTrace();
			log.error(e);
		}
		if(messages != null) {
			for (Message message : messages) {
				if (prop.containsKey(message.getLanguage())) {
					Map<String, String> messageValue = prop.get(message.getLanguage());
					messageValue.put(message.getCode(), message.getMessage());
				} else {
					Map<String, String> messageValue = new HashMap<String, String>();
					messageValue.put(message.getCode(), message.getMessage());
					prop.put(message.getLanguage(), messageValue);
				}
			}
		}
		
		return prop;
	}

	/**-----------------------------------------------------------------------
	 * @Method getDefaultMessage
	 * @Description 최종적으로 메시치가 없을 때 defualt 메시지를 리턴하도록 AbstractMessageSource.getDefaultMessage() 를 오버라이드한 메소드
	 * @author : SWA
	 * @since 2016. 4. 1.
	 * 
	 * @param String code
	 * @return String Default 메시지
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2016. 4. 1.  SWA       최초생성
	 */	
	@Override
	protected String getDefaultMessage(String code) {
		if (isUseCodeAsDefaultMessage()) {
			return code;
		} else {
			return "{" + code + "} message does not exists. Please contact your administrator.";
		}
	}
}
