/**
 * Project: 1-ResumeDemo
 * File: MeniItemDao.java
 * Date: Sep 9, 2016
 * Time: 1:10:24 PM
 */
package ca.siamakpurian.demo.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.data.Database;
import ca.siamakpurian.demo.mvc.data.restaurant.MenuItem;

/**
 * @author Siamak Pourian
 *
 * MenuItemDao Class
 */
public class MenuItemDao extends Dao {

	public static final String TABLE_NAME = "MenuItem";

	private static final MenuItemDao menuItemDaoInstance = new MenuItemDao();
	private static final Logger LOG = LogManager.getLogger(MenuItemDao.class);

	/**
	 * private constructor for MenuItemDao in order to prevent multiple instantiation
	 * as there is only one instance of this object needed
	 */
	public MenuItemDao() {
		super(TABLE_NAME);
	}
    
    /**
	 * @return the menuItemDaoInstance reference for the MenuItem table
	 */
	public static MenuItemDao getTheInstance() {
		return menuItemDaoInstance;
	}
	
	/**
	 * Creates the unit table and the initializes the SQL statement
	 * 
	 * @throws SQLException
	 */
	@Override
	public void create() throws SQLException {
		String createStatement = String.format("CREATE TABLE %s(%s INTEGER, %s VARCHAR(10), %s DECIMAL(6,2), %s VARCHAR(40), %s INTEGER, %s VARCHAR(15), %s VARCHAR(50), primary key (%s) )", TABLE_NAME, //
				Field.ID, Field.NAME, Field.PRICE, Field.DESCRIPTION, Field.MENUCATEGORY, Field.SUBCATEGORY, Field.RECIPE, Field.ID);
		super.create(createStatement);
	}
	
	/**
	 * Adds a MenuItem to the db
	 * 
	 * @param menuItem
	 *            to be added to db
	 * @throws SQLException
	 */
	public void add(MenuItem menuItem) throws SQLException {
		String sqlString = String.format("INSERT INTO %s VALUES(%d, '%s', %.2f, '%s', %d, '%s', '%s')", TABLE_NAME,
				menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription(), menuItem.getMenuCategory().getIndex(), menuItem.getSubCategory(), menuItem.getRecipe());
		LOG.debug(sqlString);
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Added %d row%s", rowCount, rowCount == 1 ? "" : "s"));
	}
	
	/**
	 * Updates the menuItem
	 * 
	 * @param menuItem
	 *            to be updated
	 * @throws SQLException
	 */
	public void update(MenuItem menuItem) throws SQLException {
	    String sqlString = String.format("UPDATE %s SET %s=%d, %s='%s', %s=%.2f, %s='%s', %s=%d, %s='%s', %s='%s' WHERE %s=%d", TABLE_NAME,
	    		Field.ID, menuItem.getId(), 
	    		Field.NAME, menuItem.getName(), 
	    		Field.PRICE, menuItem.getPrice(), 
	    		Field.DESCRIPTION, menuItem.getDescription(), 
	    		Field.MENUCATEGORY, menuItem.getMenuCategory().getIndex(), 
	    		Field.SUBCATEGORY, menuItem.getSubCategory(), 
	    		Field.RECIPE, menuItem.getRecipe(),	Field.ID, menuItem.getId());
	    LOG.debug(sqlString);
	    int rowCount = super.add(sqlString);
	    LOG.debug(String.format("Updated %d row%s", rowCount, rowCount == 1 ? "" : "s"));
	}
	
	/**
	 * Deletes the menuItem from the table
	 *
	 * @param menuItem
	 *            the menuItem to be deleted
	 * @throws SQLException            
	 */
	public void delete(MenuItem menuItem) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("DELETE from %s WHERE %s=%d ", TABLE_NAME, Field.ID, menuItem.getId());
			LOG.debug(sqlString);
			int rowCount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Deleted %d row%s", rowCount, rowCount == 1 ? "" : "s"));
		} finally {
			close(statement);
		}
	}
	
	/**
	 * Returns a List of menuItems
	 * 
	 * @return List of available menuItems
	 * @throws SQLException 
	 * @throws ApplicationException 
	 */
	public List<MenuItem> getUnitsList() throws SQLException, ApplicationException {
		List<MenuItem> menuItemsList = new ArrayList<MenuItem>();
		MenuItem menuItem = null;
		
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", TABLE_NAME);
			LOG.debug(sqlString);
			ResultSet resultSet = statement.executeQuery(sqlString);
			
			while(resultSet.next()) {
				menuItem = new MenuItem();
				menuItem.setId(resultSet.getInt(Field.ID.name()));
				menuItem.setName(resultSet.getString(Field.NAME.name()));
				menuItem.setPrice(resultSet.getInt(Field.PRICE.name()));
				menuItem.setDescription(resultSet.getString(Field.DESCRIPTION.name()));
				menuItem.setMenuCategory(resultSet.getInt(Field.MENUCATEGORY.name()));
				menuItem.setSubCategory(resultSet.getString(Field.SUBCATEGORY.name()));
				menuItem.setRecipe(resultSet.getString(Field.RECIPE.name()));
				menuItemsList.add(menuItem);
			}
		} finally {
			close(statement);
		}
		return menuItemsList;
	}
	
	/**
	 * Returns the number of rows in the table
	 * 
	 * @throws SQLException
	 */
	public int tableSize() throws SQLException {
		return super.tableSize();
	}
	
	/**
	 * Field Enum, represents column names
	 */
	public enum Field {
		
		ID(1), NAME(2), PRICE(3), DESCRIPTION(4), MENUCATEGORY(5), SUBCATEGORY(6), RECIPE(7);
		
		private final int column;
		
		Field(int column) {
			this.column = column;
		}
		
		/**
		 * Returns the column number
		 * 
		 * @return the column number in a row
		 */
		public int getColumn() {
			return column;
		}
	}
}
