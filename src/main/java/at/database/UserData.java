package at.database;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserData{
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmnt = null;
		SqlHelper sqlHelper = SqlHelper.getInstance();
		try {
//			Class.forName(sqlHelper.JDBC_DRIVER);
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(sqlHelper.DB_URL,sqlHelper.USER,sqlHelper.PASS);
			
			//INSERT Statement
			
//			stmnt = conn.createStatement();
//			String sql;
//			sql = "INSERT INTO user " + 
//					"VALUES (1,'Test','Test')";
//			stmnt.executeUpdate(sql);
//			System.out.println("Values inserted");
////			
//			sql = "SELECT * FROM user";
//			ResultSet rs = stmnt.executeQuery(sql);
//			while(rs.next()){
//				int id = rs.getInt(1);
//				String username = rs.getString(2);
//				String passwd = rs.getString(3);
//				
//				System.out.println("ID: " + id);
//				System.out.println("Username: " + username);
//				System.out.println("Password: " + passwd);
//			}
//			
//			rs.close();
//			stmnt.close();
//			conn.close();
			
			//INSERT PDF
			File pdfFile = new File("C://Users//Benni//Documents//Lebenslauf_Neckam_Benjamin.pdf");
			byte[] pdfData = new byte[(int) pdfFile.length()];
			DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
			dis.readFully(pdfData);
			dis.close();
			
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO files (" +
							"fileId, " +
							"filename, " +
							"file " +
							") Values(?,?,?)");
			ps.setInt(1,1);
			ps.setString(2, "Lebenslauf");
			ps.setBytes(3, pdfData);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finish");
	}
}
