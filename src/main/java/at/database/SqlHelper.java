package at.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
//For good example to connect properly?

//TODO Always init a new connection and statement or one for all queries?
public class SqlHelper {
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	protected static final String DB_URL = "jdbc:mysql://localhost/data";
	protected static final String USER = "root";
	protected static final String PASS = "mysqlFcstadlau1";
	Connection conn = null;
	Statement stmnt = null;
	static SqlHelper instance = null;
	
	
	public static SqlHelper getInstance() {
		if(instance == null) {
			instance = new SqlHelper();
		}
		return instance;
	}
	
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
			stmnt.close();
			System.out.println("Connction closed...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insterInto(String table, String name, String passwd) {
		String sql;
		int hash = 7;
		for(int i = 0; i < (name.length() + passwd.length()); i++) {
			hash = hash * 31 + name.charAt(i);
		}
		int id = hash;
		sql = "INSERT INTO " + table + 
				"VALUES (" + id + ", '" + name + "','" + passwd + "')";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Values inserted");
	}
	
	public ResultSet selectFrom(String select, String from) throws SQLException {
		String sql;
		stmnt = conn.createStatement();
		sql = "SELECT " + select + " FROM " + from;
		ResultSet rs = stmnt.executeQuery(sql);
//		try {
//			rs = stmnt.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return rs;
	}
}
