/**
 * Project: 1-ResumeDemo
 * File: MainMenu.java
 * Date: Aug 23, 2016
 * Time: 3:36:17 PM
 */
package ca.siamakpurian.demo.mvc.data.product;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.util.DecimalFormatter;
import ca.siamakpurian.demo.mvc.util.Validator;

/**
 * @author Siamak Pourian
 *
 * Product Class
 */
public abstract class Product {

	public static final String NOT_AVAILABLE = "N/A";
	
	protected int id;
	protected double price;
	
	protected String name;
	protected String description;
	
	/**
	 * Default constructor
	 */
	public Product() {
	}

	/**
	 * @param id
	 */
	public Product(int id) {
		this.id = id;
	}

	/**
	 * @param id product
	 * @param price product price
	 * @param name product name
	 * @param description brief description of the product
	 */
	public Product(int id, String name, int price, String description) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 * @throws ApplicationException 
	 */
	public void setPrice(double price) throws ApplicationException {
		Validator.validateNumber(price);
		DecimalFormatter.roundUpTwoDecimals(price, false);
		this.price = price;
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
		name = name.trim().toLowerCase();
		this.name = name.substring(0,1).toUpperCase() + name.substring(1);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		try {
			Validator.validateString(description);
		} catch (ApplicationException e) {
			description = NOT_AVAILABLE;
		}
		this.description = description;
	}
	
	/**
	 * Calculates the retail price of the product based on cost and profit margins
	 */
	public abstract void calculatePrice();
	
	/**
	 * Calculates all the corresponding costs to a product 
	 */
	public abstract void calculateCost();
	
	/**
	 * Calculates the profit margin of each product
	 */
	public abstract void calculateProfitMargin();
}
