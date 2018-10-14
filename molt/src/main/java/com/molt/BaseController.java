package com.molt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import com.molt.core.util.DateUtil;
import com.molt.core.vo.BaseVO;
import com.molt.core.vo.MemberVO;


@Controller
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	public Map retMap = new HashMap();
	
	@Resource(name="userDetailsService")
	private UserDetailsService userDetailService;
	
	public void initController(HttpServletRequest request, BaseVO vo){
//		vo.setClientCd(Constant.CLIENT_CD);
		vo.setRegDtm(DateUtil.getCurrentYmdHms());
		vo.setUpdDtm(DateUtil.getCurrentYmdHms());
		
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user != null && user instanceof UserDetails){
			vo.setRegMberId(((User)user).getUsername());
			vo.setUpdMberId(((User)user).getUsername());
		}
		
	}
	
	/**
	 * 자동로그인 처리
	 * 
	 * @Method      : autoLogin
	 * @author      : nptech
	 * @since       : 2018. 1. 2.
	 * @return      : void
	 * @sceneId     : 
	 * @sceneNm     : 
	 * --------------------------------------------------------
	 * Modification Information
	 * --------------------------------------------------------
	 * 날짜               수정자        변경사유
	 * 2018. 1. 2.    	nptech     	최초작성
	 */
	public Authentication autoLogin(HttpServletRequest request, MemberVO vo) throws Exception{
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(vo.getAuthorCode()));
		UserDetails userDetails = new User(vo.getMberId(), vo.getPassword(), true, true, true, true, authList);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, vo.getPassword(), userDetails.getAuthorities());
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		
		request.getSession().setAttribute("SS_MEMBER_VO", vo);
		
		return authentication;
	}
}
