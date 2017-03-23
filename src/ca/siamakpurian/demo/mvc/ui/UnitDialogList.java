package ca.siamakpurian.demo.mvc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.dao.UnitDao;
import ca.siamakpurian.demo.mvc.data.restaurant.Unit;
import ca.siamakpurian.demo.mvc.ui.abstractlists.UnitListItem;
import ca.siamakpurian.demo.mvc.ui.abstractlists.UnitListModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.sql.SQLException;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class UnitDialogList extends JDialog {

	private static final Logger LOG = LogManager.getLogger(UnitDialogList.class);

	private final JPanel contentPanel = new JPanel();
	private JList<UnitListItem> unitsList;
	private UnitListModel unitModel;

	/**
	 * Create the dialog.
	 */
	public UnitDialogList() {

		unitModel = new UnitListModel();
		
		setModal(true);
		setTitle("Unit List");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		{
			JLabel lblId = new JLabel("     ID     Unit     Symbol\n");
			contentPanel.add(lblId, "cell 0 0,alignx left");
		}

		unitsList = new JList<UnitListItem>(unitModel);
	    contentPanel.add(new JScrollPane(unitsList), "cell 0 1,grow");
		unitsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set the data
		try {
			List<Unit> list = UnitDao.getTheInstance().getUnitsList();
//			int index = 0;
			unitModel.setUnits(list);
//			for (Unit unit : list) {
//				unitModel.setElementAt(unit, index++);
//			}
			LOG.debug("Unit data set in the list model");
		} catch (ApplicationException | SQLException e) {
			e.getMessage();
		}
		   
		// JList
		{
		    unitsList.addListSelectionListener(new ListSelectionListener() {
		    	public void valueChanged(ListSelectionEvent event) {
		    		if (event.getValueIsAdjusting()) {
						return;
					}
		    		int index = unitsList.getSelectedIndex();
		    		UnitListItem item = unitsList.getSelectedValue();
		    		if(item != null) {
		    		    try {
							showUnitDetails(item, index);
						} catch (SQLException e) {
							e.getMessage();
						}
		    		}
		    	}
		    });
		}

		// Button panel
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UnitDialogList.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Displays the details of the selected Unit
	 * 
	 * @param unit the unit to be displayed
	 * @param index the index of the selected unit
	 * @throws SQLException 
	 */
    private void showUnitDetails(UnitListItem item, int index) throws SQLException {
    	UnitDialog dialog = new UnitDialog(item.getUnit());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		Unit unit = dialog.getUnit();
		if(unit != null) {
			UnitDao.getTheInstance().update(unit);
	//		unitModel.setElementAt(unit, index);
			unitsList.clearSelection();
			JOptionPane.showMessageDialog(UnitDialogList.this, "Unit updated.", "Info", JOptionPane.INFORMATION_MESSAGE);
		    LOG.debug(String.format("Updated unit: %s", unit));
		}
    }
}
