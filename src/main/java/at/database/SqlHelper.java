package at.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
//For good example to connect properly?
//TODO Singleton so there is just one connection?
public class SqlHelper {
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	protected static final String DB_URL = "jdbc:mysql://localhost/data";
	protected static final String USER = "root";
	protected static final String PASS = "mysqlFcstadlau1";
	Connection conn = null;
	Statement stmnt = null;
	
	public void initDatabaseConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			//TODO change to log4j
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void shutDownConnection(){
		try {
			//TODO change to log4j
			conn.close();
			//stmnt.close();
			System.out.println("Connction closed...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
