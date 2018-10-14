package com.molt.core.constant;


/**
 * @Class Name  : ErrorCode.java
 * @Project     : TFM
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2013. 9. 24.           최초생성
 *
 * @version	  : 1.0.0.1
 * @since     : 2013. 9. 27.
 * @author    : TFM 개발팀
 * @see  	  : 에러 코드 관리를 위한 클래스
 */
public class ErrorCode {
	
	
	/*
	 * Common(1)
	 */
	public static final int RC_OK = 0;
	public static final int NULL_ERROR = 1;
	public static final int IO_ERROR = 2;
	public static final int UNKNOWN_ERROR = 5;
	public static final int PARAMETER_ERROR = 6;
	public static final int FILE_IO_ERROR = 7;
	public static final int FILE_NOT_FOUND = 8;
	
	public static final int NOT_VALID_ERROR = 9;

	/*
	 * 메뉴별 VALID ERROR Custom Error (범위: 540 ~ 559)
	 */
	public static final int CAR_TYPE_CHECK = 540;  //삭제시 해당 차량 유형이 존재할 경우
	public static final int RESERVED_STTUS_CD_CHECK = 541;  //차량예약 변경/취소시 상태값 확인
	public static final int USED_PLCY_CAR_CHECK = 542;  //수집 정책 삭제 시 이미 정책을 사용하는 차량이 있는 경우
	
	public static final int INVALID_DATA_CHECK = 543; // 엑셀 등록 시 잘못된 데이타가 있는 경우
	
	/*
	 * DB (범위: 560 ~ 589)
	 * */
	public static final int SQL_ERROR = 560;
	
	//DataIntegrityViolationException 
	//유일키 제약 위반과 같은 정합성 위반이 삽입이나 갱신의 결과로 발생한 경우
	public static final int SQL_ORA_CODE_1 = 561; 	
	
	//CleanupFailureDataAccessException 
	//성공적으로 작동한뒤 , 데이터베이스 자원을 반환할때 발생하는 경우
	public static final int SQL_ORA_CODE_2 = 562; 	
	
	//DataAccessResourceFailureException 
	//데이터베이스로의 연결 실패 등 완전하게 자원접근에 실패했을 경우
	public static final int SQL_ORA_CODE_3 = 563; 	
	
	//DataRetrievalFailureException 
	//기본키로 레코드를 찾지 못하는 경우와 같이 어떤 데이터를 가져오지 못하는 경우
	public static final int SQL_ORA_CODE_4 = 564; 	
	
	//IncorrectUpdateSemanticsDataAccessException 
	//원하는 수보다 많은 레코드를 갱신하는 등 갱신 작업에 있어서 의도하지 않은 어떤 일이 발생하는 경우, 트렌잭션이 롤백되지 않는다
	public static final int SQL_ORA_CODE_5 = 565; 	
	
	//InvalidDataAccessResourceUsageException 
	//잘못된 sql문법을 사용하는등 자원에 대한 접근이 잘못되었을 경우
	public static final int SQL_ORA_CODE_6 = 566; 	
	
	
	public static final Integer SUCESS = 1;
	public static final Integer FAIL = 2;
	public static final Integer DUPLICATE = 10; 
}
