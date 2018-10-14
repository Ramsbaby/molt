package com.molt.core.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.molt.core.vo.BaseAttchVO;


/**
 * @Project : 2017년 교육관리시스템 재구축 사업
 * @Class : FileUtil.java
 * @Package : ems.framework.util
 * @Description : 
 * @author : 구정운
 * @since : 2017. 09. 13.
 * @version : 1.0
 *
 * @Copyright (c) 2017년 교육관리시스템 재구축 사업
 * --------------------------------------------------------------------------------------------
 * Modification Information
 * --------------------------------------------------------------------------------------------
 *     날짜              	수정자             		변경사유
 * --------------------------------------------------------------------------------------------
 * 2017.09.13   	구정운        		최초작성
 */
@Component("fileUtil")
public class FileUtil {
	
	@Value("#{moltProp['fileupload.base']}")
	private String uploadBasePath;
	
	/**
	 * 업로드된 파일리스트 처리
	 * @Method      : parseInsertFileInfo
	 * @author      : 구정운     
	 * @since       : 2017. 9. 14.
	 * @return      : List<BaseAttchVo>
	 */
	public List<BaseAttchVO> parseInsertFileInfo(BaseAttchVO vo, MultipartHttpServletRequest multipartHttpServletRequest, String filePath) throws Exception {
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		String fileUrl = null;
		String fileSize = null;
		String ntarSrno = null;
		
		List<BaseAttchVO> fileList = new ArrayList<BaseAttchVO>();
		BaseAttchVO FileVo = null;
		
		if(vo.getNtarSrno() != null){			
			ntarSrno = vo.getNtarSrno();
		}
		
		File file = new File(uploadBasePath+filePath);
		if(!file.exists()){
			file.mkdirs();
		}
			
		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){
				
				// 원본 파일명
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				// 저장파일명
				Date toDay = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String fileName = vo.getSysTpcd() + "-" + sdf.format(toDay)+ "-" + StringUtil.randomGen(5);
				
				if(!filePath.endsWith("/")){
					filePath = filePath + "/";
				}
				storedFileName = uploadBasePath + filePath + fileName + originalFileExtension;
				fileUrl = filePath + fileName + originalFileExtension;
				fileSize=Long.toString(multipartFile.getSize());
				
				file = new File(storedFileName);
				multipartFile.transferTo(file);
				
				FileVo = new BaseAttchVO();	
				FileVo.setFileStreCours(storedFileName);
				FileVo.setStreFileNm(fileName + originalFileExtension);
				FileVo.setOrignlFileNm(originalFileName);
				FileVo.setFileExtsn(originalFileExtension);
				FileVo.setFileSize(fileSize);
				
				fileList.add(FileVo);
			}
		}
		
		return fileList;
	}
	
	
	
	/**
	 * 
	 * @Method      : fileDownolad
	 * @author      : 구정운
	 * @since       : 2017. 9. 14.
	 * @return      : void
	 * @sceneId     : 
	 * @sceneNm     : 
	 * --------------------------------------------------------
	 * Modification Information
	 * --------------------------------------------------------
	 * 날짜               수정자        변경사유
	 * 2017. 9. 14.    구정운   최초작성
	 */
	public void fileDownload(BaseAttchVO vo, HttpServletResponse response) throws IOException{
		String storedFileName = vo.getFileStreCours();
		String originalFileName = vo.getOrignlFileNm();
		
		if (storedFileName != null && originalFileName != null && !storedFileName.isEmpty() && !originalFileName.isEmpty()){
			try {
				File tgtFile = new File (storedFileName);
				
				if (tgtFile.exists()) {
					byte fileByte[] = FileUtils.readFileToByteArray(tgtFile);
					
					response.setContentType("application/octet-stream");
					response.setContentLength(fileByte.length);
					response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(originalFileName,"UTF-8")+"\";");
					response.setHeader("Content-Transfer-Encoding", "binary");
					response.getOutputStream().write(fileByte);
					
					response.getOutputStream().flush();
					response.getOutputStream().close();
				} else {
					try {
						response.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().print("<script>alert('파일이 존재하지 않습니다.');history.back();</script>");
					} catch (IOException e) {

					} catch(Exception e) {
						
					}
				}
				
			} catch (Exception e) {
				// e.printStackTrace(System.out);
			}
		}

	}

	/**
	 * 
	 * @Method      : deleteFileInfo
	 * @author      : 구정운
	 * @since       : 2017. 10. 23.
	 * @return      : void
	 * @sceneId     : 
	 * @sceneNm     : 
	 * --------------------------------------------------------
	 * Modification Information
	 * --------------------------------------------------------
	 * 날짜               수정자        변경사유
	 * 2017. 10. 23.    	구정운     	최초작성
	 */
	public void deleteFileInfo(BaseAttchVO vo) {
		String storedFileName = vo.getAttchFileSorgNm();
		File tgtFile = new File (uploadBasePath + storedFileName);
		try{			
			if(tgtFile.exists() == true){
				tgtFile.delete();
			}
		} catch(NullPointerException e) {
			// e.printStackTrace(System.out);
		} catch(Exception e){
			
		}
	}
	
	/**
	 * 멀티파트 파일을 넘겨주면 fileVo를 반환한다. 
	 * 하나의 화면에서 두영역의 파일을 올려줄때 각 영역의 파일을 구분해서 업로드할 필요가 있을때 사용.
	 * @Method      : MultipartFileInfo
	 * @author      : 구정운
	 * @since       : 2017. 10. 30.
	 * @return      : BaseAttchVo
	 * @sceneId     : 
	 * @sceneNm     : 
	 * --------------------------------------------------------
	 * Modification Information
	 * --------------------------------------------------------
	 * 날짜               수정자        변경사유
	 * 2017. 10. 23.    	구정운     	최초작성
	 */
	public BaseAttchVO MultipartFileInfo(BaseAttchVO vo, MultipartFile mf, String filePath) {
		File file = new File(uploadBasePath+filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		BaseAttchVO FileVo = null;
		String ntarSrno = null;
		if(vo.getNtarSrno() != null){			
			ntarSrno = vo.getNtarSrno();
		}
		
		try{			
			// 원본 파일명
			String originalFileName = mf.getOriginalFilename();
			String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			
			// 저장파일명
			Date toDay = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fileName = vo.getSysTpcd() + "-" + sdf.format(toDay)+ "-" + StringUtil.randomGen(5);
			
			if(!filePath.endsWith("/")){
				filePath = filePath + "/";
			}
			String storedFileName = uploadBasePath + filePath + fileName + originalFileExtension;
			String fileUrl = filePath + fileName + originalFileExtension;
			String fileSize=Long.toString(mf.getSize());
			
			file = new File(storedFileName);
			mf.transferTo(file);
			
			FileVo = new BaseAttchVO();
			FileVo.setNtarSrno(ntarSrno);
			FileVo.setAttchFileNm(originalFileName);
			FileVo.setAttchFileSorgNm(fileUrl);
			FileVo.setAttchFileSize(fileSize);
		} catch(NullPointerException e) {
		
			// e.printStackTrace(System.out);
		} catch (IllegalStateException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		return FileVo;
	}
}
