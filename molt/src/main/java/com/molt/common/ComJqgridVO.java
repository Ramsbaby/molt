package com.molt.common;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : ComDefaultVO.java
 * @Description : ComDefaultVO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    조재영         최초 생성
 *
 *  @author 공통서비스 개발팀 조재영
 *  @since 2009.02.01
 *  @version 1.0
 *  @see 
 *  
 */

@SuppressWarnings("serial")
public class ComJqgridVO implements Serializable, Cloneable {

	/** 고객사코드 */
	private String clientCd;
	private String clientNm;
	
	
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    private String sidx;
    private String sord;
    private int rows;
    private int page = 1;
    private int startNum;

    /** recordCountPerPage */
    private int recordCountPerPage = 30;
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";  
    
    private String crud;
    
    private String mberPicUrl;
    
    private String mberKey;
    
	/** 등록자ID */
	private String regMberId;
	
	private String regMberkey;
	
	/** 등록자이름 */
	private String regMberNm;
	
	/** 등록일시 */
	private String regDtm;
	private String regDt;
	
	/** 수정자ID */
	private String updMberId;
	
	/** 수정자이름 */
	private String updMberNm;
	
	/** 수정일시 */
	private String updDtm;
	
	private String hstAction;
	private String hstIp;
    
	String downloadToken;		// 쿠키토큰
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getDownloadToken() {
		return downloadToken;
	}
	public void setDownloadToken(String downloadToken) {
		this.downloadToken = downloadToken;
	}
	
	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public String getHstAction() {
		return hstAction;
	}

	public void setHstAction(String hstAction) {
		this.hstAction = hstAction;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStartNum() {
		return (page-1)*rows;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public EgovPropertyService getPropertiesService() {
		return propertiesService;
	}

	public void setPropertiesService(EgovPropertyService propertiesService) {
		this.propertiesService = propertiesService;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    
    /**
	 * searchKeywordFrom attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * @param searchKeywordFrom String
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * @param searchKeywordTo String
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}

	public String getRegMberId() {
		return regMberId;
	}

	public void setRegMberId(String regMberId) {
		this.regMberId = regMberId;
	}

	public String getRegMberNm() {
		return regMberNm;
	}

	public void setRegMberNm(String regMberNm) {
		this.regMberNm = regMberNm;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public String getUpdMberId() {
		return updMberId;
	}

	public void setUpdMberId(String updMberId) {
		this.updMberId = updMberId;
	}

	public String getUpdMberNm() {
		return updMberNm;
	}

	public void setUpdMberNm(String updMberNm) {
		this.updMberNm = updMberNm;
	}

	public String getUpdDtm() {
		return updDtm;
	}

	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

	public String getClientCd() {
		return clientCd;
	}

	public void setClientCd(String clientCd) {
		this.clientCd = clientCd;
	}

	public String getClientNm() {
		return clientNm;
	}

	public void setClientNm(String clientNm) {
		this.clientNm = clientNm;
	}

	public String getHstIp() {
		return hstIp;
	}

	public void setHstIp(String hstIp) {
		this.hstIp = hstIp;
	}

	public String getRegMberkey() {
		return regMberkey;
	}

	public void setRegMberkey(String regMberkey) {
		this.regMberkey = regMberkey;
	}

	public String getMberPicUrl() {
		return mberPicUrl;
	}

	public void setMberPicUrl(String mberPicUrl) {
		this.mberPicUrl = mberPicUrl;
	}

	public String getMberKey() {
		return mberKey;
	}

	public void setMberKey(String mberKey) {
		this.mberKey = mberKey;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
}
