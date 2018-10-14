package com.molt.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 *
 *    수정일        수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 9.   이삼섭         최초생성
 *    2011. 7. 1.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
public class WebLogInterceptor extends HandlerInterceptorAdapter {

//	@Resource(name="webLogService")
//	private WebLogService webLogService;

	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler 
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/*WebLog webLog = new WebLog();
		String reqURL = request.getRequestURI();
		String uniqId = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)){
			webLog.setRqesterId(authentication.getName());
		}

		Map<String, String> bodyMap = new HashMap<String, String>();

		webLog.setUrl(reqURL);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof User){
			webLog.setRqesterId(((User)principal).getUsername());
		}
		
		webLog.setRqesterIp(request.getRemoteAddr());
		webLogService.logInsertWebLog(webLog);*/
		
		return true;
		
	}
}
