//package com.example.test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.junit.Test;
//
//import com.example.exceptions.InvalidDataException;
//import com.example.exceptions.UserExeption;
//import com.example.model.User;
//import com.example.model.DAO.AbstractDAO;
//import com.example.model.DAO.UserDAO;
//
//public class UserTestDAO extends AbstractDAO  {
//
//	private UserDAO userDao = new UserDAO();
//	private User testUser = new User("Someone", "Smith", "eXtreamEmail@gmail.com", "testPass123");
//
//	@Test
//	public void testAddAndRemoveUser() throws SQLException {
//		int id = 0;
//		try {
//			id = userDao.addUser(testUser);
//		} catch (UserExeption e) {
//			e.printStackTrace();
//		}
//		ResultSet rs = getCon().createStatement()
//				.executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
//		// Check if there is user registered with that email.
//		assertTrue(rs.next());
//		// Check if there are more users registered with the same email.
//		assertFalse(rs.next());
//		try {
//			userDao.removeUser(id);
//		} catch (UserExeption e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		rs = getCon().createStatement().executeQuery("SELECT * FROM Users WHERE email = '" + testUser.getEmail() + "';");
//		// Check if the user is removed.
//		assertFalse(rs.next());
//	}
//
//	@Test
//	public void getUserById() throws UserExeption {
//		// Create the testUser in the database
//		int id = userDao.addUser(testUser);
//		// Creating object from the database.
//		User user = null;
//		try {
//			user = new UserDAO().getUserById(id);
//		} catch (InvalidDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Check if all properties are set.
//		assertNotNull(user);
//		assertNotEquals(user.getUserId(), 0);
//		assertNotNull(user.getFirstName());
//		assertNotNull(user.getFirstName());
//		assertNotNull(user.getLastName());
//		assertNotNull(user.getPassword());
//		assertNotNull(user.getEmail());
//
//		// Remove the testUser
//		userDao.removeUser(id);
//
//	}
//
//}
