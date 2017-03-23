/**
 * Project: 1-ResumeDemo
 * File: DataManager.java
 * Date: Aug 30, 2016
 * Time: 12:54:01 PM
 */
package ca.siamakpurian.demo.mvc.data;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.dao.MenuItemDao;
import ca.siamakpurian.demo.mvc.dao.UnitDao;

/**
 * @author Siamak Pourian
 *
 * DataManager Class
 */
public class DataManager {

//	private final Database database;
	
	private static final Logger LOG = LogManager.getLogger(DataManager.class);
	
	/**
	 * 
	 */
	public DataManager() {
    //    database = Database.getDatabaseInstance();
	}
	
	/**
	 * Creates the tables only if they don't exist in the db and then loads the collections
	 * into the corresponding databases
	 * 
	 */
	public void init() {
		
		// checks whether the tables exist, if not creates them
	    try {
			createTables();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.fatal(e.getMessage());
		}	    
	}

	/**
	 * Creates the tables if they have not already been created in the db
	 * 
	 * @throws SQLException
	 */
	private void createTables() throws SQLException {
		String tableName;
		tableName = UnitDao.TABLE_NAME;
		if (!Database.tableExists(tableName)) {
			// Note that there's no need for the reference to be passed as there is 
			// only one instance of the reference and could be accessed as below
			UnitDao.getTheInstance().create();      
			LOG.debug(String.format("%s table created", tableName));
		} else {
			LOG.debug(String.format("%s table already exists in the database", tableName));
		}
		tableName = MenuItemDao.TABLE_NAME;
		if (!Database.tableExists(tableName)) {
			// Note that there's no need for the reference to be passed as there is 
			// only one instance of the reference and could be accessed as below
			MenuItemDao.getTheInstance().create();      
			LOG.debug(String.format("%s table created", tableName));
		} else {
			LOG.debug(String.format("%s table already exists in the database", tableName));
		}		
	}
}
