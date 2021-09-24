package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.UserRole;

public interface UserRoleCRUD {
	
	Util util = new Util();

	default String addUserRole(UserRole userRole) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserRoleDAO urdao = new UserRoleDAO(conn);
			urdao.addUserRole(userRole);
			
			conn.commit();
			return "UserRole added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "UserRole could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<UserRole> readUserRoles() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		UserRoleDAO urdao = new UserRoleDAO(conn);
		List<UserRole> userRoles = urdao.readUserRoles();
		
		if (conn != null) {
			conn.close();
		}
		
		return userRoles;
	}

	default List<UserRole> readUserRolesById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		UserRoleDAO urdao = new UserRoleDAO(conn);
		List<UserRole> userRoles = urdao.readUserRolesById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return userRoles;
		
	}

	default String updateUserRole(UserRole userRole) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserRoleDAO urdao = new UserRoleDAO(conn);
			urdao.updateUserRole(userRole);
			
			conn.commit();
			return "UserRole updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "UserRole could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteUserRole(UserRole userRole) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			UserRoleDAO urdao = new UserRoleDAO(conn);
			urdao.deleteUserRole(userRole);
			
			conn.commit();
			return "UserRole deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "UserRole could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
