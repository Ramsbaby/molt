package com.molt.core.vo;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Project : 2017년 교육관리시스템 재구축 사업
 * @Class : 
 * @Package : 
 * @Description : TB_EMS003M      
 * @author : okssanta
 * @since : 2017. 08. 01.
 * @version : 1.0
 *
 * @Copyright (c) 2017년 교육관리시스템 재구축 사업
 * --------------------------------------------------------------------------------------------
 * Modification Information
 * --------------------------------------------------------------------------------------------
 *     날짜              	수정자             		변경사유
 * --------------------------------------------------------------------------------------------
 * 2017.08.01   okssanta        최초작성
 */

@SuppressWarnings("serial")
public class BaseAttchVO extends BaseVO implements Serializable {
	
	private String ntarSrno;			// 첨부파일번호
	private String sysTpcd;				// URL의 2depth 코드 (/cou/opb/registerOptrBlbrForm.do -> opb), 
										// 파일업로드시 파일명생성할때 사용함.

	private String attchFileNo;			// 첨부파일번호
	private String attchFileNm;			// 첨부파일명
	private String attchFileSorgNm;		// 첨부파일저장명
	private String attchFileTpcd;		// 첨부파일구분코드
	private String attchImgeFileSorgNm;	// 첨부이미지저장명
	private String attchFileSize;		// 첨부파일크기

	private String fileSeq;				// 첨부파일SEQ
	private String fileStreCours;		// 첨부파일풀경로
	private String streFileNm; 			// 첨부파일저장명
	private String orignlFileNm; 		// 첨부파일명
	private String fileExtsn;			// 첨부파일확장자 
	private String fileSize;			// 첨부파일크기
	
	public String getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getFileStreCours() {
		return fileStreCours;
	}

	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	
	public BaseAttchVO(){
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String[] parse = req.getRequestURI().split("/");
		
		if(parse.length >= 2){
			setSysTpcd(parse[1].toUpperCase());
		}else if(parse.length >= 1){
			setSysTpcd(parse[0].toUpperCase());
		}else{
			setSysTpcd("EMS");
		}
	}

	public String getSysTpcd() {
		return sysTpcd;
	}

	public void setSysTpcd(String sysTpcd) {
		this.sysTpcd = sysTpcd;
	}

	public String getNtarSrno() {
		return ntarSrno;
	}

	public void setNtarSrno(String ntarSrno) {
		this.ntarSrno = ntarSrno;
	}

	public String getAttchFileNm() {
		return attchFileNm;
	}

	public void setAttchFileNm(String attchFileNm) {
		this.attchFileNm = attchFileNm;
	}

	public String getAttchFileSorgNm() {
		return attchFileSorgNm;
	}

	public void setAttchFileSorgNm(String attchFileSorgNm) {
		this.attchFileSorgNm = attchFileSorgNm;
	}

	public String getAttchFileSize() {
		return attchFileSize;
	}

	public void setAttchFileSize(String attchFileSize) {
		this.attchFileSize = attchFileSize;
	}

	public String getAttchFileNo() {
		return attchFileNo;
	}

	public void setAttchFileNo(String attchFileNo) {
		this.attchFileNo = attchFileNo;
	}

	public String getAttchImgeFileSorgNm() {
		return attchImgeFileSorgNm;
	}

	public void setAttchImgeFileSorgNm(String attchImgeFileSorgNm) {
		this.attchImgeFileSorgNm = attchImgeFileSorgNm;
	}

	public String getAttchFileTpcd() {
		return attchFileTpcd;
	}

	public void setAttchFileTpcd(String attchFileTpcd) {
		this.attchFileTpcd = attchFileTpcd;
	}
	
	

}
