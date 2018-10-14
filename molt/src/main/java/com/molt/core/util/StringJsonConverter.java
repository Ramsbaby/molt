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

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * String ������.
 * <p>
 * 
 * @author <a href="http://jodd.sourceforge.net">jOdd</a>
 * @author <a href="mailto:admin@jdevelop.co.kr">kang, dong-youn</a>
 * @version $Id: StringConverter.java 17 2007-03-21 01:28:50Z javaman $
 */
public class StringJsonConverter implements JsonSerializer<String>, JsonDeserializer<String> { 
	public JsonElement serialize(String src, Type typeOfSrc, 
			JsonSerializationContext context) { 
		if ( src == null ) { 
			return new JsonPrimitive(new String("")); 
		} else { 
			return new JsonPrimitive(src.toString()); 
		}
	} 
	public String deserialize(JsonElement json, Type typeOfT, 
			JsonDeserializationContext context) 
					throws JsonParseException { 
		return json.getAsJsonPrimitive().getAsString(); 
	} 
}