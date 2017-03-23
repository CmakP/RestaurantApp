/**
 * Project: 1-ResumeDemo
 * File: UnitDao.java
 * Date: Aug 28, 2016
 * Time: 4:09:37 PM
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
import ca.siamakpurian.demo.mvc.data.restaurant.Unit;

/**
 * @author Siamak Pourian
 *
 *         UnitDao Class encapsulated access to the database which manages
 *         the connection to the db
 */
public class UnitDao extends Dao {

	public static final String TABLE_NAME = "Unit";

	private static final UnitDao unitDaoInstance = new UnitDao();
	private static final Logger LOG = LogManager.getLogger(UnitDao.class);

	/**
	 * private constructor for UnitDao in order to prevent multiple instantiation
	 * as there is only one instance of this object needed
	 */
	private UnitDao() {
		super(TABLE_NAME);
	}

	/**
	 * @return the unitDaoInstance reference for the Unit table
	 */
	public static UnitDao getTheInstance() {
		return unitDaoInstance;
	}

	/**
	 * Creates the unit table and the initializes the SQL statement
	 * 
	 * @throws SQLException
	 */
	@Override
	public void create() throws SQLException {
		String createStatement = String.format("CREATE TABLE %s(%s INTEGER, %s VARCHAR(10), %s VARCHAR(5), primary key (%s) )", TABLE_NAME, //
				Field.ID, Field.NAME, Field.SYMBOL, Field.ID);
		super.create(createStatement);
	}

	/**
	 * Adds a unit to the db
	 * 
	 * @param unit
	 *            to be added to db
	 * @throws SQLException
	 */
	public void add(Unit unit) throws SQLException {
		String sqlString = String.format("INSERT INTO %s VALUES(%d, '%s', '%s')", TABLE_NAME, //
				unit.getId(), unit.getName(), unit.getSymbol());
		LOG.debug(sqlString);
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Added %d row%s", rowCount, rowCount == 1 ? "" : "s"));
	}

	/**
	 * Updates the unit
	 * 
	 * @param unit
	 *            to be updated
	 * @throws SQLException
	 */
	public void update(Unit unit) throws SQLException {
		String sqlString = String.format("UPDATE %s SET %s=%d, %s='%s', %s='%s' WHERE %s=%d", TABLE_NAME, //
				Field.ID, unit.getId(), //
				Field.NAME, unit.getName(), //
				Field.SYMBOL, unit.getSymbol(), Field.ID, unit.getId());
		LOG.debug(sqlString);
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Updated %d row%s", rowCount, rowCount == 1 ? "" : "s"));
	}

	/**
	 * Deletes the unit from the table
	 *
	 * @param unit
	 *            the unit to be deleted
	 * @throws SQLException           
	 */
	public void delete(Unit unit) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("DELETE from %s WHERE %s=%d ", TABLE_NAME, Field.ID, unit.getId());
			LOG.debug(sqlString);
			int rowCount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Deleted %d row%s", rowCount, rowCount == 1 ? "" : "s"));
		} finally {
			close(statement);
		}
	}

	/**
	 * Returns an array containing all the unit symbols
	 * 
	 * @return an array of unit symbols
	 * @throws SQLException
	 */
	public String[] getUnitSymbols() throws SQLException {
		String[] unitSymbols;
		int capacity = tableSize();
		if (capacity > 0) {
			unitSymbols = new String[capacity];
			Connection connection;
			Statement statement = null;
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				String sqlString = String.format("SELECT * FROM %s", TABLE_NAME);
				LOG.debug(sqlString);
				ResultSet resultSet = statement.executeQuery(sqlString);

				int index = 0;
				while (resultSet.next()) {
					unitSymbols[index] = resultSet.getString(Field.SYMBOL.name());
					index++;
				}
			} finally {
				close(statement);
			}
		} else {
			unitSymbols = null;
		}
		return unitSymbols;
	}
	
	/**
	 * Returns a List of units
	 * 
	 * @return List of available units
	 * @throws SQLException 
	 * @throws ApplicationException 
	 */
	public List<Unit> getUnitsList() throws SQLException, ApplicationException {
		List<Unit> unitsList = new ArrayList<Unit>();
		Unit unit = null;
		
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			String sqlString = String.format("SELECT * FROM %s", TABLE_NAME);
			LOG.debug(sqlString);
			ResultSet resultSet = statement.executeQuery(sqlString);
			
			while(resultSet.next()) {
				unit = new Unit();
				unit.setId(resultSet.getInt(Field.ID.name()));
				unit.setName(resultSet.getString(Field.NAME.name()));
				unit.setSymbol(resultSet.getString(Field.SYMBOL.name()));
				unitsList.add(unit);
			}
		} finally {
			close(statement);
		}
		return unitsList;
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

		ID(1), NAME(2), SYMBOL(3);

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
