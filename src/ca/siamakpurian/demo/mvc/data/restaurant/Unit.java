/**
 * Project: 1-ResumeDemo
 * File: Unit.java
 * Date: Aug 28, 2016
 * Time: 4:02:22 PM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.util.Validator;

/**
 * @author Siamak Pourian
 *
 * Unit Class represents symbols for the ingredient class
 */
public class Unit {

	public static final String KILOGRAM_SYMBOL = "kg";
	public static final String GRAM_SYMBOL = "g";
	public static final String POUND_SYMBOL = "lb";
	public static final String LITER_SYMBOL = "l";
	public static final String MILLILITER_SYMBOL = "ml";
	public static final String OUNCE_SYMBOL = "oz";
	
	private int id;
	private String name;
	private String symbol;

	/**
	 * Default constructor
	 */
	public Unit() {}

	/**
	 * @param id to be set as serial id
	 */
	public Unit(int id) {
		this.id = id;
		this.name = "";
		this.symbol = "";
	}

	/**
	 * @param id serial id for each unit
	 * @param name the unit name
	 * @param symbol the unit symbol
	 */
	public Unit(int id, String name, String symbol) {
		this.id = id;
		this.name = name;
		this.symbol = symbol;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 * @throws ApplicationException 
	 */
	public void setName(String name) throws ApplicationException {
		try {
			Validator.validateString(name);
		} catch (ApplicationException e) {
		    throw new ApplicationException("Name can't be empty.");
		}
		this.name = name.trim();
	}
	
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * @param symbol the symbol to set
	 * @throws ApplicationException 
	 */
	public void setSymbol(String symbol) throws ApplicationException {
		try {
			Validator.validateString(symbol);
		} catch (ApplicationException e) {
			throw new ApplicationException("Symbol can't be empty.");
		}
		this.symbol = symbol.trim().toLowerCase();
	}
	
	public String toStringListItem() {
		return id + "     " + name + "     " + symbol;
	}

	@Override
	public String toString() {
		return "Unit [id=" + id + ", name=" + name + ", symbol=" + symbol + "]";
	}
}
