/**
 * Project: 1-ResumeDemo
 * File: ApplicationException.java
 * Date: Aug 21, 2016
 * Time: 6:52:25 PM
 */
package ca.siamakpurian.demo.mvc.applicationexception;

/**
 * @author Siamak Pourian
 *
 *         ApplicationException Class
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * Default constructor
	 */
	public ApplicationException() {
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
