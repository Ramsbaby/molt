package com.molt.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ChineseCalendar;




/**
 * 음력 양력등 Date에 관한 Util
 * @author 임광규
 *
 */
public class DateUtil {

	
	/**
	 * 양력 (yyyymmdd) 날짜를 음력 날짜로 변환 해줌
	 * 
	 * @param yyyymmdd
	 * @return
	 */
	public static synchronized String toLunar(String yyyymmdd) {
		// default TimeZone, Locale을 사용
		Calendar cal = Calendar.getInstance(); // 양력 달력
		ChineseCalendar cc = new ChineseCalendar(); // 음력 달력

		if (yyyymmdd == null)
			return "";

		String date = yyyymmdd.trim();
		if (date.length() != 8) {
			if (date.length() == 4)
				date = date + "0101";
			else if (date.length() == 6)
				date = date + "01";
			else if (date.length() > 8)
				date = date.substring(0, 8);
			else
				return "";
		}

		cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

		cc.setTimeInMillis(cal.getTimeInMillis());

		// ChinessCalendar.YEAR 는 1~60 까지의 값만 가지고 ,
		// ChinessCalendar.EXTENDED_YEAR 는 Calendar.YEAR 값과 2637 만큼의 차이를 가집니다.
		int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
		int m = cc.get(ChineseCalendar.MONTH) + 1;
		int d = cc.get(ChineseCalendar.DAY_OF_MONTH);

		StringBuffer ret = new StringBuffer();
		if (y < 1000)
			ret.append("0");
		else if (y < 100)
			ret.append("00");
		else if (y < 10)
			ret.append("000");
		ret.append(y);

		if (m < 10)
			ret.append("0");
		ret.append(m);

		if (d < 10)
			ret.append("0");
		ret.append(d);

		return ret.toString();
	}

	/**
	 * 음력 날자를 양력 날짜로 변환
	 * 
	 * @param yyyymmdd
	 * @return
	 */
	public static synchronized String fromLunar(String yyyymmdd) {
		// default TimeZone, Locale을 사용
		Calendar cal = Calendar.getInstance(); // 양력 달력
		ChineseCalendar cc = new ChineseCalendar(); // 음력 달력

		if (yyyymmdd == null)
			return "";

		String date = yyyymmdd.trim();
		if (date.length() != 8) {
			if (date.length() == 4)
				date = date + "0101";
			else if (date.length() == 6)
				date = date + "01";
			else if (date.length() > 8)
				date = date.substring(0, 8);
			else
				return "";
		}

		cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(
				0, 4)) + 2637);
		cc.set(ChineseCalendar.MONTH,
				Integer.parseInt(date.substring(4, 6)) - 1);
		cc.set(ChineseCalendar.DAY_OF_MONTH, Integer
				.parseInt(date.substring(6)));

		cal.setTimeInMillis(cc.getTimeInMillis());

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DAY_OF_MONTH);

		StringBuffer ret = new StringBuffer();
		if (y < 1000)
			ret.append("0");
		else if (y < 100)
			ret.append("00");
		else if (y < 10)
			ret.append("000");
		ret.append(y);

		if (m < 10)
			ret.append("0");
		ret.append(m);

		if (d < 10)
			ret.append("0");
		ret.append(d);

		return ret.toString();
	}

	/**
	 * 지금 날짜를 return 한다. 형식은 yyyymmdd
	 * 
	 * @return
	 */
	public static synchronized String toDate() {
		Calendar cal = Calendar.getInstance();
		String month = "";
		String day = "";
		if ((cal.get(Calendar.MONTH) + 1) < 10) {
			month = "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}

		if (cal.get(Calendar.DATE) < 10) {
			day = "0" + cal.get(Calendar.DATE);

		} else {
			day = cal.get(Calendar.DATE) + "";
		}
		return cal.get(Calendar.YEAR) + month + day;
	}
	
	/**
	 * 지금 날짜와 시간까지 를 return 한다. 형식은 yyyymmddhh
	 * 
	 * @return
	 */
	public static synchronized String toDateHour() {
		Calendar cal = Calendar.getInstance();
		String month = "";
		String day = "";
		String hour = "";
		
		if ((cal.get(Calendar.MONTH) + 1) < 10) {
			month = "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}

		if (cal.get(Calendar.DATE) < 10) {
			day = "0" + cal.get(Calendar.DATE);

		} else {
			day = cal.get(Calendar.DATE) + "";
		}
		
		if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
			hour = "0" + cal.get(Calendar.HOUR_OF_DAY);

		} else {
			hour = cal.get(Calendar.HOUR_OF_DAY) + "";
		}
		return cal.get(Calendar.YEAR) + month + day + hour;
	}

	/**
	 * 현재 날짜를 기준으로 section에서 나온 것을 return 한다.
	 * 종류는 yyyy, mm, dd, yyyyMMddHHmmss, yyyyMMdd, yyyy(MM-1), 
	 * HHmmss (전달) 이다. 
	 * @param section
	 * @return
	 */
	public static synchronized String toDate(String section){
		Calendar cal = Calendar.getInstance();
		String month = "";
		String day = "";
		if ((cal.get(Calendar.MONTH) + 1) < 10) {
			month = "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			month = (cal.get(Calendar.MONTH) + 1) + "";
		}

		if (cal.get(Calendar.DATE) < 10) {
			day = "0" + cal.get(Calendar.DATE);

		} else {
			day = cal.get(Calendar.DATE) + "";
		}
		
		if("yyyy".equals(section)){
			return cal.get(Calendar.YEAR)+"";
		}else if("mm".equals(section)){
			return month;
		}else if("dd".equals(section)){
			return day;
		}else if("yyyyMMddHHmmss".equals(section)){
			DateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			return f.format(new Date());
		}else if("yyyyMMdd".equals(section)){
			DateFormat f = new SimpleDateFormat("yyyyMMdd");
			return f.format(new Date());
		}else if("yyyy(MM-1)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -1);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-2)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -2);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-3)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -3);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-4)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -4);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-5)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -5);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-6)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -6);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-7)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -7);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-8)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -8);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-9)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -9);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-10)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -10);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-11)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -11);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}else if("yyyy(MM-12)".equals(section)){
			//달을 전달로 만듬
			cal.add(Calendar.MONTH, -12);
			
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + (cal.get(Calendar.MONTH) + 1);
			} else {
				month = (cal.get(Calendar.MONTH) + 1) + "";
			}
			return cal.get(Calendar.YEAR)+month;
		}
			
		return "";
	}
	/**
	 * String 형식의 날자를 Calendar 로 변환 해준다.
	 * 
	 * @param yyyymmdd
	 * @return
	 */
	public static synchronized Calendar converterDate(String yyyymmdd) {
		Calendar cal = Calendar.getInstance(); // 양력 달력
		if (yyyymmdd == null)
			return cal;

		String date = yyyymmdd.trim();
		if (date.length() != 8) {
			if (date.length() == 4)
				date = date + "0101";
			else if (date.length() == 6)
				date = date + "01";
			else if (date.length() > 8)
				date = date.substring(0, 8);
			else
				return cal;
		}

		cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

		return cal;
	}

	/**
	 * 일자를 받아서, 해당 날짜가 음력일 경우 양력으로 변환, 양력일 경우 계산 후 일자가 현재 일자 보다 클 경우 "경과" 라는
	 * String을 return 한다.
	 * 
	 * @param yyyymmdd
	 * @param isLunar
	 * @return
	 */
	public static synchronized String checkMemorialDay(String yyyymmdd,
			boolean isLunar) {
		if (yyyymmdd == null ||("").equals(yyyymmdd) ){
			return "";
		}
		
		int resultDay = 0; // 결과 값을 체크 할때 사용
		// 날짜 형식을 yyyymmdd로 변경
		yyyymmdd = yyyymmdd.replace("\\.", "");

		if (isLunar) {
			yyyymmdd = fromLunar(yyyymmdd);
		}
		Calendar cal = Calendar.getInstance();

		// 넘어온 날짜에서 현재 날짜를 뺀다.
		try {
			resultDay = (int) getDiffDays(toDate(), yyyymmdd, "yyyyMMdd");
			
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// 결과 값이 0보다 크거나 같을 경우 결과 값을 return 한다.
		if (resultDay < 0) {
			return "D"+resultDay;
		} else if(resultDay == 0){
			return "D-day";
		}else{
			return "경과";
		}

	}

	/**
	 * 두 날짜의 차이를 구한다.
	 * 기준은 Date 형식이다
	 * @param curr
	 * @param next
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static synchronized long getDiffDays(String curr, String next,
			String format) throws ParseException {
		
		// curr 또는 next 가 null 로 들어올수도 있음.
		
		long diffDays = 0L;
		
		if (StringUtils.isNotBlank(curr) && StringUtils.isNotBlank(next)) {
			
			GregorianCalendar StartDate = getGregorianCalendar(curr);
	        GregorianCalendar EndDate = getGregorianCalendar(next);
	
	        long startTime = StartDate.getTime().getTime();
	        long endTime = EndDate.getTime().getTime();
	        diffDays = ((EndDate.getTime().getTime() - startTime) / 86400000)*-1;
		}
        return diffDays;
        
		

	}
	
	/**
     * GregorianCalendar 객체를 반환함
     *
     * @param yyyymmdd 날짜 인수
     * @return GregorianCalendar
     */
    public static GregorianCalendar getGregorianCalendar(String yyyymmdd) {
    	yyyymmdd=yyyymmdd.replace("-", "");
        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm = Integer.parseInt(yyyymmdd.substring(4, 6));
        int dd = Integer.parseInt(yyyymmdd.substring(6, 8));

        GregorianCalendar calendar =
                new GregorianCalendar(yyyy, mm - 1, dd, 0, 0, 0);

        return calendar;

    }
	
	/**
	 * 지금 날짜를 return 한다. 형식은 yyyymmddhhmmss
	 * 
	 * @return
	 */
	public static synchronized String toDateTime() {
//		Calendar cal 	= Calendar.getInstance();
//		String month 	= "";
//		String day 		= "";
//		String hour 	= "";
//		String minute 	= "";
//		String second 	= "";
//		
//		month = ((cal.get(Calendar.MONTH) + 1) < 10) ? 
//					"0" + (cal.get(Calendar.MONTH) + 1) : String.valueOf(cal.get(Calendar.MONTH) + 1);
//		
//		day = (cal.get(Calendar.DATE) < 10) ?
//					"0" + cal.get(Calendar.DATE) : String.valueOf(cal.get(Calendar.DATE));
//		
//		hour = (cal.get(Calendar.HOUR_OF_DAY) < 10) ? 
//					"0" + cal.get(Calendar.HOUR_OF_DAY) : String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
//				
//		minute = (cal.get(Calendar.MINUTE) < 10) ?
//					"0" + cal.get(Calendar.MINUTE) : String.valueOf(cal.get(Calendar.MINUTE));
//				
//		second = (cal.get(Calendar.SECOND) < 10) ?
//					"0" + cal.get(Calendar.SECOND) : String.valueOf(cal.get(Calendar.SECOND));
//		
//		return new StringBuffer().append(cal.get(Calendar.YEAR))
//								.append(month)
//								.append(day) 
//								.append(hour) 
//								.append(minute)
//								.append(second).toString();
		
		return format(new Date(), "yyyyMMddHHmmss");
	}	
	
	/**
	 * String 날자형식을 받아서 특정 world 를 기준으로 년/월/일을 나눠 준다.
	 * @param dt
	 * @param word
	 * @return
	 */
	public static synchronized String converterDate(String dt, String word){
		if(dt.length() >= 8 ){
			return dt.substring(0,4)+word+dt.substring(4,6)+word+dt.substring(6,8);
		}
		return "";
	}
	
	/**
	 * 오늘 요일의 숫자를 리턴
	 * 0:일요일 ~ 6:토요일
	 * @return
	 */
	public static synchronized int dayOfWeek() {
		Calendar cal = Calendar.getInstance();
		
		return cal.get(Calendar.DAY_OF_WEEK) - 1; 	  
	}
	
	/**
	 * 요일을 한글로 리턴함
	 * 0:일요일 ~ 6:토요일
	 * Sample.
	 * DateUtils.getTextDayOfWeek(DateUtils.dayOfWeek(yyyy, mm, dd))
	 * DateUtils.getTextDayOfWeek(DateUtils.dayOfWeek())
	 * @return
	 */
	public static synchronized String getTextDayOfWeek(int noDay) {
		String textDayOfWeek = "";
		if (noDay == 0){
			textDayOfWeek = "일";
		} else if (noDay == 1){
			textDayOfWeek = "월";
		} else if (noDay == 2){
			textDayOfWeek = "화";
		} else if (noDay == 3){
			textDayOfWeek = "수";
		} else if (noDay == 4){
			textDayOfWeek = "목";
		} else if (noDay == 5){
			textDayOfWeek = "금";
		} else if (noDay == 6){
			textDayOfWeek = "토";
		}
		return textDayOfWeek; 	  
	}
	
	/**
	 * 날짜를 지정된 포맷으로 변환한다. 
	 * 날짜형식 정의
	 * - "yyyyMM";
	 * - "yyMMdd";
	 * - "yyyyMMdd";
	 * - "yyyyMMddHHmmss";
	 * - "yyyyMMddHHmmssSSS";
	 * - "HHmmss";
	 * - "EE";
	 * - "F";
	 * @param inDate
	 * @param format
	 * @return
	 */
	public static String format(Date inDate, String format) {
		if (inDate == null)
			return "";
		
		if (format == null || format.length() <= 0) {
			format = "yyyyMMddHHmmss";
		}
		
		SimpleDateFormat SDF = new SimpleDateFormat(format);
		
		return SDF.format(inDate);
	}	
	
	/**
	 * 오늘 날짜를 얻는다.
	 * @return
	 */
	public static Date today() {
		return new Date();
	}

	/**
	 * 오늘 날짜를 지정된 포맷으로 변환한다.
	 * @param format
	 * @return
	 */
	public static String today(String format) {
		return format(new Date(), format);
	}
	
	/**
	 * 특정날짜의  요일의 숫자를 리턴
	 * 0:일요일 ~ 6:토요일
	 * @return
	 */
	public static synchronized int dayOfWeek(String yyyyMMdd) {		
		String sYear = yyyyMMdd.substring(0,4);
		String sMonth = yyyyMMdd.substring(4,6);
		String sDay = yyyyMMdd.substring(6,8);

		return dayOfWeek(sYear, sMonth, sDay);				
	}
	
	public static synchronized int dayOfWeek(String sYear, String sMonth, String sDay) {		
		
		int iYear = Integer.parseInt(sYear);
		int iMonth = Integer.parseInt(sMonth) - 1;
		int isDay = Integer.parseInt(sDay);
		
		GregorianCalendar gc = new GregorianCalendar(iYear, iMonth, isDay); 

		return gc.get(gc.DAY_OF_WEEK) - 1;				
	}
	
	
	/**
	 * 날짜를 지정된 포맷으로 변환한다.
	 * @param inDate
	 * @param format
	 * @return
	 */
	public static String formatDate(String sDate, String formatChar) {
		
		try {
			if (sDate == null) {
				return "";
			}
			if (sDate.length() < 8) {
				return sDate;
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date result = formatter.parse(sDate);			
			String resultStr = formatter.format(result);
			
			if (!StringUtils.isEmpty(formatChar)) {
				return resultStr.substring(0, 4) + formatChar + resultStr.substring(4, 6) + formatChar + resultStr.substring(6, 8);
			}
			
			return formatter.format(sDate);		
		}
		catch (Exception ex) {
			return "";
		}
	}	
	
			/**
	 * 입력한 날짜가 유효한 날짜인지 체크
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean checkDate(String year, String month, String day) {
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date result = formatter.parse(year + "\\." + month + "\\." + day);			
			String resultStr = formatter.format(result);
			
			if (resultStr.equalsIgnoreCase(year + "\\." + month + "\\." + day)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * 입력한 시간이 유효한 시간인지 체크
	 * @param hh
	 * @param mm
	 * @param ss
	 * @return
	 */
	public static boolean checkTime(String hh, String mm, String ss) {
		
		try {
			
			int h = Integer.parseInt(hh);
			int m = Integer.parseInt(mm);
			int s = Integer.parseInt(ss);
			
			if (h < 0 || h > 23) {
				return false;
			}
			
			if (m < 0 || m > 59) {
				return false;
			}
			
			if (s < 0 || s > 59) {
				return false;
			}
			
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}	
	
	/**
	 * 현재 날짜부터 몇일 지난 날짜를 FORMAT 형식으로 return
	 * 전 날짜를 구하려면 -입력
	 * Kind 에는 날짜면 day, 달이면 month
	 * @param passDate
	 * @param kind
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String passDate(int passDate, String kind, String format)throws Exception{
		//현재 날짜를 만들어냄
		Calendar calendar = Calendar.getInstance();
		
		//넘어온 날짜 만큼 이동
		if("month".equals(kind)){
			calendar.add(Calendar.MONTH, passDate);
		}else{
//			calendar.add(Calendar.DAY_OF_MONTH, passDate);	
			calendar.add(Calendar.DATE, passDate);	
		}
		
		return format(calendar.getTime(), format) ;
	}
	
	
	/**
	 * 특정일자의 플러스 나 마이너스된 데이터를 구한다
	 * Kind 에는 플러스면 plus, 마이너스이면 minus
	 * @param passDate
	 * @param kind
	 * @param date
	 * @return
	 * @throws Exception
	 */
// 버그가 있어서 주석처리 getDaysAfterYmd 사용추천	
//	public static String plusMinusDay( String YYYYMMDD, String Kind, int date)throws Exception{
//		//현재 날짜를 만들어냄
//		Calendar cal = Calendar.getInstance();
//	
//		int nowYYYY = 0;
//		int nowMM = 0;
//		int nowDD = 0;
//		
//		nowYYYY = cal.get(Calendar.YEAR);
//		nowMM = cal.get(Calendar.MONTH)+1;
//		nowDD = cal.get(Calendar.DATE);
//		
//		//특정일자
//		int afterYYYY = Integer.parseInt(YYYYMMDD.substring(0, 4));
//		int afterMM = Integer.parseInt(YYYYMMDD.substring(4, 6));
//		int afterDD = Integer.parseInt(YYYYMMDD.substring(6, 8));
//		
//		//System.out.println("afterYYYY =="+afterYYYY);
//		//System.out.println("afterMM =="+afterMM);
//		//System.out.println("afterDD =="+afterDD);
//		
//		cal.set(Calendar.YEAR, afterYYYY);
//		cal.set(Calendar.MONTH, afterMM);
//		if(Kind.equals("plus")){
//			cal.set(Calendar.DATE, afterDD+date);
//		}else{
//			cal.set(Calendar.DATE, afterDD-date);
//		}
//		
//		afterYYYY = cal.get(Calendar.YEAR);
//		afterMM = cal.get(Calendar.MONTH);
//		afterDD = cal.get(Calendar.DATE);
//		
//		String resultYYYY = "";
//		String resultMM = "";
//		String resultDD = "";
//		
//		if(afterMM == 0){
//			
//			if (cal.get(Calendar.YEAR) < 10) {
//				resultYYYY = "0" + cal.get(Calendar.YEAR);
//			} else {
//				resultYYYY = cal.get(Calendar.YEAR) + "";
//			}
//
//			if (nowMM < 10) {
//				resultMM = "0" + nowMM;
//
//			} else {
//				resultMM = nowMM + "";
//			}
//			
//			if (cal.get(Calendar.DATE) < 10) {
//				resultDD = "0" + cal.get(Calendar.DATE);
//
//			} else {
//				resultDD = cal.get(Calendar.DATE) + "";
//			}
//		}else{
//			if (cal.get(Calendar.YEAR) < 10) {
//				resultYYYY = "0" + cal.get(Calendar.YEAR);
//			} else {
//				resultYYYY = cal.get(Calendar.YEAR) + "";
//			}
//
//			if (cal.get(Calendar.MONTH) < 10) {
//				resultMM = "0" + cal.get(Calendar.MONTH);
//
//			} else {
//				resultMM = cal.get(Calendar.MONTH) + "";
//			}
//			
//			if (cal.get(Calendar.DATE) < 10) {
//				resultDD = "0" + cal.get(Calendar.DATE);
//
//			} else {
//				resultDD = cal.get(Calendar.DATE) + "";
//			}
//		}
//		return resultYYYY+resultMM+resultDD;
//	}
	
	/**
	 * 지정된 스트링을 Date 포맷으로 생성
	 * @param string
	 * @return
	 */
	public static Calendar transFromDttmStringToCalendar(String dateString) {
        int day = 0, month = 0, year = 0, hour = 0, minute = 0, second = 0, milliSecond = 0;
        try {
               year = Integer.parseInt(dateString.substring(0, 4));
               month = Integer.parseInt(dateString.substring(4, 6)) - 1;
               day = Integer.parseInt(dateString.substring(6, 8));
               if (8 < dateString.length()) {
                      hour = Integer.parseInt(dateString.substring(8, 10));
                      minute = Integer.parseInt(dateString.substring(10, 12));
                      second = Integer.parseInt(dateString.substring(12, 14));
               }
               if (14 < dateString.length()) {
                      milliSecond = Integer.parseInt(dateString.substring(15));
               }
        } catch (NumberFormatException e) {
               throw new RuntimeException("정확한 날짜 포맷(yyyymmddhhmmss)이 아닙니다. : " + dateString);
        }

        Calendar result = Calendar.getInstance();
        result.clear();
        result.set(Calendar.YEAR, year);
        result.set(Calendar.MONTH, month);
        result.set(Calendar.DAY_OF_MONTH, day);
        result.set(Calendar.HOUR_OF_DAY, hour);
        result.set(Calendar.MINUTE, minute);
        result.set(Calendar.SECOND, second);
        result.set(Calendar.MILLISECOND, milliSecond);

        return result;
	}

	/**
	 * Date 포맷의 데이터를 스트링 14자리로 변환
	 * @param calendar
	 * @return
	 */
	public static String transFromCalendarToYmdHms(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(calendar.getTime());
	}

	/**
	 * 입력된 날짜로부터 delta만큼의 날짜를 yyyymmddhhmmss형식으로 리턴
	 * @param ymd
	 * @param delta
	 * @return
	 */
	public static String getDaysAfterYmdhms(String ymd, int delta) {
        
        Calendar startCal = DateUtil.transFromDttmStringToCalendar(ymd+"000000");
        
        startCal.add(Calendar.DATE, delta);
        
        return transFromCalendarToYmdHms(startCal);
	}
	
	/**
	 * 입력된 날짜로부터 delta만큼의 날짜를 yyyymmdd형식으로 리턴
	 * @param ymd
	 * @param delta
	 * @return
	 */
	public static String getDaysAfterYmd(String ymd, int delta) {
		return getDaysAfterYmdhms(ymd, delta).substring(0, 8);
	}
	
	/**
	 * 해당달 달력의 첫번째 날짜를 Calendar 타입으로 넘겨준다.
	 * 해당달의 첫날이 일요일이면 첫날을 리턴하고 일요일이 아니면 지난달 마지막 일요일을 리턴한다.
	 * @param year
	 * @param month
	 * @return Calendar
	 */
	public static Calendar getFirstCalendarDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return getFirstCalendarDate(cal);
	}
	/**
	 * 이번달 달력의 첫번째 날짜를 Calendar 타입으로 넘겨준다.
	 * 이번달 첫날이 일요일이면 첫날을 리턴하고 일요일이 아니면 지난달 마지막 일요일을 리턴한다.
	 * @return Calendar
	 */
	public static Calendar getFirstCalendarDate() {
		Calendar cal = Calendar.getInstance();
		return getFirstCalendarDate(cal);
	}
	public static Calendar getFirstCalendarDate(Calendar cal) {
		Calendar returnCal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		returnCal.set(year,month,1);
		int dayOfWeek = returnCal.get(Calendar.DAY_OF_WEEK); 
		int diffDay = dayOfWeek - 1; 
		if( diffDay > 0 ) {
			returnCal.add(Calendar.DAY_OF_MONTH, -diffDay );
		}
		return returnCal;
	}
	/**
	 * 해당달 달력의 마지막 날짜를 Calendar 타입으로 넘겨준다.
	 * 해당달의 마지막날이 토요일이면 마지막날을 리턴하고 토요일이 아니면 다음달 첫번째 토요일을 리턴한다.
	 * @param year
	 * @param month
	 * @return
	 */
	public static Calendar getLastCalendarDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		return getLastCalendarDate(cal);
	}
	public static Calendar getLastCalendarDate() {
		Calendar cal = Calendar.getInstance();
		return getLastCalendarDate(cal);
	}
	public static Calendar getLastCalendarDate(Calendar cal) {
		Calendar returnCal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		returnCal.set(year,month,lastDay);
		int dayOfWeek = returnCal.get(Calendar.DAY_OF_WEEK); 
		if( 7 - dayOfWeek > 0 ) {
			returnCal.add(Calendar.DAY_OF_MONTH, 7 - dayOfWeek );
		}
		return returnCal;
	}
	
	/**
	 * 현재 날짜부터 몇일 지난 날짜를 FORMAT 형식으로 return
	 * 전 날짜를 구하려면 -입력
	 * Kind 에는 날짜면 day, 달이면 month
	 * @param passDate
	 * @param kind
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String passDate2(int passDate, String kind, String format)throws Exception{
		//현재 날짜를 만들어냄
		Calendar calendar = Calendar.getInstance();

		return passDate2(calendar, passDate, kind, format);
	}

	public static String passDate2(Calendar calendar, int passDate, String kind, String format)throws Exception{
		//넘어온 날짜 만큼 이동
		if( "year".equals(kind)){
			calendar.add(Calendar.YEAR, passDate);
		} else if("month".equals(kind)){
			calendar.add(Calendar.MONTH, passDate);
		}else{
			calendar.add(Calendar.DAY_OF_MONTH, passDate);	
		}

		return format(calendar.getTime(), format);
	}	
	
	private static DateFormat getStrictYmdDateFormat() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);
		return df;
	}

	/**
	 * yyyymmddhhmiss ---> yyyy/mm/dd hh:mi:ss
	 * 혹은 
	 * yyyymmdd ---> yyyy/mm/dd
	 */
	public static String transFormatYmdHmsToSlash(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
		}
		return buffer.toString();
	}	
	
	
	/**
	 * yyyymmddhhmiss ---> yyyy/mm/dd hh:mi:ss
	 * 혹은 
	 * yyyymmdd ---> yyyy/mm/dd
	 */
	public static String transFormatYmdHmsToDot(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("\\.").append(month).append("\\.").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
		}
		return buffer.toString();
	}
	
	/**
	 * yyyymmddhhmiss ---> yyyy-mm-dd hh:mi:ss
	 * 혹은 
	 * yyyymmdd ---> yyyy-mm-dd
	 */
	public static String transFormatYmdHmsToDash(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("-").append(month).append("-").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
		}
		return buffer.toString();
	}

	public static String transFormatYmdHmToSlash(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			buffer.append(" ").append(hour).append(":").append(minute);
		}
		return buffer.toString();
	}

	/**
	 * yyyymmddhhmiss ---> yy/mm/dd hh:mi:ss
	 * 혹은 
	 * yyyymmdd ---> yy/mm/dd
	 */
	public static String transFormatYmdHmsToNormal(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(2, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
		}
		return buffer.toString();
	}
	/**
	 * yyyymmddhhmiss ---> yy-mm-dd hh:mi
	 * 혹은 
	 * yyyymmdd ---> yy/mm/dd
	 */
	public static String transFormatYmdHmToNormalByHypen(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(2, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("-").append(month).append("-").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			//String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute);
		}
		return buffer.toString();
	}
	
	/**
	 * yyyymmddhhmiss ---> yyyy-mm-dd hh:mi
	 * 혹은 
	 * yyyymmdd ---> yy/mm/dd
	 */
	public static String transFormatYYYYMMDDHmToNormalByHypen(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("-").append(month).append("-").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			//String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append(":").append(minute);
		}
		return buffer.toString();
	}

	public static String transFormatYmdToShort(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("-").append(month).append("-").append(day);

		return buffer.toString();
	}

	public static String transFormatYmdShortSlash(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);

		return buffer.toString();
	}
	/**
	 * yyyymmddhhmiss ---> yyyy/mm.dd<br>hh:mi
	 */
	public static String transFormatYmdBrHhMiShortSlash(String dateString) {
	    if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);

			buffer.append("<br>").append(hour).append(":").append(minute);
		}
		return buffer.toString();
	}
	/**
	 * yyyymmddhhmiss ---> yyyy/mm/dd<br>hh:mi:ss
	 */
	public static String transFormatYmdBrHhMiSecondSlash(String dateString) {
	    if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		buffer.append(year).append("/").append(month).append("/").append(day);
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);

			buffer.append("<br>").append(hour).append(":").append(minute).append(":").append(second);
		}
		return buffer.toString();
	}
	
	/**
	 * yyyymmddhhmiss ---> yyyy년 mm월 dd일 hh시 mi분 ss초
	 * 혹은 
	 * yyyymmdd ---> yyyy년 mm월 dd일
	 */
	public static String transFormatYmdHmsToKorean(String dateString) {
		if (dateString == null || dateString.length() < 8)
			return "";

		StringBuffer buffer = new StringBuffer();

		String year = dateString.substring(0, 4);
		String month = dateString.substring(4, 6);
		String day = dateString.substring(6, 8);

		month = trimZero(month);
		day = trimZero(day);

		buffer.append(year).append("년 ").append(month).append("월 ").append(day).append("일");
		if (8 < dateString.length()) {
			String hour = dateString.substring(8, 10);
			String minute = dateString.substring(10, 12);
			String second = dateString.substring(12, 14);
			buffer.append(" ").append(hour).append("시 ").append(minute).append("분 ").append(
				second).append(
				"초");
		}
		return buffer.toString();
	}
	/**
	 * yyyy-mm-dd hh:mi:ss -->  yyyy-mm-dd
	 */
	public static String TransYmdHmsToYmd(String dttm) {
		return dttm.substring(0, 10);
	}

	/**
	 * yyyymmddhhmiss -->  calendar
	 */
	public static Calendar transFromYmdHmsToCalendar(String string) {
		if (string == null || string.length() < 8)
			throw new RuntimeException("정확한 날짜 포맷(yyyymmddhhmmss.mi)이 아닙니다. : " + string);

		int day = 0, month = 0, year = 0, hour = 0, minute = 0, second = 0, milliSecond = 0;
		try {
			year = Integer.parseInt(string.substring(0, 4));
			month = Integer.parseInt(string.substring(4, 6)) - 1;
			day = Integer.parseInt(string.substring(6, 8));
			if (8 < string.length()) {
				hour = Integer.parseInt(string.substring(8, 10));
				minute = Integer.parseInt(string.substring(10, 12));
				second = Integer.parseInt(string.substring(12, 14));
			}
			if (14 < string.length()) {
				milliSecond = Integer.parseInt(string.substring(15));
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("정확한 날짜 포맷(yyyymmddhhmmss.mi)이 아닙니다. : " + string);
		}
		Calendar result = Calendar.getInstance();
		result.clear();
		result.set(Calendar.YEAR, year);
		result.set(Calendar.MONTH, month);
		result.set(Calendar.DAY_OF_MONTH, day);
		result.set(Calendar.HOUR_OF_DAY, hour);
		result.set(Calendar.MINUTE, minute);
		result.set(Calendar.SECOND, second);
		result.set(Calendar.MILLISECOND, milliSecond);

		return result;
	}

	public static String transFromYmdHmsToKoreanYmdHm(String normalDataString) {
		Calendar cal = transFromYmdHmsToCalendar(normalDataString);
		return transFromCalendarToKoreanYmdHm(cal);
	}

	/** yyyyMMdd */
	public static String transFromCalendarToYmd(Calendar calendar) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(calendar.getTime());
	}

	public static String transFromCalendarToKoreanYmdHm(Calendar calendar) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 M월 d일 H시 m분");
		return formatter.format(calendar.getTime());
	}

	public static String getCurrentYmd() {
		return getCurrentYmdHms().substring(0, 8);
	}
	
	public static String getCurrentMmdd() {
		return new SimpleDateFormat("MM/dd").format(Calendar.getInstance().getTime());
	}

	public static String getCurrentYmdHms() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
	}
	
	public static long getTimeStamp(){
		return Calendar.getInstance().getTimeInMillis();
	}

	public static String getCurrentFormattedMdHms() {
		return new SimpleDateFormat("MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	public static String getCurrentDateYYMMDD() {
		return getCurrentYmd().substring(2, 8);
	}

	public static String getCurrentKoreanDateTimeString() {
		Calendar calendar = Calendar.getInstance();

		return calendar.get(Calendar.YEAR)
			+ "년 "
			+ (calendar.get(Calendar.MONTH) + 1)
			+ "월 "
			+ calendar.get(Calendar.DATE)
			+ "일 "
			+ calendar.get(Calendar.HOUR_OF_DAY)
			+ "시 "
			+ calendar.get(Calendar.MINUTE)
			+ "분 "
			+ calendar.get(Calendar.SECOND)
			+ "초";
	}

	public static String getCurrentTimeGroup() {
		Calendar calendar = Calendar.getInstance();
		String hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		if(hh.length() == 1) hh = "0" + hh;
		
		String mm = "";
		if(calendar.get(Calendar.MINUTE) < 30) mm = "00";
		else mm = "30";
		
		return hh + "" + mm;
	}
	
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();

		return calendar.get(Calendar.HOUR_OF_DAY)
			+ ":"
			+ calendar.get(Calendar.MINUTE)
			+ ":"
			+ calendar.get(Calendar.SECOND);
	}
	
	public static String getCurrentTimeForKor() {
		Calendar calendar = Calendar.getInstance();
		String hh = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		if(hh.length() == 1) hh = "0" + hh;
		
		String mm = String.valueOf(calendar.get(Calendar.MINUTE));
		if(mm.length() == 1) mm = "0" + mm;
		
		String ss = String.valueOf(calendar.get(Calendar.SECOND));
		if(ss.length() == 1) ss = "0" + ss;
		
		return hh + "시" + mm + "분" + ss + "초";
	}
	/*
	 * 몇일 전의 날짜를 Ymd의 형태로 나타낸다.
	 */
	public static String getYmdDaysAgo(int delta) {
		return getYmdHmsDaysAgo(delta).substring(0, 8);
	}
	
	/**
	 * 며칠전의 날짜 구하기 
	 * ymd로 부터 delta 만큼 전의 날짜 
	 */
	public static String getYmdDaysAgo(String ymd, int delta) {
		
		Calendar startCal = DateUtil.transFromDttmStringToCalendar(ymd+"000000");
		
		startCal.add(Calendar.DATE, -delta);
		
		return transFromCalendarToYmdHms(startCal).substring(0,8);
	}

	/*
	 * 몇일 전의 날짜를 YmdHms의 형태로 나타낸다.
	 */
	public static String getYmdHmsDaysAgo(int delta) {
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DAY_OF_MONTH, delta); // Calendar.DAY_OF_MONTH 와 Calendar.DATE 는 동의어

		return transFromCalendarToYmdHms(calendar);
	}

	public static String getYmdHmsDaysAfter(String date, int delta) {
		if( date.length() < 8 ) return null;
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR,Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH,Integer.parseInt(date.substring(4, 6))-1);
		calendar.set(Calendar.DATE,Integer.parseInt(date.substring(6, 8)));
		
		calendar.add(Calendar.DAY_OF_MONTH, delta); // Calendar.DAY_OF_MONTH 와 Calendar.DATE 는 동의어

		return transFromCalendarToYmdHms(calendar);
	}
	
	/*
	 * 몇 달 후의 날짜를 YmdHms의 형태로 나타낸다.
	 */
	public static String getYmdHmsMonthsAfter(String date, int delta) {
		if( date.length() < 8 ) return null;
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR,Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH,Integer.parseInt(date.substring(4, 6))-1);
		calendar.set(Calendar.DATE,Integer.parseInt(date.substring(6, 8)));
		
		calendar.add(Calendar.MONTH, delta);

		return transFromCalendarToYmdHms(calendar);
	}

	public static String getYmdHmsAfterCurrentMinutes(Calendar calendar, int interval) {
		int afterHours = interval / 60;
		int afterMinutes = interval % 60;
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + afterHours);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + afterMinutes);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
	}

	public static String getYmdHmsAfterAnyDttmMinutes(String dttm, int interval) {
		Calendar calendar = transFromDttmStringToCalendar(dttm);
		int afterHours = interval / 60;
		int afterMinutes = interval % 60;
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + afterHours);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + afterMinutes);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
	}

	public static Map getMapFromYmdHms(String YYYYMMDDHH24MI) {
		Map dateMap = new HashMap();

		if (12 <= YYYYMMDDHH24MI.length()) {
			dateMap.put("YYYY", YYYYMMDDHH24MI.substring(0, 4));
			dateMap.put("MM", YYYYMMDDHH24MI.substring(4, 6));
			dateMap.put("DD", YYYYMMDDHH24MI.substring(6, 8));
			dateMap.put("HH", YYYYMMDDHH24MI.substring(8, 10));
			dateMap.put("MI", YYYYMMDDHH24MI.substring(10, 12));
		}
		return dateMap;
	}

	public static Map getMapYYYYMMDD(String YYYYMMDD) {
		Map dateMap = new HashMap();
		dateMap.put("YYYY", YYYYMMDD.substring(0, 4));
		dateMap.put("MM", YYYYMMDD.substring(4, 6));
		dateMap.put("DD", YYYYMMDD.substring(6, 8));
		return dateMap;
	}

	private static String trimZero(String arg) {
		String str = arg;
		if (str.charAt(0) == '0')
			str = str.substring(1, 2);
		return str;
	}

	/**
	 * 과거에 대해서 days이 내인지 테스트 
	 */
	public static boolean isWithinDays(String dateTimeString, int days) {
		Calendar curCalendar = Calendar.getInstance();
		long curTime = curCalendar.getTime().getTime();

		Calendar targetCalendar = DateUtil.transFromDttmStringToCalendar(dateTimeString);
		long targetTime = targetCalendar.getTime().getTime();

		return curTime - targetTime < days * 24 * 3600000L;
	}

	public static String formatKorean(long time) {
		SimpleDateFormat df = new SimpleDateFormat("MM월 dd일 HH:mm:ss");
		return df.format(new Date(time));
	}

	public static long getDiffMinuteByDttm(String oldDttm, String afterDttm) {
		Calendar oldCal = DateUtil.transFromDttmStringToCalendar(oldDttm);
		Calendar afterCal = DateUtil.transFromDttmStringToCalendar(afterDttm);
		return (oldCal.getTime().getTime() - afterCal.getTime().getTime()) / (1000 * 60);
	}
	
	public static long getDiffDayByDttm(String oldDttm, String afterDttm) {
		Calendar oldCal = DateUtil.transFromDttmStringToCalendar(oldDttm);
		Calendar afterCal = DateUtil.transFromDttmStringToCalendar(afterDttm);
		
		return (oldCal.getTime().getTime() - afterCal.getTime().getTime()) / (1000 * 60 * 60 * 24);
	}
	
	public static long getDiffTime(String oldDttm, String afterDttm) {
		Calendar oldCal = DateUtil.transFromDttmStringToCalendar(oldDttm);
		Calendar afterCal = DateUtil.transFromDttmStringToCalendar(afterDttm);
		
		return (oldCal.getTime().getTime() - afterCal.getTime().getTime()) / 1000;
	}

	public static String getDayBeforeYesterdayYMD() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}
	
	public static String getRemainderString(String delayEndDttm, String bgnDttm) {
		Calendar endCal = DateUtil.transFromDttmStringToCalendar(delayEndDttm);
		Calendar startCal = DateUtil.transFromDttmStringToCalendar(bgnDttm);
		long finishedSecond =
			(endCal.getTime().getTime() - Calendar.getInstance().getTime().getTime()) / (1000);
		long startedSecond =
			(startCal.getTime().getTime() - Calendar.getInstance().getTime().getTime()) / (1000);

		if (startedSecond > 0) {
			return "경매 시작전입니다.";
		}

		if (finishedSecond <= 0) {
			return "종료된 경매입니다.";
		}

		long remainderSecond =
			(endCal.getTime().getTime() - Calendar.getInstance().getTime().getTime()) / (1000);

		int day = (int) remainderSecond / 86400;
		int modDay = (int) remainderSecond % 86400;
		int hour = modDay / 3600;
		int minute = modDay % 3600 / 60;
		return day + "일 " + hour + "시간 " + minute + "분";
	}

	
	
	
	
	public static boolean is24TimeInByRegDttm(String regDttm) {
		Calendar oldCal = DateUtil.transFromDttmStringToCalendar(regDttm);
		Calendar nowCal = Calendar.getInstance();
		return (nowCal.getTime().getTime() - oldCal.getTime().getTime()) < 86400000;
	}
	
	/**
	 * 오늘 날짜와 요일을 리턴한다.
	 * @throws ParseException
	 */
	public static String getTodayYmsDayWeek(String regDt) throws ParseException{
		StringBuffer buffer = new StringBuffer();
		if ( regDt.length() > 7 ){
			DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
			DateFormat ddf = new SimpleDateFormat("EEE"); 
			buffer.append(regDt.substring(0,4)).append("년 ")
			.append(regDt.substring(4,6)).append("월 ")
			.append(regDt.substring(6,8)).append("일 ")
			.append("(")
			.append(ddf.format(df.parse(regDt)))
			.append(")");
		}
		
		return buffer.toString();
		
	}
	
	/**
	 * 현재시간과 비교.-판매 시간종료 이미지 관련
	 */
	public static String getCurrentCompareEnddttm(String endDttm) {
		Calendar endCal = DateUtil.transFromDttmStringToCalendar(endDttm);
		long finishedSecond =
			(endCal.getTime().getTime() - Calendar.getInstance().getTime().getTime()) / (1000);
		
		if (finishedSecond < 0) 
			return "gray";
		else if (finishedSecond < 5*60) 
			return "red";
		else if (finishedSecond < 24*60*60)
			return "yellow";
		else 
			return "green";
	}
	
	
	
	
	/**
	 * 주어진 'yyyymmdd' 형식의 날짜가 표현되어지는 1970/01/01 GMT 이후의 총 second의 number를 리턴
	 * @param date String
	 * @return long
	 */
	public static long getTime(String date){
		Calendar cal = Calendar.getInstance( );//TimeZone.getTimeZone("JST") );
		return getTime( cal, date );
	}

	public static long getTime( Calendar cal, String date){

		int yy =0;
		int mm = 0;
		int dd = 0;
		int hh = 0;
		int mi = 0;
		int ss = 0;
	
		if( date.length() == 4 ){
			yy = Integer.parseInt( date);
		}
		else if( date.length() == 6 ){
			yy = Integer.parseInt( date.substring(0,4) );
			mm = Integer.parseInt( date.substring(4) ) -1;
		}
		else if( date.length() == 8 ){
			yy = Integer.parseInt( date.substring(0,4) );
			mm = Integer.parseInt( date.substring(4,6) ) -1;
			dd = Integer.parseInt( date.substring(6) );
		}
		else if( date.length() == 10 ){
			yy = Integer.parseInt( date.substring(0,4) );
			mm = Integer.parseInt( date.substring(4,6) ) -1;
			dd = Integer.parseInt( date.substring(6,8) );
			hh = Integer.parseInt( date.substring(8) );
		}
		else if( date.length() == 12 ){
			yy = Integer.parseInt( date.substring(0,4) );
			mm = Integer.parseInt( date.substring(4,6) ) -1;
			dd = Integer.parseInt( date.substring(6,8) );
			hh = Integer.parseInt( date.substring(8,10) );
			mi = Integer.parseInt( date.substring(10) );
		}
		else if( date.length() == 14 ){
			yy = Integer.parseInt( date.substring(0,4) );
			mm = Integer.parseInt( date.substring(4,6) ) -1;
			dd = Integer.parseInt( date.substring(6,8) );
			hh = Integer.parseInt( date.substring(8,10) );
			mi = Integer.parseInt( date.substring(10,12) );
			ss = Integer.parseInt( date.substring(12) );
		}
	
		cal.set( yy, mm, dd, hh, mi, ss);
		return cal.getTime().getTime() / 1000;
		
	}

	/**
	 * 어제의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진)
	 * @return
	 */
	public static String getYesterdayYmd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 이번주 월요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진)
	 * @return
	 */
    public static String getThisWeekFirstDay(){	
		Calendar cal = Calendar.getInstance();
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		if(yo == 1) yo = 8;
		int yonum = 7-(9-yo);
		cal.add(Calendar.DATE,-yonum); //현재 날짜에서 오일전의 날짜 가져오기
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
    }
	
	/**
	 * 이번주 일요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진)
	 * @return
	 */
    public static String getThisWeekLastDay(){
		Calendar cal = Calendar.getInstance();
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		if(yo == 1) yo = 8;
		int yonum = 7-(9-yo);
		cal.add(Calendar.DATE,-yonum); //현재 날짜에서 오일전의 날짜 가져오기
		cal.add(Calendar.DATE, 6);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
    }
    
	/**
	 * 지난주 월요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진)
	 * @return
	 */
	public static String getBeforeWeekFirstDay(){
		Calendar cal = Calendar.getInstance();
		
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyymmdd");
		String timestr=formatter.format(currentTime);
		
		return getBeforeWeekFirstDay(timestr, 2);
	}
	
	/**
	 * 입력받은 날짜가 몇번째 요일인지 알아온다.
	 * @author go2uu
	 * @return
	 */
	public static int getDayOfWeek(String date){
	    Calendar cal = Calendar.getInstance();
		cal.set( Integer.parseInt(date.substring(0,4)),
				Integer.parseInt(date.substring(4,6)) - 1,
				Integer.parseInt(date.substring(6,8)), 0, 0, 0 );
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 입력받은 날짜가 몇번째 요일인지 알아온다.
	 * @author go2uu
	 * @return
	 */
	public static int getWeekOfMonth(String date){
	    Calendar cal = Calendar.getInstance();
		cal.set( Integer.parseInt(date.substring(0,4)),
				Integer.parseInt(date.substring(4,6)) - 1,
				Integer.parseInt(date.substring(6,8)), 0, 0, 0 );

		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 입력받은 날짜의 지난주 월요일의 년월일을 구한다.
	 * 
	 * @author go2uu
	 * @return String
	 */
	public static String getBeforeWeekFirstDay(String dateYYYYMMDD, int dayOfWeek){
		Calendar cal = Calendar.getInstance();
		cal.set( Integer.parseInt(dateYYYYMMDD.substring(0,4)),
				Integer.parseInt(dateYYYYMMDD.substring(4,6)) - 1,
				Integer.parseInt(dateYYYYMMDD.substring(6,8)), 0, 0, 0 );
		
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		//if(yo == 1) yo = 8;
		int yonum = yo - dayOfWeek;
		cal.add(Calendar.DATE, -yonum); //현재 날짜에서 오일전의 날짜 가져오기
		
		// endDt가 일요일일 경우 bgnDt에 해당하는 주의 일요일을 선택한다.
		//if(dayOfWeek > 1) 
		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
		
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}
	
	/**
	 * 입력받은 날짜의 월요일의 년월일을 구한다.
	 * 
	 * @author go2uu
	 * @return String
	 */
	public static String getSomeWeekFirstDay(String dateYYYYMMDD){
	    return getSomeWeekSomeDay(dateYYYYMMDD, 2);
	}

	/**
	 * 입력받은 날짜의 일요일의 년월일을 구한다.
	 * 
	 * @author go2uu
	 * @return String
	 */
	public static String getSomeWeekLastDay(String dateYYYYMMDD){
	    return getSomeWeekSomeDay(dateYYYYMMDD, 8);
	}
	
	public static String getSomeWeekSomeDay(String dateYYYYMMDD, int dayOfWeek){
	    Calendar cal = Calendar.getInstance();
		cal.set( Integer.parseInt(dateYYYYMMDD.substring(0,4)),
				Integer.parseInt(dateYYYYMMDD.substring(4,6)) - 1,
				Integer.parseInt(dateYYYYMMDD.substring(6,8)), 0, 0, 0 );
		
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		int yonum = yo - dayOfWeek;
		
		
		// 일요일일 경우 한주를 빼준다. 왜냐하면 다음주가 되어버리므로
		if(cal.get(Calendar.DAY_OF_WEEK) == 1) cal.add(Calendar.WEEK_OF_MONTH, -1);
		
		cal.add(Calendar.DATE, -yonum); //현재 날짜에서 오일전의 날짜 가져오기
		
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;    
	}
	
	
	public static String getSomeMonthLastDay(String dateYYYYMMDD){
	    Calendar cal = Calendar.getInstance();
		cal.set( Integer.parseInt(dateYYYYMMDD.substring(0,4)),
				Integer.parseInt(dateYYYYMMDD.substring(4,6)) - 1,
				Integer.parseInt(dateYYYYMMDD.substring(6,8)), 0, 0, 0 );
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, 1);
	    cal.add(Calendar.DATE, -1);
		
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;    
	}
	
	
	/**
	 * 지난주 일요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getBeforeWeekLastDay(){
		Calendar cal = Calendar.getInstance();
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		if(yo == 1) yo = 8;
		int yonum = 7-(9-yo);
		cal.add(Calendar.DATE,-yonum); //현재 날짜에서 오일전의 날짜 가져오기
		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
		cal.add(Calendar.DATE, 6);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}
	
	/**
	 * 다음주 월요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진)
	 * @return
	 */
	public static String getAfterWeekFirstDay(){
		Calendar cal = Calendar.getInstance();
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		if(yo == 1) yo = 8;
		int yonum = 7-(9-yo);
		cal.add(Calendar.DATE,-yonum); //현재 날짜에서 오일전의 날짜 가져오기
		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 다음주 일요일의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getAfterWeekLastDay(){
		Calendar cal = Calendar.getInstance();
		int yo = cal.get(Calendar.DAY_OF_WEEK);
		if(yo == 1) yo = 8;
		int yonum = 7-(9-yo);
		cal.add(Calendar.DATE,-yonum); //현재 날짜에서 오일전의 날짜 가져오기
		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		cal.add(Calendar.DATE, 6);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 이번달 처음날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getThisMonthFirstDay() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 이번달 마지막날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getThisMonthLastDay() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, 1);
	    cal.add(Calendar.DATE, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	
	public static String getSomeBeforeMonthFirstDay(String ymd){
	    
		Calendar cal = Calendar.getInstance();
		if(ymd != null && ymd.length() >= 8)
		cal.set( Integer.parseInt(ymd.substring(0,4)),
				Integer.parseInt(ymd.substring(4,6)) - 1,
				Integer.parseInt(ymd.substring(6,8)), 0, 0, 0 );
		
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}
	
	/**
	 * 지난달 처음날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getBeforeMonthFirstDay() {
		return getSomeBeforeMonthFirstDay("");
	}

	/**
	 * 지난달 마지막날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getBeforeMonthLastDay() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.DATE, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 다음달 처음날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getAfterMonthFirstDay() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, 1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}

	/**
	 * 다음달 마지막날의 년월일을 구한다.
	 * 
	 * @author sem98@daumcorp.com (오왕진) 
	 * @return
	 */
	public static String getAfterMonthLastDay() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.add(Calendar.MONTH, 2);
	    cal.add(Calendar.DATE, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
		String timestr=formatter.format(currentTime);
		
		return timestr;
	}
	
	
    
    public static Calendar getUserDefineCalendar(String date) {
        Calendar cal = Calendar.getInstance();

        String year = null, month  = null, day    = null, 
               hour = null, minute = null, second = null;
        
        if(date != null && date.length() == 4) {
            year = date.substring(0, 4);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
        } else if(date.length() > 4 && date.length() == 6) {
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
        } else if(date.length() > 6 && date.length() == 8) {
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        } else if(date.length() > 8 && date.length() == 10) {
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            hour = date.substring(8, 10);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            cal.set(Calendar.HOUR, Integer.parseInt(hour));
        } else if(date.length() > 10 && date.length() == 12) {
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            hour = date.substring(8, 10);
            minute = date.substring(10, 12);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            cal.set(Calendar.HOUR, Integer.parseInt(hour));
            cal.set(Calendar.MINUTE, Integer.parseInt(minute));
        } else if(date.length() > 12 && date.length() == 14) {
            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            hour = date.substring(8, 10);
            minute = date.substring(10, 12);
            second = date.substring(12, 14);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            cal.set(Calendar.HOUR, Integer.parseInt(hour));
            cal.set(Calendar.MINUTE, Integer.parseInt(minute));
            cal.set(Calendar.SECOND, Integer.parseInt(second));
        }

        return cal;
    }
    
    public static String getDateFromUserDefineStyle(String date, String dateStyle) {
        String userDefineStyleDate = null;
        
        SimpleDateFormat formatter = new SimpleDateFormat(dateStyle);
        userDefineStyleDate = formatter.format(getUserDefineCalendar(date).getTime());
        return userDefineStyleDate;
    }
    
    public static String getDateFromUserDefineStyle(String date, String dateStyle, int kind, int term) {
        String userDefineStyleDate = null;
        
        SimpleDateFormat formatter = new SimpleDateFormat(dateStyle);
        Calendar cal = getUserDefineCalendar(date);
        userDefineStyleDate = formatter.format(cal.getTime());
        cal.add(kind, term);
        userDefineStyleDate = formatter.format(cal.getTime());
        return userDefineStyleDate;
    }
    
    public static void main(String args[]) {
        System.out.println(getDateFromUserDefineStyle("2004", "yyyy", Calendar.YEAR, -1));
    }

    /**
	 * 현재 날짜에서 이번달 처음 날짜를 뺀 값
	 * 
	 * @author ajelier
	 * @return
	 */
	public static int getMonFirstMinusCurDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date currentTime=cal.getTime();
		SimpleDateFormat formatter=new SimpleDateFormat("dd");
		String todayDate=formatter.format(currentTime);
		
		return Integer.parseInt(todayDate);
	}
	
	/*
	 * 몇일 후의 날짜를 YmdHms의 형태로 나타낸다.
	 */
	public static String getYmdDaysAfter(int delta) {
		return getYmdHmsDaysAfter(delta).substring(0,8);
	}
	
	/*
	 * 몇일 후의 날짜를 YmdHms의 형태로 나타낸다.
	 */
	public static String getYmdHmsDaysAfter(int delta) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DATE, -delta);
		
		return transFromCalendarToYmdHms(calendar);
	}
	
	/**
	 * 며칠후의 날짜 구하기 
	 * ymd로 부터 delta 만큼 후의 날짜 
	 */
	public static String getYmdDaysAfter(String ymd, int delta) {
		
		Calendar startCal = DateUtil.transFromDttmStringToCalendar(ymd+"000000");
		
		startCal.add(Calendar.DATE, delta);
		
		return transFromCalendarToYmdHms(startCal).substring(0,8);
	}
	public static String getDiffDays(String date){
		return getDiffDays(DateUtil.getCurrentYmd(),date);	
	}
	
	public static String getDiffDays(String start, String end){
		
		if( start.length() < 8 || end.length() < 8 ) return "0";
		
		int startYear = Integer.parseInt(start.substring(0,4));
		int startMonth = Integer.parseInt(start.substring(4,6));
		int startDay = Integer.parseInt(start.substring(6,8));

		int endYear = Integer.parseInt(end.substring(0,4));
		int endMonth = Integer.parseInt(end.substring(4,6));
		int endDay = Integer.parseInt(end.substring(6,8));
		
		Date startDate = new Date(startYear,startMonth,startDay);
		Date endDate = new Date(endYear,endMonth,endDay);
		
		return ((endDate.getTime() - startDate.getTime())/1000/60/60/24)+"";
	}	
	
	public static String getDateString(String gmtDateString, String format) {
		if(gmtDateString == null || "".equals(gmtDateString)) return "";
		
		if(format.equals("yyyy-MM-dd") && gmtDateString.length() > 8){
			gmtDateString = gmtDateString.substring(0, 8);
		}
		
		if(gmtDateString.length() == 8){
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				Date inputDate = formatter.parse(gmtDateString);
				
				DateFormat localTimeFomatter = new SimpleDateFormat(format);  
				return localTimeFomatter.format(inputDate);
				
			} catch (ParseException e1) {
				return gmtDateString;
			}
		}else{
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				Date inputDate = formatter.parse(gmtDateString);
				
				DateFormat localTimeFomatter = new SimpleDateFormat(format);  
				return localTimeFomatter.format(inputDate);
				
			} catch (ParseException e1) {
				return gmtDateString;
			}
			
		}
		
	        
	}
	
	public static String getDateString(Date date, String format) {
		try {
			
			DateFormat localTimeFomatter = new SimpleDateFormat(format);  
			return localTimeFomatter.format(date);
			
		} catch (Exception e1) {}
		
		return "";
	}
}
