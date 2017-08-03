package com.jnit.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jnit.app.model.Student;
import com.jnit.app.repositories.StudentRepository;


public class StudentPersistentTest extends ParentTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	public void testUserCreation(){
		Student student = new Student("harish@gmail.com", "Harish", "Sripalle", "", "harish23", LocalDate.of(1987, 12, 23), null);
		Student createdStudent = studentRepository.save(student);
		assertNotNull("Student id not present",createdStudent.getStudentId());
	}

	@Test
	public void testFindUserById(){
		Student student = studentRepository.findOne(1L);
		assertNotNull("Student name not present",student.getUserName());
	}
	
	@Test
	public void testFindAll(){
		List<Student>students = studentRepository.findAll();
		assertTrue( "users list is empty",students.size() > 0);
	}
	
	@Test
	public void testUpdateUser(){
		Student Student = studentRepository.findOne(1L);
		Student.setmName("Chandra");
		Student updatedUser = studentRepository.save(Student);
		assertEquals("Chandra", updatedUser.getmName());
	}
	
	@Test
	public void testDeleteUser(){
		Student student = studentRepository.findOne(1L);
		studentRepository.delete(student);
		Student studentObj = studentRepository.findOne(1L);
		assertNull("Student seems to be not deleted",studentObj);
	}
	
	@Test
	public void testFindUserByUserName(){
		Optional<Student> studentOptional = studentRepository.findByUserName("harish@gmail.com");
		assertTrue("Student not found with the Student name",studentOptional.isPresent());
	}
	
	@Test
	public void testFindByLastName(){
		List<Student>students = studentRepository.findByLName("Sripalle");
		assertTrue( "students list is empty",students.size() > 0);
	}

	@Test
	public void testCountByLastName(){
		Long count = studentRepository.countByLName("Sripalle");
		assertTrue( "students list is empty",count > 0);
	}
	
	@Test
	public void testFindByFNameAndLName(){
		Optional<Student> studentOptional = studentRepository.findByFNameAndLName("Harish","Sripalle");
		assertTrue("Student not found with the provided first and lname",studentOptional.isPresent());
	}

	@Test
	public void testFindByFNameOrLName(){
		List<Student> students = studentRepository.findByFNameOrLName("Harish","Sripalle");
		assertTrue( "students list is empty",students.size() > 0);
	}
	
	@Test
	public void testFindDistinctByMName(){
		List<Student> students = studentRepository.findDistinctByMName("Chandra");
		assertTrue( "students list is empty",students.size() > 0);	
	}
	
	@Test
	public void testFindFirst3ByLNameOrderByUserNameAsc(){
		List<Student> students = studentRepository.findFirst3ByLNameOrderByUserNameAsc("Sripalle");
		assertTrue( "students list is empty",students.size() > 0);		
	}
	
}
