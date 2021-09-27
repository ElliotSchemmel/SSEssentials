package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.User;

public class UserDAO extends BaseDAO<User>{
	
	public UserDAO(Connection conn) { super(conn); }
	
	public void addUser(User user) throws ClassNotFoundException, SQLException {
		save("INSERT INTO user (role_id, given_name, family_name, username, email, password, phone) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)", new Object[] {
				user.getRole_id(), user.getGiven_name(), user.getFamily_name(), 
				user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone()
				}); 
	}

	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		save("UPDATE user SET "
				+ "role_id = ?, given_name = ?, family_name = ?, username = ?, "
				+ "email = ?, password = ?, phone = ? "
				+ "WHERE id = ?", new Object[] { 
				user.getRole_id(), user.getGiven_name(), user.getFamily_name(), user.getUsername(), 
				user.getEmail(), user.getPassword(), user.getPhone(), user.getId()});
		}

	public void deleteUser(User user) throws ClassNotFoundException, SQLException {
		save("DELETE FROM user WHERE id = ?", new Object[] { user.getId() });
	}

	public List<User> readUsers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user", null);
	}

	public List<User> readUsersByRoleId(Integer role_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user WHERE role_id = ?", new Object[] {
				role_id
		});
	}
	
	public List<User> readUsersById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setRole_id(rs.getInt("role_id"));
			user.setGiven_name(rs.getString("given_name"));
			user.setFamily_name(rs.getString("family_name"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			users.add(user);
		}
		return users;
	}

}
