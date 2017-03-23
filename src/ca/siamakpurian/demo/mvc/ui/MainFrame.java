package ca.siamakpurian.demo.mvc.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.dao.MenuItemDao;
import ca.siamakpurian.demo.mvc.dao.UnitDao;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuCategory;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;
import ca.siamakpurian.demo.mvc.data.restaurant.Unit;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final Logger LOG = LogManager.getLogger(MainFrame.class);

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSale = new JMenu("Sale");
		menuBar.add(mnSale);

		// Res-Menu menu
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		// Add Item sub menu
		JMenuItem mntmAddItem = new JMenuItem("Add Item");
		mntmAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMenuItemDialog();
			}
		});
		
		// Menu List sub menu
		JMenuItem mntmMenuList = new JMenuItem("Menu List");
		mntmMenuList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showMenuList();
			}
		});
		mnMenu.add(mntmMenuList);
		mnMenu.add(mntmAddItem);
		
		JSeparator separator = new JSeparator();
		mnMenu.add(separator);
		
		JMenuItem mntmMenuCategories = new JMenuItem("Menu Categories");
		mntmMenuCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuCategoryList dialog = new MenuCategoryList();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		});
		mnMenu.add(mntmMenuCategories);
		
		JSeparator separator_1 = new JSeparator();
		mnMenu.add(separator_1);
		
		JMenuItem mntmSubCategories = new JMenuItem("Sub Categories");
		mnMenu.add(mntmSubCategories);
		
		JMenuItem mntmAddSubCategory = new JMenuItem("Add Sub Category");
		mnMenu.add(mntmAddSubCategory);

		// Ingredients menu
		
		JMenu mnIngredients = new JMenu("Ingredients");
		menuBar.add(mnIngredients);

		// Units sub menu
		
		JMenuItem mntmUnits = new JMenuItem("Units");
		mntmUnits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUnitDialogList();
			}
		});
		mnIngredients.add(mntmUnits);

		// Add Unit sub menu
		
		JMenuItem mntmAddUnit = new JMenuItem("Add Unit");
		mntmAddUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUnitDialog();
			}
		});
		mnIngredients.add(mntmAddUnit);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
	}

	/**
	 * Launches the MenuItemDialog
	 */
	private void showMenuItemDialog() {
		int capacity;
		MenuItem menuItem;
		try {
			capacity = MenuItemDao.getTheInstance().tableSize();
			MenuItemDialog dialog = new MenuItemDialog(new MenuItem(++capacity));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			menuItem = dialog.getMenuItem();
			if(menuItem != null) {
				MenuItemDao.getTheInstance().add(menuItem);
				JOptionPane.showMessageDialog(MainFrame.this, "Item added.", "Info", JOptionPane.INFORMATION_MESSAGE);
			    LOG.info(String.format("%s menu item saved to db.", menuItem.getName()));
			}
		} catch (Exception ex) {
			String error = ex.getMessage();
			LOG.debug(error);
		}
	}

	/**
	 * Launches the unitDialog in order to create a new unit
	 */
	private void showUnitDialog() {
		int capacity;
		Unit unit;
		try {
			capacity = UnitDao.getTheInstance().tableSize();
			unit = new Unit(++capacity);
			UnitDialog dialog = new UnitDialog(unit);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			unit = dialog.getUnit();
			if (unit != null) {
				UnitDao.getTheInstance().add(unit);
				JOptionPane.showMessageDialog(MainFrame.this, "New unit saved to db.", "Info", JOptionPane.INFORMATION_MESSAGE);
			    LOG.info(String.format("%s unit saved to db.", unit.getName()));
			}
		} catch (SQLException e) {
			String error = e.getMessage();
			LOG.debug(error);
		}
	}

	/**
	 * Launches the unitDialogList
	 */
	private void showUnitDialogList() {
		try {
			UnitDialogList dialog = new UnitDialogList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			String error = e.getMessage();
			LOG.debug(error);
	    }
	}
	
	/**
	 * Launches the menuItemDialogList
	 */
	private void showMenuList() {
		try {
			MenuDialogList dialog = new MenuDialogList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
