package com.molt.front.mypage.web;

import com.molt.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 메인클래스
 * 
 * @author okssanta
 *
 */

@Controller
public class MypageController extends BaseController {
	
	/**
	 * 로그인
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mypage.ok", method={RequestMethod.GET, RequestMethod.POST})
    public String mypage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "front/mypage/mypage";
    }
}
