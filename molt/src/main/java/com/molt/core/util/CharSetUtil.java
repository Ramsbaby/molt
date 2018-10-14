/*
 * ---------------------------------------------------------------------
 * Copyright 2009 
 * developed by go2uu for poor ant
 * Description :
 * All rights reserved.
 * ---------------------------------------------------------------------
 * NOTICE ! You can copy or redistribute this code freely, 
 * but you should not remove the information about the copyright notice 
 * and the author. 
 * For more information on the mudusnet.com, please see <http://www.mudusnet.com/>.
 * ---------------------------------------------------------------------
 */
package com.molt.core.util;

/**
 * <p>
 * Operations on <code>CharSet</code>s.
 * </p>
 * <p>
 * This class handles <code>null</code> input gracefully. An exception will
 * not be thrown for a <code>null</code> input. Each method documents its
 * behaviour in more detail.
 * </p>
 * 
 * @see CharSet
 * @author <a href="bayard@generationjava.com">Henri Yandell</a>
 * @author Stephen Colebourne
 * @author Phil Steitz
 * @author Gary Gregory
 * @author <a href="mailto:admin@jdevelop.co.kr">kang, dong-youn</a>
 * @version $Id: CharSetUtil.java 17 2007-03-21 01:28:50Z javaman $
 */
public class CharSetUtil {

    /**
     * <p>
     * 모든 메소드가 static이기 때문에 <code>CharSetUtil</code> 인스탄스는 일반적으로 필요하지 않음.<br>
     * 대신 아래와 같이 사용하면 됨.<br>
     * <code>CharSetUtil.evaluateSet(null)</code>.
     * </p>
     * <p>
     * 참고로 본 생성자는 JavaBean 인스탄스를 위해 public으로 선언됨
     * </p>
     */
    public CharSetUtil() {
    }

    // Factory
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 수행해야 할 <code>CharSet</code> 인스탄스를 얻는다.
     * </p>
     * <p>
     * 구문은 아래와 같다.:
     * </p>
     * <ul>
     * <li>"aeio" : 'a','e','i','o' 를 포함함.</li>
     * <li>"^e" : 'e'를 포함하지 않음.</li>
     * <li>"ej-m" : 'e','j','k','l','m' 을 포함함.</li>
     * </ul>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.evaluateSet(null) = null
     * CharSetUtil.evaluateSet([]) = 매칭되는 CharSet 없음
     * CharSetUtil.evaluateSet(["a-e"]) = a,b,c,d,e에 매칭됨
     *
     * @param set
     *        캐릭터 셋
     * @return CharSet 인스탄스, <code>null</code>입력시 null 리턴.
     * @deprecated {@link CharSet#getInstance(String)}를 이용할 것.
     */
    public static CharSet evaluateSet(String[] set) {
        if (set == null) {
            return null;
        }
        return new CharSet(set);
    }

    // Squeeze
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열의 반복값을 없앤다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.squeeze(null, *) = null
     * CharSetUtil.squeeze("", *) = "" 
     * CharSetUtil.squeeze(*, null) = * 
     * CharSetUtil.squeeze(*, "") = *
     * CharSetUtil.squeeze("hello", "k-p") = "helo"
     * CharSetUtil.squeeze("hello", "a-e") = "hello"
     * CharSetUtil.squeeze("12234", "1-2") = "1234"
     * CharSetUtil.squeeze("12234", "3-4") = "12234"
     * CharSetUtil.squeeze("12234", "5-9") = "12234"
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        스퀴즈에 이용될 캐릭터셋
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     */
    public static String squeeze(String str, String set) {
        if (str == null || str.length() == 0 || set == null || set.length() == 0) {
            return str;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return squeeze(str, strs);
    }

    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열의 반복값을 없앤다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.squeeze("hello", {"el"}) = "helo"
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        스퀴즈에 이용될 캐릭터셋 배열
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     */
    public static String squeeze(String str, String[] set) {
        if (str == null || str.length() == 0 || set == null || set.length == 0) {
            return str;
        }
        CharSet chars = evaluateSet(set);
        StringBuffer buffer = new StringBuffer(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        char lastChar = ' ';
        char ch = ' ';
        for ( int i = 0 ; i < sz ; i++ ) {
            ch = chrs[i];
            if (chars.contains(ch)) {
                if ((ch == lastChar) && (i != 0)) {
                    continue;
                }
            }
            buffer.append(ch);
            lastChar = ch;
        }
        return buffer.toString();
    }

    // Count
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열에 존재하는 캐릭터 갯수를 얻는다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.count(null, *) = 0 
     * CharSetUtil.count("", *) = 0
     * CharSetUtil.count(*, null) = 0 
     * CharSetUtil.count(*, "") = 0
     * CharSetUtil.count("hello", "k-p") = 3 (llo)
     * CharSetUtil.count("hello", "a-e") = 1 (e)
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        찾을 캐릭터 셋
     * @return 일치하는 캐릭터 갯수, null 입력시 '0' 리턴.
     */
    public static int count(String str, String set) {
        if (str == null || str.length() == 0 || set == null || set.length() == 0) {
            return 0;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return count(str, strs);
    }

    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열에 존재하는 캐릭터 갯수를 얻는다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.count("hello", {"c-f", "o"}) = 2 (eo)
     * CharSetUtil.count("hello", {"a-l", "o"}) = 5 (hello)
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        찾을 캐릭터 셋 배열
     * @return 일치하는 캐릭터 갯수, null 입력시 '0' 리턴.
     */
    public static int count(String str, String[] set) {
        if (str == null || str.length() == 0 || set == null || set.length == 0) {
            return 0;
        }
        CharSet chars = evaluateSet(set);
        int count = 0;
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        for ( int i = 0 ; i < sz ; i++ ) {
            if (chars.contains(chrs[i])) {
                count++;
            }
        }
        return count;
    }

    // Keep
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열과 일치하는 캐릭터를 얻는다(유지한다).
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.keep(null, *) = null 
     * CharSetUtil.keep("", *) = ""
     * CharSetUtil.keep(*, null) = "" 
     * CharSetUtil.keep(*, "") = ""
     * CharSetUtil.keep("hello", "hl") = "hll"
     * CharSetUtil.keep("hello", "le") = "ell"
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        얻을(유지할) 캐릭터셋
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     * @since 2.0
     */
    public static String keep(String str, String set) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0 || set == null || set.length() == 0) {
            return "";
        }
        String[] strs = new String[1];
        strs[0] = set;
        return keep(str, strs);
    }

    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열과 일치하는 캐릭터를 얻는다(유지한다).
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.keep("hello", {"c-f", "o"}) = eo
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        얻을(유지할) 캐릭터셋 배열
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     * @since 2.0
     */
    public static String keep(String str, String[] set) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0 || set == null || set.length == 0) {
            return "";
        }
        return modify(str, set, true);
    }

    // Delete
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열과 일치하는 캐릭터를 지운다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.delete(null, *) = null 
     * CharSetUtil.delete("", *) = ""
     * CharSetUtil.delete(*, null) = * 
     * CharSetUtil.delete(*, "") = *
     * CharSetUtil.delete("hello", "hl") = "eo"
     * CharSetUtil.delete("hello", "le") = "ho"
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        삭제할 캐릭터셋
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     */
    public static String delete(String str, String set) {
        if (str == null || str.length() == 0 || set == null || set.length() == 0) {
            return str;
        }
        String[] strs = new String[1];
        strs[0] = set;
        return delete(str, strs);
    }

    /**
     * <p>
     * 지정한 set에 대해서 주어진 문자열과 일치하는 캐릭터를 지운다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.delete("hello", {"c-f", "o"}) = "hll"
     *
     * @see #evaluateSet(java.lang.String[]) set 구문보기 예제.
     * @param str
     *        대상 문자열
     * @param set
     *        삭제할 캐릭터셋 배열
     * @return 수정된 문자열, <code>null</code>입력시 null 리턴.
     */
    public static String delete(String str, String[] set) {
        if (str == null || str.length() == 0 || set == null || set.length == 0) {
            return str;
        }
        return modify(str, set, false);
    }

    // -----------------------------------------------------------------------
    /**
     * delete()와 keep() 함수에서 내부적으로 호출한다.
     */
    private static String modify(String str, String[] set, boolean expect) {
        CharSet chars = evaluateSet(set);
        StringBuffer buffer = new StringBuffer(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        for ( int i = 0 ; i < sz ; i++ ) {
            if (chars.contains(chrs[i]) == expect) {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }

    // Translate
    // -----------------------------------------------------------------------
    /**
     * <p>
     * 문자열에서 지정한 값으로 캐릭터 단위로 옮긴다.<br />
     * 이 함수는 다중 캐릭터에 대해서 대체하는 루틴이 적용된다.
     * </p>
     * 
     * @tdf2.example . 
     * 
     * CharSetUtil.translate(null, *, *) = null
     * CharSetUtil.translate("", *, *) = ""
     * CharSetUtil.translate("hello", "ho", "jy") = "jelly"
     * StringUtil.fastReplace("hello", "ho", "jy") = "hello"
     * StringUtil.replace("hello", "ho", "jy") = "hello"
     *
     * @param str
     *        대상 문자열
     * @param searchChars
     *        찾을 문자열
     * @param replaceChars
     *        변환할 문자열, 공백이거나 null이면 절대 안된다.
     * @return 변환된 문자열, <code>null</code>입력시 null 리턴.
     * @throws NullPointerException
     *         찾을 문자열이나 변환할 문자열이 <code>null</code>이면 발생
     * @throws ArrayIndexOutOfBoundsException
     *         찾을 문자열이 공백이면 발생.
     * @deprecated 가급적 {@link StringUtil#replaceChars(String, String, String)}를
     *             이용할 것.
     */
    public static String translate(String str, String searchChars, String replaceChars) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(str.length());
        char[] chrs = str.toCharArray();
        char[] withChrs = replaceChars.toCharArray();
        int sz = chrs.length;
        int withMax = replaceChars.length() - 1;
        for ( int i = 0 ; i < sz ; i++ ) {
            int idx = searchChars.indexOf(chrs[i]);
            if (idx != -1) {
                if (idx > withMax) {
                    idx = withMax;
                }
                buffer.append(withChrs[idx]);
            }
            else {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }

}
