package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.entity.User;

public interface UserCRUD {
	
	Util util = new Util();

	default String addUser(User user) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.addUser(user);
			
			conn.commit();
			return "User added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "User could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<User> readUsers() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		UserDAO udao = new UserDAO(conn);
		List<User> users = udao.readUsers();
		
		if (conn != null) {
			conn.close();
		}
		
		return users;
	}

	default List<User> readUsersByRoleId(Integer role_id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		UserDAO udao = new UserDAO(conn);
		List<User> users = udao.readUsersByRoleId(role_id);
		
		if (conn != null) {
			conn.close();
		}
		
		return users;
	}

	default List<User> readUsersById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		UserDAO udao = new UserDAO(conn);
		List<User> users = udao.readUsersById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return users;
		
	}

	default String updateUser(User user) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.updateUser(user);
			
			conn.commit();
			return "User updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "User could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteUser(User user) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.deleteUser(user);
			
			conn.commit();
			return "User deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "User could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
