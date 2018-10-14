package com.molt.common.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.molt.BaseController;
import com.molt.common.service.UploadFileVO;
import com.molt.core.util.DateUtil;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------E
 *   2009.3.19  이삼섭          최초 생성
 *   2009.06.29	한성곤			2단계 기능 추가 (댓글관리, 만족도조사)
 *   2011.07.01 안민정		 	댓글, 스크랩, 만족도 조사 기능의 종속성 제거
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2011.09.07 서준식           유효 게시판 게시일 지나도 게시물이 조회되던 오류 수정
 * </pre>
 */


@Controller
public class CommonController extends BaseController{
	
	@Value("#{property['upload.bbs.image.dir']}")
    private String UPLOAD_BBS_IMAGE_DIR;
	
	/**
	 * 게시물에 대한 목록을 조회한다.
	 * 
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload/image.ok")
	public String selectBoardArticles(
			@ModelAttribute("uploadFileVO") UploadFileVO uploadFileVO
			, HttpSession session
			, HttpServletRequest request
    		, HttpServletResponse response
    		, ModelMap model) throws Exception {
		
		String toDay = DateUtil.getCurrentYmd();
		MultipartFile uploadFile = uploadFileVO.getUploadFile();
		
		// 확장자명 
		String srcFileName = uploadFile.getOriginalFilename();
		
		if(!"".equals(srcFileName)){
			int extPoint = srcFileName.lastIndexOf(".");
			String extName = "";
			if(extPoint > 0){
				extName = srcFileName.substring(extPoint + 1);
			}
			
			if (uploadFile != null) {
				
				String uploadPath = session.getServletContext().getRealPath(UPLOAD_BBS_IMAGE_DIR + toDay);
				String targetFileName = DateUtil.getTimeStamp() + "." + extName;
				String uploadUrl = UPLOAD_BBS_IMAGE_DIR + toDay + "/" + targetFileName;
				
				File uploadDir = new File(uploadPath);
				if(!uploadDir.exists()){
					uploadDir.mkdirs();
				}
				
				try {
					
					File targetImgFile = new File(uploadPath + "/" + targetFileName);
					uploadFile.transferTo(targetImgFile);
					model.put("uploadUrl", uploadUrl);
					model.put("result", "SUCCESS");
					
				} catch (IOException e) {
					model.put("result", "FAIL");
					model.put("resultMessage", e.getMessage());
					
				} 
			}else{
				model.put("result", "FAIL");
				model.put("resultMessage", "file is not empty!!");
			}
		}
		
		return "/common/UploadImageDone";
	}
	
}
