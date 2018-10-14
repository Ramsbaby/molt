package com.molt.core.exception;

/**
* @class SQLExecuteException.java
* @brief SQL예외처리 클래스
*
* @author TFM 개발팀
* @date 2013. 11. 28.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2013. 11. 28.       한국인               최초 파일 생성
**/
public class SQLExecuteException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String queryId;
	
	public SQLExecuteException(String message, String queryId, int errorCode){
		super(message, errorCode);
		this.setQueryId(queryId);
	}

	public SQLExecuteException(String message, int errorCode){
		super(message, errorCode);
	}
	
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getQueryId() {
		return queryId;
	}
}
