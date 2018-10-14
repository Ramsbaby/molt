/**
 * 
 */
package com.molt.core.message;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;


/**-----------------------------------------------------------------------
 * @Class MessageDao.java
 * @Description WAS 기동시에 DB에 있는 메시지를 읽어 오는 dao 클래스
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
@Repository("messageDao")
public class MessageDao extends EgovAbstractMapper{
	
    /**-----------------------------------------------------------------------
     * @Method selectMessage
     * @Description 메시지를 db에서 읽어 올 때 필요한 쿼리를 호출하는 dao
     * @author : 조준
     * @since 2013. 8. 23.
     * 
     * @param 
     * @return List
     * @throws Exception
     *------------------------------------------------------------------------
     * Modification Information
     *------------------------------------------------------------------------   
     * 수정일         수정자       수정내용
     * ----------  ---------   -----------------------------------------------
     * 2013. 8. 23.  조준       최초생성
     */	
    public List selectMessage () throws Exception {
    	Message message = new Message();
        return selectList("MessageDao_selectMessage", message);
    }

}
