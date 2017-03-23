package ca.siamakpurian.demo.mvc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuCategory;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.event.PopupMenuListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.event.PopupMenuEvent;

@SuppressWarnings("serial")
public class MenuItemDialog extends JDialog {
	
	private static final Logger LOG = LogManager.getLogger(MenuItemDialog.class);

	private final JPanel contentPanel = new JPanel();
	
	private MenuItem menuItem;
	
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	
	private JComboBox<String> cmbMenuCategory;
	private JComboBox<String> cmbSubCategory;

	private JTextArea txtDescription;
	private JTextArea txtIngredients;
	private JTextArea txtRecipe;
	
	/**
	 * Create the dialog.
	 */
	public MenuItemDialog(MenuItem menuItem) {
		this.menuItem = menuItem;
		
		setTitle("Menu Item");
		
		setModal(true);
		setBounds(100, 100, 450, 452);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][60,grow][60,grow][60,grow]"));
		
		// Id text box
		{
			JLabel lblId = new JLabel("Id");
			contentPanel.add(lblId, "cell 0 0,alignx left");
		}
		{
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setEnabled(false);
			contentPanel.add(txtId, "cell 1 0,growx");
			txtId.setColumns(10);
		}
		
		// Name text box
		{
			JLabel lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblName, "cell 0 1,alignx left");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
		}
		
		// Price text box
		{
			JLabel lblPrice = new JLabel("Price");
			lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblPrice, "cell 0 2,alignx left");
		}
		{
			txtPrice = new JTextField();
			contentPanel.add(txtPrice, "cell 1 2,growx");
			txtPrice.setColumns(10);
		}
		
		// Menu category combo box
		{
			JLabel lblMenuCategory = new JLabel("Menu Category");
			lblMenuCategory.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblMenuCategory, "cell 0 3,alignx left");
		}
		{
			cmbMenuCategory = new JComboBox<String>();
			cmbMenuCategory.addPopupMenuListener(new PopupMenuListener() {
				int index = -1;
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					index = cmbMenuCategory.getSelectedIndex();
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					index = cmbMenuCategory.getSelectedIndex();
					cmbMenuCategory.removeAllItems();
					loadMenuCategory();
					cmbMenuCategory.setSelectedIndex(index);
				}
			});
			contentPanel.add(cmbMenuCategory, "cell 1 3,growx");
		}
		{
			JLabel lblSubCategory = new JLabel("Sub Category");
			contentPanel.add(lblSubCategory, "cell 0 4,alignx left");
		}
		{
			cmbSubCategory = new JComboBox<String>();
			cmbSubCategory.addPopupMenuListener(new PopupMenuListener() {
				int index = -1;
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					index = cmbSubCategory.getSelectedIndex();
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					index = cmbSubCategory.getSelectedIndex();
					//cmbSubCategory.removeAllItems();
					//loadSubCategory();
					cmbSubCategory.setSelectedIndex(index);
				}
			});
			cmbSubCategory.addItem("None");
			contentPanel.add(cmbSubCategory, "cell 1 4,growx");
		}
		
		// Description text area
		{
			JLabel lblDescription = new JLabel("Description");
			contentPanel.add(lblDescription, "cell 0 5,alignx left");
		}
		{
			txtDescription = new JTextArea();
			contentPanel.add(new JScrollPane(txtDescription), "cell 1 5,grow");
		}
		
		// Ingredients text area
		{
			JLabel lblIngredients = new JLabel("Ingredients");
			contentPanel.add(lblIngredients, "cell 0 6,alignx left");
		}
		{
			txtIngredients = new JTextArea();
			contentPanel.add(new JScrollPane(txtIngredients), "cell 1 6,grow");
		}
		
		// Recipe text area
		{
			JLabel lblRecipe = new JLabel("Recipe");
			contentPanel.add(lblRecipe, "cell 0 7");
		}
		{
			txtRecipe = new JTextArea();
			contentPanel.add(new JScrollPane(txtRecipe), "cell 1 7,grow");
		}
		
		// Button pane
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Save button
				
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int option = 0;
				        try {
				        	setMenuItem();
						    MenuItemDialog.this.dispose();
					    } catch (ApplicationException ex) {
							String error = String.format("%s\nRetry?", ex.getMessage());
							option = JOptionPane.showConfirmDialog(MenuItemDialog.this, error, "Error", 2, 0);
							LOG.info(error);
						}
				        if(option == 2)  // discard
							closeDialog();	
					}
				});
				btnSave.setActionCommand("Save");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				//Cancel button
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setFields();
	}
	
	/**
	 * Sets the fields for this menuItem
	 */
	private void setFields() {
		txtId.setText(Integer.toString(this.menuItem.getId()));
		txtName.setText(this.menuItem.getName());
		txtPrice.setText(Double.toString(this.menuItem.getPrice()));
		if(this.menuItem.getMenuCategory() == null) {
			cmbMenuCategory.setSelectedIndex(-1);				
		} else {
			loadMenuCategory();
			cmbMenuCategory.setSelectedIndex(this.menuItem.getMenuCategory().ordinal());
		}
		cmbSubCategory.setSelectedItem(this.menuItem.getSubCategory());
		txtDescription.setText(this.menuItem.getDescription());
		txtRecipe.setText(this.menuItem.getRecipe());
	}

	/**
	 * Adds the menu categories to the combo box
	 */
	private void loadMenuCategory() {
		MenuCategory menuCategory[] = MenuCategory.values();
		int index = 0;
		while(index < menuCategory.length) {
			cmbMenuCategory.addItem(menuCategory[index].name());
			index++;
		}
	}
	
	/**
	 * Sets menuItem object from MenuItem's inputs
	 * @throws ApplicationException 
	 * @throws  
	 */
	public void setMenuItem() throws ApplicationException {
		menuItem.setName(txtName.getText());
		try {
    	    menuItem.setPrice(Double.parseDouble(txtPrice.getText()));
    	} catch(java.lang.NumberFormatException e) {
    		txtPrice.setText("0.0");
    		throw new ApplicationException("Price must be a number grater than zero.");
    	} catch(ApplicationException ex) {
    		txtPrice.setText("0.0");
    		throw new ApplicationException(ex.getMessage());
    	}
		menuItem.setMenuCategory(cmbMenuCategory.getSelectedIndex());
		menuItem.setSubCategory(cmbSubCategory.getSelectedItem().toString());
		menuItem.setDescription(txtDescription.getText());
		menuItem.setRecipe(txtRecipe.getText());
	}
	
	/**
	 * Closes the dialog and finalizes the instance variable
	 */
	private void closeDialog() {
		MenuItemDialog.this.menuItem = null;
		MenuItemDialog.this.dispose();
	}

	/**
	 * @return the menuItem
	 */
	public MenuItem getMenuItem() {
		return menuItem;
	}
}
