/**
 * Project: 1-ResumeDemo
 * File: DecimalFormatter.java
 * Date: Sep 10, 2016
 * Time: 10:27:46 PM
 */
package ca.siamakpurian.demo.mvc.util;

import java.text.DecimalFormat;

/**
 * @author Siamak Pourian
 *
 * DecimalFormatter Class, converts double to two decimal format
 */
public class DecimalFormatter {

	/**
	 * 
	 * @param number to be rounded up and converted to two decimal format
	 * @param rounding rounds the number if flag's set to true and not skips rounding otherwise
	 * @return number with two decimal places
	 */
	public static double roundUpTwoDecimals(double number, boolean rounding) {
		if(rounding)
		    number = Math.round(number * 100.0) / 100.0;
	    return Double.valueOf(new DecimalFormat("#.##").format(number));
    }
}
