/**
 * Project: 1-ResumeDemo
 * File: Database.java
 * Date: Aug 22, 2016
 * Time: 1:36:28 PM
 */
package ca.siamakpurian.demo.mvc.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Siamak Pourian
 *
 * Database used to store data is Derby
 *   
 * Database Class
 */
public class Database {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";

	private static final Logger LOG = LogManager.getLogger(Database.class);

	private static final Database theInstance = new Database();
	private static Connection connection;
	private static Properties properties;

	/**
	 * private constructor in order to prevent multiple object instantiation 
	 */
	private Database() {
	}

	/**
	 * Initialize method to complete creation of singleton object
	 * 
	 * @param properties configuration file for the database
	 */
	public static void init(Properties properties) {
		if (Database.properties == null) {
			LOG.debug("Loading database properties from db.properties");
			Database.properties = properties;
		}
	}

	/**
	 * Establishing connection to db
	 * 
	 * @return the connection to db
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	/**
	 * Connection to database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void connect() throws ClassNotFoundException, SQLException {
		String dbDriver = properties.getProperty(DB_DRIVER_KEY);
		LOG.debug(dbDriver);
		Class.forName(dbDriver);
		LOG.debug("Driver loaded");
		connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY), properties.getProperty(DB_PASSWORD_KEY));
		LOG.debug("Database connected");
	}

	/**
	 * Close the connection to the database
	 */
	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
				LOG.debug("Database shutdown");
			} catch (SQLException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	/**
	 * Checks if the database table exist
	 * 
	 * @param tableName the name of the table to be checked if exists in db
	 * @return true is the table exists, false otherwise
	 * @throws SQLException
	 */
	public static boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = getConnection().getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}

		return false;
	}
	
	/**
	 * Throws a CloneNotSupportedException Exception if attempted to be cloned
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Object not clone compatible\n Try the 'getDatabaseInstance' method");
	}

	/**
	 * Returns an instance of the Database class
	 */
	public synchronized static Database getDatabaseInstance() {
		return theInstance;
	}
}
