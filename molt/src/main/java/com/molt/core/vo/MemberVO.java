package com.molt.core.vo;

@SuppressWarnings("serial")
public class MemberVO extends BaseVO{

	private String mberKey;				// 회원KEY
	private String mberId;				// 회원아이디
	private String mberNm;				// 회원이름
	private String corpNm;				// 기관명
	private String password;			// 비밀번호
	private String mberSttus;			// 회원상태
	private String mberType;			// 회원유형
	private String postNum;				// 우편번호
	private String adres;				// 주소
	private String detailAdres;			// 상세주소
	private String email;				// 이메일
	private String newsYn;				// 뉴스레터 수신 여부
	private String mobile;				// 핸드폰번호
	private String smsYn;				// 문자 수신여부
	private String authorCode;			// 권한코드
	private String picUrl;				// 사진이미지URL
	private String picOrgNm;			// 사진원본이름
	private String certCn;				// 공인인증서
	
	private String fcltNum;				// 시설번호
	private String coRegNum;			// 사업자등록번호
	
	public String getMberKey() {
		return mberKey;
	}
	public void setMberKey(String mberKey) {
		this.mberKey = mberKey;
	}
	public String getMberId() {
		return mberId;
	}
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	public String getMberNm() {
		return mberNm;
	}
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMberSttus() {
		return mberSttus;
	}
	public void setMberSttus(String mberSttus) {
		this.mberSttus = mberSttus;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getDetailAdres() {
		return detailAdres;
	}
	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicOrgNm() {
		return picOrgNm;
	}
	public void setPicOrgNm(String picOrgNm) {
		this.picOrgNm = picOrgNm;
	}
	public String getMberType() {
		return mberType;
	}
	public void setMberType(String mberType) {
		this.mberType = mberType;
	}
	public String getNewsYn() {
		return newsYn;
	}
	public void setNewsYn(String newsYn) {
		this.newsYn = newsYn;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getCertCn() {
		return certCn;
	}
	public void setCertCn(String certCn) {
		this.certCn = certCn;
	}
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	public String getFcltNum() {
		return fcltNum;
	}
	public void setFcltNum(String fcltNum) {
		this.fcltNum = fcltNum;
	}
	public String getCoRegNum() {
		return coRegNum;
	}
	public void setCoRegNum(String coRegNum) {
		this.coRegNum = coRegNum;
	}
}
