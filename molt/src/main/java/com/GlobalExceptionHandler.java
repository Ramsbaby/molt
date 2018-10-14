package com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private Map errorResponseMap = new HashMap<String, Object>();
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleException(HttpServletRequest request, Exception ex){
		logger.error("Exception", ex);
		errorResponseMap.put("cause", ex);
		return errorResponseMap;
	}
	
	@ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleMySQLIntegrityConstraintViolationException(HttpServletRequest request, Exception ex){
		logger.error("MySQLIntegrityConstraintViolationException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleDataAccessException(HttpServletRequest request, Exception ex){
		logger.error("DataAccessException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ExceptionHandler(TransactionException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleTransactionException(HttpServletRequest request, Exception ex){
		logger.error("TransactionException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ExceptionHandler(EgovBizException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleEgovBizException(HttpServletRequest request, Exception ex){
		logger.error("EgovBizException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleAccessDeniedException(HttpServletRequest request, Exception ex){
		logger.error("AccessDeniedException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public Map<String, Object> handleSQLException(HttpServletRequest request, Exception ex){
		logger.error("SQLException", ex);
		errorResponseMap.put("cause", ex.getMessage());
		return errorResponseMap;
	}
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED, reason="IOException occured")
	@ResponseBody
	@ExceptionHandler(IOException.class)
	public Map<String, Object> handleIOException(){
		logger.error("IOException handler executed");
		errorResponseMap.put("cause", "IOException handler executed");
		return errorResponseMap;
	}
	
	@ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		List<FieldError> errorList = error.getBindingResult().getFieldErrors();
		for(FieldError fieldError : errorList){
			errorResponseMap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
        return errorResponseMap;
    }
}