package ca.siamakpurian.demo.mvc;
/**
 * Project: 1-ResumeDemo
 * File: ResApp.java
 * Date: Aug 21, 2016
 * Time: 6:03:23 PM
 */

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import ca.siamakpurian.demo.mvc.applicationexception.ApplicationException;
import ca.siamakpurian.demo.mvc.data.DataManager;
import ca.siamakpurian.demo.mvc.data.Database;
import ca.siamakpurian.demo.mvc.ui.MainFrame;

/**
 * @author Siamak Pourian
 *
 * This solution has been developed to demonstrate the programming style of the author.
 * It creates and stores the restaurant menu items to be used by order forms
 * 
 * ResApp Class
 */
public class ResApp {
	
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
//	static {
	//	configureLogging();
//	}
	
	public static final String DB_PROPERTIES_FILENAME = "db.properties";
	private static final Logger LOG = LogManager.getLogger(ResApp.class);
	
	private static Database database;
	private static DataManager dataManager;

	/**
	 * Point of entry to the program
	 * 
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) {
    	Instant startTime = Instant.now();
		LOG.info(startTime);
		File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);
		if (!dbPropertiesFile.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			new ResApp().run();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {
//			database.shutdown();
//			LOG.debug("database shutdown");
			Instant endTime = Instant.now();
			LOG.info(endTime);
		}
	}
	
	/**
	 * Create a user interface for our app.
	 */
	private void run() {
		try {
			init();
			createUI();
			LOG.info("UI created");

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	private void init() throws IOException, ApplicationException {
		Properties dbProperties = new Properties();
		dbProperties.load(new FileInputStream(DB_PROPERTIES_FILENAME));
		Database.init(dbProperties);
	    dataManager = new DataManager();
	    dataManager.init();
	}
	
	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME. 
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 *
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);			
		} catch (IOException e) {
            System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.", LOG4J_CONFIG_FILENAME));
            Configurator.initialize(new DefaultConfiguration());
		}
	}*/

	public void createUI() {
		setLookAndFeel();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Sets the Nimbus look and feel
	 */
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, use the default.
		}
	}

	/**
	 * Displays an error message for the database properties file
	 */
	private static void showUsage() {
		LOG.info("The database properties filename must be passed in the commandline or be included in the source directory.");
	}
}
