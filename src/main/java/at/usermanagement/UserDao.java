package at.usermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.database.SqlHelper;

public class UserDao {
	SqlHelper sqlHelper;

	public UserDao() {
		this.sqlHelper = SqlHelper.getInstance();
		sqlHelper = SqlHelper.getInstance();
		sqlHelper.initDatabaseConnection();
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		ResultSet rs;
		try {
			rs = sqlHelper.selectFrom("*", "users");
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPasswd(rs.getString(3));
				userList.add(user);
				System.out.println("Id: " + user.getId());
				System.out.println("Name: " + user.getName());
				System.out.println("Passwd: " + user.getPasswd());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return userList;
	}

	public void insertUser(String table, String name, String passwd) {
		sqlHelper.insterInto(table, name, passwd);
		sqlHelper.shutDownConnection();
	}

//	public static void main(String[] args) {
//		UserDao ud = new UserDao();
//		ud.sqlHelper.initDatabaseConnection();
//		ud.sqlHelper.insterInto("users", "Test", "Test");
//		ResultSet rs = ud.sqlHelper.selectFrom("*", "users");
//
//		try {
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String username = rs.getString(2);
//				String passwd = rs.getString(3);
//
//				System.out.println("ID: " + id);
//				System.out.println("Username: " + username);
//				System.out.println("Password: " + passwd);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
