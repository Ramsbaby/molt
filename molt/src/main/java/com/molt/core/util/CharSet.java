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

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 캐릭터셋을 정의한 클래스.
 * </p>
 * <p>
 * 본 클래스의 인스탄스는 변하지 않으나, 서브클래스는 그렇지 않을 수도 있다.
 * </p>
 * 
 * @author Henri Yandell
 * @author Stephen Colebourne
 * @author Phil Steitz
 * @author Pete Gieser
 * @author Gary Gregory
 * @author <a href="mailto:admin@jdevelop.co.kr">kang, dong-youn</a>
 * @version $Id: CharSet.java 17 2007-03-21 01:28:50Z javaman $
 */
public class CharSet implements Serializable {

    /** Serialization lock, Lang version 2.0. */
    private static final long serialVersionUID = 5947847346149275958L;

    /**
     * 값이 없는 CharSet.
     * 
     * @since 2.0
     */
    public static final CharSet EMPTY = new CharSet((String) null);

    /**
     * 아스키 알파벳 "a-zA-Z" 정의 CharSetA.
     * 
     * @since 2.0
     */
    public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");

    /**
     * 아스키 알파벳 "a-z" 정의 CharSetA.
     * 
     * @since 2.0
     */
    public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");

    /**
     * A CharSet defining ASCII alphabetic characters "A-Z".
     * 
     * @since 2.0
     */
    public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");

    /**
     * 아스키 알파벳 "0-9" 정의 CharSetA.
     * 
     * @since 2.0
     */
    public static final CharSet ASCII_NUMERIC = new CharSet("0-9");

    /**
     * 팩토리에서 공통으로 사용되는 맵 (아스키 알파벳 문자가 저장되어 있음). 서브클래스는 필요시 더 많은 공통패턴을 정의해도 된다.
     * 
     * @since 2.0
     */
    protected static final Map COMMON = new HashMap();

    static {
        COMMON.put(null, EMPTY);
        COMMON.put("", EMPTY);
        COMMON.put("a-zA-Z", ASCII_ALPHA);
        COMMON.put("A-Za-z", ASCII_ALPHA);
        COMMON.put("a-z", ASCII_ALPHA_LOWER);
        COMMON.put("A-Z", ASCII_ALPHA_UPPER);
        COMMON.put("0-9", ASCII_NUMERIC);
    }

    /** CharRange 객체의 셋. */
    private Set set = new HashSet();

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Factory method to create a new CharSet using a special syntax.
     * </p>
     * <ul>
     * <li><code>null</code> or empty string ("") - set containing no
     * characters</li>
     * <li>Single character, such as "a" - set containing just that character</li>
     * <li>Multi character, such as "a-e" - set containing characters from one
     * character to the other</li>
     * <li>Negated, such as "^a" or "^a-e" - set containing all characters
     * except those defined</li>
     * <li>Combinations, such as "abe-g" - set containing all the characters
     * from the individual sets</li>
     * </ul>
     * <p>
     * The matching order is:
     * </p>
     * <ol>
     * <li>Negated multi character range, such as "^a-e"
     * <li>Ordinary multi character range, such as "a-e"
     * <li>Negated single character, such as "^a"
     * <li>Ordinary single character, such as "a"
     * </ol>
     * <p>
     * Matching works left to right. Once a match is found the search starts
     * again from the next character.
     * </p>
     * <p>
     * If the same range is defined twice using the same syntax, only one range
     * will be kept. Thus, "a-ca-c" creates only one range of "a-c".
     * </p>
     * <p>
     * If the start and end of a range are in the wrong order, they are
     * reversed. Thus "a-e" is the same as "e-a". As a result, "a-ee-a" would
     * create only one range, as the "a-e" and "e-a" are the same.
     * </p>
     * <p>
     * The set of characters represented is the union of the specified ranges.
     * </p>
     * <p>
     * All CharSet objects returned by this method will be immutable.
     * </p>
     * 
     * @param setStr
     *        the String describing the set, may be null
     * @return a CharSet instance
     * @since 2.0
     */
    public static CharSet getInstance(String setStr) {
        Object set = COMMON.get(setStr);
        if (set != null) {
            return (CharSet) set;
        }
        return new CharSet(setStr);
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Constructs a new CharSet using the set syntax.
     * </p>
     * 
     * @param setStr
     *        the String describing the set, may be null
     * @since 2.0
     */
    protected CharSet(String setStr) {
        super();
        add(setStr);
    }

    /**
     * <p>
     * Constructs a new CharSet using the set syntax. Each string is merged in
     * with the set.
     * </p>
     * 
     * @param set
     *        Strings to merge into the initial set
     * @throws NullPointerException
     *         if set is <code>null</code>
     */
    protected CharSet(String[] set) {
        super();
        int sz = set.length;
        for ( int i = 0 ; i < sz ; i++ ) {
            add(set[i]);
        }
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Add a set definition string to the <code>CharSet</code>.
     * </p>
     * 
     * @param str
     *        set definition string
     */
    protected void add(String str) {
        if (str == null) {
            return;
        }

        int len = str.length();
        int pos = 0;
        while ( pos < len ) {
            int remainder = (len - pos);
            if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
                // negated range
                set.add(new CharRange(str.charAt(pos + 1), str.charAt(pos + 3), true));
                pos += 4;
            }
            else if (remainder >= 3 && str.charAt(pos + 1) == '-') {
                // range
                set.add(new CharRange(str.charAt(pos), str.charAt(pos + 2)));
                pos += 3;
            }
            else if (remainder >= 2 && str.charAt(pos) == '^') {
                // negated char
                set.add(new CharRange(str.charAt(pos + 1), true));
                pos += 2;
            }
            else {
                // char
                set.add(new CharRange(str.charAt(pos)));
                pos += 1;
            }
        }
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the internal set as an array of CharRange objects.
     * </p>
     * 
     * @return an array of immutable CharRange objects
     * @since 2.0
     */
    public CharRange[] getCharRanges() {
        return (CharRange[]) set.toArray(new CharRange[set.size()]);
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Does the <code>CharSet</code> contain the specified character
     * <code>ch</code>.
     * </p>
     * 
     * @param ch
     *        the character to check for
     * @return <code>true</code> if the set contains the characters
     */
    public boolean contains(char ch) {
        for ( Iterator it = set.iterator() ; it.hasNext() ; ) {
            CharRange range = (CharRange) it.next();
            if (range.contains(ch)) {
                return true;
            }
        }
        return false;
    }

    // Basics
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Compares two CharSet objects, returning true if they represent exactly
     * the same set of characters defined in the same way.
     * </p>
     * <p>
     * The two sets <code>abc</code> and <code>a-c</code> are <i>not</i>
     * equal according to this method.
     * </p>
     * 
     * @param obj
     *        the object to compare to
     * @return true if equal
     * @since 2.0
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CharSet == false) {
            return false;
        }
        CharSet other = (CharSet) obj;
        return (set.equals(other.set));
    }

    /**
     * <p>
     * Gets a hashCode compatable with the equals method.
     * </p>
     * 
     * @return a suitable hashCode
     * @since 2.0
     */
    public int hashCode() {
        return 89 + set.hashCode();
    }

    /**
     * <p>
     * Gets a string representation of the set.
     * </p>
     * 
     * @return string representation of the set
     */
    public String toString() {
        return set.toString();
    }

}
