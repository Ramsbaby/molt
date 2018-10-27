package com.molt.front.detail.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molt.core.dao.CommonDao;
import com.molt.core.vo.UserVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * 회원 
 * @author jcw
 *
 */

@Service("detailService")
public class DetailServiceImpl extends EgovAbstractServiceImpl implements DetailService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 회원정보 조회
	 */
	@Override
	public UserVO selectUser(Map paramMap) throws Exception {
		return commonDao.selectOne("LoginDao.selectUser", paramMap);
	}
}
