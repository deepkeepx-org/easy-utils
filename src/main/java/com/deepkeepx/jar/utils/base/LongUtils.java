package com.deepkeepx.jar.utils.base;

/**
 * This class is designed to handle long related operations.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class LongUtils {
	
	/**
	 * Used to check if the long is empty.
	 *
	 * @param val the string to check
	 * @return true if the long is empty, false otherwise
	 */
	public static Boolean isEmpty(Long val) {
		return val == null || val.equals(0L);
	}
	
}