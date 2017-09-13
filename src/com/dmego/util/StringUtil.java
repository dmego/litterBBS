package com.dmego.util;
/**
 * String ������
 * @author dmego
 *
 */
public class StringUtil {
	/**
	 * String ת float
	 * @param str
	 * @return
	 */
	public static float StrToFlo(String str) {
		float result = 0;
		try {
			result = Integer.parseInt(str);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
	/**
	 * String ת int 
	 * @param str
	 * @return
	 */
	public static int StrToInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		}catch(NumberFormatException e) {
			result = 0;
		}
		return result;
	}
	
}
