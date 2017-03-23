package ca.siamakpurian.demo.mvc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.dao.MenuItemDao;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;
import ca.siamakpurian.demo.mvc.ui.abstractlists.MenuListModel;
import ca.siamakpurian.demo.mvc.ui.abstractlists.MenuListItem;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuDialogList extends JDialog {

	private static final Logger LOG = LogManager.getLogger(MenuDialogList.class);

	private final JPanel contentPanel = new JPanel();
	private JList<MenuListItem> menuItemsList;
	private MenuListModel menuItemModel;

	/**
	 * Create the dialog.
	 */
	public MenuDialogList() {
		
		menuItemModel = new MenuListModel();
		
		setTitle("Menu Item List");
		setModal(true);
		setBounds(100, 100, 505, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		{
			JLabel lblNewLabel = new JLabel("  ID  Name  Price    Menu-Category   Sub-Category   Description    Ingredients    Recipe");
			contentPanel.add(lblNewLabel, "cell 0 0");
		}
		
		menuItemsList = new JList<MenuListItem>(menuItemModel);
		menuItemsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
	    		if (event.getValueIsAdjusting()) {
					return;
				}
	    		int index = menuItemsList.getSelectedIndex();
	    		MenuListItem item = menuItemsList.getSelectedValue();
	    		if(item != null) {
	    		    try {
						showMenuItemDetails(item, index);
					} catch (SQLException e) {
						e.getMessage();
					}
	    		}
	    	}
	    });
		contentPanel.add(new JScrollPane(menuItemsList), "cell 0 1,grow");
		menuItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try {
			List<MenuItem> list = MenuItemDao.getTheInstance().getUnitsList();
			menuItemModel.setMenuItems(list);
			LOG.debug("Menu data set in the list model");
		} catch (SQLException | ApplicationException e) {
			e.getMessage();
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
						MenuDialogList.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Displays the details of this menu item
	 * 
	 * @param menuItem to be shown
	 * @param index at which this menu item is located in the list
	 */
	public void showMenuItemDetails(MenuListItem item, int index) throws SQLException {
		MenuItemDialog dialog = new MenuItemDialog(item.getMenuItem());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		MenuItem menuItem = dialog.getMenuItem();
		menuItemsList.clearSelection();
		if(menuItem != null) {
			MenuItemDao.getTheInstance().update(menuItem);
	//		MenuItemListModel.setElementAt(menuItem, index);
			JOptionPane.showMessageDialog(MenuDialogList.this, "Unit updated.", "Info", JOptionPane.INFORMATION_MESSAGE);
		    LOG.debug(String.format("Updated unit: %s", menuItem));
		}
	}
}
