/**
 * Project: 1-ResumeDemo
 * File: SubCategory.java
 * Date: Sep 25, 2016
 * Time: 12:37:52 AM
 */
package ca.siamakpurian.demo.mvc.data.restaurant;

/**
 * @author Siamak Pourian
 *
 * SubCategory Class
 */
public class SubCategory {

	private int id;
	private String subCategory;
	
	/**
	 * Default constructor
	 */
	public SubCategory() {}
	
	/**
	 * Overloaded constructor
	 * 
	 * @param id to be set as id
	 */
	public SubCategory(int id) {
		this.id = id;
		this.subCategory = "";
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
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
}
