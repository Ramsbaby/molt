package com.molt.core.message;

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
public class Message {

	private String language;
	private String code;
	private String message;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[locale=").append(getLanguage());
		buffer.append(",code=").append(getCode());
		buffer.append(",message=").append(getMessage()).append("]");
		
		return buffer.toString();
	}
}
