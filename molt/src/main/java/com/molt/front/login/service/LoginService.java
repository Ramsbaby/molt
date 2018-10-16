package com.molt.front.login.service;

import java.util.Map;

import com.molt.core.vo.UserVO;

/**
 * 로그인 서비스
 * @author jcw
 *
 */
public interface LoginService {

	/**
	 * 회원조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public UserVO selectUser(Map paramMap) throws Exception;
	
}
