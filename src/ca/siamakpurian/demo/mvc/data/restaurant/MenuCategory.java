/**
 * Project: 1-ResumeDemo
 * File: MenuCategory.java
 * Date: Aug 26, 2016
 * Time: 2:39:55 PM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

/**
 * @author Siamak Pourian
 *
 * MenuCategory Enum
 */
public enum MenuCategory {
	
	// do not change the order of the following as being sorted in the DB correspondingly
	// append to the end of the list
	APPETIZER("Starter & Salad", 0), MAIN_COURSE("Main", 1), DESSERT("Pastry & Cold Dessert", 2), COFFEE("Hot Beverage", 3), SOFT_DRINK("Non-Alchoholic Beverages", 4), ALCHOHOLIC_BEVERAG("Bar Menu", 5);  

	private String description;
	private int index;
	
	MenuCategory(String description, int index) {
		this.description = description;
		this.index = index;
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
		this.description = description;
	}

	/**
	 * @return the column
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Displays this enum's index, name and description
	 */
	public String toStringJList() {
		return this.index + "  " + this.name() + "    " + this.description;
	}
}
