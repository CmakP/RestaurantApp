/**
 * Project: 1-ResumeDemo
 * File: UnitListModel.java
 * Date: Sep 6, 2016
 * Time: 12:27:17 PM
 */
package ca.siamakpurian.demo.mvc.ui.abstractlists;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import ca.siamakpurian.demo.mvc.data.restaurant.Unit;

/**
 * @author Siamak Pourian, A009772249
 *
 * UnitListModel Class
 */
@SuppressWarnings("serial")
public class UnitListModel extends AbstractListModel<UnitListItem> {
	
	private List<UnitListItem> unitItems;

	/**
	 * Default constructor
	 */
	public UnitListModel() {
		unitItems = new ArrayList<UnitListItem>();
	}
	
	/**
	 * Initializes the list with the list of units
	 * 
	 * @param units to be set in the AbstractList model
	 */
	public void setUnits(List<Unit> units) {
		for (Unit unit : units) {
			unitItems.add(new UnitListItem(unit));
		}
	}
	
	/**
	 * Sets the unit at the index in the abstract list
	 * 
	 * @param unit to be set
	 * @param index the position to be set in the list
	 */
	public void setElementAt(Unit unit, int index) {
		if(index < unitItems.size() && unit != null) {
			UnitListItem item = unitItems.get(index);
            item.setUnit(unit);
		}
	}

	@Override
	public UnitListItem getElementAt(int index) {
		return unitItems.get(index);
	}

	@Override
	public int getSize() {
		return unitItems == null ? 0 : unitItems.size();
	}
	
//	/**
//	 * @param unit to be added to the list model
//	 * @param index to be set at
//	 */
//	public void setElementAt(Unit unit, int index) {
//		UnitListItem item = null;
//		if (index >= unitItems.size() || unitItems.get(index) == null) {
//			item = new UnitListItem(unit);
//			unitItems.add(item);
//		} else {
//			item = unitItems.get(index);
//			item.setunit(unit);
//		}
//	}
}
