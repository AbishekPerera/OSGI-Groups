package javadatabasecon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo implements JDBCDemoInterface {

	private Connection connection;
	private static String driverName;
	private static String databaseConnectionLink;
	private static String databaseUser;
	private static String databasePassword;
	
	public JDBCDemo() {
		
		this.driverName = "com.mysql.jdbc.Driver";
		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/wireless_waves";
		this.databaseUser = "root";
		this.databasePassword = "";

	}

	@Override
	public Connection connection() {
		Connection con = null; 
	    try {
	      Class.forName(driverName);
	      con = DriverManager.getConnection(databaseConnectionLink, databaseUser, databasePassword); // connecting to our database
	      System.out.println("Connected!");
	    } catch (ClassNotFoundException | SQLException e ) {
	      
	      System.out.println(e+"AAAAAAAAAAAAAAAAAAAAAAAA");
	    }
	    
	    return con; 
	}

}
