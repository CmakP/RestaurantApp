package ca.siamakpurian.demo.mvc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import ca.siamakpurian.demo.mvc.data.restaurant.MenuCategory;
import ca.siamakpurian.demo.mvc.ui.abstractlists.DefaultSubCategoryListModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

@SuppressWarnings("serial")
public class MenuCategoryList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JList<String> menuCategoryList;
	private DefaultSubCategoryListModel menuCategories;
	
	/**
	 * Create the dialog.
	 */
	public MenuCategoryList() {
		
		this.menuCategories = new DefaultSubCategoryListModel();
		
		setTitle("Menu Category List");
		setModal(true);
		setBounds(100, 100, 410, 274);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		{
			JLabel lblIdMenuCategory = new JLabel("ID   Menu Category      Description");
			contentPanel.add(lblIdMenuCategory, "cell 0 0");
		}
		{
			menuCategoryList = new JList<String>(menuCategories);
			contentPanel.add(new JScrollPane(menuCategoryList), "cell 0 1,grow");
			menuCategoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		
		MenuCategory menuCategories[] = MenuCategory.values();
		for(MenuCategory menuCategory : menuCategories) {
		    this.menuCategories.addElement(menuCategory.toStringJList());
		}
		
		{
			// Button pane
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Cancel button
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MenuCategoryList.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
