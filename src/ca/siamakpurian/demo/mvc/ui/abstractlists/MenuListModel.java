/**
 * Project: 1-ResumeDemo
 * File: MenuItemListModel.java
 * Date: Sep 12, 2016
 * Time: 2:26:08 PM
 */
package ca.siamakpurian.demo.mvc.ui.abstractlists;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;

/**
 * @author Siamak Pourian
 *
 * MenuItemListModel Class
 */
@SuppressWarnings("serial")
public class MenuListModel extends AbstractListModel<MenuListItem> {

	private List<MenuListItem> menuItems;
	
	public MenuListModel() {
		menuItems = new ArrayList<MenuListItem>();
	}
	
	@Override
	public MenuListItem getElementAt(int index) {
		return menuItems.get(index);
	}

	@Override
	public int getSize() {
		return menuItems == null ? 0 : menuItems.size();
	}

	/**
	 * Initializes the list with the list of units
	 * 
	 * @param list to be set in the AbstractList model
	 */
	public void setMenuItems(List<MenuItem> list) {
		for (MenuItem menuItem : list) {
			menuItems.add(new MenuListItem(menuItem));
		}
	}
	
	/**
	 * Sets the menuItem at the index in the abstract list
	 * 
	 * @param menuItem to be set
	 * @param index the position to be set in the list
	 */
	public void setElementAt(MenuItem menuItem, int index) {
		if(index < menuItems.size() && menuItem != null) {
		    MenuListItem item = menuItems.get(index);
		    item.setMenuItem(menuItem);
		}
	}
}
