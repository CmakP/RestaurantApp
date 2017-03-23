/**
 * Project: 1-ResumeDemo
 * File: ValidateString.java
 * Date: Sep 4, 2016
 * Time: 2:17:31 PM
 */
package ca.siamakpurian.demo.mvc.util;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;

/**
 * @author Siamak Pourian
 *
 * ValidateInput Class
 */
public class Validator {

	public static final double ZERO = 0.0;
	
	/**
	 * Checks the validation of user input string against being null or empty
	 * 
	 * @param input the user input string to be validated
	 * @throws ApplicationException 
	 */
    public static void validateString(String input) throws ApplicationException {
    	if(input == null) {
    		throw new ApplicationException("Input string is null!");
    	}
    	if(input.trim().length() == 0) {
    		throw new ApplicationException("Empty input!");
    	}
    }
    
    /**
     * Checks the validation of the entered amount against being negative or zero
     * 
     * @param number the user input amount to be validated
     * @throws ApplicationException
	 */
    public static void validateNumber(double price) throws ApplicationException {
    	if(price < ZERO) {
    		throw new ApplicationException("Price can't be negative!");
    	}
    	if(price == ZERO) {
    		throw new ApplicationException("Price can't be zero!");
    	}    	
    }
}
