/**
 * Project: 1-ResumeDemo
 * File: UnitListItem.java
 * Date: Sep 7, 2016
 * Time: 9:50:39 PM
 */
package ca.siamakpurian.demo.mvc.ui.abstractlists;

import ca.siamakpurian.demo.mvc.data.restaurant.Unit;

/**
 * @author Siamak Pourian
 *
 * UnitListItem Class
 */
public class UnitListItem {

	private Unit unit;
	
	/**
	 * @param unit instance variable to be instantiated
	 */
	public UnitListItem(Unit unit) {
	    this.unit = unit;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (unit == null) {
			return null;
		}
		
		return unit.getId() + "       " + unit.getName() + "    " + unit.getSymbol();
}

}
