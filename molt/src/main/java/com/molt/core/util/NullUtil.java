package com.molt.core.util;

import java.util.Map;

public class NullUtil {

    /**
     * Constructor for LNullUtils.
     */
    private NullUtil() {
        super();
    }

  
    /**-----------------------------------------------------------------------
     * @Method          isNone(String value)
     * @Description     입력값이 널인지 여부를 검사한다. 단 기본은 ""과 null을 모두 true로 리턴한다. 모든 DB Access메소드는
     *                  null체크가 필요한 경우에 이것을 이용한다.
     * @author 4gt152
     * @since 2013. 6. 20.
     * @param value String
     * @return boolean
     *------------------------------------------------------------------------
     * Modification Information
     *------------------------------------------------------------------------   
     * 수정일                             수정자             수정내용
     * ------------   ------   -----------------------------------------------
     * 2013. 7. 23.   4gt152   
     */	    
    public static boolean isNone(String value) {
        return value == null || value.length() == 0;
    }    

  
    /**-----------------------------------------------------------------------
     * @Method          notNone(String value)
     * @Description     입력값이 널인지 여부를 검사한다. 단 기본은 ""과 null을 모두 false로 리턴한다. 모든 DB Access메소드는
     *                  null체크가 필요한 경우에 이것을 이용한다.
     * @author 4gt152
     * @since 2013. 6. 20.
     * @param value String
     * @return boolean
     *------------------------------------------------------------------------
     * Modification Information
     *------------------------------------------------------------------------   
     * 수정일                             수정자             수정내용
     * ------------   ------   -----------------------------------------------
     * 2013. 7. 23.   4gt152   
     */	       
    public static boolean notNone(String value) {
        return value != null && value.length() > 0;
    }
    


    /**-----------------------------------------------------------------------
     * @Method          getMapString(Map map, String key, String defaultValue)
     * @Description     입력값이 널인지 여부를 검사한다. 단 기본은 ""과 null을 모두 false로 리턴한다. 모든 DB Access메소드는
     *                  null체크가 필요한 경우에 이것을 이용한다.
     * @author 4gt152
     * @since 2013. 6. 20.
     * @param map Map
     * @param key key
     * @param defaultValue 없을시 기본값 
     * @return String value
     *------------------------------------------------------------------------
     * Modification Information
     *------------------------------------------------------------------------   
     * 수정일                             수정자             수정내용
     * ------------   ------   -----------------------------------------------
     * 2013. 7. 23.   4gt152   
     */	       
    public static String getMapString(Map map, String key, String defaultValue) {
    	
    	if(!map.containsKey(key)) return defaultValue;
    	else if(map.get(key) == null || map.get(key).equals("")) return defaultValue;
    	else return (String)map.get(key);
    }
}