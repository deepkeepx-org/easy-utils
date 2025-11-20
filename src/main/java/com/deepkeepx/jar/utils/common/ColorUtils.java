package com.deepkeepx.jar.utils.common;

/**
 * This class is used to color utils.
 *
 * @author deepkeepx
 * @version 1.0.0
 */
public class ColorUtils {
	
	/**
	 * Create a random rgb color string.
	 *
	 * @return a random rgb color string
	 */
	public static String randomRGB() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	
	/**
	 * Create a random rgba color string.
	 *
	 * @param alpha the alpha value of the color
	 * @return a random rgba color string
	 */
	public static String randomRGBA(Double alpha) {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return "rgba(" + r + "," + g + "," + b + "," + alpha + ")";
	}
	
}