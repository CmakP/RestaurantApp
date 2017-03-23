/**
 * Project: 1-ResumeDemo
 * File: Ingredient.java
 * Date: Aug 27, 2016
 * Time: 2:52:08 PM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

import ca.siamakpurian.demo.mvc.data.product.Product;

/**
 * @author Siamak Pourian
 *
 * Ingredient Class represents the daily ingredients in restaurant kitchen, cafe or bar 
 */
public class Ingredient extends Product {

	protected String unitSymbol;
	
	/**
	 * @param id product id
	 * @param price product price
	 * @param name product name
	 * @param description brief description of the product
	 * @param unitSymbol the abbreviated measuring symbol of the ingredient
	 */
    public Ingredient(int id, String name, int price, String description, String unitSymbol) {
		super(id, name, price, description);
		this.unitSymbol = unitSymbol;
	}

	@Override
	public void calculatePrice() {}

	@Override
	public void calculateCost() {}

	@Override
	public void calculateProfitMargin() {}

}
