/**
 * Project: 1-ResumeDemo
 * File: MenuItem.java
 * Date: Aug 25, 2016
 * Time: 10:39:33 PM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.data.product.Product;
import ca.siamakpurian.demo.mvc.util.Validator;

/**
 * @author Siamak Pourian
 *
 * MenuItem Class
 */
public class MenuItem extends Product {

	private MenuCategory menuCategory;
	private String subCategory;
	private String recipe;
	
	//private List<Ingredient> itemIngredients;   // Joint table serves better
	
	/**
	 * Default constructor
	 */
	public MenuItem() {
	}
	
	/**
	 * @param id serial unique id
	 */
	public MenuItem(int id) {
		super(id);
	}

	/**
	 * @param id product id
	 * @param name product name
	 * @param price product price
	 * @param description brief description of the product
	 * @param menuCategory main menu category
	 * @param recipe details of the recipe
	 * @param subCategory a customized sub category
	 */
	public MenuItem(int id, String name, int price, String description, MenuCategory menuCategory, String subCategory, String recipe) {
		super(id, name, price, description);
		this.menuCategory = menuCategory;
		this.recipe = recipe;
		this.subCategory = subCategory;
	}

	/**
	 * @return the menuCategory
	 */
	public MenuCategory getMenuCategory() {
		return menuCategory;
	}

	/**
	 * @param index the menuCategory index to be used to retrieve the MenuCategory enum
	 * @throws ApplicationException 
	 */
	public void setMenuCategory(int index) throws ApplicationException {
		if(index == -1)
			throw new ApplicationException("Select a menu category.");
		MenuCategory menuCategories[] = MenuCategory.values();
		this.menuCategory = menuCategories[index];
	}

	/**
	 * @return the recipe
	 */
	public String getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe the recipe to set
	 */
	public void setRecipe(String recipe) {
		try {
			Validator.validateString(recipe);
		} catch (ApplicationException e) {
			recipe = NOT_AVAILABLE;
		}
		this.recipe = recipe;
	}

	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		try {
			Validator.validateString(subCategory);
		} catch (ApplicationException e) {
			subCategory = NOT_AVAILABLE;
		}
		this.subCategory = subCategory;
	}
	
	public String toStringListItem() {
		return id + "   " + name + "   " + price + "    " + menuCategory + "     " + subCategory + "     " + description + "    " + recipe;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuItem [menuCategory=" + menuCategory + ", subCategory=" + subCategory + ", recipe=" + recipe + "]";
	}

	public void calculatePrice(){};
	public void calculateCost(){};
	public void calculateProfitMargin(){};
}
