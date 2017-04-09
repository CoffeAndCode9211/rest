package com.webservice.dto;

public final class StringUtil {
	
	
	public static boolean isNullOrBlank(final String str){
		return null == str || str.trim().isEmpty() || "null".equalsIgnoreCase(str.trim());
	}
	
	public static String removeNull(final String str){
		if(isNullOrBlank(str)){
			return "";
		}else{
			return str;
		}
	}
	
	public static boolean isNullCombo(final String str){
	   return null == str || str.isEmpty() || "-1".equals(str) || "null".equalsIgnoreCase(str.trim());
	}
	
	

}