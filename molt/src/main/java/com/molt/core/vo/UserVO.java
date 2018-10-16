package com.molt.core.vo;

@SuppressWarnings("serial")
public class UserVO extends BaseVO{

	private String userId;				// 회원아이디
	private String userNm;				// 회원이름
	private String userPwd;				// 회원비밀번호
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
