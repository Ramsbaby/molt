package com.molt.core.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.molt.core.vo.MemberVO;

/**
* @class UserDetailsServiceImpl.java
* @brief 로그인 요청에 따른 로그인 기본 Validat 체크 및 사용자 정보가 있는지 확인 후 UserDetail로 사용자 정보 리턴
*
* @author TFM 개발팀
* @date 2013. 9. 27.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2013. 9. 27.      한국인               최초 파일 생성
**/
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private final static Logger logger = Logger.getLogger(UserDetailsService.class);
	
//	@Autowired
//	private MberService mberService;

	
	@Autowired
	private ApplicationContext ac;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		logger.debug("loadUserByUsername: " + username);
		
		if (username.equals("")) {
            throw new UsernameNotFoundException("아이디를 입력하지 않았습니다.");
        }

		UserDetails userDetails = null;
		try {
			userDetails = getUserDetailsByUserName(username);
			
		}catch (Exception e) {
			System.out.println(e);
			
		}
		
		if(userDetails == null) {
			throw new UsernameNotFoundException("아이디나 비밀번호를 잘못 입력하였습니다.");
		}
		
		return userDetails;
	}
	
	/**
	* @Method Name  : getUserDetailsByUserName
	* @param userName 사용자 ID
	* @return User
	* @throws UsernameNotFoundException 예외처리
	* @see SeeAlso
	* @warning 주의사항
	* @bug 수정해야할 버그
	* @brief userName의 정보가 있는지 여부를 체크 후 사용자 정보 및 권한을 리턴
	*
	* @author : 한국인
	**/
	protected UserDetails getUserDetailsByUserName(String userName) throws Exception {	
		
		MemberVO memberVO = new MemberVO();
		String userId;
		String userPasswd;
		
		Map paramMap = new HashMap();
		paramMap.put("mberId", userName);
//		paramMap.put("clientCd", Constant.CLIENT_CD);
		
//		memberVO = mberService.selectMber(paramMap);
		if(memberVO == null) {
			throw new UsernameNotFoundException("실패!!");
		}

		userPasswd = memberVO.getPassword();
		
  		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		String authorCode = memberVO.getAuthorCode();
		
		authList.add(new SimpleGrantedAuthority(authorCode));
		
		return new User(userName, userPasswd, true, true, true, true, authList);
		
	}
	
	
	
}
