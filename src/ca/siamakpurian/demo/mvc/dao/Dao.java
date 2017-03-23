/**
 * Project: 1-ResumeDemo
 * File: Dao.java
 * Date: Aug 22, 2016
 * Time: 8:33:51 PM
 */
package ca.siamakpurian.demo.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.siamakpurian.demo.mvc.data.Database;

/**
 * @author Siamak Pourian
 *
 * Dao Class
 */
public abstract class Dao {


	protected final String tableName;

	protected Dao(String tableName) {
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;
	
    protected void create(String createStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(createStatement);
		} finally {
			close(statement);
		}
	}
	
	protected int tableSize() throws SQLException {
		Statement statement = null;
		int count = 0;
		try {
			Connection connection = Database.getConnection();	
			statement = connection.createStatement();
	    	String sqlString = String.format("SELECT COUNT(*) FROM %s", tableName);
	    	ResultSet resultSet = statement.executeQuery(sqlString);
	    	while (resultSet.next()) {
	    		count = Integer.parseInt(resultSet.getString(1));
    		}
	    } finally {
		    close(statement);
		}
	    return count;
	}

	protected int add(String updateStatement) throws SQLException {
		Statement statement = null;
		int row = -1;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			row = statement.executeUpdate(updateStatement);
		} finally {
			close(statement);
		}
		return row;
	}

	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			if (Database.tableExists(tableName)) {
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shutting down the database
	 */
	public void shutdown() {
		Database.getDatabaseInstance().shutdown();
	}
}
