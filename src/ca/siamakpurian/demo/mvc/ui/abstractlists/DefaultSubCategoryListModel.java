/**
 * Project: 1-ResumeDemo
 * File: DefaultSubCategoryListModel.java
 * Date: Sep 25, 2016
 * Time: 1:15:11 AM
 */
package ca.siamakpurian.demo.mvc.ui.abstractlists;

import javax.swing.DefaultListModel;

/**
 * @author Siamak Pourian
 *
 * DefaultSubCategoryListModel Class
 */
@SuppressWarnings("serial")
public class DefaultSubCategoryListModel extends DefaultListModel<String> {

	@Override
	public void addElement(String subCategory) {
		super.addElement(subCategory);
	}
}
