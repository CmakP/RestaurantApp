/**
 * Project: 1-ResumeDemo
 * File: Menu.java
 * Date: Aug 26, 2016
 * Time: 2:24:50 PM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

import java.util.List;

/**
 * @author Siamak Pourian, A009772249
 *
 * Menu Class
 */
public class MenuList {

	private List<MenuItem> menuList;

	/**
	 * @return the menuList a List of all the items in the menu
	 */
	public List<MenuItem> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the List containing all the menu items to be set
	 */
	public void setMenuList(List<MenuItem> menuList) {
		this.menuList = menuList;
	}
	

}
