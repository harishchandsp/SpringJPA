package com.jnit.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

import com.jnit.app.model.User;
import com.jnit.app.repositories.UserRepository;

public class UserPesistenceTest extends ParentTest{

	@Autowired
	private UserRepository userRepository;
	
	//@Test
	public void testUserCreation(){
		User user = new User("bhiapp@gmail.com", "Appi", "B", "R", "bhiapp4", LocalDate.of(1984, 9, 11), null);
		User createdUser = userRepository.save(user);
		assertNotNull("user id not present",createdUser.getUserId());
	}

	@Test
	public void testFindUserById(){
		User user = userRepository.findOne(1L);
		assertNotNull("user name not present",user.getUserName());
	}
	
	@Test
	public void testFindAll(){
		List<User>users = userRepository.findAll();
		assertTrue( "users list is empty",users.size() > 0);
	}
	
	@Test
	public void testUpdateUser(){
		User user = userRepository.findOne(1L);
		user.setmName("Reddy");
		User updatedUser = userRepository.save(user);
		assertEquals("Reddy", updatedUser.getmName());
	}
	
	//@Test
	public void testDeleteUser(){
		User user = userRepository.findOne(1L);
		userRepository.delete(user);
		User userObj = userRepository.findOne(1L);
		assertNull("user seems to be not deleted",userObj);
	}
	
	@Test
	public void testFindUserByUserName(){
		Optional<User> userOptional = userRepository.findByUserName("bhiapp@gmail.com");
		assertTrue("user not found with the user name",userOptional.isPresent());
	}
	
	@Test
	public void testFindByLastName(){
		List<User>users = userRepository.findByLName("B");
		assertTrue( "users list is empty",users.size() > 0);
	}

	@Test
	public void testCountByLastName(){
		Long count = userRepository.countByLName("B");
		assertTrue( "users list is empty",count > 0);
	}
	
	@Test
	public void testFindByFNameAndLName(){
		Optional<User> userOptional = userRepository.findByFNameAndLName("Appi","B");
		assertTrue("user not found with the provided first and lname",userOptional.isPresent());
	}

	@Test
	public void testFindByFNameOrLName(){
		List<User> users = userRepository.findByFNameOrLName("Appi","B");
		assertTrue( "users list is empty",users.size() > 0);
	}
	
	@Test
	public void testFindDistinctByMName(){
		List<User> users = userRepository.findDistinctByMName("Reddy");
		assertTrue( "users list is empty",users.size() > 0);	
	}
	
	@Test
	public void testFindFirst3ByLNameOrderByUserNameAsc(){
		List<User> users = userRepository.findFirst3ByLNameOrderByUserNameAsc("B");
		assertTrue( "users list is empty",users.size() > 0);		
	}
	
	

}
