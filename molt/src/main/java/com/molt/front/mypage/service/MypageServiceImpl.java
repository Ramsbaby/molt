package com.molt.front.mypage.service;

import com.molt.core.dao.CommonDao;
import com.molt.core.vo.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 회원 
 * @author jcw
 *
 */

@Service("mypageService")
public class MypageServiceImpl extends EgovAbstractServiceImpl implements MypageService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 회원정보 조회
	 */
	@Override
	public UserVO selectUser(Map paramMap) throws Exception {
		return commonDao.selectOne("MypageDao.selectUser", paramMap);
	}
}
