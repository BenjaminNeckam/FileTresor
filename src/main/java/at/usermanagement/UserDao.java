package at.usermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import at.database.SqlHelper;

public class UserDao {
	SqlHelper sqlHelper;
	
	public UserDao(){
		this.sqlHelper = SqlHelper.getInstance();
	}
	
//	public List<User> getAllUsers() {
//		List<User> userList = null;
//		
//	}
	
	public static void main(String[] args){
		UserDao ud = new UserDao();
		ud.sqlHelper.initDatabaseConnection();
		ud.sqlHelper.insterInto("users", "Test", "Test");
		ResultSet rs = ud.sqlHelper.selectFrom("*", "users");
		
		try {
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String passwd = rs.getString(3);
				
				System.out.println("ID: " + id);
				System.out.println("Username: " + username);
				System.out.println("Password: " + passwd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
