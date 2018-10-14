package com.molt.core.vo;

import java.io.Serializable;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 사용자정보 VO클래스로서일반회원, 기업회원, 업무사용자의  비지니스로직 처리시 기타조건성 항을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BaseVO extends PaginationInfo implements Serializable {

	private String clientCd;		// 고객사코드
	
	private String regDtm;			// 등록일시
	private String regMberId;		// 등록자
	private String regMberNm;		// 등록자명
	
	private String updDtm;			// 수정일시
	private String updMberId;		// 수정자
	private String updMberNm;		// 수정자명
	
	private String mberPicUrl;		// 로그인사용자의 이미지URL
	private String hstIp;			// Access IP
	
	private String searchCol;			// 검색컬럼
	private String searchVal;			// 검색값
	
	/**
	 * 페이징
	 */
	private String rows;			// 레코드개수
	private String startNum;		// 페이징 시작번호
	
	
	public BaseVO(){
		super.setRecordCountPerPage(10);
		super.setPageSize(10);
		super.setCurrentPageNo(1);
	}
	
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public String getRegMberId() {
		return regMberId;
	}
	public void setRegMberId(String regMberId) {
		this.regMberId = regMberId;
	}
	public String getUpdDtm() {
		return updDtm;
	}
	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}
	public String getRegMberNm() {
		return regMberNm;
	}
	public void setRegMberNm(String regMberNm) {
		this.regMberNm = regMberNm;
	}
	public String getUpdMberNm() {
		return updMberNm;
	}
	public void setUpdMberNm(String updMberNm) {
		this.updMberNm = updMberNm;
	}
	public String getUpdMberId() {
		return updMberId;
	}
	public void setUpdMberId(String updMberId) {
		this.updMberId = updMberId;
	}
	public String getMberPicUrl() {
		return mberPicUrl;
	}
	public void setMberPicUrl(String mberPicUrl) {
		this.mberPicUrl = mberPicUrl;
	}
	public String getClientCd() {
		return clientCd;
	}
	public void setClientCd(String clientCd) {
		this.clientCd = clientCd;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getHstIp() {
		return hstIp;
	}
	public void setHstIp(String hstIp) {
		this.hstIp = hstIp;
	}
	public String getSearchCol() {
		return searchCol;
	}
	public void setSearchCol(String searchCol) {
		this.searchCol = searchCol;
	}
	public String getSearchVal() {
		return searchVal;
	}
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}


}
