package com.molt.core.exception;
/**
* @class TfmBaseException.java
* @brief 기본 Exception처리를 정의한 클래스
*
* @author TFM 개발팀
* @date 2013. 9. 27.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2013. 9. 27.       한국인               최초 파일 생성
**/
public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode; 

	/**
	 * @param message
	 * @param errorCode
	 */
	public BaseException(String message, int errorCode){
		super(message);
		this.setErrorCode(errorCode);
	}
	
	/**
	* @Method Name  : setErrorCode
	* @author       : 한국인
	* @since        : 2013. 9. 27.
	* @see   	 	: 에러 코드 set
	* @param errorCode 에러코드
	*/
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	* @Method Name  : getErrorCode
	* @author       : 한국인
	* @since        : 2013. 9. 27.
	* @see   	 	: 에러 코드 get
	* @return errorCode 에러코드
	*/
	public int getErrorCode() {
		return errorCode;
	}
}
