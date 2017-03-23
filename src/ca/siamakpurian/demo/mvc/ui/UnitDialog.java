package ca.siamakpurian.demo.mvc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.data.restaurant.Unit;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UnitDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(UnitDialog.class);

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtSymbol;
	private JTextField txtId;
	
	private Unit unit;

	/**
	 * Create the unitDialog
	 * 
	 * @param unit to be re-instantiated
	 * @throws SQLException 
	 */
	public UnitDialog(Unit unit) throws SQLException {
		setTitle("Unit");
		
		this.unit = unit;
		
		setModal(true);
		setBounds(100, 100, 320, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		// Id text box
		{
			JLabel lblId = new JLabel("ID");
			contentPanel.add(lblId, "flowx,cell 0 0,alignx trailing");
		}
		{
			txtId = new JTextField();
			txtId.setEnabled(false);
			txtId.setEditable(false);
			contentPanel.add(txtId, "cell 1 0,growx,aligny top");
			txtId.setColumns(10);
		}
		
		// Name text box
		{
			JLabel lblUnitName = new JLabel("Name");
			contentPanel.add(lblUnitName, "cell 0 1,alignx left");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
		}
		
		// Symbol text box
		{
			JLabel lblSymbol = new JLabel("Symbol");
			contentPanel.add(lblSymbol, "cell 0 2,alignx left");
		}
		{
			txtSymbol = new JTextField();
			contentPanel.add(txtSymbol, "cell 1 2,growx,aligny top");
			txtSymbol.setColumns(10);
		}
		
		// Button Panel
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// Save Button
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = 0;
				        try {
							setUnit();
						    UnitDialog.this.dispose();
					    } catch (ApplicationException ex) {
							String error = String.format("%s\n Retry?", ex.getMessage());
							option = JOptionPane.showConfirmDialog(UnitDialog.this, error, "Error", 2, 0);
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
			
			// Cancel Button
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				        closeDialog();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
		setFields();
	}
	
	/**
	 * Sets the fields for this unit
	 */
	private void setFields() {
		txtId.setText(Integer.toString(this.unit.getId()));
		txtName.setText(this.unit.getName());
		txtSymbol.setText(this.unit.getSymbol());
	}
	
	/**
	 * Sets Unit object from unitDialog's inputs
	 * 
	 * @throws ApplicationException 
	 */
	private void setUnit() throws ApplicationException {
		unit.setName(txtName.getText());
		unit.setSymbol(txtSymbol.getText());	
	}
	
	/**
	 * Closes the dialog and finalizes the instance variable
	 */
	private void closeDialog() {
		UnitDialog.this.unit = null;
		UnitDialog.this.dispose();
	}

	/**
	 * Returns the updated unit or null if not 
	 * 
	 * @return Unit if changed or null if not
	 */
	public Unit getUnit() {
		return unit;
	}
}
