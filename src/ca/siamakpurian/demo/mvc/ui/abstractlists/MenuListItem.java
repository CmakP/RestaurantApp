/**
 * Project: 1-ResumeDemo
 * File: MenuListItem.java
 * Date: Sep 12, 2016
 * Time: 8:48:20 PM
 */
package ca.siamakpurian.demo.mvc.ui.abstractlists;

import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;

/**
 * @author Siamak Pourian
 *
 * MenuListItem Class
 */
public class MenuListItem {

	private MenuItem menuItem;

	/**
	 * Overloaded constructor
	 * 
	 * @param unit instance variable to be instantiated
	 */
	public MenuListItem(MenuItem menuItem) {
	    this.menuItem = menuItem;
	}
	
	/**
	 * @return the menuItem
	 */
	public MenuItem getMenuItem() {
		return menuItem;
	}

	/**
	 * @param menuItem the menuItem to set
	 */
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (menuItem == null) {
			return null;
		}
		
		return menuItem.getId() + "   " + menuItem.getName() + "   " + menuItem.getPrice() + "    " + menuItem.getMenuCategory() + "     " + menuItem.getSubCategory() + "     " + menuItem.getDescription() + "    " + menuItem.getRecipe();
    }
}
