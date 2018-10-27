package com.molt.front.mypage.service;

import com.molt.core.vo.UserVO;

import java.util.Map;

/**
 * 로그인 서비스
 * @author jcw
 *
 */
public interface MypageService {

	/**
	 * 회원조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public UserVO selectUser(Map paramMap) throws Exception;
	
}
