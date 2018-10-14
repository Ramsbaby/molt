/**
 * 
 */
package com.molt.core.util;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.rte.fdl.property.EgovPropertyService;

/**-----------------------------------------------------------------------
 * @Class JsonView.java
 * @Description Json형태의 메시지를 rendering 하는 클래스
 * @author 조준
 * @since 2013. 8. 23.
 * @version 1.0
 * 
 * @Copyright (c) 2013 4세대 국가관세종합정보망 , LG CNS 컨소시엄 All rights reserved.
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------   
 * 수정일         수정자       수정내용
 * ----------  ---------   -----------------------------------------------
 * 2013. 8. 23.  조준       최초생성
 */
public class JsonView extends MappingJackson2JsonView {	


	private final ObjectMapper objectMapper;

	private final JsonEncoding encoding;

	private final boolean prefixJson;

	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
	public JsonView() {
		setContentType("text/html");
		
		objectMapper = new ObjectMapper();
		encoding = JsonEncoding.UTF8;
		prefixJson = false;
	}

	/**-----------------------------------------------------------------------
	 * @Method renderMergedOutputModel
	 * @Description json형태의 데이터를 화면에 전달하는 메소드
	 * @author : 조준
	 * @since 2013. 8. 23.
	 * 
	 * @param Map model
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return 
	 * @throws Exception
	 *------------------------------------------------------------------------
	 * Modification Information
	 *------------------------------------------------------------------------   
	 * 수정일         수정자       수정내용
	 * ----------  ---------   -----------------------------------------------
	 * 2013. 8. 23.  조준       최초생성
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void renderMergedOutputModel(Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String header = StringUtil.nullConvert(request.getHeader("X-Requested-With"));
		
		if(!header.equals("XMLHttpRequest")) {
			// submit
			ArrayList<String> saveTokenList = null;
			String reqTokenKey = "";
			String sessionTokenVal = null;
			
			if(NullUtil.isNone(request.getParameter("savedToken"))){
				reqTokenKey = (String) SessionUtil.getSession("savedToken");
			} else {
				reqTokenKey = request.getParameter("savedToken");
			}
			
			// 파라메터에 savedToken이 존재하고 savedToken이 null이 아닐때
			if(!NullUtil.isNone(reqTokenKey)){
				saveTokenList = (ArrayList<String>)SessionUtil.getSession("saveTokenList");
				if(saveTokenList != null && saveTokenList.size() > 0) {
					for(int i = 0 ; i < saveTokenList.size() ; i++ ){
						String sessionTokenKey = saveTokenList.get(i).split(":")[0];
						if(reqTokenKey.equals(sessionTokenKey)) {
							sessionTokenVal = saveTokenList.get(i).split(":")[1];
							
							model.put(sessionTokenKey, sessionTokenVal);
							break;
						}
					}			
				}
			}
		}
		
//		response.setContentType("text/html ; charset=UTF-8");
//		OutputStream stream = response.getOutputStream();
//		Object value = filterModel(model);
//		writeContent(stream, value);
		
		Object value = filterModel(model);

		if(!header.equals("XMLHttpRequest") && request instanceof MultipartHttpServletRequest) { 
			String jsonValue = "<html><head><script type=\"text/javascript\">document.domain = \"customs.go.kr\";</script></head><body>" + objectMapper.writeValueAsString(value) + "</body></html>";
			response.setContentType("text/html ; charset=UTF-8");
			//response.addHeader("X-Frame-Options", "SAMEORIGIN"); 
			
			ServletOutputStream out=response.getOutputStream();
			out.write(jsonValue.getBytes("utf-8"));
			//out.flush();
			out.close();
		} else {
			JsonGenerator generator =
					objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), encoding);
			if (prefixJson) {
				generator.writeRaw("{} && ");
			}
			objectMapper.writeValue(generator, value);			
		}
	}
}