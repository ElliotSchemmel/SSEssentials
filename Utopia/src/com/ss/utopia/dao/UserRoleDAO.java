package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.UserRole;

public class UserRoleDAO extends BaseDAO<UserRole>{
	
	public UserRoleDAO(Connection conn) { super(conn); }
	
	public void addUserRole(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("INSERT INTO user_role (name) VALUES (?)", new Object[] {
				userRole.getName()}); 
	}

	public void updateUserRole(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("UPDATE user_role SET name = ? WHERE id = ?", new Object[] { 
				userRole.getName(), userRole.getId()});
	}

	public void deleteUserRole(UserRole userRole) throws ClassNotFoundException, SQLException {
		save("DELETE FROM user_role WHERE id = ?", new Object[] { userRole.getId() });
	}

	public List<UserRole> readUserRoles() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user_role", null);
	}
	
	public List<UserRole> readUserRolesById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM user_role WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<UserRole> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<UserRole> userRoles = new ArrayList<>();
		while (rs.next()) {
			UserRole userRole = new UserRole();
			userRole.setId(rs.getInt("id"));
			userRole.setName(rs.getString("name"));
			userRoles.add(userRole);
		}
		return userRoles;
	}

}
