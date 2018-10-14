package com.molt.core.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DispatcherJsonBean implements Serializable{

	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = -3427800270262453961L;
	
	private Map<String, Object> data;


	public DispatcherJsonBean(){
		data = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getMap() {
		return data;
	}
	public void setList(Map<String, Object> map) {
		this.data = data;
	}
	
	
}