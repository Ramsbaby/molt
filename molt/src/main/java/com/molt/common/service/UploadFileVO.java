package com.molt.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.molt.common.ComJqgridVO;

public class UploadFileVO extends ComJqgridVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2004457133303798340L;
	
	private String uploadFilePath;
	private MultipartFile uploadFile;
	private String uploadUrl;
	
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadUrl() {
		return uploadUrl;
	}
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}
	
}
