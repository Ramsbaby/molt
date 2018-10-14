package com.molt.core.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.util.CookieGenerator;

import com.molt.core.vo.MemberVO;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private MberService mberService;
	
//	@Resource(name="webLogService")
//	private WebLogService webLogService;

	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private String targetUrlParameter;
	private String defaultUrl;
	private boolean useReferer;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public CustomAuthenticationSuccessHandler(){
		targetUrlParameter = "";
		defaultUrl = "/";
		useReferer = false;
	}
	
	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}



	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}



	public String getDefaultUrl() {
		return defaultUrl;
	}



	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}



	public boolean isUseReferer() {
		return useReferer;
	}



	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}

	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails)authentication.getDetails();
		String saveYn = details.getSaveYn();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		/**
		 * ID저장여부 쿠키 생성
		 */
		if("Y".equals(saveYn)){
			CookieGenerator cookieGer = new CookieGenerator();
			cookieGer.setCookieMaxAge(60*60*24*365);
			cookieGer.setCookieName("SS_MBER_ID");
			cookieGer.addCookie(response, ((User)principal).getUsername());
		}else{
			CookieGenerator cookieGer = new CookieGenerator();
			cookieGer.setCookieName("SS_MBER_ID");
			cookieGer.setCookieMaxAge(-1);
			cookieGer.addCookie(response, request.getParameter(""));
		}
		
		
		/**
		 * 사용자정보 세션에 저장
		 */
		try{
			Map paramMap = new HashMap();
			if(principal instanceof User){
				paramMap.put("mberId", ((User)principal).getUsername());
//				paramMap.put("clientCd", Constant.CLIENT_CD);
//				MemberVO memberVO = mberService.selectMber(paramMap);
				MemberVO memberVO = null;
				
				if(memberVO != null){
					request.getSession().setAttribute("SS_MEMBER_VO", memberVO);
				}
				
				/**
				 * 로그인정보 저장
				 */
//				WebLog webLog = new WebLog();
//				webLog.setRqesterId(((User)principal).getUsername());
//				webLog.setUrl("/login.ok");
//				webLog.setRqesterIp(request.getRemoteAddr());
				try {
//					webLogService.logInsertWebLog(webLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			defaultUrl = "/";
			
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		
		}catch(Exception e){}
	}
}

