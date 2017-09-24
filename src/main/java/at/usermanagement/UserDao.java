package at.usermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return userList;
	}

	public User getUser(int id) {
		List<User> users = getAllUsers();

		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public int addUser(User newUser) {
		List<User> users = getAllUsers();
		boolean userExists = false;
		int hash = 7;
		for (int i = 0; i < (newUser.getName().length()); i++) {
			hash = hash * 31 + newUser.getName().charAt(i);
		}
		newUser.setId(hash);
		
		for (User user : users) {
			if (user.getId() == newUser.getId()) {
				userExists = true;
				break;
			}
		}

		if (!userExists) {
			sqlHelper.insterIntoUser(newUser.getId(), newUser.getName(), newUser.getPasswd());
			return 1;
		}
		return 0;
	}

	//TODO check if name is not multiple used
	public int updateUser(User updateUser) {
		List<User> users = getAllUsers();

		for (User user : users) {
			if (user.getId() == updateUser.getId()) {
				sqlHelper.updateUser(updateUser.getId(), updateUser.getName(), updateUser.getPasswd());
				return 1;
			}
		}
		return 0;
	}

	public int deleteUser(int id) {
		List<User> users = getAllUsers();

		for (User user : users) {
			if (user.getId() == id) {
				sqlHelper.deleteUser(id);
				return 1;
			}
		}
		return 0;
	}
}
